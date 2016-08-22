package us.illyohs.sparkyforge

import us.illyohs.sparkyforge.webhook.SparkyServer

import com.google.common.eventbus.EventBus

import us.illyohs.sparkyforge.bots.github.GitBot
import us.illyohs.sparkyforge.bots.irc.IrcBot
import us.illyohs.sparkyforge.util.ConfigUtil._


object SparkyForge {


  var EVENT_BUS: EventBus = new EventBus
  private var server: SparkyServer = null

  var github  = new GitBot(getGitHubLogin, getGitHubToken, getGitHubRepo)
  var irc     = new IrcBot(getIrcNetwork, getIrcPort, getIrcName, getIrcNickPass, getIrcChannel)
  var sparkyPort = getWebHookPort

  def main(args: Array[String]) {

    //        SparkyForge
    server = new SparkyServer(sparkyPort)
    irc.connect
    
  }
}