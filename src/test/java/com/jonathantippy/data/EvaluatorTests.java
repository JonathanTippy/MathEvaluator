package com.jonathantippy.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	void mult0() {
		assertEquals(1, Evaluator.evaluate("1 * 1"), "\"1 * 1\" should equal 1");
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
