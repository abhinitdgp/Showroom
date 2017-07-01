import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.sql.SQLException;
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


public class Vorder implements ActionListener,KeyListener,FocusListener,ItemListener
{
	JFrame f;
	
	public Vorder()
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

	private void frameDesign()
	{
		center = new JPanel();
		sp = new JPanel();	
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
		head = new JLabel("Vehicle Order");
		head.setBounds(375, 20, 300, 30);
		head.setFont(Style.fhdfont);
		center.setBackground(Style.panelColor);
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
		otdt.setBounds(550, 130, 260, 30);
		center.add(otdt);
		
		toid = new JTextField();
		todt = new JTextField();
		totdt = new JTextField();
		
		toid.setBounds(470, 80, 130, 30);
		toid.setBorder(Style.border1);
		toid.setEditable(false);
		toid.setFont(Style.txtfont);
		center.add(toid);
		
		todt.setBounds(260, 130, 130, 30);
		todt.setBorder(Style.border1);
		todt.setFont(Style.txtfont);
		todt.setEditable(false);
		todt.setText(Style.getDate(new Date()));
		center.add(todt);
		
		totdt.setBounds(790, 130, 130, 30);
		totdt.setBorder(Style.border1);
		totdt.setFont(Style.txtfont);
		totdt.setEditable(false);
		center.add(totdt);
		
		cal = new JButton(new ImageIcon("cal1.gif"));
		cal.setBounds(925, 134, 20, 20);
		cal.addActionListener(this);
		center.add(cal);
		
		opanelDesign();
		
		int n = opanel.getComponentCount();
		for (int i = 0; i < n; i++)
		{
			Component c = opanel.getComponent(i);
			if(c instanceof JTextField || c instanceof List || c instanceof JComboBox)
				c.setEnabled(false);	
		}
	}
	
	JLabel sno,mdlid,mdlname,clr,prc,qty,ttl,net;
	JComboBox cclr;
	JTextField tsno,tmdlid,tmdlname,tprc,tqty,tttl,netqty,netttl;
	List lsno,lmdlid,lmdlname,lclr,lprc,lqty,lttl;
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
		mdlid = new JLabel("Model ID");
		mdlname = new JLabel("Model Name");
		clr = new JLabel("Color");
		prc = new JLabel("Price(Rs.)");
		qty = new JLabel("Quantity");
		ttl = new JLabel("Total(Rs.)");
		net = new JLabel("Net Total");
		
		tsno = new JTextField();
		tmdlname = new JTextField();
		tprc = new JTextField();
		tqty = new JTextField();
		tttl = new JTextField();
		netqty = new JTextField();
		netttl = new JTextField();
		tmdlid = new JTextField();
		
		cclr = new JComboBox();
		
		lsno = new List();
		lmdlid = new List();
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
		
		mdlid.setFont(Style.lblfont);
		mdlid.setBounds(100, 10, 100, 30);
		
		opanel.add(mdlid);
		
		tmdlid.setBounds(80, 40, 120, 30);
		tmdlid.setFont(Style.txtfont);
		tmdlid.addKeyListener(this);
		tmdlid.addFocusListener(this);
		opanel.add(tmdlid);
		
		lmdlid.setBounds(80, 90, 120, 200);
		lmdlid.setFont(Style.txtfont);
		opanel.add(lmdlid);
		
		mdlname.setFont(Style.lblfont);
		mdlname.setBounds(250, 10, 130, 30);
		opanel.add(mdlname);
		
		tmdlname.setBounds(230, 40, 150, 30);
		tmdlname.setFont(Style.txtfont);
		tmdlname.setEditable(false);
		opanel.add(tmdlname);
		
		lmdlname.setBounds(230, 90, 150, 200);
		lmdlname.setFont(Style.txtfont);
		opanel.add(lmdlname);
		
		clr.setFont(Style.lblfont);
		clr.setBounds(420, 10, 100, 30);
		cclr.addItemListener(this);
		opanel.add(clr);
		
		cclr.setBounds(400, 40, 100, 30);
		cclr.setFont(Style.txtfont);
		cclr.addItem("Select");
		opanel.add(cclr);
		
		lclr.setBounds(400, 90, 100, 200);
		lclr.setFont(Style.txtfont);
	//	lclr.setBorder(Style.border1);
		opanel.add(lclr);
		
		prc.setFont(Style.lblfont);
		prc.setBounds(530, 10, 100, 30);
		opanel.add(prc);
		
		tprc.setBounds(520, 40, 90, 30);
		tprc.setFont(Style.txtfont);
		tprc.setEditable(false);
		opanel.add(tprc);
		
		lprc.setBounds(520, 90, 90, 200);
		lprc.setFont(Style.txtfont);
	//	lprc.setBorder(Style.border1);
		opanel.add(lprc);
		
		net.setFont(Style.lblfont);
		net.setBounds(540, 320, 100, 30);
		opanel.add(net);
		
		qty.setFont(Style.lblfont);
		qty.setBounds(650, 10, 100, 30);
		opanel.add(qty);
		
