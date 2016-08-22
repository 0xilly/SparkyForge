package us.illyohs.sparkyforge.util

import java.io._

import com.google.gson.{FieldNamingPolicy, Gson, GsonBuilder}

object ConfigUtil {

  val confFile = new File("sparky.cfg").

  val gson:Gson = null

  case object DefaultConf extends Configuration(2222, "fooLogin", "SomeToken", "repo", "irc.example.com", 6666, "NotSpaky", "somenickservpass", "#channel", "~")

  def initConf: Unit = {
    val gson = new GsonBuilder()
      .setPrettyPrinting()
      .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
      .create()

    if (!confFile.exists()) {
      confFile.createNewFile()
      val w:Writer = new FileWriter(confFile)
      val json = gson.toJson(DefaultConf)

      try {
        w.write(json)
        w.close()
      } catch {
        case io: IOException => io.printStackTrace
      }
    }
  }


  val conf:Configuration = gson.fromJson(new FileReader(confFile), classOf[Configuration])

  def getWebHookPort = conf.WebHookPort
  def getGitHubLogin = conf.GitHubUserName
  def getGitHubToken = conf.GitHubToken
  def getGitHubRepo  = conf.GitHubRepository
  def getIrcNetwork  = conf.IrcNetwork
  def getIrcPort     = conf.IrcPort
  def getIrcName     = conf.IrcName
  def getIrcNickPass = conf.IrcNickPass
  def getIrcChannel  = conf.IrcChannel
  def getCommandOperator = conf.CommandOperator

}
