// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package seleniummodule.actions;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import mercedespoc.proxies.COO_AFABDetail;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class FetchFromAFAB extends CustomJavaAction<IMendixObject>
{
	private java.util.List<IMendixObject> __AFABFieldsToFetch;
	private java.util.List<mercedespoc.proxies.COO_AFABDetail> AFABFieldsToFetch;
	private IMendixObject __COO;
	private mercedespoc.proxies.COO COO;
	private IMendixObject __Credentials;
	private mercedespoc.proxies.Credentials Credentials;

	public FetchFromAFAB(IContext context, java.util.List<IMendixObject> AFABFieldsToFetch, IMendixObject COO, IMendixObject Credentials)
	{
		super(context);
		this.__AFABFieldsToFetch = AFABFieldsToFetch;
		this.__COO = COO;
		this.__Credentials = Credentials;
	}

	@java.lang.Override
	public IMendixObject executeAction() throws Exception
	{
		this.AFABFieldsToFetch = java.util.Optional.ofNullable(this.__AFABFieldsToFetch)
			.orElse(java.util.Collections.emptyList())
			.stream()
			.map(__AFABFieldsToFetchElement -> mercedespoc.proxies.COO_AFABDetail.initialize(getContext(), __AFABFieldsToFetchElement))
			.collect(java.util.stream.Collectors.toList());

		this.COO = this.__COO == null ? null : mercedespoc.proxies.COO.initialize(getContext(), __COO);

		this.Credentials = this.__Credentials == null ? null : mercedespoc.proxies.Credentials.initialize(getContext(), __Credentials);

		// BEGIN USER CODE
		ILogNode logger = Core.getLogger("SeleniumAFABScript");

		/* -------------------------------------------------       LOCAL  CONFIGURATION ------------------------------------------------------------------------------------------*/ 
		// //Selenium
		// //Driver Resource Path
		//String driverFilePath = (Core.getConfiguration().getResourcesPath() + 
		//		File.separator + "chromedriver/chromedriver");
		//if(System.getProperty("os.name").toLowerCase().contains("win"))
		//	driverFilePath+=".exe";
		//System.setProperty("webdriver.chrome.driver", driverFilePath);
		//
		// //Chrome Resource Path
		//String chromeFilePath = (Core.getConfiguration().getResourcesPath() + 
		//		File.separator + "Chrome/Application/chrome.exe");
		//
		// //Set Opts
		//ChromeOptions opt = new ChromeOptions();
		// //opt.addArguments("headless");
		//opt.setBinary(chromeFilePath);
		//
		// //Driver
		//ChromeDriver driver = new ChromeDriver(opt);
		/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------*/ 

		/* ------------------------------------     DOCKER CONTAINER  CONFIGURATION ------------------------------------------------------------------------------------------*/ 
		 // Selenium
		 //Set Opts
		ChromeOptions opt = new ChromeOptions();
		 URL url = new URL("http://chrome:4444/wd/hub");
		//Driver
		 WebDriver driver = new RemoteWebDriver(url, opt);
		/* -------------------------------------------------------------------------------------------------------------------------------------------------------------------*/ 

		try {
			//Get Page
			driver.get("https://scv-int.i.daimler.com/afab/afab.xhtml?jfwid=1a4su8e376#!/welcome");
			
			//Wait
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			
			//Input Username
			WebElement userName = driver.findElement(By.id("userid"));
			userName.sendKeys(this.Credentials.getUsernameAFAB());
			WebElement userLoginBtn =  wait.until(ExpectedConditions.elementToBeClickable(By.id("next-btn")));
			userLoginBtn.click();
			
			//Input Password
			WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
			password.sendKeys(this.Credentials.getPasswordAFAB());
			WebElement passwordrLoginBtn =  wait.until(ExpectedConditions.elementToBeClickable(By.id("loginSubmitButton")));
			passwordrLoginBtn.click();		
			
			
			//Click Order Information
			WebElement orderDisplayBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='#!/auftragShow']")));
			orderDisplayBtn.click();
			
			//Set OrderNumber
			WebElement orderNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragDialogModel:key_temp")));
			orderNumber.sendKeys(this.COO.getOrderNumber());
			WebElement getOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("AuftragDialogModel:btnAktualisieren")));
			getOrderBtn.click();
			logger.info("Processing Fields...");
			//For each Field
			for(COO_AFABDetail eachAFABField : this.AFABFieldsToFetch) {	
				String currFieldName = eachAFABField.getFieldName();
				Boolean isLoading = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
				//CustomerAddress - Consigor - Absender
				if(currFieldName.equals("Customer Address")) {
					logger.info("Processing field "+ currFieldName);
					//CustomerAddress_Fields
					WebElement customerTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabKunde")));
					customerTab.click();
					Boolean isLoadingTab = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
					WebElement customerAddressTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragKundenModel:objekt_best_anr")));
					WebElement customerAddressNameCompany1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragKundenModel:objekt_best_name_1")));
					WebElement customerAddressNameCompany2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragKundenModel:objekt_best_name_2")));
					WebElement customerAddressNameCompany3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragKundenModel:objekt_best_name_3")));
					WebElement customerAddressStreet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragKundenModel:objekt_best_str")));
					WebElement customerAddressPostalCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragKundenModel:objekt_best_plz")));
					WebElement customerCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragKundenModel:objekt_best_land")));
					
					eachAFABField.setValue(String.format("%s \n %s - %s - %s \n %s \n %s \n %s",
											customerAddressTitle.getAttribute("value"),
											customerAddressNameCompany1.getAttribute("value"),
											customerAddressNameCompany2.getAttribute("value"),
											customerAddressNameCompany3.getAttribute("value"),
											customerAddressStreet.getAttribute("value"),
											customerAddressPostalCode.getAttribute("value"),
											customerCountry.getAttribute("value")
											));
					logger.info("Customer Address Set: "+ eachAFABField.getValue());
				}
				//CustomerCountry - Consignee - Empfänger
				else if (currFieldName.equals("Customer Country")) {
					logger.info("Processing field "+ currFieldName);
					WebElement customerTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabKunde")));
					Boolean isLoadingTab = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
					customerTab.click();
					WebElement customerCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragKundenModel:objekt_best_land")));
					eachAFABField.setValue(customerCountry.getAttribute("value"));
					logger.info("Customer Country: "+ eachAFABField.getValue());
				}
				//Description - Description - Description
				else if (currFieldName.equals("Description")) {
					logger.info("Processing field "+ currFieldName);
					WebElement descriptionTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabAbewert")));
					descriptionTab.click();
					Boolean isLoadingTab = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
					WebElement descriptionLineOne = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#AuftragAbewertModel > table > tbody > tr.textTypeWriter table tbody tr:nth-child(2) td span")));
					WebElement descriptionLineTwo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#AuftragAbewertModel > table > tbody > tr.textTypeWriter table tbody tr:nth-child(3) td span")));
					String description = descriptionLineOne.getText() + descriptionLineTwo.getText();
					String trimDescription = description.trim().replaceAll(" +", " ");
					eachAFABField.setValue(trimDescription);
					logger.info("Description: "+ eachAFABField.getValue());
				}
				//Description - Description - Description
				else if (currFieldName.equals("Chassis Number")) {
					logger.info("Processing field "+ currFieldName);
					WebElement datesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabTermin2")));
					datesTab.click();
					Boolean isLoadingTab = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
					WebElement comissionNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragTermine2Model:objekt_c_dok")));
					eachAFABField.setValue(comissionNumber.getAttribute("value"));
					logger.info("Chassis Number: "+ eachAFABField.getValue());
				}
				else if (currFieldName.equals("Engine Number")) {
					logger.info("Processing field "+ currFieldName);
					WebElement datesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabTermin2")));
					datesTab.click();
					Boolean isLoadingTab = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
					WebElement engineNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragTermine2Model:objekt_c_motor_nr")));
					eachAFABField.setValue(engineNumber.getAttribute("value"));
					logger.info("Engine Number: "+ eachAFABField.getValue());
				}
				else if (currFieldName.equals("Production Finish")) {
					logger.info("Processing field "+ currFieldName);
					WebElement datesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabTermin2")));
					datesTab.click();
					Boolean isLoadingTab = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
					WebElement prodFinishDateElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragTermine2Model:objekt_d_fert_ist_disp")));
					String dateProdFinish = prodFinishDateElem.getAttribute("value");
					if(dateProdFinish.length() >= 3) {
						String FormatedProdFinishDate = dateProdFinish.substring(3,dateProdFinish.length());
						eachAFABField.setValue(FormatedProdFinishDate);
					}
					logger.info("Production Finish: "+ eachAFABField.getValue());
				}
				else if (currFieldName.equals("Ex Factory Date")) {
					logger.info("Processing field "+ currFieldName);
					WebElement datesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabTermin2")));
					datesTab.click();
					Boolean isLoadingTab = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
					WebElement exFactoryDateElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragTermine2Model:objekt_d_vers_ista_disp")));
					eachAFABField.setValue(exFactoryDateElem.getAttribute("value"));
					logger.info("Ex Factory Date: "+ eachAFABField.getValue());
				}
				else if (currFieldName.equals("Weight")) {
					logger.info("Processing field "+ currFieldName);
					WebElement specDataTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabNfzspez")));
					specDataTab.click();
					Boolean isLoadingTab = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
					WebElement WeightElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AuftragNfzspezModel:objekt_vgew_wk_man_disp")));
					eachAFABField.setValue(WeightElem.getAttribute("value"));
					logger.info("Weight: "+ eachAFABField.getValue());
				}
				else if (currFieldName.equals("Model Year")) {
					logger.info("Processing field "+ currFieldName);
					WebElement technicsTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabTechnik")));
					technicsTab.click();
					Boolean isLoadingTab = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
					WebElement modelYearElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'XY')]"))); 
					eachAFABField.setValue(modelYearElem.getText());
					logger.info("Model Year: "+ eachAFABField.getValue());
				}
				else if (currFieldName.equals("Color Of Chassis")) {
					logger.info("Processing field "+ currFieldName);
					WebElement technicsTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tabTechnik")));
					technicsTab.click();
					Boolean isLoadingTab = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));
					WebElement colorofChassisElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[jstechnicalname='ATECHNIK_LACK_BEZ']")));
					eachAFABField.setValue(colorofChassisElem.getText());
					logger.info("Color Of Chassis: "+ eachAFABField.getValue());
				}
				eachAFABField.commit();
			}
			IMendixObject seleniumOutput = Core.instantiate(context(), seleniummodule.proxies.SeleniumOutput.getType());
			driver.quit();
	
			return seleniumOutput;
		} catch (Exception e) {
			IMendixObject seleniumOutput = Core.instantiate(context(), seleniummodule.proxies.SeleniumOutput.getType());
			seleniumOutput.setValue(getContext(), seleniummodule.proxies.SeleniumOutput.MemberNames.ErrorMessage.toString(), "There was an error fetching data from AFAB." + e.getMessage());
			seleniumOutput.setValue(getContext(), seleniummodule.proxies.SeleniumOutput.MemberNames.HasError.toString(), true);
			driver.quit();
			return seleniumOutput;
		}
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "FetchFromAFAB";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
