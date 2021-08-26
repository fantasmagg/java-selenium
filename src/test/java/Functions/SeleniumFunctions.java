package Functions;


import StepDefinitions.Hooks;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.*;

public class SeleniumFunctions {
    WebDriver driver;
    public SeleniumFunctions(){
        driver = Hooks.driver;
    }

    public static String PagesFilePath ="src/test/resources/Pages/";

    public static String FileName = "";

    /***DOM pages/json***/
    public static String GetFieldBy = "";
    public static String ValueToFind ="";

    /**Explicits Waits**/
    public static int EXPLICIT_TIMEDUT = 15;
    public static String ElementText = "";
    public static boolean isDisplayed= Boolean.parseBoolean(null);

    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");


    /***    Scenario test Data***/
    public static Map<String , String> ScenaryData = new HashMap<>();
    public static Map<String , String> HandleMyWindows = new HashMap<>();
    public static String Environment ="";

    /**log**/
    private static Logger log = Logger.getLogger(SeleniumFunctions.class);

    public static  Object readJson() throws Exception {/***this is metodo is en read the file in pages ***/
        FileReader reader = new FileReader(PagesFilePath + FileName);

        try{

            if (reader != null){

                JSONParser jsonParser = new JSONParser();
                return jsonParser.parse(reader);

            }else {

                return null;
            }

        }catch (FileNotFoundException | NullPointerException e){

            log.error("ReadEntity No existe el archivo "+ FileName);
            throw new IllegalStateException("ReadEntity No existe el archivo "+ FileName , e);

        }
    }

    public static JSONObject ReadEntity (String element) throws Exception{
        JSONObject Entity = null;

        JSONObject jsonObject = (JSONObject) readJson();
        Entity = (JSONObject) jsonObject.get(element);
        log.info(Entity.toJSONString());

        return Entity;

    }
    public static  By getCompleteElement(String element) throws Exception{

        By result = null;
        JSONObject Entity = ReadEntity(element);

        GetFieldBy = (String) Entity.get("GetFieldBy");
        ValueToFind =(String) Entity.get("ValueToFind");

        if ("className".equalsIgnoreCase(GetFieldBy)){
            result = By.className(ValueToFind);
        }else if ("cssSelector".equalsIgnoreCase(GetFieldBy)){
            result = By.cssSelector(ValueToFind);

        }else if ("id".equalsIgnoreCase(GetFieldBy)){
            result = By.id(ValueToFind);
        }else if ("linkText".equalsIgnoreCase(GetFieldBy)){
            result = By.linkText(ValueToFind);

        }else if("name".equalsIgnoreCase(GetFieldBy)){
            result = By.name(ValueToFind);
        }else if ("link".equalsIgnoreCase(GetFieldBy)){

            result = By.partialLinkText(ValueToFind);
        }else if ("tagName".equalsIgnoreCase(GetFieldBy)){
            result = By.tagName(ValueToFind);

        }else if ("xpath".equalsIgnoreCase(GetFieldBy)){
            result = By.xpath(ValueToFind);
        }
        return result;
    }
    public String readProperties(String property) throws IOException {
        prop.load(in);
        return prop.getProperty(property);

    }
    public void SaveInScenario(String key,String text){

        if (!this.ScenaryData.containsKey(key)){
            this.ScenaryData.put(key,text);
            log.info(String.format("Save as Scenario context key : %s with value: %s ",key,text));
            System.out.println(String.format("Save as Scenario context key : %s with value: %s ",key,text));
        }else {
            this.ScenaryData.replace(key, text);
            log.info(String.format("Update Scenario context key : %s with value %s ",key,text));


        }

    }
    public void RetriveTestData(String parameter) throws IOException{
        Environment = readProperties("Environment");
        try {
            SaveInScenario(parameter ,readProperties(parameter+"."+Environment));
          //  System.out.println("Este es el valor de la parametrisasion " +parameter+ " : "+this.ScenaryData.get(parameter));
        }catch (IOException e){

            e.printStackTrace();
        }

    }
    public void iSetElementWithKeyValue(String element, String key )throws Exception{

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        boolean exist = this.ScenaryData.containsKey(key);
        if (exist){
            String text =this.ScenaryData.get(key);
            driver.findElement(SeleniumElement).sendKeys(text);
            log.info(String.format("Set on element %s with text %s", element,text));
            System.out.println(String.format("Set on element %s with text %s", element,text));

        }else {

            Assert.assertTrue(String.format("The given key $s do not exist in context ",key),this.ScenaryData.containsKey(key));
        }

    }

