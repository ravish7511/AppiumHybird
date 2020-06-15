package resource;

import org.testng.annotations.DataProvider;

public class TestData {

	@DataProvider(name="inputdata")
	public Object[][] getData()
	{
		return new Object[][] {{"ravish"},{"darling"}};
	}
	
}
