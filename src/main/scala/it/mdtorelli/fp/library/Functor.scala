package it.mdtorelli.fp.library

trait Functor[+A]:
  def map[B](f: A => B): IO[B]
