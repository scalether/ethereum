package org.ethereum.rpc.mono

import org.ethereum.rpc.{JsonConverter, ManualTag}
import org.ethereum.rpc.domain.Request
import org.scalatest.FlatSpec

class WebSocketRpcTransportSpec extends FlatSpec {
  val transport = new WebSocketRpcTransport(new WebSocketReconnectingClient("localhost:8546"), JsonConverter.createMapper())

  "MonoTransport" should "send requests and get back responses" taggedAs ManualTag in {
    val resp = transport.send[String](Request(1, "net_version")).block()
    assert(resp.result.get == "17")
  }
}
