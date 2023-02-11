package org.ethereum.blockchain.poller.mono

import org.ethereum.cats.mono.implicits._
import org.ethereum.blockchain.poller.{Notifier, Poller}
import reactor.core.publisher.Mono

object implicits {
  implicit val monoSleeper: MonoSleeper = new MonoSleeper
  implicit val monoPoller: Poller[Mono] = new Poller[Mono](monoSleeper)
  implicit val monoNotifier: Notifier[Mono] = new MonoNotifier()
}
