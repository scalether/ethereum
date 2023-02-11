package scalether.listener

import org.ethereum.cats.mono.implicits._
import org.ethereum.blockchain.MonoBlockchain
import reactor.core.publisher.Mono
import scalether.core.{MonoEthereum, MonoParity}

class MonoEthereumBlockchain(ethereum: MonoEthereum, parity: MonoParity)
  extends EthereumBlockchain[Mono](ethereum, parity) with MonoBlockchain {

}
