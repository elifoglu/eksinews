name := "eksicrawler"

version := "0.1"

scalaVersion := "2.12.7"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.2"
libraryDependencies += "com.github.jurajburian" %% "mailer" % "1.2.3"
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.22.0"

val stage = taskKey[Unit]("Stage task")

val Stage = config("stage")

stage := {
  (packageBin in Compile).value
  (update in Stage).value.allFiles.foreach { f =>
    if (f.getName.matches("webapp-runner-[0-9\\.]+.jar")) {
      println("copying " + f.getName)
      IO.copyFile(f, baseDirectory.value / "target" / "webapp-runner.jar")
    }
  }
}