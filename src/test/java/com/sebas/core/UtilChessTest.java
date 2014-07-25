package com.sebas.core;

import junit.framework.TestCase;

public class UtilChessTest extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		
	}
	
	public void testGetRandomValue() {
		boolean flag = false;
		int value = UtilChess.getRandomValue(4);
		System.out.println(value);
		if (value < 4 && value >= 0) flag = true;
		assertEquals(true, flag);
	}

}
