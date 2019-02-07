package com.sapient.learning

import kafka.serializer.StringDecoder
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.streaming.State
import org.apache.spark.streaming.StateSpec
import org.apache.spark.streaming.{ Seconds, StreamingContext }
import org.apache.spark.streaming.dstream.{ DStream, InputDStream }
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka.KafkaUtils

import com.mongodb.spark.MongoSpark

import java.util.ArrayList
import java.util.regex.Matcher
import java.util.regex.Pattern

object WordDataStream {

  val localLogger = Logger.getLogger("WordDataStream")

  def main(args: Array[String]) {

    val checkpointDir = "./.checkpoint"
    val sparkConf = new SparkConf().setAppName("Word Counting")
    sparkConf.setIfMissing("spark.master", "local[2]")
    sparkConf.setIfMissing("spark.cassandra.connection.host", "127.0.0.1")
    sparkConf.setIfMissing("spark.mongodb.output.uri", "mongodb://localhost:27017/vocabulary.words")

    val sparkSess = SparkSession.builder().config(sparkConf).getOrCreate()
    val sc = sparkSess.sparkContext
    val ssc = new StreamingContext(sc, Seconds(2))

    ssc.checkpoint(checkpointDir)

    val kafkaTopic = "twitter"
    val kafkaBroker = "localhost:9092"

    val cassandraKeyspace = "isd_weather_data"
    val cassandraTableRaw = "raw_weather_data"
    val cassandraTableDailyPrecip = "daily_aggregate_precip"

    val topics: Set[String] = kafkaTopic.split(",").map(_.trim).toSet
    val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaBroker)

    localLogger.info(s"connecting to brokers: $kafkaBroker")
    localLogger.info(s"kafkaParams: $kafkaParams")
    localLogger.info(s"topics: $topics")

    val rawStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)

    val parsedStream: DStream[(String, Int)] = ingestStream(rawStream)

    val func = (key: String, value: Option[Int], state: State[Long]) => {
      val sum = value.getOrElse(0).toLong + state.getOption.getOrElse(0L)
      val output = Tuple2(key, sum)
      state.update(sum)
      output
    }

    val statefulStream = parsedStream.mapWithState(StateSpec.function(func))

    import com.datastax.spark.connector.streaming._

    statefulStream.saveToCassandra("vocabulary", "words")

    statefulStream.foreachRDD({ rdd =>
      import sparkSess.implicits._
      import com.mongodb.spark.sql._
      val wordCounts = rdd.map({
        case (word: String, count: Long) => WordData.Word(word, word, count)
      }).toDF()
      wordCounts.write.mode("append").mongo()
    })

    statefulStream.print // for demo purposes only

    ssc.start()
    ssc.awaitTermination()
  }

  def ingestStream(rawStream: InputDStream[(String, String)]): DStream[(String, Int)] = {

    val parsedWordStream =
      rawStream.map(message => Tuple2(message._1, message._2))
        .map(line => line._2)
        .flatMap(str => cleanse(str).toList)
        .map(str => Tuple2(str, 1))
        .reduceByKey((i1, i2) => i1 + i2)

    parsedWordStream
  }

  def cleanse(str: String): Array[String] = {

    var input = str
    var urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)"
    var p = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE)
    var m = p.matcher(input)
    var i = 0
    while (m.find()) {
      input = input.replaceAll(m.group(i), "").trim()
      i += 1
    }

    var words = input
      .replaceAll("@\\p{L}+", "")
      .replaceAll("[^a-zA-Z ]", "")
      .replaceAll("\\b\\w{1,4}\\b", "")
      .toLowerCase()
      .split("\\s+")

    words = words.filter(_.nonEmpty)

    words
  }

}