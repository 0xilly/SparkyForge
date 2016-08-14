package us.illyohs.sparkyforge.event.github;

public class PullRequestCreatedEvent extends PullRequestEvent
{
    public PullRequestCreatedEvent(String actor, String number, String id, String commit, String target, String origin)
    {
        super(actor, number, id, commit, target, origin);
    }
}
