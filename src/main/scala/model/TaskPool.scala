package model

import model.TopicPool._

object TaskPool {

  val sendEmail: () => Unit = () => {
    (1 to 5) foreach { _ =>
      TopicPool.crawlNews()
      Thread.sleep(5000)
    }

    consumeNews() match {
      case Nil => println("There is no news to send")
      case news => Email(
        subject = "Ekşi Daily",
        html = Html.build(
          news = news
            .map(_.outerHtml),
          exceptions = ExceptionPool.drain()
        )
      ).send
    }
  }

  val crawlNews: () => Unit = TopicPool.crawlNews
}