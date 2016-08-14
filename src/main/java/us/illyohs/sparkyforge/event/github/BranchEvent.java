package us.illyohs.sparkyforge.event.github;

import us.illyohs.sparkyforge.event.Event;

public class BranchEvent extends Event
{
    String actor;
    String branchName;

    public BranchEvent(String actor, String branchName)
    {
        this.actor = actor;
        this.branchName = branchName;
    }

    public String getActor()
    {
        return actor;
    }

    public String getBranchName()
    {
        return branchName;
    }
}
