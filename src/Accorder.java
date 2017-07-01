import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Accorder implements ActionListener,KeyListener,FocusListener,ItemListener
{
	JFrame f;
	public Accorder()
	{
		f = new JFrame("Automobile Showroom Management System");
		frameDesign();
		f.setVisible(true);
		f.setSize(1024, 725);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	JPanel sp,center;
	JButton button[] = new JButton[4];
	String btext[] = {"Submit","Add","Reset","Exit"};
	Image im = new ImageIcon("motorcycle-background-1867523.jpg").getImage();

	private void frameDesign()
	{
		center = new JPanel();
		sp = new JPanel();
//		center.setOpaque(false);
		center.setBackground(Style.panelColor);
		sp.setBackground(Color.DARK_GRAY);
		
		for (int i = 0; i < button.length; i++)
		{
			button[i] = new JButton(btext[i]);
			button[i].addActionListener(this);
			sp.add(button[i]);
		}
		button[0].setEnabled(false);
		button[2].setEnabled(false);
		centerDesign();
		f.add(center);
		f.add(sp,"South");
	}
	
	
	JLabel head,oid,odt,otdt;
	JTextField toid,todt,totdt;
	JPanel opanel;
	JButton cal;
	
	private void centerDesign()
	{
		center.setLayout(null);
		head = new JLabel("Accessories Order");
		head.setBounds(320, 20, 450, 30);
		head.setFont(Style.fhdfont);
	//	center.setBackground(Style.panelColor);
		center.add(head);
		
		oid = new JLabel("Order ID");
		odt = new JLabel("Order Date");
		otdt = new JLabel("Tentative Date of Delivery");
		
		oid.setFont(Style.lblfont);
		oid.setBounds(380, 80, 150, 30);
		center.add(oid);
		
		odt.setFont(Style.lblfont);
		odt.setBounds(150, 130, 150, 30);
		center.add(odt);
		
		otdt.setFont(Style.lblfont);
		otdt.setBounds(540, 130, 250, 30);
		center.add(otdt);
		
		toid = new JTextField();
		todt = new JTextField();
		totdt = new JTextField();
		
		toid.setBounds(460, 80, 150, 30);
		toid.setEditable(false);
		center.add(toid);
		
		todt.setBounds(270, 130, 140, 30);
		todt.setFont(Style.txtfont);
		todt.setEditable(false);
		todt.setText(getDate());
		center.add(todt);
		
		totdt.setBounds(790, 130, 140, 30);
		totdt.setFont(Style.txtfont);
		totdt.setEditable(false);
		center.add(totdt);
		
		int n = center.getComponentCount();
		for (int i = 0; i < n; i++)
		{
			Component c = center.getComponent(i);
			if(c instanceof JTextField)
				((JTextField) c).setBorder(Style.border1);
		}
		cal = new JButton(new ImageIcon("cal1.gif"));
		cal.setBounds(935, 134, 20, 20);
		cal.addActionListener(this);
		center.add(cal);
		opanelDesign();	
	}
	
	
	JLabel sno,accid,accname,mdlname,clr,prc,qty,ttl,net;
	JComboBox caccid,cclr;
	JTextField tsno,taccname,tmdlname,tprc,tqty,tttl,netqty,netttl;
	List lsno,laccid,laccname,lmdlname,lclr,lprc,lqty,lttl;
	JButton add,sub;
	
	private void opanelDesign()
	{
		opanel = new JPanel();
		opanel.setLayout(null);
		opanel.setBorder(Style.border1);
		opanel.setOpaque(false);
		opanel.setBounds(40, 190, 950, 400);
		center.add(opanel);
		
		sno = new JLabel("S.No.");
		accid = new JLabel("Accesory ID");
		accname = new JLabel("Accessory Name");
		mdlname = new JLabel("Model Name");
		clr = new JLabel("Color");
		prc = new JLabel("Price(Rs.)");
		qty = new JLabel("Quantity");
		ttl = new JLabel("Total(Rs.)");
		net = new JLabel("Net Total");
		
		tsno = new JTextField();
		taccname = new JTextField();
		tmdlname = new JTextField();
		tprc = new JTextField();
		tqty = new JTextField();
		tttl = new JTextField();
		netqty = new JTextField();
		netttl = new JTextField();
		
		caccid = new JComboBox();
		cclr = new JComboBox();
		
		lsno = new List();
		laccid = new List();
		laccname = new List();
		lmdlname = new List();
		lclr = new List();
		lprc = new List();
		lqty = new List();
		lttl = new List();
		
		add = new JButton("+");
		sub = new JButton("-");
		
		sno.setFont(Style.lblfont);
		sno.setBounds(10, 10, 50, 30);
		opanel.add(sno);
		
		tsno.setBounds(10, 40, 40, 30);
		tsno.setFont(Style.txtfont);
		opanel.add(tsno);
		
		lsno.setBounds(10, 90, 40, 200);
		lsno.setFont(Style.txtfont);
		opanel.add(lsno);
		
		accid.setFont(Style.lblfont);
		accid.setBounds(80, 10, 120, 30);
		opanel.add(accid);
		
		caccid.setBounds(70, 40, 120, 30);
		caccid.setFont(Style.txtfont);
		opanel.add(caccid);
		
		laccid.setBounds(70, 90, 120, 200);
		laccid.setFont(Style.txtfont);
		opanel.add(laccid);
		
		accname.setFont(Style.lblfont);
		accname.setBounds(200, 10, 150, 30);
		opanel.add(accname);
		
		taccname.setBounds(210, 40, 130, 30);
		taccname.setFont(Style.txtfont);
		taccname.setEditable(false);
		opanel.add(taccname);
		
		laccname.setBounds(210, 90, 130, 200);
		laccname.setFont(Style.txtfont);
		opanel.add(laccname);
		
		mdlname.setFont(Style.lblfont);
		mdlname.setBounds(355, 10, 120, 30);
		opanel.add(mdlname);
		
		tmdlname.setBounds(360, 40, 100, 30);
		tmdlname.setFont(Style.txtfont);
		opanel.add(tmdlname);
		
		lmdlname.setBounds(360, 90, 100, 200);
		lmdlname.setFont(Style.txtfont);
		opanel.add(lmdlname);
		
		clr.setFont(Style.lblfont);
		clr.setBounds(500, 10, 100, 30);
		opanel.add(clr);
		
		cclr.setBounds(470, 40, 100, 30);
		cclr.setFont(Style.txtfont);
		opanel.add(cclr);
		
		lclr.setBounds(470, 90, 100, 200);
		lclr.setFont(Style.txtfont);
		opanel.add(lclr);
		
		prc.setFont(Style.lblfont);
		prc.setBounds(575, 10, 100, 30);
		opanel.add(prc);
		
		tprc.setBounds(575, 40, 90, 30);
		tprc.setFont(Style.txtfont);
		tprc.setEditable(false);
		opanel.add(tprc);
		
		lprc.setBounds(575, 90, 90, 200);
		lprc.setFont(Style.txtfont);
		opanel.add(lprc);
		
		net.setFont(Style.lblfont);
		net.setBounds(585, 320, 100, 30);
		opanel.add(net);
		
		qty.setFont(Style.lblfont);
		qty.setBounds(685, 10, 100, 30);
		opanel.add(qty);
		
		tqty.setBounds(675, 40, 90, 30);
		tqty.setFont(Style.txtfont);
		opanel.add(tqty);
		
		lqty.setBounds(675, 90, 90, 200);
		lqty.setFont(Style.txtfont);
		opanel.add(lqty);
		
		netqty.setBounds(675, 320, 90, 30);
		netqty.setFont(Style.txtfont);
		netqty.setEditable(false);
		opanel.add(netqty);
		
		ttl.setFont(Style.lblfont);
		ttl.setBounds(780, 10, 100, 30);
		opanel.add(ttl);
		
		tttl.setBounds(770, 40, 100, 30);
		tttl.setFont(Style.txtfont);
		tttl.setEditable(false);
		opanel.add(tttl);
		
		lttl.setBounds(770, 90, 100, 200);
		lttl.setFont(Style.txtfont);
		opanel.add(lttl);
		
		netttl.setBounds(770, 320, 100, 30);
		netttl.setFont(Style.txtfont);
		netttl.setEditable(false);
		opanel.add(netttl);
		
		add.setBounds(885, 70, 50, 50);
		add.setEnabled(false);
		opanel.add(add);
		
		sub.setBounds(885, 130, 50, 50);
		sub.setEnabled(false);
		opanel.add(sub);
		
		int n = opanel.getComponentCount();
		for (int i = 0; i < n; i++)
		{
			Component c = opanel.getComponent(i);
			if(c instanceof JTextField)
				((JTextField) c).setBorder(Style.border1);
		}
		
	}
	private String getDate()
	{
		Date d = new Date();
		DateFormat d1 = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return d1.format(d);
	}
	public static void main(String[] args)
	{
		new Accorder();

	}
	
	Statement stmt,stmt1;
	ResultSet rs,rs1;
	int netq,netto;
	@Override
	public void actionPerformed(ActionEvent arg0)
	{

		if(arg0.getSource() == add)
		{
			lsno.addItem(tsno.getText());
			laccid.addItem(caccid.getSelectedItem().toString());
			lmdlname.addItem(tmdlname.getText());
			lclr.addItem(cclr.getSelectedItem().toString());
			lprc.addItem(tprc.getText());
			lqty.addItem(tqty.getText());
			lttl.addItem(tttl.getText());
			
			int n = lqty.getItemCount();
			netq = 0;
			netto = 0;
			for (int i = 0; i < n; netq+=Integer.valueOf(lqty.getItem(i)),i++);
				netqty.setText(String.valueOf(netq));
				
			int m = lttl.getItemCount();
			for (int i = 0; i < m; netto+=Long.valueOf(lttl.getItem(i)),i++);
				netttl.setText(String.valueOf(netto));
			button[0].setEnabled(true);
			netqty.setText(Integer.toString(netq));
			
			
			tsno.setText("");
			caccid.setSelectedIndex(0);
			tmdlname.setText("");
			cclr.setSelectedIndex(0);
			tprc.setText("");
			tqty.setText("");
			tttl.setText("");
		}
		
		if(arg0.getSource() == sub)
		{
			int n = lsno.getItemCount();
			lsno.remove(n-1);
			laccid.remove(n-1);
			lmdlname.remove(n-1);
			lclr.remove(n-1);
			lprc.remove(n-1);
			lqty.remove(n-1);
			lttl.remove(n-1);
			
			int count = lsno.getItemCount();
			if(count == 0)
				button[0].setEnabled(false);
		}
		if(arg0.getSource() == button[3])
			f.dispose();
		
		if(arg0.getSource() == button[2])
		{
			toid.setText("");
			todt.setText("");
			totdt.setText("");
			tsno.setText("");
			caccid.setSelectedIndex(0);
			tmdlname.setText("");
			tprc.setText("");
			tqty.setText("");
			tttl.setText("");
			netqty.setText("");
			netttl.setText("");	
			cclr.setSelectedIndex(0);
			
			int m = cclr.getMaximumRowCount();
			for (int i = 1; i < m; i++)
			{
			//	cclr.remove
			}
			
			int n = opanel.getComponentCount();
			for (int i = 0; i < n; i++)
			{
				Component c = opanel.getComponent(i);
				if(c instanceof List)
				{
					((List) c).removeAll();
					c.setEnabled(false);
				}
				if(c instanceof JTextField || c instanceof JComboBox)
					c.setEnabled(false);
					
			}
		}
		
		if(arg0.getSource() == button[1])
		{
			int n = opanel.getComponentCount();
			for (int i = 0; i < n; i++)
			{
				Component c = opanel.getComponent(i);
				if(c instanceof JTextField || c instanceof List || c instanceof JComboBox)
					c.setEnabled(true);	
			}
			toid.setText(utility.getOrdId());
			todt.setText(Style.getDate(new Date()));
			tsno.grabFocus();
			button[2].setEnabled(true);
		}
		
		if(arg0.getSource() == button[0])
		{
			Connection con = DataBaseUtility.getConnection();
			String sql="insert into acc_order_details values('"+toid.getText()+"','"+todt.getText()+"','"+netqty.getText()+"','"+totdt.getText()+"')";
			try
			{
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
				
				stmt1 = con.createStatement();
				for (int i = 0; i < laccid.getItemCount(); i++)
				{
					stmt1.executeUpdate("insert into acc_order_pro_details values('"+toid.getText()+"','"+laccid.getItem(i)+"','"+lclr.getItem(i)+"','"+lqty.getItem(i)+"')");
				}
				JOptionPane.showMessageDialog(f,"Order Saved");
				utility.setOrdId(toid.getText());
				stmt1.close();
				con.close();
				
			}
		
			catch(Exception ex)
			{
	        	JOptionPane.showMessageDialog(f, ex.toString());
	        	ex.getStackTrace();
	        }
		}
		
//		if(arg0.getSource() == button[0])
//		{
//	//		toid.setText(utility.());
//		}
		
		if(arg0.getSource() == cal)
			totdt.setText(new DatePicker(f).setPickedDate());
	
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusGained(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent arg0)
	{
		if(arg0.getSource() == tqty)
		{
			if(tqty.getText().length()>0)
			{
				int price = Integer.parseInt(tprc.getText());
				int qty = Integer.parseInt(tqty.getText());
				int tot = price * qty;
				tttl.setText(Integer.toString(tot));
				add.setEnabled(true);
				sub.setEnabled(true);
			}
		}
		
	}
	
	private void browseData()
	{
		flag = false;
		Vector<String> vcolor;
		Connection con = DataBaseUtility.getConnection();
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("select vname,price from v_engine where v_id='"+tmdlid.getText()+"'");
			while(rs.next())
			{
				tmdlname.setText(rs.getString(1));
				tprc.setText(rs.getString(2));
			}
			stmt.close();
			stmt1 = con.createStatement();
			rs1 = stmt1.executeQuery("select clr from v_color where v_id='"+caccid.getText()+"'");
			
			vcolor = new Vector<String>();
				while(rs1.next())
				{
					String color = rs1.getString(1);
					for (String string : color.split(";"))
					{
						vcolor.add(string);
					}
				}
//				int n = cclr.getMaximumRowCount();
//				System.out.println(n);
				for (int i = 0; i < vcolor.size(); i++)
				{
					cclr.addItem(vcolor.elementAt(i));					
				}
				caccid.removeFocusListener(this);
			con.close();
		}
			catch (Exception e)
			{
				
	}
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
