package us.illyohs.sparkyforge.bots.irc.command.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import us.illyohs.sparkyforge.SparkyForge;
import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;
import us.illyohs.sparkyforge.util.GitHubHelper;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;
import org.kohsuke.github.GHLabel;

public class LabelCmd extends BaseCMD
{

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

            int    id     = string2int(args[2]);

            Collection<GHLabel> labels = SparkyForge.getGitbot().getLabels(id);

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

            } else if (args[1].equals("add"))
            {
//                if (!doesLabelexsit(id,args2String(3, args)))
//                {
//                    SparkyForge.getGitbot().addLabel(id, args2String(3, args));
////                    System.out.println(args2String(3, args));
//                }
//
//                if (!SparkyForge.getGitbot().doesLabelExist(id, args2String(3, args)))
//                {
//                    SparkyForge.getGitbot().addLabel(id, args2String(3, args));
//                }
            }

        } catch (IOException e)
        {

//           e.printStackTrace();
        }

        return true;
    }

}
