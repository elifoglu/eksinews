package model

import java.util.{Calendar, Date, Timer, TimerTask}

import org.joda.time.DateTime

object Scheduler {

  val timer = new Timer

  val daily = 86400000
  val hourly = 3600000
  val half_hourly = 1800000

  def scheduleAt(HHmm: String, period: Int, f: () => Unit): Unit = {
    HHmm
      .split(":")
      .map(_.toInt)
      .toList match {
      case List(h, m) =>
        val calendar = Calendar.getInstance
        calendar.set(Calendar.HOUR_OF_DAY, h)
        calendar.set(Calendar.MINUTE, m)
        calendar.set(Calendar.SECOND, 0)
        var start: DateTime = new DateTime(calendar.getTime)
        while (start.isBefore(DateTime.now)) {
          start = start.plusMillis(period)
        }
        timer.schedule(task(f), start.toDate, period)
        println("Scheduled successfully")
      case _ =>
        println("Incorrect time format. Please use HH:mm")
    }
  }

  def scheduleAtMin(startMin: Int, period: Int, f: () => Unit): Unit = {
    val now = DateTime.now
    val calendar = Calendar.getInstance
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MINUTE, startMin)
    if (startMin < now.minuteOfHour().get()) {
      calendar.set(Calendar.HOUR_OF_DAY, now.hourOfDay().get() + 1)
    }
    val start: Date = calendar.getTime
    timer.schedule(task(f), start, period)
  }

  private def task(f: () => Unit) = new TimerTask {
    override def run(): Unit = f()
  }
}