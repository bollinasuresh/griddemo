package com.trishana.griddemo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

//https://www.seleniumhq.org/docs/07_selenium_grid.jsp

//project containing 200 testcases
// sequentially -- time required to complete the suite ?
// 200 testcases in different browsers [IE, Chrome, Firefox]
// 200 testcases with different browser versions [chrome68, chrome72, firefox40, firefox60]
// 200 testcases in different operating systesms [windows, linux, mac]

//run
// 200 testcases once with each browser
// 200 testcases in different machines [[windows, linux, mac] you have to move the complete project to individual machines]
// 200 testcases with different chrome versions in different machines

//Grid architecture 
//To run your tests against multiple browsers, multiple versions of browser, and browsers running on different operating systems.
//To reduce the time it takes for the test suite to complete a test pass.

//Grid
// hub -- local machine
//start the hub
// node -- different machines [different computers, laptops]
//note: local machine can act as HUB and one Node
// configure node to hub
// launch the test cases

//steps
//1) start selenium hub
//by default hub will start on port 4444
//java -jar selenium-server-standalone-3.141.59.jar -role hub 

//if you want to start on different port
//java -jar selenium-server-standalone-3.141.59.jar -role hub  -port 4445

//2) start selenium node

//java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone-3.141.59.jar -role node -hub <url> http://localhost:4444/grid/register/

//if you want to start on different port
//java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone-3.141.59.jar -role node -hub <url> -port 5556

//3) implement testcases
//below are sample testcases

public class GridTest {
	@Test
	public void googleTest() throws MalformedURLException {

		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setBrowserName("firefox");
		dc.setPlatform(Platform.WINDOWS);
		dc.setJavascriptEnabled(true);
		// grid url
		// desired capabilities
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		driver.get("https://www.google.com");
		System.out.println(driver.getTitle());
		driver.quit();
	}

	@Test
	public void wikiTest() throws MalformedURLException {

		DesiredCapabilities dc = DesiredCapabilities.chrome();
		dc.setBrowserName("chrome");
		dc.setPlatform(Platform.WINDOWS);
		dc.setJavascriptEnabled(true);
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		driver.get("https://www.wikipedia.com");
		System.out.println(driver.getTitle());
		driver.quit();
	}

}
