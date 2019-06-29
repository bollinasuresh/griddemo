package com.trishana.gridtests;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

public class GoogleTest extends GridFactory {
	@Test
	public void googleTest() throws MalformedURLException {
		driver.get("https://www.google.com");
		System.out.println(driver.getTitle());
	}
}
