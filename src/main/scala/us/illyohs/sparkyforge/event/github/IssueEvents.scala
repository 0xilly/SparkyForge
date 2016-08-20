package us.illyohs.sparkyforge.event.github

class IssueEvent(actor:String, issueNumber:String, id:String, issueName:String, name:String, url:String)
  extends GitEvent(actor){

  def getIssueNumber = this.issueNumber

  def getID = this.id

  def getIssueName = this.issueName

  def getUrl = this.url
}

class IssueCreatedEvent(actor:String, issueNumber:String, id:String, issueName:String, name:String, url:String)
  extends IssueEvent(actor, issueNumber, id, issueName, name, url)

class IssueClosedEvent(actor:String, issueNumber:String, id:String, issueName:String, name:String, url:String, closer:String)
  extends IssueEvent(actor, issueNumber, id, issueName, name, url) {

  def getCloser = closer

}
