package scalether.abi.tuple

import java.math.BigInteger

import org.ethereum.rpc.domain.{Binary, Bytes}
import scalether.abi.{Decoded, Type, Uint256Type}

import scala.collection.mutable.ListBuffer

class Tuple8Type[T1, T2, T3, T4, T5, T6, T7, T8](val type1: Type[T1], val type2: Type[T2], val type3: Type[T3], val type4: Type[T4], val type5: Type[T5], val type6: Type[T6], val type7: Type[T7], val type8: Type[T8]) extends TupleType[(T1, T2, T3, T4, T5, T6, T7, T8)] {
  def string = s"(${type1.string},${type2.string},${type3.string},${type4.string},${type5.string},${type6.string},${type7.string},${type8.string})"

  def types = List(type1, type2, type3, type4, type5, type6, type7, type8)

  def encode(value: (T1, T2, T3, T4, T5, T6, T7, T8)): Binary = {
    val head = ListBuffer[Byte]()
    val tail = ListBuffer[Byte]()
    if (type1.dynamic) {
      head ++= Uint256Type.encode(BigInteger.valueOf(headSize + tail.size)).bytes
      tail ++= type1.encode(value._1).bytes
    } else {
      head ++= type1.encode(value._1).bytes
    } 
    if (type2.dynamic) {
      head ++= Uint256Type.encode(BigInteger.valueOf(headSize + tail.size)).bytes
      tail ++= type2.encode(value._2).bytes
    } else {
      head ++= type2.encode(value._2).bytes
    } 
    if (type3.dynamic) {
      head ++= Uint256Type.encode(BigInteger.valueOf(headSize + tail.size)).bytes
      tail ++= type3.encode(value._3).bytes
    } else {
      head ++= type3.encode(value._3).bytes
    } 
    if (type4.dynamic) {
      head ++= Uint256Type.encode(BigInteger.valueOf(headSize + tail.size)).bytes
      tail ++= type4.encode(value._4).bytes
    } else {
      head ++= type4.encode(value._4).bytes
    } 
    if (type5.dynamic) {
      head ++= Uint256Type.encode(BigInteger.valueOf(headSize + tail.size)).bytes
      tail ++= type5.encode(value._5).bytes
    } else {
      head ++= type5.encode(value._5).bytes
    } 
    if (type6.dynamic) {
      head ++= Uint256Type.encode(BigInteger.valueOf(headSize + tail.size)).bytes
      tail ++= type6.encode(value._6).bytes
    } else {
      head ++= type6.encode(value._6).bytes
    } 
    if (type7.dynamic) {
      head ++= Uint256Type.encode(BigInteger.valueOf(headSize + tail.size)).bytes
      tail ++= type7.encode(value._7).bytes
    } else {
      head ++= type7.encode(value._7).bytes
    } 
    if (type8.dynamic) {
      head ++= Uint256Type.encode(BigInteger.valueOf(headSize + tail.size)).bytes
      tail ++= type8.encode(value._8).bytes
    } else {
      head ++= type8.encode(value._8).bytes
    } 
    Binary((head ++ tail).toArray)
  }

  def decode(data: Bytes, offset: Int): Decoded[(T1, T2, T3, T4, T5, T6, T7, T8)] = {
    val v1 = if (type1.dynamic) {
      val bytesOffset = Uint256Type.decode(data, offset + headOffset(0)).value.intValue()
      type1.decode(data, offset + bytesOffset)
    } else {
      type1.decode(data, offset + headOffset(0))
    } 
    val v2 = if (type2.dynamic) {
      val bytesOffset = Uint256Type.decode(data, offset + headOffset(1)).value.intValue()
      type2.decode(data, offset + bytesOffset)
    } else {
      type2.decode(data, offset + headOffset(1))
    } 
    val v3 = if (type3.dynamic) {
      val bytesOffset = Uint256Type.decode(data, offset + headOffset(2)).value.intValue()
      type3.decode(data, offset + bytesOffset)
    } else {
      type3.decode(data, offset + headOffset(2))
    } 
    val v4 = if (type4.dynamic) {
      val bytesOffset = Uint256Type.decode(data, offset + headOffset(3)).value.intValue()
      type4.decode(data, offset + bytesOffset)
    } else {
      type4.decode(data, offset + headOffset(3))
    } 
    val v5 = if (type5.dynamic) {
      val bytesOffset = Uint256Type.decode(data, offset + headOffset(4)).value.intValue()
      type5.decode(data, offset + bytesOffset)
    } else {
      type5.decode(data, offset + headOffset(4))
    } 
    val v6 = if (type6.dynamic) {
      val bytesOffset = Uint256Type.decode(data, offset + headOffset(5)).value.intValue()
      type6.decode(data, offset + bytesOffset)
    } else {
      type6.decode(data, offset + headOffset(5))
    } 
    val v7 = if (type7.dynamic) {
      val bytesOffset = Uint256Type.decode(data, offset + headOffset(6)).value.intValue()
      type7.decode(data, offset + bytesOffset)
    } else {
      type7.decode(data, offset + headOffset(6))
    } 
    val v8 = if (type8.dynamic) {
      val bytesOffset = Uint256Type.decode(data, offset + headOffset(7)).value.intValue()
      type8.decode(data, offset + bytesOffset)
    } else {
      type8.decode(data, offset + headOffset(7))
    } 
    Decoded((v1.value, v2.value, v3.value, v4.value, v5.value, v6.value, v7.value, v8.value), v8.offset)
  }
}

object Tuple8Type {
  def apply[T1, T2, T3, T4, T5, T6, T7, T8](type1: Type[T1], type2: Type[T2], type3: Type[T3], type4: Type[T4], type5: Type[T5], type6: Type[T6], type7: Type[T7], type8: Type[T8]): Tuple8Type[T1, T2, T3, T4, T5, T6, T7, T8] = 
    new Tuple8Type(type1, type2, type3, type4, type5, type6, type7, type8)
}
