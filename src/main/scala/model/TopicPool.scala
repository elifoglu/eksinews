package model

import org.jsoup.Jsoup

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

object TopicPool {

  private var oldNews = ListBuffer[String]()
  private var news = ListBuffer[Topic]()

  def crawlNews(): Unit = {
    try {
      println("crawling news...")
      Jsoup.connect("""https://www.eksisozluk.com/basliklar/gundem""")
        .userAgent("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36")
        .get
        .getElementById("content-body")
        .getElementsByTag("a")
        .asScala
        .dropRight(1) //remove "daha da..."
        .map(Topic(_))
        .filterNot(x => oldNews.contains(x.name))
        .filter(_.count > 100)
        .foreach { t =>
        oldNews += t.name
        news += t
        println(t.name)
      }
      println("done crawling")
      oldNews = oldNews.take(100)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        ExceptionPool.add(e.getMessage)
    }
  }

  def consumeNews(): List[Topic] = {
    val consumed = news.sortBy(-_.count).toList
    news.clear()
    consumed
  }
}