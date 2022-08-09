package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import util.*

private object ImmutabilityProgram:
  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance - amount)

    override def toString: String = s"ImmutableBankAccount($balance)"

  val value: IO[Unit] = IO.delay(ImmutableBankAccount(balance = 0))
    .map { x =>
      println("deposit 20")
      x.deposit(amount = 20)
    }.map { x =>
      println("withdraw 5")
      x.withdraw(amount = 5)
    }.map { x =>
      println(x)
      println(x.balance)
    }

  //println(value)

object Immutability extends FunctionalApp:
  override def run: IO[Any] = ImmutabilityProgram.value
    .map(_ => printSeparator())
    .map(_ => ImmutabilityProgram.value)
    .map(_ => println(":-("))