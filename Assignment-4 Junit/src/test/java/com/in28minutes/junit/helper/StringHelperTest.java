package com.in28minutes.junit.helper;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringHelperTest {
	
	StringHelper helper=new StringHelper();

	@Test
	public void testtruncateAInFirst2Positions_Ainfirsttwo() {
		
		//String actual=helper.truncateAInFirst2Positions("AACD");
		//String expected="CD";
		assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
		
	}
	
	@Test
	public void testtruncateAInFirst2Positions_Ainfirstposition() {
		//StringHelper helper=new StringHelper();
   
		assertEquals("CDF", helper.truncateAInFirst2Positions("ACDF"));
	}
	
	@Test
	public void testareFirstAndLastTwoCharactersTheSame_basicscenario() {
		assertEquals(false, helper.areFirstAndLastTwoCharactersTheSame("ABCD"));
	}
	
	@Test
	public void testareFirstAndLastTwoCharactersTheSame_secondscenario() {
		assertFalse(helper.areFirstAndLastTwoCharactersTheSame("ABBA"));
	}
	
	@Test
	public void testareFirstAndLastTwoCharactersTheSame_thirdscenario() {
		assertTrue(helper.areFirstAndLastTwoCharactersTheSame("AB"));
	}
}
	