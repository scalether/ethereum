package scalether.abi

import org.ethereum.rpc.domain.{Binary, Bytes, Word}
import scalether.abi.array.{FixArrayType, VarArrayType}
import scalether.abi.tuple.{TupleType, UnitType}
import scalether.domain.request.SimpleTopicFilter

trait Type[T] {
  def string: String

  def size: Option[Int] = Some(32)

  def dynamic: Boolean = size.isEmpty

  def encode(value: T): Binary

  def decode(data: Bytes, offset: Int): Decoded[T]

  //todo implement for types with size > 32
  def encodeForTopic(value: T): SimpleTopicFilter =
    if (value != null)
      SimpleTopicFilter(Word(encode(value)))
    else
      null
}

object Type {
  def apply(`type`: String): Type[_] = `type`.trim match {
    case s if s.endsWith("[]") => VarArrayType(Type(s.substring(0, s.length - 2)))
    case s if s.endsWith("]") =>
      val idx = s.indexOf("[")
      val size = s.substring(idx + 1, s.length - 1).toInt
      FixArrayType(size, Type(s.substring(0, idx)))
    case s if s.startsWith("(") && s.endsWith(")") =>
      val inner = s.substring(1, s.length - 1)
      if (inner.trim.isEmpty) {
        UnitType
      } else {
        val types = inner.split(",")
          .toList
          .map(one => Type(one))
        TupleType(types)
      }
    case "string" => StringType
    case "bytes" => BytesType
    case "bool" => BoolType
    case "address" => AddressType
    case "uint" => Uint256Type
    case "int" => Int256Type
    case s if s.startsWith("uint") => UintType(s.substring(4).toShort)
    case s if s.startsWith("int") => IntType(s.substring(3).toShort)
    case s if s.startsWith("bytes") => FixedBytesType(s.substring(5).toShort)
    case _ => throw new IllegalArgumentException(s"Unknown type: ${`type`.trim}")
  }
}