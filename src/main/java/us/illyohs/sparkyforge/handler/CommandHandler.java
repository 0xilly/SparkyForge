package us.illyohs.sparkyforge.handler;

import java.io.IOException;
import java.util.HashMap;

import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;
import us.illyohs.sparkyforge.bots.irc.command.github.IsPRMergedCmd;
import us.illyohs.sparkyforge.bots.irc.command.github.LabelCmd;
import us.illyohs.sparkyforge.util.ConfigUtil;

import org.kitteh.irc.client.library.event.channel.ChannelMessageEvent;
import org.kitteh.irc.lib.net.engio.mbassy.listener.Handler;

public class CommandHandler
{

    private HashMap<String, BaseCMD> cmdReg = new HashMap<String, BaseCMD>();
    private String cmdOper  = ConfigUtil.getIrcCommendOperator();

    private BaseCMD labelCmd = new LabelCmd();
    private BaseCMD ismerged = new IsPRMergedCmd();

    public CommandHandler()
    {
        cmdReg.put(labelCmd.getName(), labelCmd);
        cmdReg.put(ismerged.getName(), ismerged);
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
                return cmdReg.get(getFirstWord(message)).execute(event.getChannel(), event.getActor(), getArgs(message));

            } catch (ArrayIndexOutOfBoundsException e)
            {
                event.getChannel().sendMessage("Command error with arg");
            }
        }
        return false;
    }
}
