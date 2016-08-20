package us.illyohs.sparkyforge.webhook.github

import com.google.gson.{Gson, GsonBuilder}


class PullHandler(json:String) {

  var gson: Gson = null

  parseJson(getJson)

  private def parseJson(json:String): Unit = {
    gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()


  }

  def getJson: String = json

}