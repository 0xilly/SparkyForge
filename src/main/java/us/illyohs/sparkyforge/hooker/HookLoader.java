package us.illyohs.sparkyforge.hooker;

import java.util.ArrayList;
import java.util.List;

import us.illyohs.sparkyforge.hooker.hooks.GitHubHooker;
import us.illyohs.sparkyforge.hooker.hooks.Hooker;
import us.illyohs.sparkyforge.util.ConfigUtil;

import spark.Spark;


public class HookLoader
{
    private List<Hooker> hookList = new ArrayList<>();

    public HookLoader()
    {
        hookList.add(new GitHubHooker());
    }

    public void loadHooks()
    {
        Spark.port(ConfigUtil.getWebHookPort());
        System.out.println();
        Spark.init();

        for (Hooker hook: hookList)
        {
            Spark.post("/"+ hook.getName() , hook::init);
        }
    }
}
