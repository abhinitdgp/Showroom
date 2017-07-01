import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;


public class utility 
{

//	public static String getIdOrd()
//	{
//		String id = "ORD",nextid=null;
//		File f = new  File("temp/order_id.txt");
//		FileInputStream fi=null;
//		FileOutputStream fo=null;
//		int ch=0;
//		try
//		{
//			fi = new FileInputStream(f);
//			do
//			{
//				ch = fi.read();
//				if(ch!='*')
//					id+=(char)ch;
//			}while(ch!='*');
//			int tempid = Integer.parseInt(id.substring(3));
//			tempid++;
//			if(tempid<10)
//				nextid ="000"+ new Integer(tempid).toString();
//			else if(tempid<100)
//				nextid ="00"+ new Integer(tempid).toString();
//			else
//				nextid ="0"+ new Integer(tempid).toString();
//			
//			fo= new FileOutputStream(f);
//			for (int i = 0; i < nextid.length(); i++)
//				 fo.write(nextid.charAt(i));
//			fo.write('*');
//		} 
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		try
//		{
//			fi.close();
//			fo.close();
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//		return id;
//	}
	
//	public static String getIdAccOrder()
//	{
//		String id = "AORD",nextid=null;
//		File f = new  File("temp/aord_id.txt");
//		FileInputStream fi=null;
//		FileOutputStream fo=null;
//		int ch=0;
//		try
//		{
//			fi = new FileInputStream(f);
//			do
//			{
//				ch = fi.read();
//				if(ch!='*')
//					id+=(char)ch;
//			}while(ch!='*');
//			int tempid = Integer.parseInt(id.substring(3));
//			tempid++;
//			if(tempid<10)
//				nextid ="000"+ new Integer(tempid).toString();
//			else if(tempid<100)
//				nextid ="00"+ new Integer(tempid).toString();
//			else
//				nextid ="0"+ new Integer(tempid).toString();
//			
//			fo= new FileOutputStream(f);
//			for (int i = 0; i < nextid.length(); i++)
//				 fo.write(nextid.charAt(i));
//			fo.write('*');
//		} 
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		try
//		{
//			fi.close();
//			fo.close();
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//		return id;
//	}	
	
	public static String getEnqId()
	{
		String id = "ENQ";
		File f = new  File("temp/enq_id.txt");
		FileInputStream fi=null;
		int ch=0;
		try
		{
			fi = new FileInputStream(f);
			do
			{
				ch = fi.read();
				if(ch!='*')
					id+=(char)ch;
			}while(ch!='*');
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			fi.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return id;
	}	
	
	public static void setEnqId(String id)
	{
		String nextid=null;
		File f = new  File("temp/enq_id.txt");
		FileOutputStream fo=null;
		int ch=0;
		try
		{
			int tempid = Integer.parseInt(id.substring(3));
			tempid++;
			if(tempid<10)
				nextid ="000"+ new Integer(tempid).toString();
			else if(tempid<100)
				nextid ="00"+ new Integer(tempid).toString();
			else
				nextid ="0"+ new Integer(tempid).toString();
			
			fo= new FileOutputStream(f);
			for (int i = 0; i < nextid.length(); i++)
				fo.write(nextid.charAt(i));
			fo.write('*');
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{	
			fo.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}	
	public static String getRegId()
	{
		String id = "Reg";
		File f = new  File("temp/reg_id.txt");
		FileInputStream fi=null;
		int ch=0;
		try
		{
			fi = new FileInputStream(f);
			do
			{
				ch = fi.read();
				if(ch!='*')
					id+=(char)ch;
			}while(ch!='*');
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			fi.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return id;
	}	
	
	public static void setRegId(String id)
	{
		String nextid=null;
		File f = new  File("temp/reg_id.txt");
		FileOutputStream fo=null;
		int ch=0;
		try
		{
			int tempid = Integer.parseInt(id.substring(3));
			tempid++;
			if(tempid<10)
				nextid ="000"+ new Integer(tempid).toString();
			else if(tempid<100)
				nextid ="00"+ new Integer(tempid).toString();
			else
				nextid ="0"+ new Integer(tempid).toString();
			
			fo= new FileOutputStream(f);
			for (int i = 0; i < nextid.length(); i++)
				fo.write(nextid.charAt(i));
			fo.write('*');
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{	
			fo.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}	
	
	public static String getOrdId()
	{
		String id = "ORD";
		File f = new  File("temp/order_id.txt");
		FileInputStream fi=null;
		int ch=0;
		try
		{
			fi = new FileInputStream(f);
			do
			{
				ch = fi.read();
				if(ch!='*')
					id+=(char)ch;
			}while(ch!='*');
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			fi.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return id;
	}	
	
	public static void setOrdId(String id)
	{
		String nextid=null;
		File f = new  File("temp/order_id.txt");
		FileOutputStream fo=null;
		int ch=0;
		try
		{
			int tempid = Integer.parseInt(id.substring(3));
			tempid++;
			if(tempid<10)
				nextid ="000"+ new Integer(tempid).toString();
			else if(tempid<100)
				nextid ="00"+ new Integer(tempid).toString();
			else
				nextid ="0"+ new Integer(tempid).toString();
			
			fo= new FileOutputStream(f);
			for (int i = 0; i < nextid.length(); i++)
				fo.write(nextid.charAt(i));
			fo.write('*');
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{	
			fo.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}	
	
	
	public static String getBukId()
	{
		String id = "BK";
		File f = new  File("temp/buk_id.txt");
		FileInputStream fi=null;
		int ch=0;
		try
		{
			fi = new FileInputStream(f);
			do
			{
				ch = fi.read();
				if(ch!='*')
					id+=(char)ch;
			}while(ch!='*');
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			fi.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return id;
	}	
	
	public static void setBukId(String id)
	{
		String nextid=null;
		File f = new  File("temp/buk_id.txt");
		FileOutputStream fo=null;
		int ch=0;
		try
		{
			int tempid = Integer.parseInt(id.substring(3));
			tempid++;
			if(tempid<10)
				nextid ="000"+ new Integer(tempid).toString();
			else if(tempid<100)
				nextid ="00"+ new Integer(tempid).toString();
			else
				nextid ="0"+ new Integer(tempid).toString();
			
			fo= new FileOutputStream(f);
			for (int i = 0; i < nextid.length(); i++)
				fo.write(nextid.charAt(i));
			fo.write('*');
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{	
			fo.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}	
	
	public static String getAccOrdId()
	{
		String id = "Acc-Ord";
		File f = new  File("temp/aord.txt");
		FileInputStream fi=null;
		int ch=0;
		try
		{
			fi = new FileInputStream(f);
			do
			{
				ch = fi.read();
				if(ch!='*')
					id+=(char)ch;
			}while(ch!='*');
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			fi.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return id;
	}	
	
	public static void setAccOrdId(String id)
	{
		String nextid=null;
		File f = new  File("temp/aord.txt");
		FileOutputStream fo=null;
		int ch=0;
		try
		{
			int tempid = Integer.parseInt(id.substring(3));
			tempid++;
			if(tempid<10)
				nextid ="000"+ new Integer(tempid).toString();
			else if(tempid<100)
				nextid ="00"+ new Integer(tempid).toString();
			else
				nextid ="0"+ new Integer(tempid).toString();
			
			fo= new FileOutputStream(f);
			for (int i = 0; i < nextid.length(); i++)
				fo.write(nextid.charAt(i));
			fo.write('*');
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{	
			fo.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}			
}


