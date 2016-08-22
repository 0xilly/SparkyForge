package us.illyohs.sparkyforge.command

import us.illyohs.sparkyforge.util.{CommandRegistry, MessageUtil}

import org.kitteh.irc.client.library.element.{Channel, User}



abstract class BaseCmd(cmdname:String)
  extends ICommand {
  
  override def name: String = this.cmdname

}

object HelpCmd
  extends BaseCmd("help") {

  override def help(): String = "lists all the commands"

  override def execute(user: User, channel: Channel, message: String, args:Array[String]): Unit = {

    val commands = CommandRegistry.commandReg

    for (i <- 0 to commands.size()) {
      val cmd = commands.get(i)
      MessageUtil.sendIrcMessageToUser(user, cmd.name + ": " + cmd.help)
    }

  }
}
