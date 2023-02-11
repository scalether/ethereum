package org.ethereum.blockchain

import java.math.BigInteger

import org.ethereum.rpc.domain.Bytes
import reactor.core.publisher.Mono

trait MonoBlockchain extends Blockchain[Mono] {
  override def blockNumber: Mono[BigInteger]

  override def getTransactionIdsByBlock(block: BigInteger): Mono[List[Bytes]]

  override def getTransactionsByBlock(block: BigInteger): Mono[List[Transaction]]
}
