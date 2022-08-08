package it.mdtorelli.fp

import util.*

@main def Immutability(args: String*): Unit =
  printSeparator()

  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance - amount)

  val bankAccount = ImmutableBankAccount(balance = 0)
    .deposit(amount = 20)
    .withdraw(amount = 5)
  println(bankAccount.balance)

  printSeparator()