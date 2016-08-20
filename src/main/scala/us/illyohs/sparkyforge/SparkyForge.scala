package us.illyohs.sparkyforge

import us.illyohs.sparkyforge.webhook.SparkyServer
import com.google.common.eventbus.EventBus

object SparkyForge {
  var EVENT_BUS: EventBus = new EventBus
  private var server: SparkyServer = null

  def main(args: String*) {
    //        SparkyForge
    server = new SparkyServer(2222)
  }
}