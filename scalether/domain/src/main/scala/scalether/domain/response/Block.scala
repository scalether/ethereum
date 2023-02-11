package scalether.domain.response

import java.math.BigInteger

import org.ethereum.rpc.domain.{Binary, Bytes, Word}
import scalether.domain.Address

case class Block[T](number: BigInteger,
                    hash: Word,
                    parentHash: Word,
                    nonce: String,
                    sha3Uncles: String,
                    logsBloom: String,
                    transactionsRoot: String,
                    stateRoot: String,
                    miner: Address,
                    difficulty: BigInteger,
                    totalDifficulty: BigInteger,
                    extraData: Binary,
                    size: BigInteger,
                    gasLimit: BigInteger,
                    gasUsed: BigInteger,
                    transactions: List[T],
                    timestamp: BigInteger) extends org.ethereum.rpc.domain.Block {

  override def getBlockNumber: BigInteger = number

  override def getBlockHash: Bytes = hash
}
