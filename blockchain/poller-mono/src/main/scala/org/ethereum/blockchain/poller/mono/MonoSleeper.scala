package org.ethereum.blockchain.poller.mono

import java.util.concurrent.{Executors, ScheduledExecutorService, TimeUnit}

import org.ethereum.blockchain.poller.Sleeper
import reactor.core.publisher.Mono

class MonoSleeper extends Sleeper[Mono] {
  val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(1)

  def sleep(sleep: Long): Mono[Unit] =
    Mono.create[Unit](sink => {
      executor.schedule(new CompleteMonoRunnable(sink, ()), sleep, TimeUnit.MILLISECONDS)
    })
}
