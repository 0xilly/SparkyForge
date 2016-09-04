package us.illyohs.test;

import java.io.IOException;

import us.illyohs.sparkyforge.util.WebUtils;

public class ReadURLTest
{
    public static void main(String... args)
    {
        try
        {
            System.out.println(WebUtils.readFromURL("http://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
