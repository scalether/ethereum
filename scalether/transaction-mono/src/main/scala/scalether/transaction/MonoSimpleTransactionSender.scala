package scalether.transaction

import java.math.BigInteger

import org.ethereum.cats.mono.implicits._
import reactor.core.publisher.Mono
import scalether.core.MonoEthereum
import scalether.domain.Address

class MonoSimpleTransactionSender(override val ethereum: MonoEthereum, from: Address, gas: BigInteger, gasPrice: MonoGasPriceProvider, chainId: Long)
  extends SimpleTransactionSender[Mono](ethereum, from, gas, gasPrice, chainId) with MonoTransactionSender {
}
