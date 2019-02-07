name := "spark-streaming-scala"

version := "1.0"

logLevel := Level.Debug

updateOptions := updateOptions.value.withCachedResolution(true)

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", "spark", "unused", "UnusedStubClass.class") => MergeStrategy.first
  case PathList(pl @ _*) if pl.contains("log4j.properties") => MergeStrategy.concat
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

//scalaVersion := "2.10.6"
scalaVersion := "2.11.8"

resolvers += "jitpack" at "https://jitpack.io"

// still want to be able to run in sbt
// https://github.com/sbt/sbt-assembly#-provided-configuration
run in Compile := Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run))

fork in run := true
javaOptions in run ++= Seq(
  "-Dlog4j.debug=true",
  "-Dlog4j.configuration=log4j.properties")

libraryDependencies ++= Seq(
  "com.groupon.sparklint" %% "sparklint-spark162" % "1.0.4" excludeAll (
    ExclusionRule(organization = "org.apache.spark")
    ),
  "org.apache.spark" %% "spark-core" % "2.3.0" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.3.0" % "provided",
  "org.apache.spark" %% "spark-streaming" % "2.3.0" % "provided",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.3",
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.3.0",
  "org.mongodb.spark" %% "mongo-spark-connector" % "2.2.6"
)