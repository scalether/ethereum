package scalether.transaction

import org.ethereum.cats.mono.implicits._
import reactor.core.publisher.Mono
import scalether.core.MonoEthereum
import scalether.domain.Address

import scala.language.higherKinds
class ReadOnlyMonoTransactionSender(override val ethereum: MonoEthereum, from: Address)
  extends ReadOnlyTransactionSender[Mono](ethereum, from) with MonoTransactionSender {

}
