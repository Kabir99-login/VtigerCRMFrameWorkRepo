package genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	public int genRandomNumber()
	{
		  Random r=new Random();
          return r.nextInt();
	}
	
	public String currentDate()
	{
		Date date=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("MM-dd-yyyy");
		String currentDate=sim.format(date);
		return currentDate;
	}

}
