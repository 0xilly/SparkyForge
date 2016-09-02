package us.illyohs.sparkyforge.handler;

import java.io.IOException;
import java.util.HashMap;

import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;
import us.illyohs.sparkyforge.bots.irc.command.github.LabelCmd;
import us.illyohs.sparkyforge.util.ConfigUtil;

import org.kitteh.irc.client.library.event.channel.ChannelMessageEvent;
import org.kitteh.irc.lib.net.engio.mbassy.listener.Handler;

public class CommandHandler
{

    private HashMap<String, BaseCMD> cmdReg = new HashMap<String, BaseCMD>();
    private BaseCMD baseCMD = new LabelCmd();
    private String cmdOper  = ConfigUtil.getIrcCommendOperator();

    public CommandHandler()
    {
        cmdReg.put(baseCMD.getName(), baseCMD);
    }


    private String getFirstWord(String message)
    {
        return message.replace(cmdOper, "").split(" ")[0];
    }

    private String[] getArgs(String message)
    {
        return message.replace(getFirstWord(message), "").split(" ");
    }

    @Handler
    public boolean messageEvent(ChannelMessageEvent event) throws IOException
    {
        String message = event.getMessage();
        String striped = getFirstWord(message);
        if (message.startsWith(cmdOper))
        {
            return cmdReg.get(getFirstWord(message)).execute(event.getChannel(), event.getActor(), getArgs(message));
        }
        return false;
    }
}
