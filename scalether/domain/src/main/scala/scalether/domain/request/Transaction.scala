package scalether.domain.request

import java.math.BigInteger

import org.ethereum.rpc.domain.Binary
import scalether.domain.Address

case class Transaction(to: Address = null,
                       from: Address = null,
                       gas: BigInteger = null,
                       gasPrice: BigInteger = null,
                       value: BigInteger = null,
                       data: Binary = new Binary(Array()),
                       nonce: BigInteger = null) {
  assert(data != null)
}
