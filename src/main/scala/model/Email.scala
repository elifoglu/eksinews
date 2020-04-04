package model

import com.github.jurajburian.mailer.{Content, Message}

case class Email(subject: String, html: String) {

  val message = Message(
    from = MailSender.get.senderAddress,
    subject = subject,
    content = Content().html(html),
    to = MailSender.get.receiverAddresses.toSeq
  )

  def send = {
    println(
      s"""Email is being sent:
         |${this.toString}"""
    )
    MailSender.get.send(message)
  }
}