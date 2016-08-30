package us.illyohs.sparkyforge.command

import org.kitteh.irc.client.library.element.Channel
import org.kitteh.irc.client.library.element.User

trait ICommand {

  def name: String

  def help: String

  def execute(user: User, channel: Channel, message: String, args: Array[String])
}