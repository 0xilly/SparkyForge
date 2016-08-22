package us.illyohs.sparkyforge.webhook.github

import com.google.gson.JsonParser



class PullHandler(json:String) {
  
  val parser = new JsonParser
  val jObj   = parser.parse(json).getAsJsonObject
  
  val action = jObj.get("action").getAsString
  val number = jObj.get("number").getAsInt

}