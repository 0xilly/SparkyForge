package us.illyohs.sparkyforge.bots.github

import us.illyohs.sparkyforge.util.MessageUtil

import com.google.common.collect.Iterables
import org.kitteh.irc.client.library.util.Format._
import org.kohsuke.github.GitHub


class GitBot(login:String, token:String, repository:String) {
  
  private val github = GitHub.connect(login, token)

  def getRepository = github.getRepository(repository)

  def getDefaultBranch = getRepository.getDefaultBranch

  def getPullRequest(iD:Int) = getRepository.getPullRequest(iD)

  def getPullRequestTitle(id:Int) = getPullRequest(id).getTitle

  def getState(id:Int) = getPullRequest(id).getState

  def isMerged(id:Int) = getPullRequest(id).isMerged

  def isPointedToDefault(id:Int) = {
    if (getDefaultBranch == getPullRequest(id).getBase.getRef) {
      true
    } else {
      false
    }
  }

  def haveIMadeAComment(iD:Int): Boolean = {
    val comments = getPullRequest(iD).getComments
    for (i <- 0 to comments.size()) {
      val getUser = comments.get(i).getUser
      if (getUser == github.getMyself) {
        true
      }
    }
    false
  }

  def setStatus(id:Int): Unit = {
    val pr = getPullRequest(id)
    val last = Iterables.getLast(pr.listFiles().asList())
    last.getStatus
  }

  def checkMergeAbility(id:Int): Unit = {

    val user    = getPullRequest(id).getUser
    val author  = user.getName + "(" + user.getLogin + ")"
    val title   = getPullRequestTitle(id)

    if (!isPointedToDefault(id) && !haveIMadeAComment(id)) {

      val PullMessage = "@" + user.getLogin + " This pull request is not targeted to the current default branch: __" +
        getDefaultBranch + "__. Please re-target to __" + getDefaultBranch + "__ if you want this branch to be merged."

      val IrcMessage = GREEN + author +"'s "+ RESET +"pull request: "+ RED + title + RESET+ " is" + BOLD + RED + "NOT" +
        RESET + " pointed to the default branch("+ GREEN + getDefaultBranch + RESET + ") and can't be merged!"

      MessageUtil.sendPullRequestMessage(id, PullMessage)

      MessageUtil.sendIrcMessageToChannel(IrcMessage)
    } else if (!haveIMadeAComment(id) && getPullRequest(id).getMergeable) {
      val IrcMessage = GREEN + author +"'s "+ RESET +"pull request:"+ GREEN + title + RESET + "is" + BOLD + GREEN +
        "Mergeable " + RESET + "and ready to be reviewed!"
    }
  }

}

