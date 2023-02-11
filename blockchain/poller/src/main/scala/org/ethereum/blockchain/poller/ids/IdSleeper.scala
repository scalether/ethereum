package org.ethereum.blockchain.poller.ids

import cats.Id
import org.ethereum.blockchain.poller.Sleeper

class IdSleeper extends Sleeper[Id] {
  def sleep(sleep: Long): Unit =
    Thread.sleep(sleep)
}
