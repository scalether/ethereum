package scalether.transport

import org.ethereum.rpc.mono.WebClientTransport
import scalether.core.Ethereum

object EthereumWebClientTransport {
  def apply(rpcUrl: String, requestTimeoutMs: Int = 10000, readWriteTimeoutMs: Int = 10000) =
    new WebClientTransport(rpcUrl, Ethereum.mapper, requestTimeoutMs, readWriteTimeoutMs)
}
