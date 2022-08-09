package it.mdtorelli.fp.library

trait Monad[+A] extends Functor[A]:
  def flatMap[B](f: A => IO[B]): IO[B]
