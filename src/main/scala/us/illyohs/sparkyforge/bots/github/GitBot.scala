package us.illyohs.sparkyforge.bots.github

import org.kohsuke.github.GitHub



class GitBot(username:String, token:String, repository:String) {
  
  private val hub = GitHub.connect(username, token)


  def getRepo = hub.getRepository(repository)

  def getDefualtBranch = getRepo.getDefaultBranch

  def getIssue(id:Int) = getRepo.getIssue(id)

  def getPullRequest(id:Int) = getRepo.getPullRequest(id)
  
  def isPointedToDefualt(id:Int): Boolean = {
    val defualt = getDefualtBranch

    val pointed = getPullRequest(id).getHead.getRef
    
    true
  }

  def checkMergeAblity(id:Int): Unit = {
    getPullRequest(id)
  }

}

