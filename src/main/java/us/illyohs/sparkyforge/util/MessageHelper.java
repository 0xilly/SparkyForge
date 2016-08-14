package us.illyohs.sparkyforge.util;

public class MessageHelper
{
    public static String lexHelper(String message)
    {
        if (message.contains("LexManos"))
        {
           return message.replace("LexManos", "lex");
        } else {
            return  message;
        }
    }
}
