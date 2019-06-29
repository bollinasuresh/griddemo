package com.trishana.gridtests;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

public class WikiTest extends GridFactory {

	@Test
	public void wikiTest() throws MalformedURLException {
		driver.get("https://www.wikipedia.com");
		System.out.println(driver.getTitle());
	}

}
