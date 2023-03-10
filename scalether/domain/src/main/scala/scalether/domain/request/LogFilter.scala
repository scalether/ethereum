package scalether.domain.request

import org.ethereum.rpc.domain.{Bytes, Word}
import scalether.domain.Address

import scala.annotation.varargs
import scala.language.implicitConversions

case class LogFilter(topics: List[TopicFilter] = Nil,
                     address: List[Address] = Nil,
                     fromBlock: String = "latest",
                     toBlock: String = "latest",
                     blockHash: Word = null) {
  @varargs def address(address: Address*): LogFilter = copy(address = address.toList)

  def blocks(fromBlock: String, toBlock: String): LogFilter =
    this.copy(fromBlock = fromBlock, toBlock = toBlock)

  def blockHash(blockHash: Word): LogFilter =
    this.copy(blockHash = blockHash, toBlock = null, fromBlock = null)
}

object LogFilter {
  @varargs def apply(topics: TopicFilter*): LogFilter = LogFilter(topics.toList)
}

sealed trait TopicFilter {

}

object TopicFilter {
  implicit def simple(word: Bytes): SimpleTopicFilter = if (word != null) new SimpleTopicFilter(word) else null

  @varargs def or(word: Word*): OrTopicFilter = OrTopicFilter(word.toList)
}

case class SimpleTopicFilter(word: Word) extends TopicFilter {
  def this(bytes: Bytes) {
    this(Word(bytes))
  }
}

case class OrTopicFilter(words: List[Word]) extends TopicFilter

object OrTopicFilter {
  @varargs def apply(words: Word*): OrTopicFilter = OrTopicFilter(words.toList)
}