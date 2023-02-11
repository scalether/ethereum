package org.ethereum.bitcoin.rpc.listener

import org.ethereum.cats.mono.implicits._
import org.ethereum.bitcoin.rpc.core.{MonoBitcoind, MonoRestBitcoind}
import org.ethereum.blockchain.MonoBlockchain
import reactor.core.publisher.Mono

class MonoBitcoinBlockchain(bitcoind: MonoBitcoind, restBitcoind: MonoRestBitcoind)
  extends BitcoinBlockchain[Mono](bitcoind, restBitcoind) with MonoBlockchain {

}
