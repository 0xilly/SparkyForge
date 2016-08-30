package us.illyohs.sparkyforge.webhook.github

class GitHooker(sig:String, evenType:String, payload:Array[Byte]) {
  val data: String = new String(getPayload)
//  dumpPayLoad()
  eventDispatcher(getEvenType, data)

  def eventDispatcher(evenType: String, json: String) {
     new PullHandler(json)
  }

  def getEvenType: String = evenType


  def getSig: String = sig


  def getPayload: Array[Byte] = payload
  

}