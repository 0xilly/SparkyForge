package us.illyohs.sparkyforge.bots.irc.command.mcis;

import javax.annotation.Nullable;
import java.io.IOException;

import us.illyohs.sparkyforge.bots.irc.command.BaseCMD;
import us.illyohs.sparkyforge.util.WebUtils;

import com.google.gson.JsonObject;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

public class LatestCmd extends BaseCMD
{
    public LatestCmd()
    {
        super("latest");
    }

    @Override
    public String getHelp()
    {
        return "Gets the latest version of forge";
    }

    @Override
    public boolean execute(Channel channel, User user, @Nullable String... args) throws ArrayIndexOutOfBoundsException
    {
        String url  = "http://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json";
        String file = "http://files.minecraftforge.net/";
        try {

            JsonObject jObj = WebUtils.readJObjFromURL("http://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json");

            JsonObject promos = jObj.get("promos").getAsJsonObject();
            String     latest = promos.get("latest").getAsString();
            channel.sendMessage(user.getNick() + ": The latest version of forge is " + latest + ". Go to "+ file +
                    " to download.");
        } catch (IOException e)
        {

        }

        return true;
    }
}
