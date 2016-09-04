package us.illyohs.test;

import java.net.MalformedURLException;
import java.net.URL;

import us.illyohs.sparkyforge.util.Shorteners;

public class ShortenUrlTest
{
    public static void main(String... args) throws MalformedURLException
    {
        URL url = new URL("https://github.com/Illyohs/SparkyForge");
        System.out.println(Shorteners.gitIo(url));
    }
}
