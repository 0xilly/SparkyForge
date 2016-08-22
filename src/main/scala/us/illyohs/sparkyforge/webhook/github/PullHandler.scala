package us.illyohs.sparkyforge.webhook.github

import com.google.gson.JsonParser
import com.google.gson.JsonObject



class PullHandler(json:String) {
  
  val parser = new JsonParser
  val jObj   = parser.parse(json).getAsJsonObject
  
  val action = jObj.get("action").getAsString
  val number = jObj.get("number").getAsInt
  val pr     = jObj.get("pull_request")
  
  val sender = jObj.get("sender").getAsJsonObject.get("login").getAsString
  val 
}