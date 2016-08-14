package us.illyohs.sparkyforge.event.github;

public class PullRequestClosedEvent extends PullRequestEvent
{
    private boolean merged;
    private String closer;

    public PullRequestClosedEvent(String actor, String number, String id, String commit, String target, String origin, String closer, boolean merged)
    {
        super(actor, number, id, commit, target, origin);
        this.merged = merged;
        this.closer = closer;
    }

    public String getCloser()
    {
        return closer;
    }

    public boolean isMerged()
    {
        return merged;
    }
}
