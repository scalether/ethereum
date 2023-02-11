package scalether.listener

import cats.Id
import org.ethereum.cats.implicits._
import org.ethereum.blockchain.IdBlockchain
import scalether.core.{IdEthereum, IdParity}

class IdEthereumBlockchain(ethereum: IdEthereum, parity: IdParity)
  extends EthereumBlockchain[Id](ethereum, parity) with IdBlockchain {

}
