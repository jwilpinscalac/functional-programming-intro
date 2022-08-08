package it.mdtorelli.fp

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
      println("deposit 20")
      x.deposit(amount = 20)
    }.pipe { x =>
      println("withdraw 5")
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
    ImmutableBankAccount(balance = 0).pipe { x => println("deposit 20"); x.deposit(amount = 20) }.pipe { x => println("withdraw 5"); x.withdraw(amount = 5) },
    ImmutableBankAccount(balance = 0).pipe { x => println("deposit 20"); x.deposit(amount = 20) }.pipe { x => println("withdraw 5"); x.withdraw(amount = 5) }
  )
  println(bankAccounts2)

  // uh-oh! the behavior is not the same!

  printSeparator()