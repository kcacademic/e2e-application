package com.sapient.learning

import java.util.regex.Pattern

object Utility {

  def main(args: Array[String]) {

    var str = "RT @Femi_Sorry: I am sick to death of privileged politicians saying that they personally would be happy with No Deal Brexit."

    println(cleanse(str).length)
    println(cleanse(str).mkString(" "))

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