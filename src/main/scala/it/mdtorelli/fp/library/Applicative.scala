package it.mdtorelli.fp.library

trait Applicative[F[_]] extends Functor[F]:
  def pure[A](a: A): F[A]

  extension [A](fa: F[A]) def product[B](fb: F[B]): F[(A, B)]
