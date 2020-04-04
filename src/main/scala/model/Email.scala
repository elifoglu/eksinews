package model

import com.github.jurajburian.mailer.{Content, Message}
import model.MailSender.{receiverAddress, senderAddress}

case class Email(subject: String, html: String) {

  val message = Message(
    from = senderAddress,
    subject = subject,
    content = Content().html(html),
    to = Seq(receiverAddress)
  )

  def send = {
    println("Email is being sent:")
    println(this.toString)
    MailSender.send(message)
  }
}