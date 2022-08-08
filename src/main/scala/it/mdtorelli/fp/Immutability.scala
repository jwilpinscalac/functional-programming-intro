package it.mdtorelli.fp

import util.*

@main def Immutability(args: String*): Unit =
  printSeparator()

  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance - amount)

  val firstBankAccount = ImmutableBankAccount(balance = 0)
  println(firstBankAccount.balance)

  val secondBankAccount = firstBankAccount.deposit(amount = 20)
  println(secondBankAccount.balance)

  val thirdBankAccount = secondBankAccount.withdraw(amount = 5)
  println(thirdBankAccount.balance)

  printSeparator()