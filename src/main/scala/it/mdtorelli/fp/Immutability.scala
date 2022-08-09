package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import it.mdtorelli.fp.library.console.*
import util.*

private object ImmutabilityProgram:
  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance - amount)

    override def toString: String = s"ImmutableBankAccount($balance)"

  val value: IO[Unit] =
    for
      x1 <- IO.delay(ImmutableBankAccount(balance = 0))
      _  <- println("deposit 20")
      x2 <- IO.delay(x1.deposit(amount = 20))
      _  <- println("withdraw 5")
      x3 <- IO.delay(x2.withdraw(amount = 5))
      _  <- println(x3)
      _  <- println(x3.balance)
    yield ()

  //println(value)

object Immutability extends FunctionalApp:
  override def run: IO[Any] =
    for
      _ <- ImmutabilityProgram.value
      _ <- printSeparator()
      _ <- ImmutabilityProgram.value
      _ <- println(";-D")
    yield ()