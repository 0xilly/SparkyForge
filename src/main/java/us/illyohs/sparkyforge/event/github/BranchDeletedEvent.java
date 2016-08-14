package us.illyohs.sparkyforge.event.github;

public class BranchDeletedEvent extends BranchEvent
{
    public BranchDeletedEvent(String actor, String branchName)
    {
        super(actor, branchName);
    }
}
