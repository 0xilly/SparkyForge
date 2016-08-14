package us.illyohs.sparkyforge.event.github;

public class IssueClosedEvent extends IssueEvent
{
    private String closer;

    public IssueClosedEvent(String actor, String issueNumber, String id, String issueName, String closer)
    {
        super(actor, issueNumber, id, issueName);
        this.closer = closer;
    }

    public String getCloser()
    {
        return closer;
    }
}
