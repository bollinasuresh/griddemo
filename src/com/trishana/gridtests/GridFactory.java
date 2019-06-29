package com.trishana.gridtests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class GridFactory {

	WebDriver driver;
	DesiredCapabilities dc;

	@BeforeClass
	@Parameters({ "browser", "grid" })
	public void init(@Optional("chrome") String browser, @Optional("flase") String grid) throws MalformedURLException {

		driver = getDriver(browser, grid);

	}

	private WebDriver getDriver(String browser, String grid) throws MalformedURLException {
		browser = browser.toLowerCase();
		if (grid.equalsIgnoreCase("true")) {
			System.out.println(browser + "\t" + grid);
			if (browser.equalsIgnoreCase("firefox")) {
				dc = DesiredCapabilities.firefox();
				dc.setPlatform(Platform.WINDOWS);
				dc.setBrowserName(browser);
			} else if (browser.equalsIgnoreCase("chrome")) {
				dc = DesiredCapabilities.chrome();
				dc.setBrowserName(browser);
				dc.setPlatform(Platform.WINDOWS);
			}

			dc.setJavascriptEnabled(true);
			return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		} else {
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "false");
				return new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				return new ChromeDriver();
			}
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return null;
	}

	@AfterClass
	public void close() {
		driver.quit();
	}

}
