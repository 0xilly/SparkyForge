package us.illyohs.sparkyforge.handler;

import java.io.IOException;
import java.util.HashMap;

import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;
import us.illyohs.sparkyforge.bots.irc.command.github.CloseCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.DefaultBranchCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.IsMergedCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.LabelCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.ReopenCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.StatCheckCmd;
import us.illyohs.sparkyforge.bots.irc.command.mcis.LatestCmd;
import us.illyohs.sparkyforge.bots.irc.command.mcis.StatusCmd;
import us.illyohs.sparkyforge.util.ConfigUtil;

import org.kitteh.irc.client.library.event.channel.ChannelMessageEvent;
import org.kitteh.irc.lib.net.engio.mbassy.listener.Handler;

public class CommandHandler
{

    private HashMap<String, BaseCMD> cmdReg = new HashMap<String, BaseCMD>();
    private String cmdOper  = ConfigUtil.getIrcCommendOperator();

    //GitHub
    private BaseCMD label       = new LabelCmd();
    private BaseCMD ismerged    = new IsMergedCmd();
    private BaseCMD defualt     = new DefaultBranchCmd();
    private BaseCMD statCheck   = new StatCheckCmd();
    private BaseCMD close       = new CloseCmd();
    private BaseCMD reopen      = new ReopenCmd();

    //Mcis
    private BaseCMD latest      = new LatestCmd();
    private BaseCMD status      = new StatusCmd();

    public CommandHandler()
    {
        cmdReg.put(label.getName(), label);
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
        return false;
    }
}
