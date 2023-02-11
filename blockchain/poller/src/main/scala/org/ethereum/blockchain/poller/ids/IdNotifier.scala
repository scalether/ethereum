package org.ethereum.blockchain.poller.ids

import cats.Id
import org.ethereum.blockchain.poller.Notifier

class IdNotifier extends Notifier[Id] {
  override def notify[T](list: Seq[T])(f: T => Unit): Unit =
    list.foreach(f(_))
}
