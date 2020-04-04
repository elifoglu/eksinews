package model

import org.jsoup.nodes.Element

case class Topic(name: String, count: Integer, outerHtml: String)

object Topic {

  def apply(topic: Element): Topic = {
    var count: Integer = 0
    topic.child(0).text match {
      case s if s.contains("b") => //for topics with count like "1,3b"
        count = (s.charAt(0).toString + s.charAt(2).toString).toInt * 100
      case s =>
        count = s.toInt
    }
    topic.attr("href", "https://eksisozluk.com" + topic.attr("href"))
    Topic(topic.textNodes.get(0).text, count, topic.outerHtml)
  }
}