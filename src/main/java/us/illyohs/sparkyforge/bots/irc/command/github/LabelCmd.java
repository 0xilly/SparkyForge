package us.illyohs.sparkyforge.bots.irc.command.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
        List<String> ar = new ArrayList<>();

        try
        {

            int    id     = string2int(args[1]);

            Collection<GHLabel> labels = SparkyForge.getGitbot().getLabels(id);
            boolean doesExist = SparkyForge.getGitbot().doesLabelExist(id, args[3]);

            if (args[2].equals("ls"))
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

            } else if (args[2].equals("add"))
            {
                labels.forEach(lab -> ar.add(lab.getName()));

                if (doesExist)
                {
                    String[] lbz    = new String[0];
                    String addlabel = SparkyForge.getGitbot().getLabelFromName(args[3]).getName();
                    ar.add(addlabel);

                    lbz = ar.toArray(lbz);
                    SparkyForge.getGitbot().getPullRequest(id).setLabels(lbz);
                } else
                {
                    channel.sendMessage(user.getNick() + " The label:" + args[3] + ", does not exist!");
                }

            } else if (args[2].equals("rm"))
            {
                labels.forEach(lab -> ar.add(lab.getName()));
                if (doesExist)
                {
                    String[] lbz    = new String[0];
                    String rmlabel = SparkyForge.getGitbot().getLabelFromName(args[3]).getName();
                    ar.remove(rmlabel);

                    lbz = ar.toArray(lbz);
                    SparkyForge.getGitbot().getPullRequest(id).setLabels(lbz);
                } else
                {
                    channel.sendMessage(user.getNick() + ", The label: " + args[3] + ", does not exist!");
                }
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return true;
    }

}
