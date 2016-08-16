package us.illyohs.sparkyforge.webhook.github

import spray.json._

class PullHandler(json:String) {
  parseJson(getJson)

  private def parseJson(json:String): Unit = {
    val data = json.parseJson
  }

  def getJson: String = json

}