package org.ethereum.blockchain.block

import java.math.BigInteger

import org.ethereum.cats.mono.implicits._
import org.ethereum.blockchain.MonoBlockchain
import org.ethereum.blockchain.state.{MonoState, MonoStateAdapter}
import reactor.core.publisher.Mono

class MonoBlockListenService(blockchain: MonoBlockchain, listener: MonoBlockListener, state: MonoState[BigInteger]) {
  private val scala = new BlockListenService[Mono](blockchain, new MonoBlockListenerAdapter(listener), new MonoStateAdapter[BigInteger](state))

  def check(): Mono[BigInteger] =
    scala.check()
}
