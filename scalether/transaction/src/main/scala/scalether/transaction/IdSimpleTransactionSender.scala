package scalether.transaction

import java.math.BigInteger

import cats.Id
import org.ethereum.cats.implicits._
import scalether.core.IdEthereum
import scalether.domain.Address

class IdSimpleTransactionSender(override val ethereum: IdEthereum, from: Address, gas: BigInteger, gasPrice: IdGasPriceProvider, chainId: Long)
  extends SimpleTransactionSender[Id](ethereum, from, gas, gasPrice, chainId) with IdTransactionSender {
}
