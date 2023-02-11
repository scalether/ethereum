package scalether.transaction

import java.math.BigInteger

import org.ethereum.cats.mono.implicits._
import reactor.core.publisher.Mono

class MonoValGasPriceProvider(value: BigInteger) extends ValGasPriceProvider[Mono](value) with MonoGasPriceProvider
