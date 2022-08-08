package it.mdtorelli.fp.util

extension [A](self: A)
  def pipe[B](f: A => B): B =
    f(self)

  def tap[B](f: A => B): A =
    f(self)
    self
