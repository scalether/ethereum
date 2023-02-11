package org.ethereum.bitcoin.rpc.core

import org.ethereum.bitcoin.rpc.domain.{Block, Transaction}
import org.ethereum.rpc.HttpTransport
import org.ethereum.rpc.domain.Binary

class RestBitcoind[F[_]](transport: HttpTransport[F]) {
  def getBlockSimple(hash: String): F[Block[Binary]] =
    transport.get(s"/rest/block/notxdetails/$hash.json")

  def getBlockFull(hash: String): F[Block[Transaction]] =
    transport.get(s"/rest/block/$hash.json")
}
