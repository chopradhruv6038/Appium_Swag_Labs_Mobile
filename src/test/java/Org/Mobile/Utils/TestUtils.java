package Org.Mobile.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    public static final long WAIT = 10;


public String  getDateTime(){

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    Date date = new Date();
    System.out.println(dateFormat.format(date));
    return dateFormat.format(date);


}


}
