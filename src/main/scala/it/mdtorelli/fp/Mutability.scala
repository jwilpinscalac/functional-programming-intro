package it.mdtorelli.fp

import it.mdtorelli.fp.util.*

@main def Mutability(args: String*): Unit =
  printSeparator()

  final class MutableBankAccount(initialBalance: Int):
    private var currentBalance: Int = initialBalance

    def balance: Int = currentBalance

    def deposit(amount: Int): Unit = currentBalance += amount

    def withdraw(amount: Int): Unit = currentBalance -= amount

  val bankAccount =
    val result = MutableBankAccount(initialBalance = 0)
    result.deposit(amount = 20)
    result.withdraw(amount = 5)
    result
  println(bankAccount.balance)

  printSeparator()