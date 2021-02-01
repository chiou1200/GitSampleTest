package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BowlingGameTest {
	Game bg = new Game();
	private void rollMany(int n, int pins) {
		for(int i=0;i<n;i++) {
			bg.roll(pins);
		}
	}
	@BeforeEach
	public void before() {
		bg = new Game();
	}
	@AfterEach
	public void after() {
		bg = null;
	}
	@Test
	public void testGutter() {
		rollMany(20,0);
		assertEquals(0,bg.score());
	}
	@Test 
	public void testAllOnes() {
		rollMany(20,1);
		assertEquals(20,bg.score());
	}
	@Test 
	public void testOneSpace() {
		rollSpare();
		bg.roll(3); 
		rollMany(17,0);
		assertEquals(16,bg.score());
	}
	@Test 
	public void testOneStrike() {
		rollStrike();    //strike
		bg.roll(3);
		bg.roll(4);
		rollMany(16,0);
		assertEquals(24,bg.score());
	}
	@Test 
	public void testPerfectGame() {
		rollMany(12,10);
		assertEquals(300, bg.score());
	}
	private void rollStrike() {
		bg.roll(10);
	} 
	private void rollSpare() {
		bg.roll(5);
		bg.roll(5);
	}
}
class Game{
	private int rolls[] = new int [21];
	private int currentRoll = 0;
	public void roll(int pins) {
		rolls[currentRoll++] = pins;
	}
	public int score() {
		int score = 0;
		int frameIndex = 0;
		for(int frame = 0; frame < 10; frame++) {
			if (isStrike(frameIndex)) { // strike
				score += 10 +strikeBonus(frameIndex);
						frameIndex++;
			}else if(isSpare(frameIndex)) {
				score += 10 + spareBonus(frameIndex);
				frameIndex += 2;
			}else {
				score += sumOfBallsIndexFrame(frameIndex);
				frameIndex += 2;
			}
		}
		return score;
	}
	private boolean isStrike(int frameIndex) {
		return rolls[frameIndex] == 10;
	}
	private int sumOfBallsIndexFrame(int frameIndex) {
		return rolls[frameIndex] + rolls[frameIndex + 1];
	}
	private int spareBonus(int frameIndex) {
		return rolls[frameIndex + 2];
	}
	private int strikeBonus(int frameIndex) {
		return rolls[frameIndex+1]+rolls[frameIndex+2];
	}
	private boolean isSpare(int frameIndex) {
		return rolls[frameIndex] + 
			   rolls[frameIndex + 1] == 10;
	}
}

