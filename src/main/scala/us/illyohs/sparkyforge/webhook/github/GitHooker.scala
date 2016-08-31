package us.illyohs.sparkyforge.webhook.github

class GitHooker(sig:String, evenType:String, payload:Array[Byte]) {
  val data: String = new String(getPayload)
//  dumpPayLoad
  eventDispatcher(getEvenType, data)

  def dumpPayLoad: Unit = {
    println(getSig)
    println(getEvenType)
    println(data)
  }

  /**
    *
    * @param evenType
    * @param json
    */
  def eventDispatcher(evenType: String, json: String) {
     PullHandler.handlePull(json)
  }

  def getEvenType: String = evenType


  def getSig: String = sig


  def getPayload: Array[Byte] = payload
  

}