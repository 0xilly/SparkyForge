package us.illyohs.sparkyforge.bots.github

import us.illyohs.sparkyforge.util.MessageUtil

import org.kitteh.irc.client.library.util.Format._
import org.kohsuke.github.GitHub

/**
  *
  * @param token
  * @param repository example MinecraftForge/MinecraftForge
  */
class GitBot(token:String, repository:String) {
  
  private val github = GitHub.connectUsingOAuth(token)

  /**
    * Get the getRepository
    * @return
    */
  def getRepository = github.getRepository(repository)

  /**
    * Get the default branch
    * @return
    */
  def getDefaultBranch = getRepository.getDefaultBranch

  /**
    * Get the pull request of the Repo
    * @param iD
    * @return
    */
  def getPullRequest(iD:Int) = getRepository.getPullRequest(iD)

  /**
    * Get the tile of the pull request
    * @param id
    * @return
    */
  def getPullRequestTitle(id:Int) = getPullRequest(id).getTitle

  /**
    * Get the author of a pull request
    * @param id
    * @return
    */
  def getPullRequestAuthor(id:Int) = getPullRequest(id).getUser

  /**
    * get the state of a pull request
    * @param id
    * @return
    */
  def getState(id:Int) = getPullRequest(id).getState

  /**
    * Get if the pr is merged
    * @param id
    * @return
    */
  def isMerged(id:Int) = getPullRequest(id).isMerged

  /**
    * Get the url of the pull request
    * @param id
    * @return
    */
  def getPullURL(id:Int) = getPullRequest(id).getHtmlUrl

  /**
    * See if the branch is pointed to the default branch
    * @param id
    * @return
    */
  def isPointedToDefault(id:Int) = {
    if (getDefaultBranch == getPullRequest(id).getBase.getRef) {
      true
    } else {
      false
    }
  }

  /**
    * Has the bot made a comment already
    * @param id
    * @return
    */
  def haveIMadeAComment(id:Int): Boolean = {
    val comments = getPullRequest(id).getComments
    if (comments.size() !=0) {
      for (i <- 0 until comments.size()) {
        val getUser = comments.get(i).getUser
        if (getUser == github.getMyself) {
          true
        }
      }
    }
    false
  }

  /**
    * Set the status of the PR to good or bad
    * @param id
    * @param isbad
    */
  def setStatus(id:Int, isbad:Boolean): Unit = {

    import org.kohsuke.github.GHCommitState._

    val pr     = getPullRequest(id)
    val prRepo = pr.getRepository
    for(i <- 0 until pr.listCommits().asList().size()) {

      val commit  = pr.listCommits().asList().get(i).getSha

      if (isbad) {
        getRepository.createCommitStatus(commit, FAILURE, "", "Is not pointed to the default branch")
      } else {
        getRepository.createCommitStatus(commit, SUCCESS, "", "Is pointed to master")
      }
    }

  }

  /**
    * Check to see if the branch is mergeable
    * @param id
    */
  def checkMergeAbility(id:Int): Unit = {

    val user   = getPullRequest(id).getUser
    val author = user.getName + "(" + user.getLogin + ")"
    val title  = getPullRequestTitle(id)

    if (!isPointedToDefault(id) && !haveIMadeAComment(id)) {

      val PullMessage = "@" + user.getLogin + " This pull request is not targeted to the current default branch: __" +
        getDefaultBranch + "__. Please re-target to __" + getDefaultBranch + "__ if you want this branch to be merged."

      val IrcMessage = author +"'s pull request: "+ RED + title + RESET+ " is" + BOLD + RED + " NOT" + RESET +
        " pointed to the default branch("+ GREEN + getDefaultBranch + RESET + ") and can't be merged!"

      setStatus(id, true)//Mark commit as bad
      MessageUtil.sendPullRequestMessage(id, PullMessage)

      MessageUtil.sendIrcMessageToChannel(IrcMessage)
      MessageUtil.sendIrcMessageToChannel("Link: " + getPullURL(id))

    } else if (!haveIMadeAComment(id) && getPullRequest(id).getMergeable) {

      val IrcMessage = author +"'s "+ RESET +"pull request: "+ GREEN + title + RESET + " is " + BOLD + GREEN +
        "MERGEABLE " + RESET + "and ready to be reviewed!"

      setStatus(id, false)//Mark as good
      MessageUtil.sendIrcMessageToChannel(IrcMessage)
      MessageUtil.sendIrcMessageToChannel("Link: " + getPullURL(id))
    }
  }

  /**
    * To lazy to make another check in checkMergeAbility so I made this...
    * @param id
    */
  def syncCheck(id:Int): Unit = {
    if (!isPointedToDefault(id)) {
      setStatus(id, true)//Mark commit as bad
    } else {
      setStatus(id, false)//Mark commit as good
    }
  }

}