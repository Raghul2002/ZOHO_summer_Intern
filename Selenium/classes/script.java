
import java.io.File;  
import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

import java.util.concurrent.TimeUnit;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class script12 {
	public static void main(String[] args) throws InterruptedException {
		try {  
			List<String> login=new ArrayList<String>(); 
			login.add("00001");
			login.add("2002-03-07");
			ArrayList<ArrayList<String> > fullList = new ArrayList<ArrayList<String>>();
			File file = new File("D:\\Eclipse\\excel.xlsx");    
			FileInputStream fis = new FileInputStream(file);   
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     
			Iterator<Row> itr = sheet.iterator();    
			while (itr.hasNext()) {  
				ArrayList<String> list = new ArrayList<String>();
				Row row = itr.next();  
				Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
				while (cellIterator.hasNext()) {  
					Cell cell = cellIterator.next();  
					list.add(cell.getStringCellValue());
				}  
				fullList.add(list);
			}

			System.setProperty("webdriver.chrome.driver","D:\\ZOHO\\Selenium\\chromedriver_win32\\chromedriver.exe"); 	//Set path of Executable Browser Driver
			WebDriver driver = new ChromeDriver();  //Parent p = new Child
			driver.manage().window().maximize();			      
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.get("http://localhost:8080/servlet/login");
			List <WebElement> elements = driver.findElements(By.tagName("input"));
			int count=0;
			for(WebElement element : elements) {
				element.sendKeys(login.get(count++));
				Thread.sleep(1000);
				if (count==2){
					break;
				}
			}
		    WebElement l=driver.findElement(By.cssSelector("input[value='admin']"));
		    l.click();
		    Thread.sleep(1000);
			driver.findElement(By.id("idSubmit")).click();
			Thread.sleep(1000);
			for (int i = 0; i < fullList.size(); i++) {
				int j=0; 
				driver.findElement(By.linkText("Add Details")).click();			//Add Details page
				Thread.sleep(1000);
				List <WebElement> addDetails = driver.findElements(By.tagName("input"));
				for(WebElement element : addDetails) {
					element.sendKeys(fullList.get(i).get(j));
					j+=1;
					Thread.sleep(500);
					if (j==10) {
						break;
					}
				}
				driver.findElement(By.id("idSubmit")).click();
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				driver.navigate().back();	
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				driver.navigate().back();	
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				Thread.sleep(500);
				
			 }
			 driver.findElement(By.linkText("View Details")).click();		//View Details page
			 Thread.sleep(3000);
			 driver.close();		  
		}  
		catch(Exception e){  
			e.printStackTrace();  
		} 
	}			

}
