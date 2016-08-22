package us.illyohs.sparkyforge.util

import us.illyohs.sparkyforge.SparkyForge

import org.kitteh.irc.client.library.element.{Channel, User}

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
    * @param channel
    * @param message
    */
  def sendIrcMessageToChannel(channel:Channel, message:String): Unit = channel.sendMessage(lexHelper(message))

  /**
    * Send a message to the channel
    * @param user
    * @param message
    */
  def sendIrcMessageToUser(user:User, message:String): Unit = user.sendMessage(message)

  /**
    * send Issue comment
    * @param id
    * @param comment
    */
  def sendIssueMessage(id:Int, comment:String): Unit = SparkyForge.github.getIssue(id).comment(comment)

  /**
    * send a Pull Request comment
    * @param id
    * @param comment
    */
  def sendPullRequest(id:Int, comment:String): Unit = SparkyForge.github.getPullRequest(id).comment(comment)

}