package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileManager {
	Properties pro;

	public FileManager()
	{
		try {
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/config/config.properties");
			pro=new Properties();
			pro.load(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getApkName()
	{
		String val = pro.getProperty("GeneralStore");
		if(val==null)
			throw new RuntimeException("invalid apk file");
		return val;
	}
	
	public String getDeviceName()
	{
		String val = pro.getProperty("device");
		if(val==null)
			throw new RuntimeException("invalid device name");
		return val;
	}
}
