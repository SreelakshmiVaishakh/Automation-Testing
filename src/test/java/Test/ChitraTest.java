package Test;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Page.ChitraPage;

public class ChitraTest {
WebDriver driver;
	
	@BeforeTest
	public void setup() {
		driver=new ChromeDriver();
		
	}
	@BeforeMethod
	public void url() {
		driver.get("https://chitrachaya.com/");
		
	}
	@Test
	public void test()throws IOException,AWTException{
		ChitraPage ob=new ChitraPage(driver);
		driver.manage().window().maximize();
		
		 // ob.login();
		 // ob.titleverification(); 
		 // ob.logodisplay();
		 // ob.contentverification(); 
		 // ob.screenshot();
		 // ob.upload();
		 // ob.mousehover();
		  ob.scrolldown();
		  ob.handlewindow();
	}

}
