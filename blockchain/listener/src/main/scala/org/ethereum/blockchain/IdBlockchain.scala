package org.ethereum.blockchain

import java.math.BigInteger

import cats.Id
import org.ethereum.rpc.domain.Bytes

trait IdBlockchain extends Blockchain[Id] {
  override def blockNumber: BigInteger

  override def getTransactionIdsByBlock(block: BigInteger): List[Bytes]

  override def getTransactionsByBlock(block: BigInteger): List[Transaction]
}
