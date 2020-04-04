import model.Scheduler._
import model.TaskPool._

object Main extends App {

  println("App is started")
  scheduleAtMin(45, half_hourly, crawlNews)
  scheduleAt("00:30", daily, sendEmail)
  scheduleAt("09:00", daily, sendEmail)
  scheduleAt("15:30", daily, sendEmail)
  scheduleAt("19:30", daily, sendEmail)
}