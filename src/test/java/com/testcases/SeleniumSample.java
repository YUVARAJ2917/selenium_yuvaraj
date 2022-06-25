package com.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.google.common.collect.Table.Cell;

public class SeleniumSample {

	static ChromeDriver driver;

	// https://www.softwaretestingmaterial.com/selenium-tutorial/

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
	public static void brokenLinks() {
		try {
			String url = "https://www.flipkart.com/checkout1/init?otracker=search/sdgsfhdfhd";
			HttpURLConnection huc = null;
			huc = (HttpURLConnection) (new URL(url).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			int respCode = huc.getResponseCode();
			if (respCode >= 400) {
				System.out.println(url + " is a broken link");
			} else {
				System.out.println(url + " is a valid link");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void automateAmazon() {
		driver.get("https://www.amazon.in/");
		WebElement element = driver.findElement(By.xpath("//*[@data-csa-c-content-id='nav_cs_mobiles']"));
		element.click();

		element = driver.findElement(By.id("twotabsearchtextbox"));
		element.sendKeys("iphone");

		element = driver.findElement(By.id("nav-search-submit-button"));
		element.click();

		element = driver.findElement(By.xpath("//*[@class='a-section aok-relative s-image-fixed-height']"));
		element.click();

		// driver.navigate();

		element = driver.findElement(By.xpath("//*[@id='buy-now-button']"));
		element.click();

		driver.close();
	}

	@Test
	public static void automateAmazon1() {
		driver.get("https://www.amazon.in/");
		WebElement element = driver.findElement(By.id("twotabsearchtextbox"));

		element.click();
		element.sendKeys("air condioner");

		element = driver.findElementById("nav-search-submit-button");
		element.click();

		// *[@id="buy-now-button"]
		element = driver.findElementByXPath("//div[@data-asin='B07L451HZW']");
		element.click();

		driver.findElementById("buy-now-button").click();
		/*
		 * element = driver.findElementById("ap_email"); element.sendKeys("8754506324");
		 * element.click();
		 * 
		 * element = driver.findElementById("continue"); element.click()
		 */

	}

	@Test
	public static void automateFlipkart() {
		driver.get("https://www.flipkart.com/");

		WebElement element = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']"));
		element.click();
		element = driver.findElementByName("q");
		element.click();
		element.sendKeys("LAPTOP");
		driver.findElementByXPath("//button[@class='L0Z3Pu']").click();
		driver.findElementByXPath("//div[@class='_4rR01T']").click();

		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));

		driver.findElementByXPath("//button[@class='_2KpZ6l _2U9uOA ihZ75k _3AWRsL']").click();
		element = driver.findElementByXPath("//input[@maxlength='auto']");
		element.click();
		element.sendKeys("8754506324");
		element = driver.findElementByXPath("//button[@class='_2KpZ6l _20xBvF _3AWRsL']");
		element.click();
		element = driver.findElementByXPath("//input[@type='password']");
		element.click();
		element.sendKeys("Yuvaraj@2917");
		element = driver.findElementByXPath("//button[@type='submit']");
		element.click();

	}

	@Test

	public static void automateApollo247() {
		//button[contains(@class,'AphButton_primaryBtn__1pAAx')]
		
		driver.get(
				"https://www.apollopharmacy.in/?variant=2&utm_source=google&utm_medium=cpc&utm_campaign=AP_Branding_Pharmacy_Chennai&gclid=CjwKCAjw5NqVBhAjEiwAeCa97etcGCl5D4ku3P3KqOwOB4x9aIGrXlCvvEN58VUVwLaPxo5XHaktgBoCXksQAvD_BwE");
		WebElement element = driver.findElement(By.id("searchProduct"));
		element.click();
		element.sendKeys("dolo 650");
		element = driver.findElementByXPath(" //i[@class='icon-ic-search MedicineAutoSearch_searchIco__2Hi4p']");
		element.click();
		element = driver.findElementByXPath("//button[contains(text(),'Add to Cart')]");
		element.click();
		
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
			// we will close.sefhafhdsa
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

	@Test
	public static void webDriverWait() {
		driver.get("https://www.softwaretestingmaterial.com");
		// To maximize the browser window
		driver.manage().window().maximize();
		// This waits up to 15 seconds before throwing a TimeoutException or if it finds
		// the element will return it in 0 - 15 seconds
		WebDriverWait wait = new WebDriverWait(driver, 15);
		// Title of the webpage is "Software Testing Material - A site for Software
		// Testers"
		wait.until(ExpectedConditions.titleIs("Software Testing Material - A site for Software Testers"));
	}

	@Test
	public static void readExcel() throws IOException {
		FileInputStream fis = new FileInputStream("c:\\Raja\\Test.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		// I have added test data in the cell A1 as "SoftwareTestingMaterial.com"
		// Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as 0.
		Row row = sheet.getRow(0);
		@SuppressWarnings("rawtypes")
		Cell cell = (Cell) row.getCell(0);
		System.out.println(cell);
		System.out.println(sheet.getRow(0).getCell(0));
		// String cellval = cell.getStringCellValue();
		// System.out.println(cellval);
	}

	@SuppressWarnings("resource")
	@Test
	public static void writeExcel() throws IOException {
		FileInputStream fis = new FileInputStream("c:\\Raja\\Test.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// call the getSheet() method of Workbook and pass the Sheet Name here.
		// In this case I have given the sheet name as “TestData”
		// or if you use the method getSheetAt(), you can pass sheet number starting
		// from 0. Index starts with 0.
		XSSFSheet sheet = workbook.getSheet("TestData");
		// XSSFSheet sheet = workbook.getSheetAt(0);
		// Now create a row number and a cell where we want to enter a value.
		// Here im about to write my test data in the cell B2. It reads Column B as 1
		// and Row 2 as 1. Column and Row values start from 0.
		// The below line of code will search for row number 2 and column number 2
		// (i.e., B) and will create a space.
		// The createCell() method is present inside Row class.
		Row row = sheet.createRow(0);
		org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
		// Now we need to find out the type of the value we want to enter.
		// If it is a string, we need to set the cell type as string
		// if it is numeric, we need to set the cell type as number
		cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
		cell.setCellValue("yuvaraj");
		FileOutputStream fos = new FileOutputStream("c:\\Raja\\Test.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("END OF WRITING DATA IN EXCEL");
	}

	@Test
	public static void chromeBrowserScript() {

		System.setProperty("webdriver.chrome.driver", "C:/SeleniumEnvironment/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.softwaretestingmaterial.com/software-testing-interview-questions-free-ebook/");
		System.out.println("Selenium Webdriver Script in Chrome browser | Software Testing Material");
	}

	@Test
	public static void fluentWait() {
		driver.get("http://softwaretestingplace.blogspot.com/2017/02/selenium-fluent-wait.html");
		driver.findElement(By.xpath("//*[@id='post-body-5280210221385817166']/div[1]/button")).click();
		@SuppressWarnings("deprecation")
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		@SuppressWarnings("unused")
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(By.xpath("//*[@id='softwareTestingMaterial']"));
				String getTextOnPage = element.getText();
				if (getTextOnPage.equals("Software Testing Material - DEMO PAGE")) {
					System.out.println(getTextOnPage);
					return element;
				} else {
					System.out.println("FluentWait Failed");
					return null;
				}
			}
		});
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
