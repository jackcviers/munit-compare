package example

import munit.FunSuite
import munit.Compare

class HelloSuite extends FunSuite {
  test("The Hello object") {
    assertEquals(Hello.greeting, "hello")
  }

  test("Thing(\"jack\") should equal Thing(\"jack\")".fail.only) { // this
    // test
    // fails
    // because
    // Thing
    // does
    // reference
    // comparison
    assertEquals(new Thing("jack"), new Thing("jack"))
  }
  test(
    "Thing(\"jack\") should equal Thing(\"jack\") with custom compare".only
  ) {
    implicit val compare: Compare[Thing, Thing] =
      new munit.Compare[Thing, Thing] {
        override def isEqual(obtained: Thing, expected: Thing): Boolean =
          if (obtained == expected) true else obtained.name == expected.name
      }
    assertEquals(new Thing("jack"), new Thing("jack"))
  }
}