		tqty.setBounds(640, 40, 90, 30);
		tqty.addFocusListener(this);
		tqty.addKeyListener(this);
		tqty.setFont(Style.txtfont);
		opanel.add(tqty);
		
		lqty.setBounds(640, 90, 90, 200);
		lqty.setFont(Style.txtfont);
		opanel.add(lqty);
		
		netqty.setBounds(640, 320, 100, 30);
		netqty.setFont(Style.txtfont);
		netqty.setEditable(false);
		opanel.add(netqty);
		
		ttl.setFont(Style.lblfont);
		ttl.setBounds(780, 10, 100, 30);
		opanel.add(ttl);
		
		tttl.setBounds(770, 40, 100, 30);
		tttl.setFont(Style.txtfont);
		tttl.setEditable(false);
		tttl.addKeyListener(this);
		
		opanel.add(tttl);
		
		lttl.setBounds(770, 90, 100, 200);
		lttl.setFont(Style.txtfont);
		opanel.add(lttl);
		
		netttl.setBounds(770, 320, 100, 30);
		netttl.setFont(Style.txtfont);
		netttl.setEditable(false);
		opanel.add(netttl);
		
		add.setBounds(885, 70, 50, 50);
		add.addActionListener(this);
		add.setEnabled(false);
		opanel.add(add);
		
		sub.setBounds(885, 130, 50, 50);
		sub.addActionListener(this);
		sub.setEnabled(false);
		opanel.add(sub);
		
		int n = opanel.getComponentCount();
		for (int i = 0; i < n; i++)
		{
			Component c = opanel.getComponent(i);
			if(c instanceof JTextField)
				((JTextField) c).setBorder(Style.border1);
			if(c instanceof List)
				((List) c).addItemListener(this);
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
		new Vorder();
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
			lmdlid.addItem(tmdlid.getText());
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
			tmdlid.setText("");
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
			lmdlid.remove(n-1);
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
			tmdlid.setText("");
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
			String sql="insert into v_order values('"+toid.getText()+"','"+todt.getText()+"','"+netqty.getText()+"','"+totdt.getText()+"')";
			try
			{
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
				
				stmt1 = con.createStatement();
				for (int i = 0; i < lmdlid.getItemCount(); i++)
				{
					stmt1.executeUpdate("insert into v_order_prod_details values('"+toid.getText()+"','"+lmdlid.getItem(i)+"','"+lclr.getItem(i)+"','"+lqty.getItem(i)+"')");
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
	public void focusGained(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	JList l = new JList();
	boolean flag = true;
	@Override
	public void focusLost(FocusEvent arg0)
	{
		
		if(arg0.getSource()==tmdlid)
		{
			//System.out.println(l);
			if(l!=null)
			{
			//	l.setVisible(false);
				if(!l.isSelectionEmpty())
				{
					flag = true;
					tmdlid.setText(l.getSelectedValue().toString());
					l.setVisible(false);
					cclr.grabFocus();
				}
			}
			
			
			if(flag)browseData();
		}
		
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
			rs1 = stmt1.executeQuery("select clr from v_color where v_id='"+tmdlid.getText()+"'");
			
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
				tmdlid.removeFocusListener(this);
			con.close();
		}
			catch (Exception e)
			{
			}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		tmdlid.addFocusListener(this);
		Vector v = new Vector();
		l.addFocusListener(this);
		if(arg0.getSource()==tmdlid)
		{
			if(tmdlid.getText().length()>1)
			{
				Connection con = DataBaseUtility.getConnection();
				try
				{
					
					stmt = con.createStatement();
					rs = stmt.executeQuery("select V_ID from V_ENGINE where V_ID like '%"+tmdlid.getText().toUpperCase()+"%'");
					while(rs.next())
					{
						v.add(rs.getObject(1));
					}
					con.close();
					l.setListData(v);
					if(v.size()>0)
					{
						l.setVisible(true);
						l.setBounds(80, 70, 120, v.size()*20);
					//	l.setBackground(Style.panelColor);
						l.setBackground(Color.LIGHT_GRAY);
						l.setOpaque(true);
						opanel.add(l);
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				l.setVisible(false);
			}
		}
		
		if(arg0.getSource() == tqty)
		{
			if(tqty.getText().length()>0)
			{
				try
				{
					int mob = Integer.parseInt(tqty.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!! Only numbers 0-9 are allowed.");
					tqty.setText("");;			
					tqty.grabFocus();		
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		if(arg0.getSource() == cclr)
			tqty.grabFocus();
		
		if(arg0.getSource()==lsno)
		{
			int i=lsno.getSelectedIndex();
			lmdlid.select(i);
			lmdlname.select(i);
			lclr.select(i);
			lprc.select(i);
			lqty.select(i);
			lttl.select(i);
		}
		if(arg0.getSource()==lmdlid)
		{
			int i=lmdlid.getSelectedIndex();
			lsno.select(i);
			lmdlname.select(i);
			lclr.select(i);
			lprc.select(i);
			lqty.select(i);
			lttl.select(i);
		}	
	}
}
