package scalether.transaction

import java.math.BigInteger

import cats.Id
import org.ethereum.cats.implicits._
import org.ethereum.rpc.domain.{Binary, Word}
import scalether.core.IdEthereum
import scalether.domain.request.Transaction
import scalether.sync.{IdSynchronizer, SemaphoreIdSynchronizer}

class IdSigningTransactionSender(override val ethereum: IdEthereum, nonceProvider: IdNonceProvider, synchronizer: IdSynchronizer, privateKey: BigInteger, gas: BigInteger, gasPrice: IdGasPriceProvider, chainId: Long)
  extends SigningTransactionSender[Id](ethereum, nonceProvider, synchronizer, privateKey, gas, gasPrice, chainId) with IdTransactionSender {

  def this(ethereum: IdEthereum, nonceProvider: IdNonceProvider, privateKey: BigInteger, gas: BigInteger, gasPrice: IdGasPriceProvider, chainId: Long) {
    this(ethereum, nonceProvider, new SemaphoreIdSynchronizer(), privateKey, gas, gasPrice, chainId)
  }

  override def sendTransaction(transaction: Transaction): Word =
    super.sendTransaction(transaction)

  override def prepare(transaction: Transaction): Transaction =
    super.prepare(transaction)

  override def getRawTransaction(transaction: Transaction): Binary =
    super.getRawTransaction(transaction)
}
