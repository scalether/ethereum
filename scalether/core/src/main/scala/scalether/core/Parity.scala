package scalether.core

import java.math.BigInteger

import org.ethereum.cats.MonadThrowable
import org.ethereum.rpc.{RpcClient, RpcTransport}
import scalether.domain.request.Transaction
import scalether.domain.response.parity.{Trace, TraceResult}

class Parity[F[_]](transport: RpcTransport[F])
                  (implicit me: MonadThrowable[F])
  extends RpcClient[F](transport) {

  def traceTransaction(txHash: String): F[List[Trace]] = {
    exec("trace_transaction", txHash)
  }

  def traceBlock(blockNumber: BigInteger): F[List[Trace]] = {
    exec("trace_block", blockNumber)
  }

  def traceCallMany(transactions: List[Transaction], defaultBlockParameter: String): F[List[TraceResult]] = {
    exec("trace_callMany", transactions.map(tx => List(tx, List("trace"))), defaultBlockParameter)
  }
}
