import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class Style
{
	static Color panelColor = Color.getHSBColor(100, 100, 100);
//	static Color panelColor = Color.DARK_GRAY;
	static Font fhdfont = new Font("Stencil",Font.BOLD,36);
	static Font lblfont = new Font("Copperplate Gothic Bold",Font.PLAIN,16);
//	static Font lblfont = new Font("Cooper Black",Font.PLAIN,18);
//	static Font lblfont = new Font("Arial Rounded MT Bold",Font.PLAIN,18);
	static Font txtfont = new Font("Courier New",Font.PLAIN,18);
	static Font tabfont = new Font("Tempus Sans ITC",Font.BOLD,13);
	
	static Border border = BorderFactory.createLineBorder(Color.black,2);
	static Border border1 = BorderFactory.createLineBorder(null);
	
//	static Border border2 = BorderFactory.createLoweredSoftBevelBorder();
	
//	Border borde = BorderFactory.createLineBorder(Color.red,4);
	static Border border2 = BorderFactory.createTitledBorder(border, "",2,2,new Font("",Font.BOLD,12) );

	static String getDate(Date d)
	{
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String S = df.format(d);
//		System.out.println(S);
//		System.out.println(S);
		 String dd =  S.substring(0,S.indexOf(' '));
         String mmm =  S.substring(S.indexOf(' ')+1,S.lastIndexOf(','));
         String	yyyy =  S.substring(S.lastIndexOf(' ')+1);
         
         return dd+"-"+mmm+"-"+yyyy;
	}
	
	static Date setDate(String s)
	{
		Date d = new Date(s);
		return d;
		
	}
}
