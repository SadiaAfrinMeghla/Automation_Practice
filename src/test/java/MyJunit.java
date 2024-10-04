import dev.failsafe.internal.util.Assert;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunit {

        WebDriver driver;
        @BeforeAll
        public void setup(){

            ChromeOptions options= new ChromeOptions();
            options.addArguments("--headed");
            driver = new ChromeDriver();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));


        }
    @DisplayName("Get Website Title")
    @Test
    public void getTitle(){
        driver.get("https://demoqa.com/");
        String titleActule =driver.getTitle();
        System.out.println(titleActule);
        String titleExpected = "DEMOQA";


        Assertions.assertTrue(titleActule.contains("DEMOQA"));
        Assertions.assertEquals(titleExpected,titleActule);

    }
@Test
    public void submitForm(){
            driver.get("https://demoqa.com/text-box");
             //driver.findElement(By.id("userName")).sendKeys("Meghla");
            //driver.findElement(By.cssSelector("[type=email]")).sendKeys("sadiameghlaa@gmail.com");

List<WebElement> formControls =  driver.findElements(By.className("form-control"));
formControls.get(0).sendKeys("Meghla");
formControls.get(1).sendKeys("sadiameghlaa@gmail.com");
formControls.get(2).sendKeys("Master Para ");
formControls.get(3).sendKeys("Naogaon");
Utils.scroll( driver , 600);
driver.findElement(By.id("submit")).click();
    }
@Test
    public void clickButton(){
            driver.get("https://demoqa.com/buttons");
            driver.findElements(By.cssSelector("[type=button]"));
            List<WebElement> buttonElements= driver.findElements(By.cssSelector("[type=button]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(buttonElements.get(1)).perform();
        actions.contextClick(buttonElements.get(2)).perform();
        actions.click(buttonElements.get(3)).perform();
        }
@Test
        public void handleAlart() throws InterruptedException {
            driver.get("https://demoqa.com/alerts");
//            driver.findElement(By.id("alartButton")).click();
//            Thread.sleep(6000);
//            driver.switchTo().alert().accept();

    Utils.scroll(driver,600);
//
//            driver.findElement(By.id("confirmButton")).click();
//            driver.switchTo().alert().accept();
//           // Thread.sleep(3000);
//            driver.findElement(By.id("confirmButton")).click();
//            driver.switchTo().alert().dismiss();

            driver.findElement(By.id("promtButton")).click();
            driver.switchTo().alert().sendKeys("Meghla");
            driver.switchTo().alert().accept();
            Thread.sleep(3000);
            String text =driver.findElement(By.id("promptResult")).getText();

        }
@Test
        public void selectDate(){
            driver.get("https://demoqa.com/date-picker");
            WebElement calendarElen=driver.findElement(By.id("datePickerMonthYearInput"));
    calendarElen.click();
    calendarElen.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
    calendarElen.sendKeys(Keys.ENTER);
        }
@Test
public void selectDropdown(){
            driver.get("https://demoqa.com/select-menu");
            //Select select= new Select(driver.findElement(By.id("oldSelectMenu")));
//    select.selectByVisibleText("Black");
    WebElement dropdownElen=driver.findElement(By.id("oldSelectMenu"));
    dropdownElen.click();
    dropdownElen.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);



}
@Test
public void selectDropDown2(){
    driver.get("https://demoqa.com/select-menu");
    WebElement dropdownElen= driver.findElement(By.id("withOptGroup"));
    dropdownElen.click();
    Actions actions1=new Actions(driver);
    actions1.keyDown(Keys.ARROW_DOWN).perform();
    actions1.sendKeys(Keys.ENTER).perform();

}
//    @Test
//    public void selectMultipleOptions() throws InterruptedException {
//        driver.get("https://demoqa.com/select-menu"); // Update with the actual URL
//
//
//Utils.scroll(driver , 600);
//Thread.sleep(5000);
//        List<WebElement> selectInput = driver.findElements(By.className("css-yk16xz-control"));
//        selectInput.get(2).click();
//
//        Actions actions = new Actions(driver);
//        List<WebElement> options = driver.findElements(By.className("css-yk16xz-control"));
//
//
////        Select select= new Select(driver.findElement(By.className("css-yk16xz-control")));
////        select.selectByVisibleText("Black");
//
//
//    }
@Test
    public void mouseHover(){
            driver.get("https://www.aiub.edu/");
            Actions  actions= new Actions(driver);
            List<WebElement> menuElmn =  driver.findElements(By.xpath("//a[contains(text(),\"About\")]"));
            actions.moveToElement(menuElmn.get(1)).perform();

    }

@Test
public void takeScreenShot() throws IOException {
            driver.get("https://demoqa.com/");
    File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
    String fileWithPath = "./src/test/resources/screenshots/" + time + ".png";
    File DestFile = new File(fileWithPath);
    FileUtils.copyFile(screenshotFile, DestFile);


}
@Test
    public void modalDialog(){
        driver.get("https://demoqa.com/modal-dialogs");
        driver.findElement(By.id("showSmallModal")).click();
        String text= driver.findElement(By.className("modal-body")).getText();
        System.out.println(text);
        driver.findElement(By.id("closeSmallModal")).click();
    }
//@Test
//    public void uploadImage() {
//    driver.get("https://demoqa.com/upload-download");
//    driver.findElement(By.id("uploadFile")).sendKeys("c://badge.png");
//    driver.findElement(By.id("downloadButton")).click();
//}


//    @AfterAll
//    public void closeBrowser(){
//        driver.quit();
    }
