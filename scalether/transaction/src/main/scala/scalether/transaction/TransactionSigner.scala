package scalether.transaction

import java.math.BigInteger
import java.nio.ByteBuffer

import org.ethereum.rpc.domain.Binary
import org.scalether.crypto.Sign
import org.scalether.crypto.Sign.SignatureData
import org.scalether.rlp.{RlpEncoder, RlpList, RlpString, RlpType}
import org.scalether.utils.Numeric
import scalether.domain.request.Transaction
import scalether.transaction.TransactionSigner._
import scalether.util.Bytes

class TransactionSigner(privateKey: BigInteger, chainId: Long) {
  private val publicKey = Sign.publicKeyFromPrivate(privateKey)

  def sign(transaction: Transaction): Array[Byte] = {
    val signatureData = new SignatureData(longToBytes(chainId), Array[Byte](), Array[Byte]())
    val encodedTransaction = RlpEncoder.encode(new RlpList(asRlp(transaction) ++ asRlp(signatureData):_*))
    val data = Sign.signMessage(encodedTransaction, publicKey, privateKey)
    val eip155SignatureData =  createEip155SignatureData(data,chainId)
    RlpEncoder.encode(new RlpList(asRlp(transaction) ++ asRlp(eip155SignatureData):_*))
  }
}

object TransactionSigner {

  def asRlp(transaction: Transaction): Array[RlpType] = Array(
    RlpString.create(transaction.nonce),
    RlpString.create(transaction.gasPrice),
    RlpString.create(transaction.gas),
    Option(transaction.to).map(a => RlpString.create(a.bytes)).getOrElse(RlpString.create("")),
    RlpString.create(Option(transaction.value).getOrElse(BigInteger.ZERO)),
    RlpString.create(transaction.data.bytes)
  )

  def asRlp(ecSignature: SignatureData): Array[RlpType] = Array(
    RlpString.create(Bytes.trimLeadingZeroes(ecSignature.getV)),
    RlpString.create(Bytes.trimLeadingZeroes(ecSignature.getR)),
    RlpString.create(Bytes.trimLeadingZeroes(ecSignature.getS)),
  )

  protected def longToBytes(x: Long) = {
    val buffer = ByteBuffer.allocate(8)
    buffer.putLong(x)
    buffer.array
  }

  def createEip155SignatureData(signatureData: Sign.SignatureData, chainId: Long): Sign.SignatureData = {
    var v = Numeric.toBigInt(signatureData.getV)
    v = v.subtract(BigInteger.valueOf(27L))
    v = v.add(BigInteger.valueOf(chainId).multiply(BigInteger.valueOf(2L)))
    v = v.add(BigInteger.valueOf(35L))
    new Sign.SignatureData(v.toByteArray, signatureData.getR, signatureData.getS)
  }
}
