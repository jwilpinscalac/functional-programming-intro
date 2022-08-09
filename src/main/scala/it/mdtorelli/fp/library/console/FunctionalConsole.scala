package it.mdtorelli.fp.library
package console

def println[A](in: => A): IO[Unit] =
  IO.delay(scala.Console.println(in))

def readLine: IO[String] =
  IO.delay(scala.io.StdIn.readLine())
