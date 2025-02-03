package Project.businessLib;

import Project.pages.CalculatorPage;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;

public class ArithmaticOperations {

    static WebDriver driver;
    CalculatorPage CalculatorPage;
    public static int count;
    public void takeScreenshot(String name) {
        count++;
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("C:\\Users\\Acer\\Desktop\\Automation testing\\SS\\Screenshot_"+name+count);
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public ArithmaticOperations(WebDriver driver) {
        this.driver = driver;
        CalculatorPage = new CalculatorPage(driver);
    }

    public double performOperation(String operation, double... numbers) {

        CalculatorPage.back.click();

        double count=numbers.length;

        for (double number: numbers){
            String numberString=String.valueOf(number);

            for(int i=0; i<numberString.length();i++){
                char numberchar= numberString.charAt(i);
                if(Character.isDigit(numberchar)){
                    int digit=Character.getNumericValue(numberchar);
                    clickNumber(digit);
                }
                else{
                    clickSign(numberchar);
                }
            }
            count--;
            if (count!=0){
                clickoperation(operation);
            }
        }

        CalculatorPage.equals.click();
        String actualresult = CalculatorPage.outputField.getText().trim();
        return Double.parseDouble(actualresult);
    }

    private void clickNumber(int number) {
        switch (number) {

            case 0:
                CalculatorPage.number0.click();
                break;

            case 1:
                CalculatorPage.number1.click();
                break;

            case 2:
                CalculatorPage.number2.click();
                break;

            case 3:
                CalculatorPage.number3.click();
                break;

            case 4:
                CalculatorPage.number4.click();
                break;

            case 5:
                CalculatorPage.number5.click();
                break;

            case 6:
                CalculatorPage.number6.click();
                break;

            case 7:
                CalculatorPage.number7.click();
                break;

            case 8:
                CalculatorPage.number8.click();
                break;

            case 9:
                CalculatorPage.number9.click();
                break;
        }
    }
    private void clickoperation(String operation) {
        switch (operation) {
            case "Add":
                CalculatorPage.plus.click();
                break;

            case "minus":
                CalculatorPage.minus.click();
                break;

            case "divide":
                CalculatorPage.divide.click();
                break;

            case "multiply":
                CalculatorPage.multiply.click();
                break;
        }
    }

    public void clickSign(char sign){
        switch(sign){
            case '-':
                CalculatorPage.minus.click();
                break;
            case '.':
                CalculatorPage.point.click();
                break;
        }

    }



}
