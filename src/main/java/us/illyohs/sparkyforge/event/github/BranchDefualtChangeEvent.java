package us.illyohs.sparkyforge.event.github;

public class BranchDefualtChangeEvent extends BranchEvent
{
    private String newDefualtBranch;

    public BranchDefualtChangeEvent(String actor, String branchName, String newDefualtBranch)
    {
        super(actor, branchName);
        this.newDefualtBranch = newDefualtBranch;
    }

    public String getNewDefualtBranch()
    {
        return newDefualtBranch;
    }
}
