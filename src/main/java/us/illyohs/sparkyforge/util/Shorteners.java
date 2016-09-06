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

package us.illyohs.sparkyforge.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.github.kevinsawicki.http.HttpRequest;

public class Shorteners
{

    public static URL gitIo(URL url)
    {
        try
        {
            HashMap<String, String> map = new HashMap<>();
            String target = "https://git.io";
            map.put("url", url.toExternalForm());
            return new URL(HttpRequest.post(target).form(map).header("Location"));
        } catch (MalformedURLException e)
        {
            return url;
        }
    }
}
