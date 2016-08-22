package us.illyohs.sparkyforge.bots.github

import org.kohsuke.github.GitHub



class GitBot(
    username:String,
    token:String,
    repository:String) {
  
  val hub = GitHub.connect(username, token)

  val repo = hub.getRepository("")
  
  def defBranch: String = repo.getDefaultBranch
  
  def isPointedToDefualt(id:Int): Boolean = {
    val pointed = repo.getPullRequest(id)
    
    true
  }
}

