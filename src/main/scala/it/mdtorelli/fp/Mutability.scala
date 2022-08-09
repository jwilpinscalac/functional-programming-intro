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

  val value: IO[Unit] = IO.delay(MutableBankAccount(initialBalance = 0))
    .flatMap { x =>
      IO.delay {
        println("deposit 20")
        x.deposit(amount = 20)
        x
      }
    }
    .flatMap { x =>
      IO.delay {
        println("withdraw 5")
        x.withdraw(amount = 5)
        x
      }
    }.flatMap { x =>
      IO.delay {
        println(x)
        println(x.balance)
      }
    }

  //println(value)

object Mutability extends FunctionalApp:
  override def run: IO[Any] = MutabilityProgram.value
    .flatMap(_ => IO.delay(printSeparator()))
    .flatMap(_ => MutabilityProgram.value)
    .flatMap(_ => IO.delay(println(":-)")))