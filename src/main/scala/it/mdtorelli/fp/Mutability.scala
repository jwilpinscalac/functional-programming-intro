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
    .map { x =>
      IO.delay {
        println("deposit 20")
        x.deposit(amount = 20)
        x
      }
    }
    .map { x =>
      IO.delay {
        println("withdraw 5")
        val newX = x.unsafeRun()
        newX.withdraw(amount = 5)
        newX
      }
    }.map { x =>
      IO.delay {
        val newX = x.unsafeRun()
        println(newX)
        println(newX.balance)
      }
    }

  //println(value)

object Mutability extends FunctionalApp:
  override def run: IO[Any] = MutabilityProgram.value
    .map(_ => IO.delay(printSeparator()))
    .map(_ => MutabilityProgram.value)
    .map(_ => IO.delay(println(":-(")))