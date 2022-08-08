package it.mdtorelli.fp

import it.mdtorelli.fp.util.*
import scala.util.chaining.*

@main def Mutability(args: String*): Unit =
  printSeparator()

  final class MutableBankAccount(initialBalance: Int):
    private var currentBalance: Int = initialBalance

    def balance: Int = currentBalance

    def deposit(amount: Int): Unit = currentBalance += amount

    def withdraw(amount: Int): Unit = currentBalance -= amount

    override def toString: String = s"MutableBankAccount($balance)"

  val bankAccount = MutableBankAccount(initialBalance = 0)
    .tap { x =>
      println("deposit 20")
      x.deposit(amount = 20)
    }
    .tap { x =>
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
    MutableBankAccount(initialBalance = 0).tap { x => println("deposit 20"); x.deposit(amount = 20) }.tap { x => println("withdraw 5"); x.withdraw(amount = 5) },
    MutableBankAccount(initialBalance = 0).tap { x => println("deposit 20"); x.deposit(amount = 20) }.tap { x => println("withdraw 5"); x.withdraw(amount = 5) }
  )
  println(bankAccounts2)

  // uh-oh! the behavior is not the same!

  printSeparator()