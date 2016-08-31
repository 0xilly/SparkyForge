package us.illyohs.sparkyforge.util

import java.io.{FileNotFoundException, IOException}
import java.nio.file.{Files, Paths}
import java.util.Properties

object ConfigUtil {

  private val conf = Paths.get("sparky.properties")
  private val props = new Properties()

  private def writeProperties: Unit = {
    if (!Files.exists(conf)) {
      println(conf.getFileName + ": Not found now creating!")
      try {
        props.setProperty("GitHub.Login", "login")
        props.setProperty("GitHub.Token", "token")
        props.setProperty("GitHub.Repository", "repo")

        props.setProperty("IRC.Network", "irc.esper.net")
        props.setProperty("IRC.Port", "6666")
        props.setProperty("IRC.Nick", "Sparky")
        props.setProperty("IRC.Password", "12345")
        props.setProperty("IRC.Channel", "#channel")
//        props.setProperty("IRC.CommendOperator", "~")

        props.setProperty("WebHook.Port", "2222")

        val out = Files.newOutputStream(conf)
        props.store(out, "Sparky conf")
        out.close

      } catch {
        case fileio: FileNotFoundException => fileio.printStackTrace()
        case io: IOException => io.printStackTrace()
      }
      println("please restart the bot after I exit")
      sys.exit()
    }
  }

  def getGitHubToken: String = {
    writeProperties
    val in = Files.newInputStream(conf)
    props.load(in)
    props.getProperty("GitHub.Token")
  }

  def getGitHubRepo: String = {
    writeProperties
    val in = Files.newInputStream(conf)
    props.load(in)
    props.getProperty("GitHub.Repository")
  }

  def getIrcNetwork: String = {
    writeProperties
    val in = Files.newInputStream(conf)
    props.load(in)
    props.getProperty("IRC.Network")
  }

  def getIrcNick: String = {
    writeProperties
    val in = Files.newInputStream(conf)
    props.load(in)
    props.getProperty("IRC.Nick")
  }

  def getIrcPass: String = {
    writeProperties
    val in = Files.newInputStream(conf)
    props.load(in)
    props.getProperty("IRC.Password")
  }

  def getIrcPort: Int = {
    writeProperties
    val in = Files.newInputStream(conf)
    props.load(in)
    props.getProperty("IRC.Port").toInt
  }

  def getIrcChannel: String = {
    writeProperties
    val in = Files.newInputStream(conf)
    props.load(in)
    props.getProperty("IRC.Channel")
  }

//  def getIrcCMD: String = {
//    writeProperties
//    val in = Files.newInputStream(conf)
//    props.load(in)
//    props.getProperty("IRC.CommendOperator")
//  }

  def getWebHookPort: Int = {
    writeProperties
    val in = Files.newInputStream(conf)
    props.load(in)
    props.getProperty("WebHook.Port").toInt
  }

}
