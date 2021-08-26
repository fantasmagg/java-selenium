package StepDefinitions;

import Functions.CreateDriver;
import Functions.SeleniumFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;


public class StepsDefinitions {
    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");
    private static boolean Actual = Boolean.parseBoolean(null);

    WebDriver driver;

    SeleniumFunctions functions = new SeleniumFunctions();

    /***log Atribute***/

    Logger log = Logger.getLogger(StepsDefinitions.class);

    public StepsDefinitions(){
        driver = Hooks.driver;
    }

    @Given("^open web")
    public void open_web() throws IOException{


        String url = functions.readProperties("MainAppUrlBase") ;
        log.info("Navigate to " + url);
        driver.get(url);

    }
    @Given("Local (.*)")
    public void openocd(String loc){

        log.info("Navigate to : " + loc);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(loc);
        functions.page_has_loaded();
        functions.WindowsHandle("Principal");
    }
    @Then("^I quit the app$")
    public void quitapp(){

        driver.quit();

    }
    @Then("^I close the app$")
    public void closeapp(){

        driver.close();
    }


    @Then("^I load the DOM Information (.*)")
    public void iLoadTheDOMInformationGalaciaJson(String json) throws Exception {

        SeleniumFunctions.FileName = json;
        SeleniumFunctions.readJson();
        log.info("initilize file: "+json);

        //JSONObject Entity = SeleniumFunctions.ReadEntity("Title");/**lo comente para poder usar el frames.json , se los puedes quitar cuando quieras**/
       // System.out.println(Entity);
    }

    @And("I do a click in element (.*)")
    public void iDoAClickInElementEmail(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        functions.waitForElementCliqueable(element);
        driver.findElement(SeleniumElement).click();
        log.info("Click on element by "+element);
    }


