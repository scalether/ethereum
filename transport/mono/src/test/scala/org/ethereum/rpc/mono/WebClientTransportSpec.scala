package org.ethereum.rpc.mono

import org.ethereum.rpc.{JsonConverter, ManualTag}
import org.ethereum.rpc.domain.Request
import org.scalatest.FlatSpec

class WebClientTransportSpec extends FlatSpec {
  val transport = new WebClientTransport("http://ether-ropsten:8545", JsonConverter.createMapper())

  "MonoTransport" should "send post requests and get back responses" taggedAs ManualTag in {
    val resp = transport.send[String](Request(1, "net_version")).block()
    assert(resp.result.get == "3")
  }
}
