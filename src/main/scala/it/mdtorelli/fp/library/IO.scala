package it.mdtorelli.fp.library

type Thunk[+A] = () => A

final case class IO[+A](unsafeRun: Thunk[A]):
  override def toString: String = "<a description of a program>"

object IO:
  def delay[A](a: => A): IO[A] = IO(() => a)
  
  val unit: IO[Unit] = IO(() => ())

  given Monad[IO] with
    extension [A](io: IO[A])
      def map[B](f: A => B): IO[B] = IO.delay {
        f(io.unsafeRun())
      }

      def flatMap[B](f: A => IO[B]): IO[B] = IO.delay {
        f(io.unsafeRun()).unsafeRun()
      }