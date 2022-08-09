package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import it.mdtorelli.fp.library.console.*
import it.mdtorelli.fp.util.*

private object MutabilityProgram:
  final class MutableBankAccount(initialBalance: Int):
    private var currentBalance: Int = initialBalance

    def balance: Int = currentBalance

    def deposit(amount: Int): Unit = currentBalance += amount

    def withdraw(amount: Int): Unit = currentBalance -= amount

    override def toString: String = s"MutableBankAccount($balance)"

  val value: IO[Unit] =
    for
      x <- IO.delay(MutableBankAccount(initialBalance = 0))
      _ <- println("deposit 20")
      _ <- IO.delay(x.deposit(amount = 20))
      _ <- println("withdraw 5")
      _ <- IO.delay(x.withdraw(amount = 5))
      _ <- println(x)
      _ <- println(x.balance)
    yield ()

  //println(value)

object Mutability extends FunctionalApp:
  override def run: IO[Any] =
    for
      _ <- MutabilityProgram.value
      _ <- printSeparator()
      _ <- MutabilityProgram.value
      _ <- println(";-D")
    yield ()