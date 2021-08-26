package Functions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverFactory {
    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");
    private static String resourceFolder ;

    /*** Log Atribute ***/
    private static Logger log = Logger.getLogger(WebDriverFactory.class);

    private static WebDriverFactory instance = null ;


    public static WebDriver createNewWebDriver(String browser ,String os ) throws IOException {

        WebDriver driver ;

        prop.load(in);

        resourceFolder = prop.getProperty("resourceFolder");


          if ("CHROME".equalsIgnoreCase(browser)){
            if ("WINDOWS".equalsIgnoreCase(os)){

                System.setProperty("webdriver.chrome.driver" , resourceFolder + os + "/chromedriver.exe");


            }
            else {

                System.setProperty("webdriver.chrome.driver" , resourceFolder + os + "/chromedriver");

            }
            driver = new ChromeDriver();
        }

        else if("FIREFOX".equalsIgnoreCase(browser)){
            if ("WINDOWS".equalsIgnoreCase(os)){

                System.setProperty("webdriver.gecko.driver" , resourceFolder + os + "/geckodriver.exe");
            }
            else {

                System.setProperty("webdriver.gecko.driver" , resourceFolder + os + "/geckodriver");

            }
            driver = new FirefoxDriver();

        }


        else if ("INTERNET EXPLORER".equalsIgnoreCase(browser)){

            System.setProperty("webdriver.ie.driver",resourceFolder+os+"/IEDriverServer.exe" );
            driver = new InternetExplorerDriver();

        }
        else{

            log.error("internet explore "+browser+ ", "+ os);
            return null;


        }


        driver.manage().window().maximize();

        return driver;


    }

}
