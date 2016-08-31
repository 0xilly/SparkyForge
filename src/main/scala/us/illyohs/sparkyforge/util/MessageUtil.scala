package us.illyohs.sparkyforge.util

import us.illyohs.sparkyforge.SparkyForge

import org.kitteh.irc.client.library.element.User

object MessageUtil {

  /**
    * Because We dont want to ping lex
    * @param message
    * @return
    */
  def lexHelper(message: String): String = {
    if (message.contains("LexManos")) {
      message.replace("LexManos", "lex")
    } else {
      message
    }
  }

  /**
    * Send a message to channel
    * @param message
    */
  def sendIrcMessageToChannel(message:String): Unit = SparkyForge.irc.getChannel.sendMessage(lexHelper(message))

  /**
    * Send a message to the User
    * @param user
    * @param message
    */
  def sendIrcMessageToUser(user:User, message:String): Unit = user.sendMessage(message)

  /**
    * send a Pull Request comment
    * @param id
    * @param comment
    */
  def sendPullRequestMessage(id:Int, comment:String): Unit = SparkyForge.github.getPullRequest(id).comment(comment)

}