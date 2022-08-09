package it.mdtorelli.fp.library

import it.mdtorelli.fp.library.console.FunctionalConsole.*

trait FunctionalApp extends App:
  def run: IO[Any]

  (for
    _ <- print(Console.BLUE)
    _ <- run
    _ <- print(Console.RESET)
  yield ()).unsafeRun()

