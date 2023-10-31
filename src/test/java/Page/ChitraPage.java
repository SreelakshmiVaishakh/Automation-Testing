package Page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

public class ChitraPage {
	WebDriver driver;
	By account =By.xpath("//*[@id=\"masthead\"]/div[1]/div[4]/ul/li[2]/a");
	By user=By.xpath("//*[@id=\"username\"]");
	By pass=By.xpath("//*[@id=\"password\"]");
	By login=By.xpath("//*[@id=\"customer_login\"]/div[1]/div/form/p[3]/button");
	By gift=By.xpath("//*[@id=\"menu-item-213579\"]/a");
	By love=By.xpath("//*[@id=\"menu-item-212980\"]/a");
	By choose=By.xpath("//*[@id=\"file_7405245824\"]/div/div");
	By item =By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/div[2]/div[1]/div[1]/a");
	By shelves =By.xpath("//*[@id=\"menu-item-213578\"]/a");
	By clocks=By.xpath("//*[@id=\"menu-item-215132\"]/a");
	By logo=By.xpath("//*[@id=\"logo\"]/a");
	
	
	
	public ChitraPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void titleverification() {
		String title=driver.getTitle();
		String t="Buy Personalized Name Plate Online| Personalized Gift |FootballPersonalized Gifts";
		if(title.equals(t)) {
			System.out.println("same");
		}
		else {
			System.out.println("different");
		}
	}
	public void upload() throws AWTException{
		
		
		driver.findElement(gift).click();
		driver.findElement(love).click();
		driver.findElement(item).click();
		driver.findElement(choose).click();
		
		fileupload("\"C:\\Users\\sreel\\Downloads\\Test.jpeg\"");
		driver.navigate().back();
		
	}
	private void fileupload(String s) throws AWTException{
		StringSelection strSelection =new StringSelection(s);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelection, null);
		Robot robot=new Robot();
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public void mousehover() {
		Actions act=new Actions(driver);
		WebElement ai=driver.findElement(shelves);
		act.moveToElement(ai);
		act.perform();
		driver.findElement(clocks);
		driver.navigate().back();
	}
	public void login() throws IOException
	{
	driver.findElement(account).click();
	File f=new File("D:\\account.xlsx");
	FileInputStream fi=new FileInputStream(f);
	XSSFWorkbook wb=new XSSFWorkbook(fi);
	XSSFSheet sh=wb.getSheet("Sheet1");
	System.out.println(sh.getLastRowNum());
	for(int i=1;i<=sh.getLastRowNum();i++)
	{
	String Username=sh.getRow(i).getCell(0).getStringCellValue();
	System.out.println(Username);
	String Password=sh.getRow(i).getCell(1).getStringCellValue();
	System.out.println(Password);

	driver.findElement(user).clear();
	driver.findElement(user).sendKeys(Username);
	driver.findElement(pass).clear();
	driver.findElement(pass).sendKeys(Password);
	driver.findElement(login).click();


	}

	}
	public void logodisplay() {
		WebElement ob=driver.findElement(logo);
		boolean b=ob.isDisplayed();
		if(b) {
			System.out.println("logo displayed");
		}
		else {
			System.out.println("logo is not displayed");
		}
	}
	public void contentverification() {
		String content =driver.getPageSource();
		if(content.contains("my-account")) {
			System.out.println("contains");
		}
		else {
			System.out.println("not contain");
		}
	}
	
	public void scrolldown()
	{
	JavascriptExecutor js=(JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	js.executeScript("window.scrollBy(0,1000)", "");


	}
	public void screenshot() throws IOException
	{
	File src=(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE));
	FileHandler.copy(src, new File("D:\\screenshot\\screenshot.png"));
	}
	public void handlewindow()
	{
	String parentWindow=driver.getWindowHandle();
	driver.findElement(By.xpath("/html[1]/body[1]/div[1]/footer[1]/section[1]/div[2]/div[2]/div[1]/div[1]/div[2]/a[2]/i[1]")).click();

	Set<String>childwindow=driver.getWindowHandles();
	for(String handle:childwindow)
	{
	if(!handle.equalsIgnoreCase(parentWindow))
	{
		driver.switchTo().window(handle);
		
		}
		}
	    driver.navigate().back();
		driver.switchTo().window(parentWindow);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

}
}
