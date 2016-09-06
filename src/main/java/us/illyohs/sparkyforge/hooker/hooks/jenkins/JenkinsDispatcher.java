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

import us.illyohs.sparkyforge.util.MessageUtils;
import us.illyohs.sparkyforge.util.WebUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JenkinsDispatcher
{
    public JenkinsDispatcher(String json)
    {
        JsonObject jObj   = WebUtils.readJsonObjectFromString(json);
        JsonObject build  = jObj.get("build").getAsJsonObject();

        String     name   = jObj.get("name").getAsString();
        int        number = build.get("number").getAsInt();
        String     phase  = build.get("phase").getAsString();
        String     status = build.get("status").getAsString();
        
        buildHandler(name, number, phase, status);
    }

    private void buildHandler(String name, int number, String phase, String status)
    {
        switch (phase)
        {
            case "STARTED": handlerStarted(name, number, status);
                break;
            case "COMPLETED": handleCompleted(name, number, status);
                break;
            default:
                break;
        }
    }

    private void handlerStarted(String name, int number, String status)
    {
    }
    
    private void handleCompleted(String name, int number, String status)
    {
        if (status == "SUCCESS")
        {
            sendJenkinsMessage(name, number, status);
            MessageUtils.sendMessageToChannel("Go to and grab the latest build from http://files.minecraftforge.net/");
        } else if (status == "FAILURE")
        {
            sendJenkinsMessage(name, number, status);
        } else if (status == "UNSTABLE")
        {

        } else if (status == "ABORTED")
        {
            MessageUtils.sendMessageToChannel("[Jenkins](project: " + name + ", Build: #"+ number +")");

            sendJenkinsMessage(name, number, status);
        }
    }

    private void sendJenkinsMessage(String name, int number, String status)
    {
        MessageUtils.sendMessageToChannel("[Jenkins] project: " + name + ", Build: #" + number + ", Status: "+ status);
    }


}
