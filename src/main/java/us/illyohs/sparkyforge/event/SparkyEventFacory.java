package us.illyohs.sparkyforge.event;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.event.github.*;

public class SparkyEventFacory
{
    public static void brachCreatedEvent(String actor, String branchName)
    {
        SparkyForge.EVENT_BUS.post(new BranchCreatedEvent(actor, branchName));
    }

    public static void brachDeletedEvent(String actor, String brancName)
    {
        SparkyForge.EVENT_BUS.post(new BranchDeletedEvent(actor, brancName));
    }

    public static void branchDefaultChangeEvent(String actor, String branchname, String newDefault)
    {
        SparkyForge.EVENT_BUS.post(new BranchDefualtChangeEvent(actor, branchname, newDefault));
    }

    public static void issueClosedEvent(String actor, String issueNumber, String id, String issueName, String closer)
    {
        SparkyForge.EVENT_BUS.post(new IssueClosedEvent(actor, issueNumber, id, issueName, closer));
    }

    public static void issueCreatedEvent(String actor, String issueNumber, String id, String issueName)
    {
        SparkyForge.EVENT_BUS.post(new IssueCreatedEvent(actor, issueNumber, id, issueName));
    }

    public static void pullRequestClosedEvent(String actor, String number, String id, String commit, String target, String origin, String closer, boolean merged)
    {
        SparkyForge.EVENT_BUS.post(new PullRequestClosedEvent(actor, number, id, commit, target, origin, closer, merged));
    }

    public static void PullRequestCreated(String actor, String number, String id, String commit, String target, String origin)
    {
        SparkyForge.EVENT_BUS.post(new PullRequestCreatedEvent(actor, number, id, commit, target, origin));
    }

}
