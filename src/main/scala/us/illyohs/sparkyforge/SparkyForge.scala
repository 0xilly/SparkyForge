package us.illyohs.sparkyforge


import us.illyohs.sparkyforge.bots.github.GitBot
import us.illyohs.sparkyforge.bots.irc.IrcBot
import us.illyohs.sparkyforge.util.ConfigUtil
import us.illyohs.sparkyforge.webhook.SparkyServer


object SparkyForge {

  private var server: SparkyServer = _

  var github = new GitBot(
    ConfigUtil.getGitHubLogin,
    ConfigUtil.getGitHubToken,
    ConfigUtil.getGitHubRepo)

  var irc = new IrcBot(
    ConfigUtil.getIrcNetwork,
    ConfigUtil.getIrcPort,
    ConfigUtil.getIrcNick,
    ConfigUtil.getIrcPass,
    ConfigUtil.getIrcChannel,
    ConfigUtil.getIrcCMD)

  def main(args: Array[String]) {
    server = new SparkyServer(ConfigUtil.getWebHookPort)
    irc.connect

  }
}

