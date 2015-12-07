package pl.metastack.metarx

import minitest._

import scala.collection.mutable.ArrayBuffer

object SubscriberTest extends SimpleTestSuite {
  test(":=") {
    val x = Var(23)
    val y = Var(404)

    val subscriber = Subscriber[Int]()

    val values = ArrayBuffer.empty[Int]
    subscriber.attach(values += _)

    subscriber := x
    assertEquals(values, Seq(23))

    x := 42
    assertEquals(values, Seq(23, 42))

    subscriber := y
    assertEquals(values, Seq(23, 42, 404))

    x := 21
    assertEquals(values, Seq(23, 42, 404))

    subscriber.detach()

    y := 21
    assertEquals(values, Seq(23, 42, 404))
  }
}