    @And("I do write in (.*) this (.*)")
    public void iDoWriteInThis(String element, String text)  throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).sendKeys(text);
        log.info("Click on element by "+element +" text " + text);
    }

    /**@And("It other click (.*)$")
    public void itOtherClikEmailComfirmfromRecord(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).click();
        log.info("Click on element by "+element);
    }*/

    @Given("I set (.*) Value in Data Scenario")
    public void iSetUserEmailValueInDataScenario(String parameter) throws IOException{
        functions.RetriveTestData(parameter);

    }
    @And("^I save text of (.*?) as Scenario context$")
    public void Implementor(String element) throws Exception{

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        String ScenarioElementText = driver.findElement(SeleniumElement).getText();
        functions.SaveInScenario(element+".text",ScenarioElementText);

    }

    @And("I set (.*?) With (.*)")
    public void iSetText(String element , String key) throws Exception{
        functions.iSetElementWithKeyValue(element , key);
    }
    @And("I set text (.*) en dropdown (.*)")
    public void iSetTextFebreroEnDropdwnMesDeNacimiento( String option,String element  )throws Exception {
        Select opt = (Select) functions.selectOption(element);
        opt.selectByVisibleText(option);
    }

    @And("I Set index (.*) in dropdown (.*)")
    public void iSetIndexInDropdwn(int option, String element) throws Exception {
        Select opt = (Select) functions.selectOption(element);
        opt.selectByIndex(option);
    }
    /*** Wait for element to be present for a specific period of time ***/
    @Then("^I wait for element (.*) to be present")
    public void waitForElementPresent(String element)throws Exception{

        functions.waitForElementPresent(element);

    }
    /*** Wait for element to be Visible for a specific period of time ***/
    @Then("^I wait for element (.*) to be visible")
    public void waitForElementVisible(String element)throws Exception{

        functions.waitForElementVisible(element);

    }


    @Then("I check if (.*) error message is (.*)")
    public void iCheckIfEmailErrorErrorMessageIsFalse(String element , String state) throws Exception {

        boolean Actual = functions.isElementDisplayed(element);
        Assert.assertEquals("El estado es diferente al esperado ",Actual,Boolean.valueOf(state));
    }

    @And("I switch to Frame: (.*)")/**esto es para entrar al frema que le pasemos ***/
    public void iSwitchToFrame(String frame) throws Exception{
        functions.switchToFrame(frame);
    }

    @And("I switch to Frame frame")
    public void iSwitchToFrameFrame() {/***esta es para volver al cuerpo primcipal de la pagina***/
        functions.switchToParentFrame();
    }


    @And("click on the (.*?)")
    public void clickOnTheGenaroM(String element)throws Exception {

        functions.checkCheckbox(element);

    }

    @When("I uncheck the checkbox (.*?)")
    public void iUncheckTheCheckboxGenaroM(String element)throws Exception {

        functions.UncheckCheckbox(element);
    }
    /***i click on JS  element ***/
    @And("^I click in JS element (.+)")
    public void ClickJSElement(String element) throws Exception{

        functions.ClickJSElement(element);

    }
    /****Scroll to on element****/
    @And("^I scroll to element (.+)")
    public void scrollToElement(String element)throws Exception{

        functions.scrollToElement(element);

    }
    /****Scroll to on element****/
    @And("^I scroll (top|end) page")
    public void scrollPage(String element)throws Exception{

        functions.scrollPage(element);

    }
    @Given("^I open new tab with URL (.*)")
    public void OpenNewTabWithURL(String URL){

        functions.OpenNewTabWithURL(URL);

    }

    @When("^I switch to new windows")
    public void swishNewWindows(){
        System.out.println(driver.getWindowHandles());
        for (String winHandle :driver.getWindowHandles()){

            System.out.println(winHandle);
            log.info("Switching to new windows");
            driver.switchTo().window(winHandle);
        }
    }

    @When("^I go to (.*?) window")
    public void WindowsHandle(String WindowaName){

        functions.WindowsHandle(WindowaName);

    }
    @And("^I wait (.*) seconds")
    public void iainSeconds(int seconds)throws Exception{/***esto es para conjela o mejor dicho para el programa**/

        int secs = seconds * 100;//aqui si multiplica por que el tiempo para ,cojelar el programa es por milis segundos
        //port eso lo multiplicamos por 100 para que sea ejemplo 1000 esos son milis segundo cuando lo multiplicamos
        //por 100 se vuelve un 1 segundo
        Thread.sleep(secs);

    }

    /**Handle and accpt a alerts**/
    @Then("^I (accept|dismiss) alerts")
    public void AcceptAlert(String want){

        functions.AcceptAlert(want);

    }

    @And("I wait site is loaded")
    public void iWaitSiteIsLoaded() {
        functions.page_has_loaded();
    }

    @And("I take screenshot: (.*?)")
    public void iTakeScreenshotHolyScreen(String TestCaptura ) throws IOException{
        functions.ScreenShot(TestCaptura);
    }

    @And("I attach a Screenshot to Report : (.*)")
    public  void attachScreenshots(String TestCaptura){

        functions.attachScreenShot(TestCaptura);
    }



    @Then("Assert if (.*?) contains text (.*)")/***esta valida una parte del texto  **/
    public void assertIfContainsText(String element , String text) throws Exception{

        functions.checkPartialTextElementPresent(element, text);
    }

    @Then("Assert if (.*?) is equal to (.*)")
    public void assertIfIsEqualTo(String element , String text) throws Exception {/**esta es valida todo el text si el texto
     esta incompleto dara error **/
        functions.checkTextElementEqualTo(element,text);

    }

    @Then("check (.*?) no is this text  (.*)")
    public void checkEmailErrorNoIsThisText(String element , String text) throws Exception{
        functions.checkPartialTextElementNotPresent(element,text);
    }

    /**Assert if element precente **/
    @Then("^Assert if (.*) is Displayed")/**comprobar que el elemento este visible **/
    public void checkIfElementIsPresent (String element)throws  Exception{

        boolean isDisplayed = functions.isElementDisplayed(element);
        Assert.assertTrue("Element is not present : "+element,isDisplayed);


    }
    /**Assert if element precente **/
    @Then("^Check if (.*?) NOT is Displayed$")/**comprobar que el elemento no este visible **/
    public void checkIfElementNotPresent (String element)throws  Exception{

        boolean isDisplayed = functions.isElementDisplayed(element);
        Assert.assertFalse("Element is not present : "+element,isDisplayed);


    }
}

