package it.mdtorelli.fp

import it.mdtorelli.fp.library.*
import it.mdtorelli.fp.library.console.FunctionalConsole

private trait MutabilityProgram[F[_]](using F: Monad[F], console: FunctionalConsole[F]):
  import console.*

  final class MutableBankAccount(initialBalance: Int):
    private var currentBalance: Int = initialBalance

    def balance: Int = currentBalance

    def deposit(amount: Int): Unit = currentBalance += amount

    def withdraw(amount: Int): Unit = currentBalance -= amount

    override def toString: String = s"MutableBankAccount($balance)"

  val program: F[Unit] =
    for
      x <- F.pure(MutableBankAccount(0))
      _ <- println("deposit 20")
      _ <- F.pure(x.deposit(amount = 20))
      _ <- println("withdraw 5")
      _ <- F.pure(x.withdraw(amount = 5))
      _ <- println(x)
      _ <- println(x.balance)
    yield ()

  //println(value)

object Mutability extends MutabilityProgram[IO] with FunctionalApp:
  import library.console.FunctionalConsole.*

  override def run: IO[Any] =
    for
      _ <- program
      _ <- printSeparator()
      _ <- program
      _ <- println(";-O")
    yield ()