package org.ethereum.blockchain.poller

import scala.language.higherKinds

trait Sleeper[F[_]] {
  def sleep(sleep: Long): F[Unit]
}
