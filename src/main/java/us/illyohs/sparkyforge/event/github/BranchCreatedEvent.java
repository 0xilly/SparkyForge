package us.illyohs.sparkyforge.event.github;

public class BranchCreatedEvent extends BranchEvent
{
    public BranchCreatedEvent(String actor, String branchName)
    {
        super(actor, branchName);
    }
}
