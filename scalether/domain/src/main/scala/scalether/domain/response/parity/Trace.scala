package scalether.domain.response.parity

import java.math.BigInteger

import org.ethereum.rpc.domain.{Binary, Word}
import scalether.domain.Address

case class TraceResult(output: Binary,
                       trace: List[Trace])

case class Trace(action: Action,
                 blockHash: Word,
                 blockNumber: BigInteger,
                 result: ActionResult,
                 subtraces: Int,
                 traceAddress: List[Int],
                 transactionHash: Word,
                 transactionPosition: Int,
                 error: String,
                 `type`: String)

case class Action(callType: String, from: Address, gas: BigInteger, input: String, to: Address, value: BigInteger)

case class ActionResult(gasUsed: BigInteger, output: String, address: Address)