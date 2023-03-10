package scalether.transaction

import java.math.BigInteger

import org.ethereum.cats.MonadThrowable
import org.ethereum.rpc.domain.{Binary, Word}
import scalether.core.Ethereum
import scalether.domain.request.Transaction
import scalether.domain.Address

import scala.language.higherKinds

class ReadOnlyTransactionSender[F[_]](val ethereum: Ethereum[F], val from: Address)
                                     (implicit m: MonadThrowable[F])
  extends TransactionSender[F] {

  override def call(transaction: Transaction): F[Binary] = {
    val tx = transaction.copy(from = Option(transaction.from).getOrElse(from))
    ethereum.ethCall(tx, "latest")
  }

  override def estimate(transaction: Transaction): F[BigInteger] =
    ethereum.ethEstimateGas(transaction.copy(from = from), "latest")

  override def sendTransaction(transaction: Transaction): F[Word] =
    m.raiseError(new UnsupportedOperationException)
}
