package org.ethereum.blockchain.block

import java.math.BigInteger

import cats.Id
import org.ethereum.blockchain.IdBlockchain
import org.ethereum.blockchain.state.{IdState, IdStateAdapter}

class IdBlockListenService(blockchain: IdBlockchain, listener: IdBlockListener, state: IdState[BigInteger]) {
  private val scala = new BlockListenService[Id](blockchain, new IdBlockListenerAdapter(listener), new IdStateAdapter[BigInteger](state))

  def check(): BigInteger =
    scala.check()
}
