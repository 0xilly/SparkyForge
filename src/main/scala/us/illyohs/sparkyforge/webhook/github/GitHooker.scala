package us.illyohs.sparkyforge.webhook.github

class GitHooker(sig:String, evenType:String, payload:Array[Byte]) {
  val data: String = new String(getPayload)
  dumpPayLoad()
  eventDispatcher(evenType, data)

  def dumpPayLoad() {
    //        System.out.println(getSig());
    //        String s = new String(getPayload());
    //        System.out.println(s);
    System.out.println(getEvenType + "\n")
  }

  def eventDispatcher(evenType: String, json: String) {
    evenType match {
      case "push" =>
      case "issue" =>
      case "pull" =>
      case "pull_request" => new PullHandler(json)
    }
  }

  def getEvenType: String = evenType


  def getSig: String = sig


  def getPayload: Array[Byte] = payload

}