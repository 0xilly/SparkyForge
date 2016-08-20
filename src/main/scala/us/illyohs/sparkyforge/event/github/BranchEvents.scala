package us.illyohs.sparkyforge.event.github

class BranchEvent(actor: String, branchName:String, url:String)
  extends GitEvent(actor) {

  def getBranchName = this.branchName

  def getUrl = this.url

}

class BranchCreatedEvent(actor:String, branchName:String, url:String) extends BranchEvent(actor, branchName, url)
class BranchDeletedEvent(actor:String, branchName:String, url:String) extends BranchEvent(actor, branchName, url)
