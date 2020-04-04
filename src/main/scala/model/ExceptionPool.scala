package model

import scala.collection.mutable.ListBuffer

object ExceptionPool {

  private var exceptions = ListBuffer[String]()

  def add(e: String) = exceptions += e

  def drain(): List[String] = {
    val pool = exceptions.toList
    exceptions.clear()
    pool
  }
}
