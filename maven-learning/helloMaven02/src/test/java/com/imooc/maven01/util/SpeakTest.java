package com.imooc.maven02.util;

import org.junit.*;
import org.junit.Assert.*;

public class SpeakTest{
	@Test
	public void testsayHi(){
		Assert.assertEquals("HelloWorld",new Speak().sayHi());
	}
}