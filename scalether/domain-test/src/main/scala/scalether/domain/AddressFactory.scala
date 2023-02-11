package scalether.domain

import org.ethereum.rpc.domain.BinaryFactory

object AddressFactory {
  def create() = Address(BinaryFactory.createByteArray(20))
}
