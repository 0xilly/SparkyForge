package us.illyohs.sparkyforge.util

import java.util.HashMap
import us.illyohs.sparkyforge.command.ICommand

object CommandRegistry {
  
  val commandReg:HashMap[String, ICommand] = new  HashMap[String, ICommand]
}