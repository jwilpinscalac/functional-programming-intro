package it.mdtorelli.fp.zio.console

import it.mdtorelli.fp.library.console.FunctionalConsole
import zio.*

object ZIOFunctionalConsole:
  def print[A](in: => A): Task[Unit] =
    ZIO.attempt(scala.Console.print(in))

  def println[A](in: => A): Task[Unit] =
    ZIO.attempt(scala.Console.println(in))

  def printSeparator(): Task[Unit] = println("-" * 50)

  def readLine: Task[String] =
    ZIO.attempt(scala.io.StdIn.readLine())

  given FunctionalConsole[Task] with
    export ZIOFunctionalConsole.*
