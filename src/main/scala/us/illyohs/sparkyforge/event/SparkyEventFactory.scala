package us.illyohs.sparkyforge.event

import us.illyohs.sparkyforge.SparkyForge
import us.illyohs.sparkyforge.event.github._

object SparkyEventFactory {

  def branchCreatedEvent(actor: String,
                         branchName:
                         String,
                         url: String) {
    SparkyForge.EVENT_BUS.post(new BranchCreatedEvent(actor, branchName, url))
  }

  def branchDeletedEvent(actor: String,
                         brancName: String,
                         url: String) {
    SparkyForge.EVENT_BUS.post(new BranchDeletedEvent(actor, brancName, url))
  }

  def issueClosedEvent(actor: String,
                       issueNumber: String,
                       id: String,
                       issueName: String,
                       name:String,
                       closer: String,
                       url: String) {
    SparkyForge.EVENT_BUS.post(new IssueClosedEvent(actor, issueNumber, id, issueName, name, closer, url))
  }

  def issueCreatedEvent(actor: String,
                        issueNumber: String,
                        id: String,
                        issueName: String,
                        name:String,
                        url: String) {
    SparkyForge.EVENT_BUS.post(new IssueCreatedEvent(actor, issueNumber, id, issueName, name, url))
  }

  def pullRequestClosedEvent(actor: String,
                             number: String,
                             id: String,
                             commit: String,
                             target: String,
                             origin: String,
                             url: String,
                             closer: String,
                             merged: Boolean) {
    SparkyForge.EVENT_BUS.post(new PullRequestClosedEvent(actor, number, id, commit, target, origin,
      url, closer, merged))
  }

  def pullRequestCreated(actor: String,
                         number: String,
                         id: String,
                         commit: String,
                         target: String,
                         origin: String,
                         url: String) {
    SparkyForge.EVENT_BUS.post(new PullRequestCreatedEvent(actor, number, id, commit, target, origin,
      url))
  }
}
