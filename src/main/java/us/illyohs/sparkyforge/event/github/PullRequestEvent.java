package us.illyohs.sparkyforge.event.github;

public class PullRequestEvent
{
    private String actor;
    private String number;
    private String id;
    private String commit;
    private String target;
    private String origin;

    public PullRequestEvent(String actor, String number, String id, String commit, String target, String origin)
    {
        this.actor = actor;
        this.number = number;
        this.id = id;
        this.commit = commit;
        this.target = target;
        this.origin = origin;
    }

    public String getActor()
    {
        return actor;
    }

    public String getNumber()
    {
        return number;
    }

    public String getId()
    {
        return id;
    }

    public String getCommit()
    {
        return commit;
    }

    public String getTarget()
    {
        return target;
    }

    public String getOrigin()
    {
        return origin;
    }
}
