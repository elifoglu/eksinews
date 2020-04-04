package model

import com.github.jurajburian.mailer._
import javax.mail.Session
import javax.mail.internet.InternetAddress

object MailSender {

  private val credentials = "your_mail_sender@gmail.com" -> "your_gmail_password"

  private def session: Session =
    (SessionFactory() + SmtpStartTls() + SmtpAddress("smtp.gmail.com", 587))
      .session(Some(credentials))

  val senderAddress = new InternetAddress(MailSender.credentials._1)
  val receiverAddress = new InternetAddress("your_mail_receiver@xyz.com")

  def send(message: Message) = {
    try {
      Mailer(session).send(message).close()
      println("Email has been sent")
    } catch {
      case e: Throwable => e.printStackTrace()
    }
  }
}