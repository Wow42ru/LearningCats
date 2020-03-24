import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
case class Test[A]() {
  def traverse(list: List[Future[A]]): Future[(List[Throwable], List[A])] = {
    val seq: Future[List[Try[A]]] = Future.sequence(list.map(_.transform(Success(_))))
    val successes: Future[List[A]] = seq.map(_.collect { case Success(x) => x })
    val failures: Future[List[Throwable]] = seq.map(_.collect { case Failure(x) => x })
    val result: Future[(List[Throwable], List[A])] = failures.zip(successes)
    result
  }
}

val t =Test.apply().traverse(List[Future[Int]](Future(3),Future(2),Future(throw new Exception)))