package AmazonTest.com.Amazon.Test;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
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
	public void runTest(String excel,String condition,String testCase)
	{


		if(condition.equalsIgnoreCase("yes"))
		{
			String projectPath=System.getProperty("user.dir");
			String excelpath=projectPath+"/excel/"+excel;
			Exelreader ex=new Exelreader(excelpath, "Sheet1");
			int row=ex.rowCount();
			for(int i=2;i<=row;i++)
			{
				String browserName=Exelreader.wordRead("Sheet1", "Browser", i);
				String urL=Exelreader.wordRead("Sheet1", "Url", i);
				String word=Exelreader.wordRead("Sheet1", "Word", i);
				String met=Exelreader.wordRead("Sheet1", "method", i);

				TestNG myTestNG = new TestNG(); 

				XmlSuite mySuite = new XmlSuite(); 
				mySuite.setName("MySuite"); 

				XmlTest test = new XmlTest(mySuite);
				test.setName("TmpTest");

				XmlClass clas=new XmlClass("com.Amazon.Test."+testCase);
				List<XmlClass> classes = new ArrayList<XmlClass>();
				classes.add(clas);

				List<XmlInclude> include=new ArrayList<XmlInclude>();
				include.add(new XmlInclude(met));
				clas.setIncludedMethods(include);
				test.setXmlClasses(classes);

				test.addParameter("browserName", browserName);
				test.addParameter("Url", urL);
				test.addParameter("word", word);
				List<XmlSuite> suites = new ArrayList<XmlSuite>();
				suites.add(mySuite);

				myTestNG.setXmlSuites(suites);
				myTestNG.run();
			}
		}


	}

	@DataProvider(name="testData")
	public Object[][] getData() 
	{
		String projectPath=System.getProperty("user.dir");
		String excelpath=projectPath+"/excel/data.xlsx";
		return getExceldata(excelpath, "Sheet1");	

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
