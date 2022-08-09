package it.mdtorelli.fp.library
package console

def print[A](in: => A): IO[Unit] =
  IO.delay(scala.Console.print(in))

def println[A](in: => A): IO[Unit] =
  IO.delay(scala.Console.println(in))

def readLine: IO[String] =
  IO.delay(scala.io.StdIn.readLine())
