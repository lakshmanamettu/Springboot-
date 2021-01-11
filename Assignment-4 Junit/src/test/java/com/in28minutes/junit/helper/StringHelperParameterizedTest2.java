package com.in28minutes.junit.helper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class StringHelperParameterizedTest2 {
	String input;
	StringHelper helper=new StringHelper();

	boolean expectedoutput;
	
	@Parameters
	 public static Collection <boolean[]> testconditions(){
		  boolean expetedoutputs[][]= {{"ABAB", true},
				  {"ABCD",false}};
		  return Arrays.asList(expetedoutputs);
	  }
	
	
	@Test
	public void test() {
		assertEquals(expectedoutput, helper.areFirstAndLastTwoCharactersTheSame(input));
		//fail("Not yet implemented");
	}
  public StringHelperParameterizedTest2(String input,boolean expectedoutput) {
	  this.input=input;
	  this.expectedoutput=expectedoutput;
  }
}
