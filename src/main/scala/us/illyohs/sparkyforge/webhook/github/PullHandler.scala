package us.illyohs.sparkyforge.webhook.github

import us.illyohs.sparkyforge.util.{ConfigUtil, MessageUtil}

import com.google.gson.JsonParser



class PullHandler(json:String) {
  
  val parser = new JsonParser
  val jObj   = parser.parse(json).getAsJsonObject
  
  val action = jObj.get("action").getAsString
  val number = jObj.get("number").getAsInt

  try {
    action match {
      case "open" => handleOpen(number)
      case "reopened" => handleClose(number)
      case "closed" => handleClose(number)
    }
  } catch {
    case e:Exception => e.printStackTrace()
  }



  def handleOpen(id:Int): Unit = {
//    SparkyForge.github.checkMergeAbility(id)
//    val name = SparkyForge.github.getPullRequestTitle(id)

//    MessageUtil.sendIrcMessageToChannel(name)
  }

  def handleClose(id:Int): Unit = {
//    val name  = SparkyForge.github.getPrMaker(id)
//    val pr    = SparkyForge.github.getPullRequestTitle(id)
//    MessageUtil.sendIrcMessageToChannel(name.getName + "("+ name.getLogin +") "+ "has " + Format.RED + "closed "+
//      Format.RESET + "PullRequest "+ id +": " + pr)
  }

  def handleReopen(id:Int): Unit = {
//    val name  = SparkyForge.github.getPrMaker(id)
//    val pr    = SparkyForge.github.getPullRequestTitle(id)
//    MessageUtil.sendIrcMessageToChannel(name.getName + "("+ name.getLogin +") "+ "has " + Format.GREEN + "reopend " +
//      Format.RESET + "PullRequest #" + id +": " + pr)
    println(ConfigUtil.getGitHubRepo)
    MessageUtil.sendIrcMessageToChannel("the repo is " + ConfigUtil.getGitHubRepo)
  }


}