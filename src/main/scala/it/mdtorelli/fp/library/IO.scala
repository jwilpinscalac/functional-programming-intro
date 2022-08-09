package it.mdtorelli.fp.library

type Thunk[+A] = () => A

final case class IO[+A](unsafeRun: Thunk[A]):
  override def toString: String = "<a description of a program>"

object IO:
  def delay[A](a: => A): IO[A] = IO(() => a)
  
  val unit: IO[Unit] = IO(() => ())

  given Monad[IO] with
    override def pure[A](a: A): IO[A] = IO.delay(a)

    extension [A](io: IO[A])
      def product[B](fb: IO[B]): IO[(A, B)] =
        for
          a <- io
          b <- fb
        yield (a, b)

      def map[B](f: A => B): IO[B] = IO.delay {
        f(io.unsafeRun())
      }

      def flatMap[B](f: A => IO[B]): IO[B] = IO.delay {
        f(io.unsafeRun()).unsafeRun()
      }