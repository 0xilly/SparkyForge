package us.illyohs.sparkyforge.event.github

class PullRequestEvent(actor:String, pullNumber:String, id:String, comment:String, target:String, origin:String, url:String)
  extends GitEvent(actor) {

  def getPullNumber = this.pullNumber

  def getID = this.id

  def getComment = this.comment

  def getTarget = this.target

  def getOrigin = this.origin

  def getURL = this.url
}

class PullRequestCreatedEvent(actor:String, pullNumber:String, id:String, comment:String, target:String, origin:String, url:String)
  extends PullRequestEvent(actor, pullNumber, id, comment, target, origin, url)


class PullRequestClosedEvent(actor:String, pullNumber:String, id:String, comment:String, target:String, origin:String, url:String, closer:String, merged:Boolean)
  extends PullRequestEvent(actor, pullNumber, id, comment, target, origin, url) {

  def getCloser = this.closer

  def isMerged = this.merged
}
