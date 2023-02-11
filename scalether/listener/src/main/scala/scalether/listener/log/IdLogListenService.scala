package scalether.listener.log

import java.math.BigInteger
import java.util

import cats.Id
import org.ethereum.cats.implicits._
import org.ethereum.blockchain.state.{IdState, IdStateAdapter}
import scalether.core.IdEthereum
import scalether.domain.response.Log

import scala.collection.JavaConverters._

class IdLogListenService(ethereum: IdEthereum, confidence: Int, listener: IdLogListener, state: IdState[BigInteger]) {
  private val scala = new LogListenService[Id](ethereum, confidence, new IdLogListenerAdapter(listener), new IdStateAdapter[BigInteger](state))

  def checkAndGetJava(blockNumber: BigInteger): util.List[Log] =
    scala.checkAndGet(blockNumber).asJava
}