    public ISelect selectOption(String element)throws Exception{/**esto es para poder
     mani pular las lestas desplegables***/

        By SeleniumElment = SeleniumFunctions.getCompleteElement(element);
        log.info(String.format("Waiting Element %s",element));
        Select opt = new Select(driver.findElement(SeleniumElment));
        System.out.println("Element: "+element);
        return opt;

    }
    public void waitForElementPresent(String element)throws Exception{

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver,EXPLICIT_TIMEDUT);
        log.info("Waiting for the element : "+element+" to be present");
        wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));


    }
    public void waitForElementVisible(String element)throws Exception{

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver,EXPLICIT_TIMEDUT);
        log.info("Waiting for the element : "+element+" to be present");
        wait.until(ExpectedConditions.visibilityOfElementLocated(SeleniumElement));


    }
    public void waitForElementCliqueable(String element)throws Exception{

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver,EXPLICIT_TIMEDUT);
        log.info("Waiting for the element : "+element+" to be present");
        wait.until(ExpectedConditions.elementToBeClickable(SeleniumElement));


    }

    public void switchToFrame(String Frame) throws Exception{/**es to es para entrar al frame ***/

        By SeleniumElement = SeleniumFunctions.getCompleteElement(Frame);
        log.info("Switching to frame : " + Frame);
        driver.switchTo().frame(driver.findElement(SeleniumElement));

    }
    public void switchToParentFrame(){/**esto es para salir al cuerpo principal de la pagina***/


        log.info("Switching to parent frame ");
        driver.switchTo().parentFrame();

    }
    public void checkCheckbox(String element)throws Exception{/*** esto es para los check y esas cosas ***/

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        boolean isChecked = driver.findElement(SeleniumElement).isSelected();
        System.out.println(isChecked);
        if (!isChecked){/**este if hace lo siguiente si lo que la , si la varible boolean (isChecked) de vuelve falso pasa al if
         y if dice si es diferente a true pues entra y hace click al elemento **/

            log.info("Clicking on the checkbox to select: "+ element);
            driver.findElement(SeleniumElement).click();

        }

    }
    public void UncheckCheckbox (String element)throws Exception{

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        boolean isChecked = driver.findElement(SeleniumElement).isSelected();
        if (isChecked){
            log.info("Clicking on the checkbox to select: "+ element);
            driver.findElement(SeleniumElement).click();

        }

    }

    public void ClickJSElement(String element)throws Exception{

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        log.info("click to element with: " + element);
        jse.executeScript("arguments[0].click()",driver.findElement(SeleniumElement));

    }
    public void scrollPage (String to)throws Exception{/**esta es para hacer scroll solo **/

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        if (to.equals("top")){
            log.info("Scrolling to the end the page");
            jse.executeScript("scroll(0,-250);");

        }
        else if (to.equals("end")){
            log.info("Scrolling to the end the page");
            jse.executeScript("scroll(0,-250);");

        }

    }
    public void scrollToElement (String element)throws Exception{

        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        log.info("Scrolling to element: "+ element);
        jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(SeleniumElement));
    }

    public void page_has_loaded (){/**esto es para que el codigo se pare y espere que la pagina alla cargado completamente ***/

        String GetActual = driver.getCurrentUrl();
        System.out.println(String.format("Checking if %s page is loaded .", GetActual));
        log.info(String.format("Checking if %s page is loaded .", GetActual));

        new WebDriverWait(driver , EXPLICIT_TIMEDUT).until(

                WebDriver ->((JavascriptExecutor)WebDriver).executeScript("return document.readyState").equals("complete")
        );

    }
    public void OpenNewTabWithURL(String URL){/**esto es para abrir otra ventana **/

        log.info("Open New tab With Url : "+URL);
        System.out.println("Open New tab With Url : "+URL);
        JavascriptExecutor jse =(JavascriptExecutor)driver;
        jse.executeScript(String.format("window.open('%s','_blank');",URL));
    }
    public void WindowsHandle (String WindowsName){/**esto lo que hace es alamacenar los nombres del las ventanas que abramos por orden ***/

        if(this.HandleMyWindows.containsKey(WindowsName)){
            driver.switchTo().window(this.HandleMyWindows.get(WindowsName));
            log.info(String.format("I go to Windows: %s with value: %s ",WindowsName ,this.HandleMyWindows.get(WindowsName)));
        }else{
            for(String windHandle : driver.getWindowHandles()){
                this.HandleMyWindows.put(WindowsName,windHandle);
                System.out.println("The new window "+ WindowsName+" is saved in scenario with value "+this.HandleMyWindows.get(WindowsName));
                log.info("The new window "+ WindowsName+" is saved in scenario with value "+this.HandleMyWindows.get(WindowsName));
                driver.switchTo().window(this.HandleMyWindows.get(WindowsName));
            }

        }
    }

    public void AcceptAlert(String want){/**esto es para las alertas el los sitios web**/

        try{

            WebDriverWait wait =  new WebDriverWait(driver,EXPLICIT_TIMEDUT);
            Alert alert= wait.until(ExpectedConditions.alertIsPresent());
            System.out.println(alert.getText());
            ElementText = alert.getText();
            if (want =="accpet"){
                alert.accept();
                System.out.println("Alert accept ");
            }
            else {

                alert.dismiss();
                System.out.println("Alert dismiss ");
            }
            log.info("The alert was accepted successfully.");

        }catch (Throwable e){

            log.error("Error came while waiting for the alert popup. "+e.getMessage());

        }

    }
   /** public void dismissAlert(){

        try {
            WebDriverWait wait =  new WebDriverWait(driver,EXPLICIT_TIMEDUT);
            Alert alert= wait.until(ExpectedConditions.alertIsPresent());
            System.out.println(alert.getText());
            alert.dismiss();
            log.info("The alert was accepted successfully.");
        }catch (Throwable e){

            log.error("Error came while waiting for the alert popup. "+e.getMessage());

        }


    }*/

   public void ScreenShot(String TestCaptura) throws IOException{

       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmm");
       String screenShotName = readProperties("ScreenShotPath")+ "\\" +readProperties("browser")+"\\"+ TestCaptura +"_(" + dateFormat.format(GregorianCalendar.getInstance().getTime())+")";

       File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       log.info("Screenshot saved as:"+ screenShotName);

       FileUtils.copyFile(scrFile, new File(String.format("%s.png",screenShotName)) );

   }
   public byte[] attachScreenShot(String TestCaptura){

       log.info("Attaching Screenshot");
       byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
       Allure.addAttachment(TestCaptura, new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
       return screenshot;

   }
   public String GetTextElement(String element)throws Exception{/***esto es para captura algun texto ***/

       By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
       WebDriverWait wait = new WebDriverWait(driver,EXPLICIT_TIMEDUT);
       wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));

       log.info(String.format("Esperando el elemento: %s",element));

       ElementText = driver.findElement(SeleniumElement).getText();

        return ElementText;
   }

   public void checkPartialTextElementNotPresent(String element,String text)throws Exception{/**este es para validar una parte del texteto OJO puede
    ser la parte que nosotros queramos ***/

       ElementText = GetTextElement(element);/**aqui obtenemos el texto**/

       boolean isFoundFalse = ElementText.indexOf(text) !=-1? true: false;/** aqui preguntamos si parte del texto que hemos obtenido coinside con lo
        que le pasamos  devolvera -1 = true  0=false**/
       Assert.assertFalse("Text is present in element : "+ element+" current text is : "+ElementText, isFoundFalse);
   }
   public void  checkPartialTextElementPresent(String element , String text) throws Exception{/**este es para validar una parte del texteto OJO puede
    ser la parte que nosotros queramos ***/

       ElementText = GetTextElement(element);

       boolean isFound = ElementText.indexOf(text) !=-1? true: false;/** aqui preguntamos si parte del texto que hemos obtenido coinside con lo
        que le pasamos  devolvera -1 = true  0=false**/
       Assert.assertTrue("Text is not present in element : "+ element+" current text is : "+ElementText,isFound);

   }
   public void checkTextElementEqualTo (String element,String text)throws Exception{

       ElementText = GetTextElement(element);
       Assert.assertEquals("Text is not present in element : "+ element+" current text is :  "+ElementText,text,ElementText);
   }


    public boolean isElementDisplayed(String element)throws  Exception{/**esto es para validar si un valor esta **/


        try{
            By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
            log.info(String.format("Waiting Element: %s",element));
            WebDriverWait wait = new WebDriverWait(driver,EXPLICIT_TIMEDUT);
            isDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement)).isDisplayed();
        }catch(NoSuchElementException | TimeoutException e){

            isDisplayed = false;

            log.info(e);

        }
        log.info(String.format("%s visibility is: %s",element,isDisplayed));
        System.out.println(String.format("%s visibility is: %s",element,isDisplayed));
        return isDisplayed;
    }


}
