package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import util.*

private object ImmutabilityProgram:
  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance - amount)

    override def toString: String = s"ImmutableBankAccount($balance)"

  val value: IO[Unit] =
    for
      x1 <- IO.delay(ImmutableBankAccount(balance = 0))
      x2 <- IO.delay { println("deposit 20"); x1.deposit(amount = 20) }
      x3 <- IO.delay { println("withdraw 5"); x2.withdraw(amount = 5) }
      _ <- IO.delay { println(x3); println(x3.balance) }
    yield ()

  //println(value)

object Immutability extends FunctionalApp:
  override def run: IO[Any] =
    for
      _ <- ImmutabilityProgram.value
      _ <- IO.delay(printSeparator())
      _ <- ImmutabilityProgram.value
      _ <- IO.delay(println(":-D"))
    yield ()