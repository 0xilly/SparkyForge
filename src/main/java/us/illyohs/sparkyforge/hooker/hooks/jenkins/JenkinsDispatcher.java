/*
 * Copyright (c) 2016, Anthony Anderson
 * Copyright (c) 2016, Minecraft Forge
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package us.illyohs.sparkyforge.hooker.hooks.jenkins;

import java.util.Objects;

import us.illyohs.sparkyforge.util.ConfigUtil;
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

            if (ConfigUtil.isTwitterBotEnabled()) {

                MessageUtils.postMessageTwitterMessage("Forge build #" + build + " has been built!\n" +
                        "Go grab it from http://files.minecraftforge.net/");
            }
        }

    }



}
