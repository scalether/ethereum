package org.ethereum.bitcoin.rpc

import org.ethereum.bitcoin.rpc.core.{MonoBitcoind, MonoRestBitcoind}
import org.ethereum.rpc.mono.WebClientTransport

trait IntegrationSpec {
  val transport: WebClientTransport = WebClientTransport.createWithBasicAuth("http://btc:8332", "user", "pass")
  val bitcoind = new MonoBitcoind(transport)
  val restBitcoind = new MonoRestBitcoind(transport)
}
