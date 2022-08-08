package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import it.mdtorelli.fp.util.*

@main def Mutability(args: String*): Unit =
  printSeparator()

  final class MutableBankAccount(initialBalance: Int):
    private var currentBalance: Int = initialBalance

    def balance: Int = currentBalance

    def deposit(amount: Int): Unit = currentBalance += amount

    def withdraw(amount: Int): Unit = currentBalance -= amount

    override def toString: String = s"MutableBankAccount($balance)"

  val bankAccount = IO.delay(MutableBankAccount(initialBalance = 0))
    .map { x =>
      println("deposit 20")
      x.deposit(amount = 20)
      x
    }
    .map { x =>
      println("withdraw 5")
      x.withdraw(amount = 5)
      x
    }
  println(bankAccount.map(_.balance))
  
  // is still referential transparent?
  // substitution model:

  printSeparator()
  
  val bankAccounts = (bankAccount, bankAccount)
  println(bankAccounts)

  printSeparator()

  val bankAccounts2 = (
    IO.delay(MutableBankAccount(initialBalance = 0)).map { x => println("deposit 20"); x.deposit(amount = 20); x }.map { x => println("withdraw 5"); x.withdraw(amount = 5); x },
    IO.delay(MutableBankAccount(initialBalance = 0)).map { x => println("deposit 20"); x.deposit(amount = 20); x }.map { x => println("withdraw 5"); x.withdraw(amount = 5); x }
  )
  println(bankAccounts2)

  // yay! they still behave the same!

  printSeparator()