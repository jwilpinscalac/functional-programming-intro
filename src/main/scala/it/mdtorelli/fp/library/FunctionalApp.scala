package it.mdtorelli.fp.library

trait FunctionalApp extends App:
  def run: IO[Any]
  
  print(Console.BLUE)
  run.unsafeRun()
  print(Console.RESET)
