package testcases;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SeleniumSample {

	static ChromeDriver driver;
	
	//https://www.softwaretestingmaterial.com/selenium-tutorial/

	@BeforeTest
	public void test() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--use-fake-ui-for-media-stream");
		options.addArguments("--incognito");
		options.addArguments("start-maximized");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-gpu");
		DesiredCapabilities c = DesiredCapabilities.chrome();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public static void dropDownTest() throws Exception {
		driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
		WebElement mySelectElement = driver.findElement(By.name("dropdown"));
		Select dropdown = new Select(mySelectElement);
		dropdown.selectByVisibleText("Automation Testing");
	}

	@Test
	public static void uploadTest() throws Exception {
		driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
		driver.navigate().refresh();
		Thread.sleep(10000);
		WebElement browse = driver.findElement(By.name("filename"));
		browse.sendKeys("pom.xml");
	}

	@Test
	public static void alartTestcases() throws Exception {
		driver.get("http://softwaretestingplace.blogspot.com/2017/03/javascript-alert-test-page.html");
		driver.findElement(By.xpath("//*[@id='content']/button")).click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		String print = alert.getText();
		System.out.println(print);
		alert.accept();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='content']/button")).click();
		Thread.sleep(3000);
		alert.dismiss();
	}

	@Test
	public static void multipleWindow() {
		driver.get("http://www.naukri.com/");
		// It will return the parent window name as a String
		String mainWindow = driver.getWindowHandle();
		// It returns no. of windows opened by WebDriver and will return Set of Strings
		Set<String> set = driver.getWindowHandles();
		// Using Iterator to iterate with in windows
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			// Compare whether the main windows is not equal to child window. If not equal,
			// we will close.
			if (!mainWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				System.out.println(driver.switchTo().window(childWindow).getTitle());
				driver.close();
			}
		}
		// This is to switch to the main window
		driver.switchTo().window(mainWindow);
	}

	@Test
	public static void rightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		Actions action = new Actions(driver);
		By locator = By.cssSelector(".context-menu-one");
		// Wait for the element. Used Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement rightClickElement = driver.findElement(locator);
		// contextClick() method to do right click on the element
		action.contextClick(rightClickElement).build().perform();
		WebElement getCopyText = driver.findElement(By.cssSelector(".context-menu-icon-copy"));
		// getText() method to get the text value
		String GetText = getCopyText.getText();
		// To print the value
		System.out.println(GetText);
	}

	@Test
	public static void dragAndDrop() {
		Actions action = new Actions(driver);
		driver.get("http://jqueryui.com/droppable/");
		// WebdriverWait is used to wait for a frame to be available. Once it is
		// availble we switch to the frame to achieve our task
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".demo-frame")));
		// To get source locator
		WebElement sourceLocator = driver.findElement(By.cssSelector("#draggable"));
		// To get target locator
		WebElement targetLocator = driver.findElement(By.cssSelector("#droppable"));
		// dragAndDrop(source, target) method accepts two parameters source and locator.
		// used dragAndDrop method to drag and drop the source locator to target locator
		action.dragAndDrop(sourceLocator, targetLocator).build().perform();
		// driver.close();
	}

	@Test
	public static void doubleClick() {
		driver.get("http://api.jquery.com/dblclick/");
		// Maximize the browser
		driver.manage().window().maximize();
		// As per the above URL we need to switch to frame. The targeted element is in
		// the frame
		driver.switchTo().frame(0);
		// Create the object 'action'
		Actions action = new Actions(driver);
		WebElement ele = driver.findElement(By.cssSelector("html>body>div"));
		// Here I used JavascriptExecutor interface to scroll down to the targeted
		// element
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
		// used doubleClick(element) method to do double click action
		action.doubleClick(ele).build().perform();
	}

	@Test
	public static void photo() throws IOException {
		driver.get("https://www.softwaretestingmaterial.com/capture-screenshot-using-selenium-webdriver");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("C:\\Users\\91960\\Desktop\\Yuva\\SoftwareTestingMaterial.png"));
	}

	@Test
	public static void fullScreenShot() {
		driver.get("https://www.softwaretestingmaterial.com");
		WebElement elementLogo = driver.findElement(By.className("has-logo-image"));
		File src = elementLogo.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/screenshots/elementLogo.png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@Test(dataProvider = "getData")
	public void loginTest(String Uid, String Pwd) {
		System.out.println("UserName is " + Uid);
		System.out.println("Password is " + Pwd);
	}

	@DataProvider(name = "getData")
	public Object[][] getData() {
		Object[][] data = new Object[2][2];
		data[0][0] = "FirstUid";
		data[0][1] = "FirstPWD";
		data[1][0] = "SecondUid";
		data[1][1] = "SecondPWD";
		return data;
	}

}