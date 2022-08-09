package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import util.*

private object ImmutabilityProgram:
  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance - amount)

    override def toString: String = s"ImmutableBankAccount($balance)"

  val value: IO[Unit] = IO.delay(ImmutableBankAccount(balance = 0))
    .flatMap { x =>
      IO.delay {
        println("deposit 20")
        x.deposit(amount = 20)
      }
    }.flatMap { x =>
      IO.delay {
        println("withdraw 5")
        x.withdraw(amount = 5)
      }
    }.flatMap { x =>
      IO.delay {
        println(x)
        println(x.balance)
      }
    }

  //println(value)

object Immutability extends FunctionalApp:
  override def run: IO[Any] = ImmutabilityProgram.value
    .flatMap(_ => IO.delay(printSeparator()))
    .flatMap(_ => ImmutabilityProgram.value)
    .flatMap(_ => IO.delay(println(":-)")))