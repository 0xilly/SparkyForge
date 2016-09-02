package us.illyohs.sparkyforge.bots.irc.command.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import org.apache.commons.lang.StringUtils;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;
import org.kohsuke.github.GHLabel;

public class LabelCmd extends BaseCMD
{

//    String[] lables =
//            {
//            "Bug", //0
//            "Vanilla Bug",//1
//            "BreakingChange",//2
//            "Confirmed",//3
//            "Cleanup",//4
//            "Fluids" ,//5
//            "Needs Update",//6
//            "Performance",//7
//            "Regression",//8
//            "RFC",//9
//            "Superseded"//10
//            };

    Collection<GHLabel> labels;

    public LabelCmd()
    {
        super("label");
    }

    @Override
    public String getHelp()
    {
        return "ls";
    }

    @Override
    public boolean execute(Channel channel, User user, String... args) throws IOException
    {
        String first  = args[1];
        String second = args[2];
        int    id     = Integer.parseInt(second);

        System.out.println("test");

        ArrayList<String>   ar     = new ArrayList<>();


        if (first == "ls")
        {
            labels = SparkyForge.getGitbot().gitLabels(id);

            labels.forEach(lab -> System.out.println(Arrays.toString(labels.toArray())));

//            channel.sendMessage(message);
        }
        return true;
    }

//    private String getLables(int id)
//    {
//        return
//    }
}
