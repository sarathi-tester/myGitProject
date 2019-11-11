package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil 
{
	public static String getValueForKey(String Key) throws Throwable, IOException
	{
		Properties configProperties=new Properties();
		configProperties.load(new FileInputStream(new File("D:\\OjtEvening\\ERP_Maven2\\PropertiesFile\\Environment.properties")));
		return configProperties.getProperty(Key);
		
	}
}
