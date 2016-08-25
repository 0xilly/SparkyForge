package us.illyohs.sparkyforge.bots.irc

import org.kitteh.irc.client.library.Client
import org.kitteh.irc.client.library.element.User
import org.kitteh.irc.client.library.event.channel.ChannelMessageEvent
import org.kitteh.irc.lib.net.engio.mbassy.listener.Handler

class IrcBot(server:String, port:Int, name:String, password:String, channel:String, commandOpperator:Char) {

  var client: Client = null

  def connect: Unit = {
    client = Client.builder()
      .serverHost(server)
      .serverPort(port)
      .nick(name)
      .name(name)
      .user(name)
      .build()


    client.addChannel(channel)
  }

  def getChannel = client.getChannel(this.channel).get()

  @Handler
  def onMessageEvent(e:ChannelMessageEvent): Unit = {
    if (!e.getActor.isInstanceOf[User]) return

    val channel = e.getChannel
    val user:User = e.getActor.asInstanceOf[User]
    val message = e.getMessage

  }
}