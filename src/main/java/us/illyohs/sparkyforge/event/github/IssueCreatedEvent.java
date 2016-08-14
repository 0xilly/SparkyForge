package us.illyohs.sparkyforge.event.github;

public class IssueCreatedEvent extends IssueEvent
{
    public IssueCreatedEvent(String actor, String issueNumber, String id, String issueName)
    {
        super(actor, issueNumber, id, issueName);
    }
}
