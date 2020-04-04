import model.MailSender
import model.Scheduler._
import model.TaskPool._

object Main extends App {

  println("App is started")

  args.length match {
    case l if l < 3 =>
      println(
        """|Please provide following arguments:
           |[0]: Sender gmail address
           |[1]: Sender gmail app password
           |[2]: Receiver mail address 1
           |[3]: Receiver mail address 2
           |...
           |[n]: Receiver mail address n
           |
           |Shutting down...""".stripMargin
      )
    case _ =>
      MailSender.init(
        sender = (args(0), args(1)),
        receivers = args.drop(2).toSet
      )
      scheduleAtMin(45, half_hourly, crawlNews)
      scheduleAt("00:30", daily, sendEmail)
      scheduleAt("09:00", daily, sendEmail)
      scheduleAt("15:30", daily, sendEmail)
      scheduleAt("19:30", daily, sendEmail)
  }
}