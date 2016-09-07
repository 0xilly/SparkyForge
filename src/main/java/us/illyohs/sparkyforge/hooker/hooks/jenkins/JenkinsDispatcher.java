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

package us.illyohs.sparkyforge.hooker.hooks.jenkins;

import java.util.Objects;

import us.illyohs.sparkyforge.util.MessageUtils;
import us.illyohs.sparkyforge.util.WebUtils;

import com.google.gson.JsonObject;

public class JenkinsDispatcher
{
    public JenkinsDispatcher(String json)
    {
        System.out.println(json);
        JsonObject jObj   = WebUtils.readJsonObjectFromString(json);
        JsonObject build  = jObj.get("build").getAsJsonObject();

        String     name   = jObj.get("name").getAsString();
        int        number = build.get("number").getAsInt();
        String     phase  = build.get("phase").getAsString();

        buildHandler(name, number, phase, json);
    }

    private void buildHandler(String name, int number, String phase, String json)
    {
        switch (phase)
        {
            case "STARTED": handleStarted(name, number);
                break;
            case "FINALIZED": handleCompleted(name, number, json);
                break;
            default:
                break;
        }
    }

    private void handleStarted(String name, int number)
    {
        MessageUtils.sendMessageToChannel("[Jenkins] Project: " + name + ", Build: #"+ number +" has started!");
    }
    
    private void handleCompleted(String name, int number, String json)
    {
        JsonObject jObj   = WebUtils.readJsonObjectFromString(json);
        JsonObject build  = jObj.get("build").getAsJsonObject();
        String     status = build.get("status").getAsString();


        MessageUtils.sendMessageToChannel("[Jenkins] Project: " + name + ", Build: #" + number + ", Status: "+ status);

        if (!Objects.equals(status, "SUCCESS")) {
            MessageUtils.sendMessageToChannel("[Jenkins] Beep boop beep somethings wrong ");
        } else {
            MessageUtils.sendMessageToChannel("Go to and grab the latest build from http://files.minecraftforge.net/");
        }

    }



}
