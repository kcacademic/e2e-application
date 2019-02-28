package com.sapient.learning

import org.scalatest.FunSuite

class UtilityUnitTest extends FunSuite {

  test("testUtility") {

    var str = "RT @Femi_Sorry: I am sick to death of privileged politicians saying that they personally would be happy with No Deal Brexit."

    println(Utility.cleanse(str).length)
    assert(Utility.cleanse(str).length === 9)
  }

}