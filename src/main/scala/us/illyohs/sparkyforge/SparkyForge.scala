package us.illyohs.sparkyforge

import us.illyohs.sparkyforge.webhook.SparkyServer
import com.google.common.eventbus.EventBus
import us.illyohs.sparkyforge.bots.github.GitBot
import us.illyohs.sparkyforge.bots.irc.IrcBot

object SparkyForge {
  var EVENT_BUS: EventBus = new EventBus
  private var server: SparkyServer = null
  var git = new GitBot("", "", "")
  var irc = new IrcBot("", 6667, "", "", "")

  def main(args: String*) {
    //        SparkyForge
    server = new SparkyServer(2222)
    irc.botStart
    
  }
}