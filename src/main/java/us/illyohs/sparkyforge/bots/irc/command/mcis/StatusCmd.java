package us.illyohs.sparkyforge.bots.irc.command.mcis;

import javax.annotation.Nullable;
import java.io.IOException;

import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;
import us.illyohs.sparkyforge.util.WebUtils;

import com.google.gson.JsonObject;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class StatusCmd extends BaseCMD
{
    public StatusCmd()
    {
        super("status");
    }

    @Override
    public String getHelp()
    {
        return null;
    }

    @Override
    public boolean execute(Channel channel, User user, @Nullable String... args) throws IOException, ArrayIndexOutOfBoundsException
    {
        if (args[1] == "github" || args[1] == "gh")
        {
            github(channel, user);
        }
        else {
            channel.sendMessage("Currently supported status check: github");
        }
        return true;
    }

    private void github(Channel channel, User user) throws IOException
    {

        JsonObject jObj    = WebUtils.readJObjFromURL("https://status.github.com/api/last-message.json");
        String     status  = jObj.get("status").getAsString();
        String     message = jObj.get("body").getAsString();

        channel.sendMessage("GitHub is status: " + status + ", Message: " + message);

    }

}
