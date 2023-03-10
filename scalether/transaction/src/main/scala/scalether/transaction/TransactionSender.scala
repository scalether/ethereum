package scalether.transaction

import java.math.BigInteger

import org.ethereum.rpc.domain.{Binary, Word}
import scalether.core.Ethereum
import scalether.domain.Address
import scalether.domain.request.Transaction

import scala.language.higherKinds

trait TransactionSender[F[_]] {
  val from: Address
  val ethereum: Ethereum[F]
  def call(transaction: Transaction): F[Binary]
  def estimate(transaction: Transaction): F[BigInteger]
  def sendTransaction(transaction: Transaction): F[Word]
}
