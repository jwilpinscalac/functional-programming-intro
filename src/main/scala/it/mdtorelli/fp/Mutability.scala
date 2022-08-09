package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import it.mdtorelli.fp.library.console.*
import it.mdtorelli.fp.util.*

private object MutabilityProgram:
  final class MutableBankAccount(initialBalance: Int):
    private var currentBalance: Int =
      Console.println("launching rockets to space")
      initialBalance

    def balance: Int = currentBalance

    def deposit(amount: Int): Unit = currentBalance += amount

    def withdraw(amount: Int): Unit = currentBalance -= amount

    override def toString: String = s"MutableBankAccount($balance)"

  val tooEarly = MutableBankAccount(0)

  val value: IO[Unit] =
    for
      _ <- println("starting...")
      x = tooEarly
      _ <- println("deposit 20")
      _ <- IO.delay(x.deposit(amount = 20))
      _ <- println("withdraw 5")
      _ <- IO.delay(x.withdraw(amount = 5))
      _ <- println(x)
      _ <- println(x.balance)
    yield ()

  //println(value)

object Mutability extends FunctionalApp:
  MutabilityProgram.value
  override def run: IO[Any] = IO.unit
//    for
//      _ <- MutabilityProgram.value
//      _ <- printSeparator()
//      _ <- MutabilityProgram.value
//      _ <- println(";-D")
//    yield ()