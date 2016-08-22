package us.illyohs.sparkyforge.command

import org.pircbotx.UserLevel

abstract class BaseCmd(cmdname:String, perm:UserLevel) 
  extends ICommand {
  
  override def name: String = this.cmdname
  
  override def getPermLevel: UserLevel = this.perm
}