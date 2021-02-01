package App01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MathCalTest {

	@Test
	void test() {
		//fail("Not yet implemented");
		assertEquals(8,new MathCal().add(3,5));
	}
	@Test
	void test1() {
		//fail("Not yet implemented");
		assertEquals(3,new MathCal().substract(6,3));
	}
}
