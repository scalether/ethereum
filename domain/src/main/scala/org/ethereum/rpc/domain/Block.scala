package org.ethereum.rpc.domain

import java.math.BigInteger

trait Block {
  def getBlockNumber: BigInteger
  def getBlockHash: Bytes
}
