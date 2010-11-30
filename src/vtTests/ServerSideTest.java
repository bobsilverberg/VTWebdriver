package vtTests;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ServerSideTest {
	
	WebDriver driver;
	Selenium selenium;

	@Before
	public void startSelenium() {
		driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver, "http://localhost/validatethis/tests/selenium/FacadeDemo/index.cfm?init=true");
	}

	@After
	public void stopSelenium() {
		driver.close();
	}

	@Test
	public void testServerSideTest() {
		selenium.open("http://localhost/validatethis/samples/FacadeDemo/index.cfm?init=true&noJS=true");
		assertEquals("ValidateThis Demo Page", selenium.getTitle());
		selenium.type("UserPass", "");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("ValidateThis Demo Page", selenium.getTitle());
		assertTrue(selenium.getText("//form[@id='frmMain']/fieldset[1]/div[1]/p[1]").matches("^The Email Address is required\\.\nHey, buddy, you call that an Email Address[\\s\\S]$"));
		assertEquals("The Password is required.\nThe Password must be between 5 and 10 characters long.\nThe password must be between 5 and 10 characters long (expression).", selenium.getText("//form[@id='frmMain']/fieldset[1]/div[3]/p[1]"));
		assertEquals("The Verify Password is required.", selenium.getText("//form[@id='frmMain']/fieldset[1]/div[4]/p[1]"));
		assertEquals("The User Group is required.", selenium.getText("//form[@id='frmMain']/fieldset[1]/div[5]/p[1]"));
		assertEquals("If you don't like Cheese and you don't like Chocolate, you must like something!", selenium.getText("//form[@id='frmMain']/fieldset[2]/div[6]/p[1]"));
		selenium.type("Nickname", "BobRules");
		selenium.type("UserName", "aaa");
		selenium.type("UserPass", "aaa");
		selenium.type("VerifyPassword", "zzz");
		selenium.type("Salutation", "aa");
		selenium.type("FirstName", "aaa");
		selenium.type("HowMuch", "aaa");
		selenium.click("AllowCommunication-1");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("ValidateThis Demo Page", selenium.getTitle());
		assertTrue(selenium.getText("//form[@id='frmMain']/fieldset[1]/div[1]/p[1]").matches("^Hey, buddy, you call that an Email Address[\\s\\S]$"));
		assertEquals("That Nickname has already been used. Try to be more original!", selenium.getText("//form[@id='frmMain']/fieldset[1]/div[2]/p[1]"));
		assertEquals("The Password must be between 5 and 10 characters long.\nThe password must be between 5 and 10 characters long (expression).", selenium.getText("//form[@id='frmMain']/fieldset[1]/div[3]/p[1]"));
		assertEquals("The Verify Password must be the same as the Password.", selenium.getText("//form[@id='frmMain']/fieldset[1]/div[4]/p[1]"));
		assertEquals("Only Dr, Prof, Mr, Mrs, Ms, or Miss (with or without a period) are allowed.", selenium.getText("//form[@id='frmMain']/fieldset[2]/div[1]/p[1]"));
		assertTrue(selenium.getText("//form[@id='frmMain']/fieldset[2]/div[7]/p[1]").matches("^The How much money would you like[\\s\\S] must be a number\\.$"));
		assertEquals("If you are allowing communication, you must choose a communication method.", selenium.getText("//form[@id='frmMain']/fieldset[2]/div[9]/p[1]"));
		assertEquals("The Last Name is required if you specify a value for the First Name.", selenium.getText("//form[@id='frmMain']/fieldset[2]/div[3]/p[1]"));
		selenium.type("UserName", "bob.silverberg@gmail.com");
		selenium.type("Nickname", "different");
		selenium.type("UserPass", "aaaaa");
		selenium.type("VerifyPassword", "aaaaa");
		selenium.select("UserGroupId", "label=Member");
		selenium.type("Salutation", "Mr");
		selenium.type("LastName", "aaa");
		selenium.click("LikeCheese-1");
		selenium.type("HowMuch", "999");
		selenium.click("AllowCommunication-2");
		selenium.click("AllowCommunication-1");
		selenium.select("CommunicationMethod", "label=Email");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("ValidateThis Demo Page", selenium.getTitle());
		assertFalse(selenium.isElementPresent("error-UserName"));
		assertFalse(selenium.isElementPresent("error-Nickname"));
		assertFalse(selenium.isElementPresent("error-UserPass"));
		assertFalse(selenium.isElementPresent("error-VerifyPassword"));
		assertFalse(selenium.isElementPresent("error-UserGroupId"));
		assertFalse(selenium.isElementPresent("error-Salutation"));
		assertFalse(selenium.isElementPresent("error-LastName"));
		assertFalse(selenium.isElementPresent("error-LikeOther"));
		assertFalse(selenium.isElementPresent("error-HowMuch"));
		assertFalse(selenium.isElementPresent("error-CommunicationMethod"));
		assertEquals("The User has been saved!", selenium.getText("//div[@id='content']/h3[2]"));
		selenium.click("link=Edit an Existing User");
		selenium.waitForPageToLoad("30000");
		assertEquals("ValidateThis Demo Page", selenium.getTitle());
		selenium.type("FirstName", "");
		selenium.type("LastName", "");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("ValidateThis Demo Page", selenium.getTitle());
		assertEquals("The First Name is required.", selenium.getText("//form[@id='frmMain']/fieldset[2]/div[2]/p[1]"));
		assertEquals("The Last Name is required.", selenium.getText("//form[@id='frmMain']/fieldset[2]/div[3]/p[1]"));
		selenium.type("FirstName", "bob");
		selenium.type("LastName", "bob");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("ValidateThis Demo Page", selenium.getTitle());
		assertFalse(selenium.isElementPresent("error-FirstName"));
		assertFalse(selenium.isElementPresent("error-LastName"));
	}

	@Test
	public void testEndToEndServerNew() {
		selenium.open("http://localhost/validatethis/tests/selenium/FacadeDemo/index.cfm?init=true&noJS=true&context=newValidations-server");
		assertEquals("ValidateThis Demo Page", selenium.getTitle());
		selenium.type("UserName", "");
		selenium.type("UserPass", "");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("ValidateThis Demo Page", selenium.getTitle());
		assertEquals("The Email Address is required.\nThe Email Address must be a date between 2010-01-01 and 2011-12-31.\nThe Email Address must be a date in the future. The date entered must come after 2010-12-31.\nThe Email Address must be a date in the past. The date entered must come before 2011-02-01.\nThe Email Address was not found in the list: 2011-01-30,2011-01-29.", selenium.getText("error-UserName"));
		assertEquals("The Password is required.\nThe Password must be a valid boolean.\nThe Password must be a true boolean.\nThe Password must be a false boolean.", selenium.getText("error-UserPass"));
		assertEquals("The User Group is invalid: The User Group Name is required.", selenium.getText("//form[@id='frm-Main2']/fieldset[1]/div[5]/p[1]"));
		selenium.type("UserName", "b");
		selenium.type("Nickname", "abcd");
		selenium.type("UserPass", "c");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Nickname must not contain the values of properties named: UserName,UserPass.\n1 patterns were matched but 3 were required.\nThe Nickname must be a valid URL.", selenium.getText("//form[@id='frm-Main2']/fieldset[1]/div[2]/p[1]"));
		assertEquals("The Email Address must be a date between 2010-01-01 and 2011-12-31.\nThe Email Address must be a date in the future. The date entered must come after 2010-12-31.\nThe Email Address must be a date in the past. The date entered must come before 2011-02-01.\nThe Email Address was not found in the list: 2011-01-30,2011-01-29.", selenium.getText("error-UserName"));
		selenium.type("UserName", "2010-02-02");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Email Address must be a date in the future. The date entered must come after 2010-12-31.\nThe Email Address was not found in the list: 2011-01-30,2011-01-29.", selenium.getText("error-UserName"));
		selenium.type("UserName", "2011-02-02");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Email Address must be a date in the past. The date entered must come before 2011-02-01.\nThe Email Address was not found in the list: 2011-01-30,2011-01-29.", selenium.getText("error-UserName"));
		selenium.type("UserName", "2011-01-31");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Email Address was not found in the list: 2011-01-30,2011-01-29.", selenium.getText("error-UserName"));
		selenium.type("UserName", "2011-01-29");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Email Address was found in the list: 2011-01-29,2011-01-28.", selenium.getText("error-UserName"));
		selenium.type("UserName", "2011-01-30");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isElementPresent("error-UserName"));
		selenium.type("UserName", "2011-01-29");
		selenium.type("Nickname", "<a href=2011-01-29>");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Nickname cannot contain HTML tags.\nThe Nickname must not contain the values of properties named: UserName,UserPass.\nThe Nickname must be a valid URL.", selenium.getText("//form[@id='frm-Main2']/fieldset[1]/div[2]/p[1]"));
		selenium.type("Nickname", "2011-01-29");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Nickname must not contain the values of properties named: UserName,UserPass.\n2 patterns were matched but 3 were required.\nThe Nickname must be a valid URL.", selenium.getText("error-Nickname"));
		selenium.type("Nickname", "http://www.Abc.com");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isElementPresent("error-Nickname"));
		selenium.type("UserPass", "abc");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Password must be a valid boolean.\nThe Password must be a true boolean.\nThe Password must be a false boolean.", selenium.getText("//form[@id='frm-Main2']/fieldset[1]/div[3]/p[1]"));
		selenium.type("UserPass", "yes");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Password must be a false boolean.", selenium.getText("//form[@id='frm-Main2']/fieldset[1]/div[3]/p[1]"));
		selenium.type("UserPass", "0");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Password must be a true boolean.", selenium.getText("//form[@id='frm-Main2']/fieldset[1]/div[3]/p[1]"));
		selenium.type("VerifyPassword", "ab");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Verify Password size is not between 3 and 10.", selenium.getText("//form[@id='frm-Main2']/fieldset[1]/div[4]/p[1]"));
		selenium.type("VerifyPassword", "abc");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isElementPresent("error-VerifyPassword"));
		selenium.type("VerifyPassword", "a,b");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Verify Password size is not between 3 and 10.", selenium.getText("//form[@id='frm-Main2']/fieldset[1]/div[4]/p[1]"));
		selenium.type("VerifyPassword", "a,b,c");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isElementPresent("error-VerifyPassword"));
		selenium.type("VerifyPassword", "structbad");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Verify Password size is not between 3 and 10.", selenium.getText("//form[@id='frm-Main2']/fieldset[1]/div[4]/p[1]"));
		selenium.type("VerifyPassword", "structok");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isElementPresent("error-VerifyPassword"));
		selenium.type("VerifyPassword", "arraybad");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("The Verify Password size is not between 3 and 10.", selenium.getText("//form[@id='frm-Main2']/fieldset[1]/div[4]/p[1]"));
		selenium.type("VerifyPassword", "arrayok");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertFalse(selenium.isElementPresent("error-VerifyPassword"));
	}

}
