package it.mdtorelli.fp.library

type Thunk[+A] = () => A

final case class IO[+A](unsafeRun: Thunk[A]):
  def map[B](f: A => B): IO[B] = IO.delay {
    f(unsafeRun())
  }

  override def toString: String = "<a description of a program>"

object IO:
  def delay[A](a: => A): IO[A] = IO(() => a)
