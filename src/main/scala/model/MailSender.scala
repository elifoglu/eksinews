package model

import com.github.jurajburian.mailer._
import javax.mail.Session
import javax.mail.internet.InternetAddress

case class MailSender(sender: (String, String), receivers: Set[String]) {

  private def session: Session =
    (SessionFactory() + SmtpStartTls() + SmtpAddress("smtp.gmail.com", 587))
      .session(Some(sender))

  val senderAddress: InternetAddress = new InternetAddress(sender._1)
  val receiverAddresses: Set[InternetAddress] = receivers.map(new InternetAddress(_))

  def send(message: Message) = {
    try {
      Mailer(session).send(message).close()
      println("Email has been sent")
    } catch {
      case e: Throwable => e.printStackTrace()
    }
  }
}

object MailSender {

  var instance: Option[MailSender] = None

  def init(sender: (String, String), receivers: Set[String]): Unit =
    instance = Some(MailSender(sender, receivers))

  def get: MailSender =
    instance.getOrElse(throw new RuntimeException("Mail sender is not initialized"))
}