package ru.wow42.tasks.futures

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

case class FutureTask[A]() {
  def traverse(list: List[Future[A]]): Future[(List[Throwable], List[A])] = {
    val seq: Future[List[Try[A]]] = Future.sequence(list.map(_.transform(Success(_))))
    val successes: Future[List[A]] = seq.map(_.collect { case Success(x) => x })
    val failures: Future[List[Throwable]] = seq.map(_.collect { case Failure(x) => x })
    val res: Future[(List[Throwable], List[A])] = failures.zip(successes)
    res
  }
}
