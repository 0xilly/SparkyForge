package us.illyohs.sparkyforge.bots.irc.command.github;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;

import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;
import org.kohsuke.github.GHLabel;

public class LabelCmd extends BaseCMD
{

    String[] labz =
            {
            "Bug", //0
            "Vanilla Bug",//1
            "BreakingChange",//2
            "Confirmed",//3
            "Cleanup",//4
            "Fluids" ,//5
            "Needs Update",//6
            "Performance",//7
            "Regression",//8
            "RFC",//9
            "Superseded"//10
            };




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
    public boolean execute(Channel channel, User user, String... args)
    {

        ArrayList<String> ar = new ArrayList<>();

        try
        {
            String first  = args[1];
            String second = args[2];
            int    id     = Integer.parseInt(second);

            Collection<GHLabel> labels = SparkyForge.getGitbot().gitLabels(id);

            if (args[1].equals("ls"))
            {
                labels.forEach(lab -> ar.add(lab.getName()));

                String strLab = Arrays.toString(ar.toArray()).replace("[", "").replace("]", "");
                if (!ar.isEmpty())
                {
                    channel.sendMessage(user.getNick() + ": The current labels are (" + strLab + ")");
                }
                else {
                    channel.sendMessage(user.getNick() + ": There are no labels for this issue/pr");
                }

            }

        } catch (IOException e)
        {

//           e.printStackTrace();
        }

        return true;
    }

}
