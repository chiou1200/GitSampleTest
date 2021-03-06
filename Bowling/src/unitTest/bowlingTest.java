package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class bowlingTest {
	
	Game bg;
	private void rollMany(int n, int pins) {
		for(int i = 0; i < n; i++)
			bg.roll(pins);
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
		rollSpace();
		bg.roll(3);
		rollMany(17,0);
		assertEquals(16,bg.score());
	}
	@Test
	public void testOneStrike() {
		rollStrike();
		bg.roll(3);
		bg.roll(4);
		rollMany(16,0);
		assertEquals(24,bg.score());
	}
	@Test
	public void testPerfectGame() {
		rollMany(12,10);
		assertEquals(300,bg.score());
	}
	@BeforeEach
	public void before() {
		bg = new Game();
	}
	@AfterEach
	public void after() {
		bg = null;
	}
	private void rollSpace() {
		bg.roll(5);
		bg.roll(5);
	}
	private void rollStrike() {
		bg.roll(10);
	}
}
class Game{
	private int score = 0;
	private int rolls[] = new int [21];
	private int currentRoll = 0; //打到第幾顆球
	public void roll(int pins) {
		score += pins;
		rolls[currentRoll++] = pins;
	}
	public int score() {
	int score = 0;
	int frameIndex = 0;
	for(int frame = 0; frame < 10; frame++) {//frame :第幾局
		if(isStike(frameIndex)) {
			score += 10 + strikeBouns(frameIndex);;
			frameIndex++;
		}else if(isSpare(frameIndex)) { //目前這顆球和下一顆球加起來是10分的話
			score += 10 + spareBouns(frameIndex);
			frameIndex+=2;
		}else {
			score += sumOfBallIndexFrame(frameIndex);
			frameIndex += 2;
		}
	}
	return score;
	}
	private int sumOfBallIndexFrame(int frameIndex) {
		return rolls[frameIndex] + rolls[frameIndex+1];
	}
	private int spareBouns(int frameIndex) {
		return rolls[frameIndex+2];
	}
	private int strikeBouns(int frameIndex) {
		return rolls[frameIndex+1] + rolls[frameIndex+2];
	}
	private boolean isSpare(int frameIndex) {
		return rolls[frameIndex] + rolls[frameIndex+1] == 10;
	}
	private boolean isStike(int frameIndex) {
		return rolls[frameIndex] == 10;
	}
}
	
