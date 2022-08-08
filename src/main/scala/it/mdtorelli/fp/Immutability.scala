package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import util.*

@main def Immutability(args: String*): Unit =
  printSeparator()

  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance - amount)

    override def toString: String = s"ImmutableBankAccount($balance)"

  val bankAccount = IO.delay(ImmutableBankAccount(balance = 0))
    .map { x =>
      println("deposit 20")
      x.deposit(amount = 20)
    }.map { x =>
      println("withdraw 5")
      x.withdraw(amount = 5)
    }
  println(bankAccount.map(_.balance))

  // is still referential transparent?
  // substitution model:

  printSeparator()

  val bankAccounts = (bankAccount, bankAccount)
  println(bankAccounts)

  printSeparator()

  val bankAccounts2 = (
    IO.delay(ImmutableBankAccount(balance = 0)).map { x => println("deposit 20"); x.deposit(amount = 20) }.map { x => println("withdraw 5"); x.withdraw(amount = 5) },
    IO.delay(ImmutableBankAccount(balance = 0)).map { x => println("deposit 20"); x.deposit(amount = 20) }.map { x => println("withdraw 5"); x.withdraw(amount = 5) }
  )
  println(bankAccounts2)

  // yay! they still behave the same!

  printSeparator()