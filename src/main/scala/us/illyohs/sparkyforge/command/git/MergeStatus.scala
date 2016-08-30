package us.illyohs.sparkyforge.command.git


import us.illyohs.sparkyforge.SparkyForge
import us.illyohs.sparkyforge.command.BaseCmd
import us.illyohs.sparkyforge.util.MessageUtil

import org.kitteh.irc.client.library.element.{Channel, User}

class MergeStatus 
  extends BaseCmd("mergestat") {
  
  override def help: String = "merge stat <pr number>"
  

  def getPRstatus(id:Int): Boolean = SparkyForge.github.getPullRequest(id).isMerged

  override def execute(user:User, channel:Channel, message:String, args:Array[String]): Unit = {
    val id = args.apply(0).toInt
    val prTile = SparkyForge.github.getPullRequest(id).getTitle
    val pr = SparkyForge.github.getPullRequest(id)

    val isMerged = SparkyForge.github.getPullRequest(id)

    if (getPRstatus(id)) {
      MessageUtil.sendIrcMessageToChannel(user.getNick + ", Pull Request: "+ prTile +" has been merged by " +
        pr.getMergedBy)
    } else {
      MessageUtil.sendIrcMessageToChannel(user.getNick + ": Pull Request: "+ prTile + " has not been merged")
    }

    MessageUtil.sendIrcMessageToChannel("")
  }
}