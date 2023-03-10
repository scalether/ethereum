package scalether.domain.response

import java.math.BigInteger

import org.ethereum.rpc.domain.{Binary, Word}
import scalether.domain.Address

case class Transaction(hash: Word,
                       nonce: BigInteger,
                       blockHash: Word,
                       blockNumber: BigInteger,
                       creates: Address,
                       transactionIndex: BigInteger,
                       from: Address,
                       to: Address,
                       value: BigInteger,
                       gasPrice: BigInteger,
                       gas: BigInteger,
                       input: Binary)
