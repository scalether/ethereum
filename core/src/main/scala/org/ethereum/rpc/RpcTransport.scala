package org.ethereum.rpc

import org.ethereum.rpc.domain.{Request, Response}

trait RpcTransport[F[_]] {
  def send[T: Manifest](request: Request): F[Response[T]]
}
