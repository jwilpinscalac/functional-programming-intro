package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import it.mdtorelli.fp.library.console.FunctionalConsole

private trait ImmutabilityProgram[F[_]](using F: Monad[F], console: FunctionalConsole[F]):
  import console.*

  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount = ImmutableBankAccount(balance - amount)

    override def toString: String = s"ImmutableBankAccount($balance)"

  val program: F[Unit] =
    for
      x1 <- F.pure(ImmutableBankAccount(balance = 0))
      _  <- println("deposit 20")
      x2 <- F.pure(x1.deposit(amount = 20))
      _  <- println("withdraw 5")
      x3 <- F.pure(x2.withdraw(amount = 5))
      _  <- println(x3)
      _  <- println(x3.balance)
    yield ()

  //println(value)

object Immutability extends ImmutabilityProgram[IO] with FunctionalApp:
  import library.console.FunctionalConsole.*

  override def run: IO[Any] =
    for
      _ <- program
      _ <- printSeparator()
      _ <- program
      _ <- println(";-O")
    yield ()