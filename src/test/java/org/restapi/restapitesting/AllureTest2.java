package org.restapi.restapitesting;

import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AllureTest2 {
	@Test
	@Parameters({"roleName"})
	public void testMethod2(String role) {
		Assert.assertTrue(true);
		System.out.println("This is working method == "+role);
	}
}
