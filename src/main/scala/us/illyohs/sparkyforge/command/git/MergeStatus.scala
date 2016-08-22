package us.illyohs.sparkyforge.command.git

import us.illyohs.sparkyforge.command.BaseCmd
import org.pircbotx.UserLevel
import us.illyohs.sparkyforge.SparkyForge

class MergeStatus 
  extends BaseCmd("mergestat", null) {
  
  override def help: String = "merge stat <pr number>"
  
  override def execute(args:String*): Unit = {
    val mergeId = args.indexOf(0).toInt
//    SparkyForge.irc.bot.send().m
  }
  
  def getPRstatus(id:Int): Boolean = SparkyForge.git.repo.getPullRequest(id).isMerged()
  
}