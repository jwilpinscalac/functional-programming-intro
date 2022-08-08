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

  val bankAccount = MutableBankAccount(initialBalance = 0)
    .tap(_.deposit(amount = 20))
    .tap(_.withdraw(amount = 5))
  println(bankAccount.balance)

  printSeparator()