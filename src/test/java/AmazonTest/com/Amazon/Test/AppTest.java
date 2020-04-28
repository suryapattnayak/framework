package AmazonTest.com.Amazon.Test;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.Amazon.ExcelReader.Exelreader;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	/**
	 * Rigorous Test :-)
	 */
	@Test(dataProvider="testData")
	public void runTest(String testCase,String browserName,String urL,String word)
	{
		TestNG myTestNG = new TestNG(); 

		XmlSuite mySuite = new XmlSuite(); 
		mySuite.setName("MySuite"); 

		XmlTest test = new XmlTest(mySuite);
		test.setName("TmpTest");
		
		List<XmlClass> classes = new ArrayList<XmlClass>();
	    classes.add(new XmlClass("com.Amazon.Test."+testCase));
	    test.setXmlClasses(classes) ;  
	    test.addParameter("browserName", browserName);
	    test.addParameter("Url", urL);
	    
	    List<XmlSuite> suites = new ArrayList<XmlSuite>();
	    suites.add(mySuite);
	    
	    myTestNG.setXmlSuites(suites);
	    myTestNG.run();
	}

	@DataProvider(name="testData")
	public Object[][] getData() 
	{
		String projectPath=System.getProperty("user.dir");
		String excelpath=projectPath+"/excel/data.xlsx";
		return getExceldata(excelpath,"Sheet1");

	}

	private Object[][] getExceldata(String excelpath, String sheetName) {
		// TODO Auto-generated method stub
		
		Exelreader excel=new Exelreader(excelpath, sheetName);
		
		int rowCount = excel.rowCount();
		int colCount = excel.colCount();

		Object data[][] = new Object[rowCount-1][colCount];

		for(int i=1;i<rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				String cellData = excel.getSheetData(i,j);
				data[i-1][j] = cellData;
			}
		}
		return data;
	}
	
}
