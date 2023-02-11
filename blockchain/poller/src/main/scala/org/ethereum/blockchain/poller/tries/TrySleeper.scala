package org.ethereum.blockchain.poller.tries

import org.ethereum.blockchain.poller.Sleeper

import scala.util.Try

class TrySleeper extends Sleeper[Try] {
  def sleep(sleep: Long): Try[Unit] = Try {
    Thread.sleep(sleep)
  }
}
