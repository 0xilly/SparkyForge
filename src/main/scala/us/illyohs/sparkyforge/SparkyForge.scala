package us.illyohs.sparkyforge


import us.illyohs.sparkyforge.bots.github.GitBot
import us.illyohs.sparkyforge.bots.irc.IrcBot
import us.illyohs.sparkyforge.util.ConfigUtil
import us.illyohs.sparkyforge.webhook.SparkyServer

import com.google.common.eventbus.EventBus


object SparkyForge {

  ConfigUtil.init()

  var EVENT_BUS: EventBus = new EventBus
  private var server: SparkyServer = _
  var github  = new GitBot(
    ConfigUtil.getConf.getUsername,
    ConfigUtil.getConf.getToken,
    ConfigUtil.getConf.getRepository)

  var irc     = new IrcBot(
    ConfigUtil.getConf.getNetwork,
    ConfigUtil.getConf.getPort,
    ConfigUtil.getConf.getNick,
    ConfigUtil.getConf.getPassword,
    ConfigUtil.getConf.getChannel,
    ConfigUtil.getConf.getCommandOpperator)
  var sparkyPort = ConfigUtil.getConf.getWebHookPort

  def main(args: Array[String]) {

            SparkyForge
    server = new SparkyServer(sparkyPort)
//    irc.connect

  }
}