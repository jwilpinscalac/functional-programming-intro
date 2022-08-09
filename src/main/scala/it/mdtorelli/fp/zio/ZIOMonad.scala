package it.mdtorelli.fp.zio

import it.mdtorelli.fp.library.Monad
import zio.*

object ZIOMonad:
  given Monad[Task] with
    override def pure[A](a: A): Task[A] = ZIO.attempt(a)

    extension[A] (io: Task[A])
      def product[B](fb: Task[B]): Task[(A, B)] =
        for
          a <- io
          b <- fb
        yield (a, b)

      def map[B](f: A => B): Task[B] = io.map(f)

      def flatMap[B](f: A => Task[B]): Task[B] = io.flatMap(f)