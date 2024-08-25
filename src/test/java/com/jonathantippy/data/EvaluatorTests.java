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
		assertEquals(2, Evaluator.evaluate("1+1"), "\"1+1\" should equal 2");
	}

	@Test
	@DisplayName("1 + 1 = 2")
	void add2() {
		assertEquals(2, Evaluator.evaluate("1 + 1"), "\"1 + 1\" should equal 2");
	}

	@Test
	@DisplayName("1 + 1 + 1 = 3")
	void add3() {
		assertEquals(3, Evaluator.evaluate("1 + 1 + 1"), "\"1 + 1 + 1\" should equal 3");
	}

	@Test
	@DisplayName("1 * 1 = 1")
	void mult1() {
		assertEquals(1, Evaluator.evaluate("1 * 1"), "\"1 * 1\" should equal 1");
	}

	@Test
	@DisplayName("2 + 7 * 2 + 1")
	void order1() {
		assertEquals(17, Evaluator.evaluate("2 + 7 * 2 + 1"), "\"2 + 7 * 2 + 1\" should equal 17");
	}

	@Test
	@DisplayName("2 + 7 * 2 + 1 * 8")
	void order2() {
		assertEquals(24, Evaluator.evaluate("2 + 7 * 2 + 1 * 8"), "\"2 + 7 * 2 + 1 * 8\" should equal 24");
	}

	@Test
	@DisplayName("5 - 2")
	void sub1() {
		assertEquals(3, Evaluator.evaluate("5 - 2"), "\"5 - 2\" should equal 3");
	}

	@Test
	@DisplayName("5 - 3 + 2 - 2 - 7 + 8 - 9 + 7 + 2 - 1")
	void order3() {
		assertEquals(2, Evaluator.evaluate("5 - 3 + 2 - 2 - 7 + 8 - 9 + 7 + 2 - 1"), "\"5 - 3 + 2 - 2 - 7 + 8 - 9 + 7 + 2 - 1\" should equal 2");
	}

	@Test
	@DisplayName("6 / 2")
	void div1() {
		assertEquals(3, Evaluator.evaluate("6 / 2"), "\"6 / 2\" should equal 3");
	}

	@Test
	@DisplayName("5 / 5 * 2")
	void order4() {
		assertEquals(2, Evaluator.evaluate("5 / 5 * 2"), "\"5 / 5 * 2\" should equal 2");
	}

	@Test
	@DisplayName("5 / 2")
	void break1() {
		assertEquals(5.0 / 2.0, Evaluator.evaluate("5 / 2"), "\"5 / 2\" should equal 5/2");
	}

	@Test
	@DisplayName("7 / 3 + 2 / 5")
	void break2() {
		assertEquals(41.0 / 15.0, Evaluator.evaluate("7 / 3 + 2 / 5"), "\"7 / 3 + 2 / 5\" should equal 41/15");
	}

	@Test
	@DisplayName("(5 + 5) / 2")
	void paren1() {
		assertEquals(5, Evaluator.evaluate("(5 + 5) / 2"), "\"(5 + 5) / 2\" should equal 5");
	}

	@Test
	@DisplayName("(5 * (5 + 5))")
	void paren2() {
		assertEquals(50, Evaluator.evaluate("(5 * (5 + 5))"), "\"(5 * (5 + 5))\" should equal 50");
	}

	@Test
	@DisplayName("-5 + -6")
	void unaryMinus1() {
		assertEquals(-11, Evaluator.evaluate("-5 + -6"), "\"-5 + -6\" should equal -11");
	}

	@Test
	@DisplayName("----7")
	void unaryMinus2() {
		assertEquals(7, Evaluator.evaluate("----7"), "\"----7\" should equal 7");
	}

	@Test
	@DisplayName("-(5+2)")
	void negateParens() {
		assertEquals(-7, Evaluator.evaluate("-(5+2)"), "\"-(5+2)\" should equal -7");
	}

	@Test
	@DisplayName("5-3=1+1")
	void equals1() {
		assertEquals(true, Evaluator.evaluateComparison("5-3=1+1"), "\"5-3=1+1\" should be true");
	}

	@Test
	@DisplayName("5-3 = 7-5 = 2*1+0")
	void equalslong1() {
        assertEquals(true, Evaluator.evaluateComparison("5-3 = 7-5 = 2*1+0"), "\"5-3 = 7-5 = 2*1+0\" should be true");
    }

    @Test
    @DisplayName("5>2>1")
    void gt1() {
        assertEquals(true, Evaluator.evaluateComparison("5>2>1"), "\"5>2>1\" should be true");
    }


/*
	@Test
	@DisplayName("7 / 3 + 2 / 0")
	void ex1() {

		ArithmeticException exception = assertThrows(ArithmeticException.class, () -> 
		{Evaluator.evaluate("7 / 3 + 2 / 0");});

		assertEquals(true, exception.getMessage().contains("by zero"));
	}
*/
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
