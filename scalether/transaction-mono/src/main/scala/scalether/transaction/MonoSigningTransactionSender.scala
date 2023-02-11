package scalether.transaction

import java.math.BigInteger

import org.ethereum.cats.mono.implicits._
import org.ethereum.rpc.domain.{Binary, Word}
import reactor.core.publisher.Mono
import scalether.core.MonoEthereum
import scalether.domain.request.Transaction
import scalether.sync.{MonoSynchronizer, SemaphoreMonoSynchronizer}

class MonoSigningTransactionSender(override val ethereum: MonoEthereum, nonceProvider: MonoNonceProvider, synchronizer: MonoSynchronizer, privateKey: BigInteger, gas: BigInteger, gasPrice: MonoGasPriceProvider, chainId: Long)
  extends SigningTransactionSender[Mono](ethereum, nonceProvider, synchronizer, privateKey, gas, gasPrice, chainId ) with MonoTransactionSender {

  def this(ethereum: MonoEthereum, nonceProvider: MonoNonceProvider, privateKey: BigInteger, gas: BigInteger, gasPrice: MonoGasPriceProvider, chainId: Long) {
    this(ethereum, nonceProvider, new SemaphoreMonoSynchronizer(), privateKey, gas, gasPrice, chainId)
  }

  override def sendTransaction(transaction: Transaction): Mono[Word] =
    super.sendTransaction(transaction)

  override def prepare(transaction: Transaction): Mono[Transaction] =
    super.prepare(transaction)

  override def getRawTransaction(transaction: Transaction): Mono[Binary] =
    super.getRawTransaction(transaction)
}
