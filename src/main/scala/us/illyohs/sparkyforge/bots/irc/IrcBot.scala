package us.illyohs.sparkyforge.bots.irc

import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.PircBotX
import org.pircbotx.Configuration
import org.pircbotx.hooks.events.MessageEvent

class IrcBot(
    server:String,
    port:Int,
    name:String,
    passord:String,
    channel:String) {
  
//  PircBotX bot = new Bot
  
  private val conf = new Configuration
    .Builder()
    .setName(name)
    .setNickservPassword(passord)
    .addServer(server, port)
    .addAutoJoinChannel(channel)
    .addListener(SparkyListener)
    .buildConfiguration()
    
    val bot = new PircBotX(conf)
  
  def botStart: Unit = bot.startBot
  
  def botStop: Unit = bot.close()
  
  def botRestart: Unit = {
    botStop
    botStart
  }
  
//  def botSendMessage(message:String): Unit = {
//    bot.send()
//  }
}


object SparkyListener extends ListenerAdapter {
  
  override def onMessage(e:MessageEvent): Unit = {
    
  }
}