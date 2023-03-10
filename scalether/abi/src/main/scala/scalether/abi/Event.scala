package scalether.abi

import java.nio.charset.StandardCharsets

import org.ethereum.rpc.domain.{Binary, Word}
import scalether.abi.tuple.TupleType
import scalether.util.Hash

case class Event[IT <: TupleType[_], NI](name: String, types: List[Type[_]], indexed: IT, nonIndexed: TupleType[NI]) {
  def decode(data: Binary): NI = {
    nonIndexed.decode(data, 0).value
  }

  override def toString = s"$name(${types.map(_.string).mkString(",")})"

  def id: Word = {
    val bytes = toString.getBytes(StandardCharsets.US_ASCII)
    Word(Hash.sha3(bytes))
  }
}
