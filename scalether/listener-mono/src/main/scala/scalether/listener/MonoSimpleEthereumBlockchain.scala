package scalether.listener

import org.ethereum.cats.mono.implicits._
import org.ethereum.blockchain.MonoBlockchain
import reactor.core.publisher.Mono
import scalether.core.MonoEthereum

class MonoSimpleEthereumBlockchain(ethereum: MonoEthereum)
  extends SimpleEthereumBlockchain[Mono](ethereum) with MonoBlockchain {

}
