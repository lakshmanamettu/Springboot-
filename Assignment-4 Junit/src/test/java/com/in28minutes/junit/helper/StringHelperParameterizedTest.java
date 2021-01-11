package com.in28minutes.junit.helper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class StringHelperParameterizedTest {
  
	String input;
	String expetedoutputs;
  StringHelper helper=new StringHelper();
  
  @Parameters
  public static Collection <String[]> testconditions(){
	  String expetedoutputs[][]= {{"AACD", "CD"},
			  {"ACD", "CD"}};
	  return Arrays.asList(expetedoutputs);
  }

	@Test
	public void testtruncateAInFirst2Positions() {
       assertEquals(expetedoutputs,helper.truncateAInFirst2Positions(input));	
	}

	public  StringHelperParameterizedTest(String input,String expetedoutputs) {
		this.input=input;
		this.expetedoutputs=expetedoutputs;
	}
}
	
