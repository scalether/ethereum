package scalether.transaction

import cats.Monad
import cats.implicits._
import org.ethereum.blockchain.poller.Poller
import org.ethereum.rpc.domain.Word
import scalether.core.Ethereum
import scalether.domain.response.Transaction

import scala.language.higherKinds

class TransactionReceivePoller[F[_]](val ethereum: Ethereum[F])
                                    (implicit f: Monad[F], poller: Poller[F]) {

  def receiveTransaction(txHash: Word): F[Transaction] = for {
    result <- poller.poll(1000)(pollForTransaction(txHash))
  } yield result

  private def pollForTransaction(txHash: Word): F[Option[Transaction]] =
    ethereum.ethGetTransactionByHash(txHash)
}
