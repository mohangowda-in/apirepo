package org.restapi.restapitesting;

import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AllureTest1 {
	
	@Test
	@Parameters({"roleName"})
	public void testMethod1(String role) {
		Assert.assertTrue(true);
		System.out.println("this is not working method role == "+role);
	}

}
