package com.sapient.learning

import com.typesafe.config.ConfigFactory

object MyProperties {
  
  val conf = ConfigFactory.load()
  
  def main(args: Array[String]) {
    
    System.out.println(conf.getString("kafka.server"))
  }
  
  def getValue(key: String): String = {
    
    conf.getString(key)
  }
  
}