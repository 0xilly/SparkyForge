package us.illyohs.sparkyforge.event.github;

import us.illyohs.sparkyforge.event.Event;

public class IssueEvent extends Event
{
    private String actor;
    private String issueNumber;
    private String id;
    private String issueName;

    public IssueEvent(String actor, String issueNumber, String id, String issueName)
    {
        this.actor = actor;
        this.id = id;
        this.issueName = issueName;
        this.issueNumber = issueNumber;
    }

    public String getActor()
    {
        return actor;
    }

    public String getIssueNumber()
    {
        return issueNumber;
    }

    public String getId()
    {
        return id;
    }

    public String getIssueName()
    {
        return issueName;
    }
}
