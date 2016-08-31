package us.illyohs.sparkyforge.webhook.github

import us.illyohs.sparkyforge.SparkyForge
import us.illyohs.sparkyforge.util.MessageUtil

import com.google.gson.JsonParser
import org.kitteh.irc.client.library.util.Format



object PullHandler {

  /**
    *
    * @param json
    */
  def handlePull(json:String): Unit = {
    val parser = new JsonParser
    val jObj   = parser.parse(json).getAsJsonObject

    val action = jObj.get("action").getAsString
    val number = jObj.get("number").getAsInt

    try {
        action match  {
        case "opened" => handleOpen(number)
        case "reopened" => handleReopen(number)
        case "closed" => handleClose(number)
        case "synchronize" => handelSync(number)
        case _ =>
        }
    } catch {
      case e:Exception => e.printStackTrace()
    }
  }

  /**
    *
    * @param id
    */
  def handleOpen(id:Int): Unit = {
    SparkyForge.github.checkMergeAbility(id)
  }

  /**
    *
    * @param id
    */
  def handleClose(id:Int): Unit = {
    val name  = SparkyForge.github.getPullRequestAuthor(id)
    val pr    = SparkyForge.github.getPullRequestTitle(id)
    val url   = SparkyForge.github.getPullURL(id)
    MessageUtil.sendIrcMessageToChannel(name.getName + "("+ name.getLogin +") "+ "has " + Format.RED + "closed "+
      Format.RESET + "pull request "+ id +": " + pr + " " + url)
  }

  /**
    *
    * @param id
    */
  def handleReopen(id:Int): Unit = {
    val name  = SparkyForge.github.getPullRequestAuthor(id)
    val pr    = SparkyForge.github.getPullRequestTitle(id)
    val url   = SparkyForge.github.getPullURL(id)
    MessageUtil.sendIrcMessageToChannel(name.getName + "("+ name.getLogin +") "+ "has " + Format.GREEN + "reopend " +
      Format.RESET + "pull request #" + id +": " + pr + " " + url)
  }

  /**
    *
    * @param id
    */
  def handelSync(id:Int): Unit = {
    SparkyForge.github.syncCheck(id)
    //temp
//    println(SparkyForge.github.getPullRequestTitle(id) + " has updated")
  }

}