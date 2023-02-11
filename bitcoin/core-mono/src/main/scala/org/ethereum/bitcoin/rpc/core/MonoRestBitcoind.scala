package org.ethereum.bitcoin.rpc.core

import org.ethereum.bitcoin.rpc.domain.{Block, Transaction}
import org.ethereum.rpc.MonoHttpTransport
import org.ethereum.rpc.domain.Binary
import reactor.core.publisher.Mono

class MonoRestBitcoind(transport: MonoHttpTransport) extends RestBitcoind[Mono](transport) {
  override def getBlockSimple(hash: String): Mono[Block[Binary]] =
    super.getBlockSimple(hash)

  override def getBlockFull(hash: String): Mono[Block[Transaction]] =
    super.getBlockFull(hash)
}
