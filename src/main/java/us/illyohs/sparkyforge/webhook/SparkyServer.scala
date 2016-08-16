package us.illyohs.sparkyforge.webhook

import us.illyohs.sparkyforge.webhook.github.GitHooker

import com.google.common.io.ByteStreams
import spark.{Request, Response, Route, Spark}

object SparkyServer {

  def Github() {
    Spark.post("/github", new Route {
      override def handle(request: Request, response: Response) = {
        val sig = request.headers("X-Hub-Signature")
        val eventType = request.headers("X-GitHub-GitEvent")
        val data:Array[Byte] = ByteStreams.toByteArray(request.raw().getInputStream)

        new GitHooker(sig, eventType, data)

      }
    })
  }
}

class SparkyServer(val port: Int) {
  Spark.port(port)
  Spark.init()
  SparkyServer.Github()
  //    public static handle
}