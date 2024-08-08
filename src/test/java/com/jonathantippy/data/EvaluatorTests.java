package com.jonathantippy.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jonathantippy.data.Evaluator;

class EvaluatorTests {

	@Test
	@DisplayName("1+1 = 2")
	void add1() {
		assertEquals(new Rational(2), Evaluator.evaluate("1+1"), "\"1+1\" should equal 2");
	}

	@Test
	@DisplayName("1 + 1 = 2")
	void add2() {
		assertEquals(new Rational(2), Evaluator.evaluate("1 + 1"), "\"1 + 1\" should equal 2");
	}

	@Test
	@DisplayName("1 + 1 + 1 = 3")
	void add3() {
		assertEquals(new Rational(3), Evaluator.evaluate("1 + 1 + 1"), "\"1 + 1 + 1\" should equal 3");
	}

	@Test
	@DisplayName("1 * 1 = 1")
	void mult1() {
		assertEquals(new Rational(1), Evaluator.evaluate("1 * 1"), "\"1 * 1\" should equal 1");
	}

	@Test
	@DisplayName("2 + 7 * 2 + 1")
	void order1() {
		assertEquals(new Rational(17), Evaluator.evaluate("2 + 7 * 2 + 1"), "\"2 + 7 * 2 + 1\" should equal 17");
	}
	
	@Test
	@DisplayName("2 + 7 * 2 + 1 * 8")
	void order2() {
		assertEquals(new Rational(24), Evaluator.evaluate("2 + 7 * 2 + 1 * 8"), "\"2 + 7 * 2 + 1 * 8\" should equal 24");
	}

	@Test
	@DisplayName("5 - 2")
	void sub1() {
		assertEquals(new Rational(3), Evaluator.evaluate("5 - 2"), "\"5 - 2\" should equal 3");
	}

	@Test
	@DisplayName("5 - 3 + 2 - 2 - 7 + 8 - 9 + 7 + 2 - 1")
	void order3() {
		assertEquals(new Rational(2), Evaluator.evaluate("5 - 3 + 2 - 2 - 7 + 8 - 9 + 7 + 2 - 1"), "\"5 - 3 + 2 - 2 - 7 + 8 - 9 + 7 + 2 - 1\" should equal 2");
	}

	@Test
	@DisplayName("6 / 2")
	void div1() {
		assertEquals(new Rational(3), Evaluator.evaluate("6 / 2"), "\"6 / 2\" should equal 3");
	}

	@Test
	@DisplayName("5 / 5 * 2")
	void order4() {
		assertEquals(new Rational(2), Evaluator.evaluate("5 / 5 * 2"), "\"5 / 5 * 2\" should equal 2");
	}

	@Test
	@DisplayName("5 / 2")
	void break1() {
		assertEquals(new Rational(5, 2), Evaluator.evaluate("5 / 2"), "\"5 / 2\" should equal 5/2");
	}

	@Test
	@DisplayName("7 / 3 + 2 / 5")
	void break2() {
		assertEquals(new Rational(41, 15), Evaluator.evaluate("7 / 3 + 2 / 5"), "\"7 / 3 + 2 / 5\" should equal 41/15");
	}

	@Test
	@DisplayName("7 / 3 + 2 / 0")
	void ex1() {

		ArithmeticException exception = assertThrows(ArithmeticException.class, () -> 
		{Evaluator.evaluate("7 / 3 + 2 / 0");});

		assertEquals(true, exception.getMessage().contains("by zero"));
	}

	@Test
	@DisplayName("test bint string")
	void bint1() {
		BalloonInteger bint = BalloonInteger.parseBalloonInt("1");
		assertEquals("1", bint.toString());
	}

	@Test
	@DisplayName("test bint string 2")
	void bint2() {
		BalloonInteger bint = BalloonInteger.parseBalloonInt("100");
		assertEquals("100", bint.toString());
	}
	
	@Test
	@DisplayName("test bint string 3")
	void bint3() {
		BalloonInteger bint = BalloonInteger.parseBalloonInt("2147483647");
		assertEquals("2147483647", bint.toString());
	}

	@Test
	@DisplayName("test bint string 4")
	void bint4() {
		BalloonInteger bint = BalloonInteger.parseBalloonInt("2147483653");
		assertEquals("2147483653", bint.toString());
	}


	/*
	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({
			"0,    1,   1",
			"1,    2,   3",
			"49,  51, 100",
			"1,  100, 101"
	})
	void add(String first, String second, int expectedResult) {
		assertEquals(expectedResult, Evaluator.evaluate(first + "+" + second),
				() -> first + " + " + second + " should equal " + expectedResult);
	}
	*/
}
