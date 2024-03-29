package stepDefinations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CountryPage;
import pageObjects.LandingPage;
import pageObjects.OrderVerficationPage;

public class ItemsOnPage extends BaseClass {

	@Before
	public void setup() throws IOException {

		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);

		if (configProp.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();

	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Given("User is on {string} page")
	public void user_is_on_page(String site) {

		lp = new LandingPage(driver);
		if (configProp.getProperty("browser").equals("chrome")) {
			driver.get(configProp.getProperty("baseurl"));
		}
	}

	@When("User selects the item and adds to cart")
	public void user_selects_the_item_and_adds_to_cart(DataTable dataTable) throws InterruptedException {

		List<Map<String, String>> row = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> col : row) {
			for (int i = 0; i < lp.prdtName().size(); i++) {
				if (lp.prdtName().get(i).getText().contains(col.get("Item"))) {
					lp.addQuantity().get(i).clear();
					lp.addQuantity().get(i).sendKeys(col.get("Quantity"));
					lp.btnAddtoCart().get(i).click();

				}

			}

		}
	}

	@When("the user clicks on proceed to check out")
	public void the_user_clicks_on_proceed_to_check_out() throws InterruptedException {
		lp.clickMainCart();
		lp.proceedToCheckout();
		Thread.sleep(2000);
	}

	@Then("user verifies the purchase and adds {string} and place order")
	public void user_verifies_the_purchase_and_adds_and_place_order(String discount, DataTable datatable)
			throws InterruptedException {
		vp = new OrderVerficationPage(driver);

		//// Find the table element by its ID, XPath, or any other suitable selector
		WebElement table = vp.productCartTable;
		// Get the rows of the web table
		List<WebElement> webTableRows = table.findElements(By.tagName("tr"));

		// Convert the Cucumber DataTable into a list of lists of strings
		List<List<String>> cucumberTableData = datatable.asLists();

		// Check if the number of rows matches
		if (webTableRows.size() != cucumberTableData.size()) {
			System.out.println(
					"Number of rows in Cucumber DataTable does not match the number of rows in the web table.");
			return;
		}
		// Iterate through the rows of both data structures
		for (int i = 0; i < webTableRows.size(); i++) {

			WebElement webTableRow = webTableRows.get(i);
			List<WebElement> webTableCells = webTableRow.findElements(By.tagName("td"));
			List<String> cucumberTableRow = cucumberTableData.get(i);
			// Skip the first cell in the web table
			int x = cucumberTableRow.size() + 1;
			if (webTableCells.size() != x) {
				System.out.println("Number of columns does not match.");
				driver.quit();
				return;
			}

			// Compare data for the remaining cells
			for (int j = 1; j < webTableCells.size(); j++) {
				String webTableCellText = webTableCells.get(j).getText();
				String cucumberTableCellText = cucumberTableRow.get(j - 1); // Adjust index since we're skipping the
																			// first cell
				// Compare cell data
				if (!webTableCellText.contains(cucumberTableCellText)) {
					System.out.println("Data does not match at Row " + (i + 1) + ", Column " + j);
					System.out.println("Web table value: " + webTableCellText);
					System.out.println("Cucumber table value: " + cucumberTableCellText);
					driver.quit();
					return;
				}
			}
		}
		System.out.println("All data matches.");
		if (discount.contains("discount")) {
			vp.enterPromoCode(configProp.getProperty("promocode"));// code for applying discount
			vp.btnApply();
			Thread.sleep(10000);
			Assert.assertEquals(vp.checkLblPromoInfo(), "Code applied ..!", "Wrong String found");

		}
		vp.clickPlaceOrder();
		Thread.sleep(3000);
	}

	@Then("user selects {string} with {string} terms and condition proceed")
	public void user_selects_with_terms_and_condition_proceed(String country, String policy)
			throws InterruptedException {
		cp = new CountryPage(driver);

		cp.selectValueFromdown(country);
		System.out.println("Country is selected");

		if (policy.equals("yes")) {
			cp.selectCheckbox();
			Assert.assertTrue(CountryPage.policyCheckBox.isSelected());

			System.out.println("Checkbox is selected");
			cp.btnProceed();
		} else if (policy.equals("no")) {
			cp.btnProceed();
			Assert.assertTrue(cp.errorMessage().contains(configProp.getProperty("errormessage")));
			System.out.print("Error message step is passed for China");
		}
		Thread.sleep(3000);
	}

	@Then("user verfies {string}")
	public void user_verfies(String message) {

		if (message.equals("yes")) {
			Assert.assertTrue(cp.successMessage().contains(configProp.getProperty("successmessage")));
		}
	}
}
