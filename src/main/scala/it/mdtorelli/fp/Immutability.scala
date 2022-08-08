package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import util.*
import scala.util.chaining.*

@main def Immutability(args: String*): Unit =
  printSeparator()

  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance - amount)

    override def toString: String = s"ImmutableBankAccount($balance)"

  val bankAccount = ImmutableBankAccount(balance = 0)
    .pipe { x =>
      IO.delay {
        println("deposit 20")
      }
      x.deposit(amount = 20)
    }.pipe { x =>
      IO.delay {
        println("withdraw 5")
      }
      x.withdraw(amount = 5)
    }
  println(bankAccount.balance)

  // is still referential transparent?
  // substitution model:

  printSeparator()

  val bankAccounts = (bankAccount, bankAccount)
  println(bankAccounts)

  printSeparator()

  val bankAccounts2 = (
    ImmutableBankAccount(balance = 0).pipe { x => IO.delay { println("deposit 20") }; x.deposit(amount = 20) }.pipe { x => IO.delay { println("withdraw 5") }; x.withdraw(amount = 5) },
    ImmutableBankAccount(balance = 0).pipe { x => IO.delay { println("deposit 20") }; x.deposit(amount = 20) }.pipe { x => IO.delay { println("withdraw 5") }; x.withdraw(amount = 5) },
  )
  println(bankAccounts2)

  // yay! they behave the same now!

  printSeparator()