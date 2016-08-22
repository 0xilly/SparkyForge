package us.illyohs.sparkyforge.util

import java.util

import us.illyohs.sparkyforge.command.{HelpCmd, ICommand}

object CommandRegistry {
  
  val commandReg:util.HashMap[String, ICommand] = new util.HashMap[String, ICommand]
  val prefix = "~"

  commandReg.put(HelpCmd.name, HelpCmd)

  def commandDispatcher(cmdMessage:String): Unit = {
    if (cmdMessage.startsWith(prefix +"help")) {

    }
  }
}