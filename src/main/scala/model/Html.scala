package model

object Html {

  def build(news: List[String], exceptions: List[String]) =
    s"""
       |<html>
       |<head>
       |<style>
       |  a {
       |      color: black;
       |      text-decoration: none;
       |  }
       |  a:hover {
       |      color: white;
       |      text-decoration: underline;
       |  }
       |</style>
       |</head>
       |<body>
       |<div style=" font-family: 'Times New Roman', Times, serif; padding: 10px; background-color: #dcffce; border-width: 1px; border-style: solid; border-color: green; border-radius: 3px; width: fit-content;">
       |${news.mkString("<br>")}
       |</div>
       |<div>
       |${exceptions.mkString("<br>")}
       |</div>
       |</body>
       |</html>""".stripMargin
}
