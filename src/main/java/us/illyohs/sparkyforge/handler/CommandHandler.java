/*
 *     Copyright (C) 2016  Anthony Anderson
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Library General Public
 *     License as published by the Free Software Foundation; either
 *     version 2 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Library General Public License for more details.
 *
 *     You should have received a copy of the GNU Library General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package us.illyohs.sparkyforge.handler;

import java.io.IOException;
import java.util.HashMap;

import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;
import us.illyohs.sparkyforge.bots.irc.command.github.CloseCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.DefaultBranchCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.IsMergedCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.LabelPRCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.LabelissueCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.ListLabelsCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.ReopenCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.StatCheckCmd;
import us.illyohs.sparkyforge.bots.irc.command.mcis.LatestCmd;
import us.illyohs.sparkyforge.bots.irc.command.mcis.GHStatusCmd;
import us.illyohs.sparkyforge.util.ConfigUtil;

import org.kitteh.irc.client.library.event.channel.ChannelMessageEvent;
import org.kitteh.irc.lib.net.engio.mbassy.listener.Handler;

public class CommandHandler
{

    private HashMap<String, BaseCMD> cmdReg = new HashMap<String, BaseCMD>();
    private String cmdOper  = ConfigUtil.getIrcCommendOperator();

    //GitHub
    private BaseCMD labelpr    = new LabelPRCmd();
    private BaseCMD lableissue = new LabelissueCmd();
    private BaseCMD listlabels = new ListLabelsCmd();
    private BaseCMD ismerged   = new IsMergedCmd();
    private BaseCMD defualt    = new DefaultBranchCmd();
    private BaseCMD statCheck  = new StatCheckCmd();
    private BaseCMD close      = new CloseCmd();
    private BaseCMD reopen     = new ReopenCmd();

    //Mcis
    private BaseCMD latest      = new LatestCmd();
    private BaseCMD status      = new GHStatusCmd();

    public CommandHandler()
    {
        cmdReg.put(labelpr.getName(), labelpr);
        cmdReg.put(lableissue.getName(), lableissue);
        cmdReg.put(listlabels.getName(), listlabels);
        cmdReg.put(ismerged.getName(), ismerged);
        cmdReg.put(defualt.getName(), defualt);
        cmdReg.put(statCheck.getName(), statCheck);
        cmdReg.put(close.getName(), close);
        cmdReg.put(reopen.getName(), reopen);
        cmdReg.put(latest.getName(), latest);
        cmdReg.put(status.getName(), status);
    }

    private String getFirstWord(String message)
    {
        return message.replace(cmdOper, "").split(" ")[0];
    }

    private String[] getArgs(String message)
    {
        String mes = message.replace(getFirstWord(message), "");
        return mes.replace(cmdOper, "").split(" ");
    }

    @Handler
    public boolean messageEvent(ChannelMessageEvent event) throws IOException
    {
        String message = event.getMessage();
        if (message.startsWith(cmdOper))
        {
            try {
                if (cmdReg.containsKey(getFirstWord(message)))
                {
                    return cmdReg.get(getFirstWord(message).toLowerCase()).execute(event.getChannel(), event.getActor(), getArgs(message.toLowerCase()));
                }

            } catch (ArrayIndexOutOfBoundsException e)
            {
                event.getChannel().sendMessage("error with arg");
            }
        }

        if (message.startsWith(".commands"))
        {
            for (BaseCMD cmd : cmdReg.values()) {
                event.getActor().sendNotice(cmd.getName() + ": " + cmd.getHelp());
            }
        }
        return false;
    }
}
