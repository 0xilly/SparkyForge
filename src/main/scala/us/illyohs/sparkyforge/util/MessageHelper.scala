package us.illyohs.sparkyforge.util

object MessageHelper {

  def lexHelper(message: String): String = {
    if (message.contains("LexManos")) {
      message.replace("LexManos", "lex")
    }
    else {
      message
    }
  }
}