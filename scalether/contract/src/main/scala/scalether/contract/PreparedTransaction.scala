package scalether.contract

import java.math.BigInteger

import cats.implicits._
import org.ethereum.cats.MonadThrowable
import org.ethereum.rpc.domain.{Binary, Word}
import org.slf4j.{Logger, LoggerFactory}
import scalether.abi.Signature
import scalether.abi.tuple.TupleType
import scalether.domain.request.Transaction
import scalether.domain.Address
import scalether.transaction.TransactionSender

import scala.language.higherKinds

class PreparedTransaction[F[_], O](val address: Address,
                                   val out: TupleType[O],
                                   val data: Binary,
                                   val sender: TransactionSender[F],
                                   val value: BigInteger,
                                   val gas: BigInteger = null,
                                   val gasPrice: BigInteger = null,
                                   val from: Address = null,
                                   val description: String = null,
                                   val nonce:BigInteger = null)
                                  (implicit m: MonadThrowable[F]) {

  def appendData(addData: Binary): PreparedTransaction[F, O] =
    new PreparedTransaction[F, O](address, out, data.add(addData), sender, value, gas, gasPrice, from, description, nonce)

  def withGas(newGas: BigInteger): PreparedTransaction[F, O] =
    new PreparedTransaction[F, O](address, out, data, sender, value, newGas, gasPrice, from, description, nonce)

  def withGasPrice(newGasPrice: BigInteger): PreparedTransaction[F, O] =
    new PreparedTransaction[F, O](address, out, data, sender, value, gas, newGasPrice, from, description, nonce)

  def withData(newData: Binary): PreparedTransaction[F, O] =
    new PreparedTransaction[F, O](address, out, newData, sender, value, gas, gasPrice, from, description, nonce)

  def withValue(newValue: BigInteger): PreparedTransaction[F, O] =
    new PreparedTransaction[F, O](address, out, data, sender, newValue, gas, gasPrice, from, description, nonce)

  def withFrom(newFrom: Address): PreparedTransaction[F, O] =
    new PreparedTransaction[F, O](address, out, data, sender, value, gas, gasPrice, newFrom, description, nonce)

  def withSender(newSender: TransactionSender[F]): PreparedTransaction[F, O] =
    new PreparedTransaction[F, O](address, out, data, newSender, value, gas, gasPrice, from, description, nonce)

  def withNonce(nonce: BigInteger): PreparedTransaction[F, O] =
    new PreparedTransaction[F, O](address, out, data, sender, value, gas, gasPrice, from, description, nonce)

  def call(): F[O] = {
    val tx = createTransaction()
    sender.call(tx)
      .map { binary =>
        try {
          out.decode(binary, 0).value
        } catch {
          case th: Throwable => if (description != null) {
            throw new IllegalArgumentException(s"$description: unable to decode value for $tx", th)
          } else {
            throw new IllegalArgumentException(s"unable to decode value for $tx", th)
          }
        }
      }
  }

  def execute(): F[Word] =
    sender.sendTransaction(createTransaction())

  def estimate(): F[BigInteger] =
    sender.estimate(createTransaction())

  def estimateAndExecute(): F[Word] =
    estimate().flatMap(estimated => this.withGas(estimated).execute())

  def createTransaction(): Transaction =
    Transaction(to = address, data = data, value = value, gas = gas, gasPrice = gasPrice, from = from, nonce = nonce)
}

object PreparedTransaction {
  def apply[F[_], I, O](address: Address,
                        signature: Signature[I, O],
                        in: I,
                        sender: TransactionSender[F],
                        value: BigInteger = null,
                        gas: BigInteger = null,
                        gasPrice: BigInteger = null,
                        from: Address = null,
                        chainId: Long = 0,
                        description: String = null,
                        nonce:BigInteger = null)
                       (implicit m: MonadThrowable[F]): PreparedTransaction[F, O] =
    new PreparedTransaction[F, O](address, signature.out, signature.encode(in), sender, value, gas, gasPrice, from, description, nonce)

  val logger: Logger = LoggerFactory.getLogger(PreparedTransaction.getClass)
}