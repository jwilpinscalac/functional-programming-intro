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
      IO.delay {
        println("deposit 20")
        x.deposit(amount = 20)
      }
    }.map { x =>
      IO.delay {
        println("withdraw 5")
        x.unsafeRun().withdraw(amount = 5)
      }
    }.map { x =>
      IO.delay {
        val newX = x.unsafeRun()
        println(newX)
        println(newX.balance)
      }
    }

  //println(value)

object Immutability extends FunctionalApp:
  override def run: IO[Any] = ImmutabilityProgram.value
    .map(_ => IO.delay(printSeparator()))
    .map(_ => ImmutabilityProgram.value)
    .map(_ => IO.delay(println(":-(")))