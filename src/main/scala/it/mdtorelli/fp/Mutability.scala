package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
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
      _ <- IO.delay { println("deposit 20"); x.deposit(amount = 20) }
      _ <- IO.delay { println("withdraw 5"); x.withdraw(amount = 5) }
      _ <- IO.delay { println(x); println(x.balance) }
    yield ()

  //println(value)

object Mutability extends FunctionalApp:
  override def run: IO[Any] =
    for
      _ <- MutabilityProgram.value
      _ <- IO.delay(printSeparator())
      _ <- MutabilityProgram.value
      _ <- IO.delay(println(":-D"))
    yield ()