package org.ethereum.blockchain.transaction

import java.math.BigInteger

import org.ethereum.rpc.domain.Bytes

import scala.language.higherKinds

trait TransactionListener[F[_]] {
  def onTransaction(id: Bytes, blockNumber: BigInteger, confirmations: Int, confirmed: Boolean): F[Unit]
}
