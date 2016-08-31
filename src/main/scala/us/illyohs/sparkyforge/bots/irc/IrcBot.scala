package us.illyohs.sparkyforge.bots.irc

import org.kitteh.irc.client.library.Client

class IrcBot(network:String, port:Int, name:String, password:String, channel:String) {

  var client: Client = null

  def connect: Unit = {
    client = Client.builder()
      .serverHost(network)
      .serverPort(port)
      .nick(name)
      .name(name)
      .user(name)
      .secure(false)
      .build()

    client.addChannel(channel)
  }

  def getChannel = client.getChannel(this.channel).get()

}