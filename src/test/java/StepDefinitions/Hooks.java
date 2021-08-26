package StepDefinitions;

import Functions.CreateDriver;
import Functions.WebDriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;

public class Hooks {

    public static WebDriver driver;
    Logger log = Logger.getLogger(Hooks.class);
    Scenario scenario = null;
    @Before
    public void initDriver (Scenario scenario) throws IOException {
        log.info("****************************************************************************************************");
        log.info("[ Configuration ] - Initializing driver configuration");
        log.info("****************************************************************************************************");
        this.scenario = scenario;
        driver = CreateDriver.initConfig();



        log.info("****************************************************************************************************");
        log.info("[ Scenario ] - " + scenario.getName());
        log.info("****************************************************************************************************");
    }

    @After
    public void tearDown(){

        if (scenario.isFailed()){



            try{
                scenario.write("The  scenario faild");
                scenario.write("Current Page URL is "+driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot,"src/test/resources/Data/Screenshots/Failed");
            }catch (WebDriverException somePlatformsDontSupportScreenshots){

                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }

        }

        log.info("****************************************************************************************************");
        log.info("[ Driver Status ] - Clean and close the intance ");
        log.info("****************************************************************************************************");
        //driver.quit();
    }


}
