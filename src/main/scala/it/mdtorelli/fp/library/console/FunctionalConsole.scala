package it.mdtorelli.fp.library
package console

object FunctionalConsole:
  def print[A](in: => A): IO[Unit] =
    IO.delay(scala.Console.print(in))

  def println[A](in: => A): IO[Unit] =
    IO.delay(scala.Console.println(in))

  def printSeparator(): IO[Unit] = println("-" * 50)

  def readLine: IO[String] =
    IO.delay(scala.io.StdIn.readLine())

  given FunctionalConsole[IO] with
    export FunctionalConsole.*

trait FunctionalConsole[F[_]]:
  def print[A](in: => A): F[Unit]

  def println[A](in: => A): F[Unit]

  def printSeparator(): F[Unit]

  def readLine: F[String]