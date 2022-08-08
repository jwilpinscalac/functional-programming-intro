package it.mdtorelli.fp

import it.mdtorelli.fp.util.*

@main def Mutability(args: String*): Unit =
  printSeparator()

  final class MutableBankAccount(initialBalance: Int):
    private var currentBalance: Int = initialBalance

    def balance: Int = currentBalance

    def deposit(amount: Int): Unit = currentBalance += amount

    def withdraw(amount: Int): Unit = currentBalance -= amount

  val firstBankAccount = MutableBankAccount(initialBalance = 0)
  println(firstBankAccount.balance)

  firstBankAccount.deposit(amount = 20)
  println(firstBankAccount.balance)

  val _ = firstBankAccount.withdraw(amount = 5)
  println(firstBankAccount.balance)

  printSeparator()