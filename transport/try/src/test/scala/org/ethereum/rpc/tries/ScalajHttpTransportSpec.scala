package org.ethereum.rpc.tries

import org.ethereum.rpc.{JsonConverter, ManualTag}
import org.scalatest.FlatSpec

class ScalajHttpTransportSpec extends FlatSpec {
  val transport = ScalajHttpTransport("http://localhost:18332", "user", "pass", new JsonConverter())

  "ScalajHttpTransport" should "execute req with basic auth" taggedAs ManualTag in {
    val resp = transport.post("", "{\"id\":1,\"method\":\"getblockchaininfo\",\"params\":[],\"jsonrpc\":\"2.0\"}").get
    println(resp)
    assert(resp.code == 200)
  }
}
