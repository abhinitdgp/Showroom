import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;



public class Enquiry implements ActionListener,FocusListener,ItemListener,KeyListener,MouseListener
{
	JFrame f;

	public Enquiry()
	{
		f = new JFrame();
		frameDesign();
		f.setVisible(true);
		f.setResizable(false);
		f.setSize(1024,725);
		f.setLocationRelativeTo(null);
		f.setTitle("Automobile Showroom Management System");
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		int n = cpanel.getComponentCount();
		for (int i = 0; i < n; i++)
		{
			Component c = cpanel.getComponent(i);
			
				if( (c instanceof JTextField))
					((JTextField) c).setEditable(false);
				if( (c instanceof TextArea))
					((TextArea) c).setEditable(false);
		}
		
		int m = vpanel.getComponentCount();
		for (int i = 0; i < m; i++)
		{
			Component c = vpanel.getComponent(i);
			
			if( (c instanceof JTextField))
				((JTextField) c).setEditable(false);
			if( (c instanceof JComboBox))
				((JComboBox) c).setEnabled(false);
			if( (c instanceof JRadioButton))
				((JRadioButton) c).setEnabled(false);
		}
		
		Connection con = DataBaseUtility.getConnection();
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select v_id from v_engine");
			while(rs.next())
			{
				cb.addItem(rs.getString("v_id"));
			}
			con.close();
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		
	}
	
	
	JPanel cpanel,vpanel,spanel,panel;
	JButton button[] = new JButton[4];
	String btext[] = {"Add","Save","Reset","Exit"};
	
	private void frameDesign()
	{
		
		spanel = new JPanel();
		panel = new JPanel();
		panel.setOpaque(true);
		panelDesign();
		spanel.setBackground(Color.DARK_GRAY);
		
		for (int i = 0; i < button.length; i++)
		{
			button[i] = new JButton(btext[i]);
			button[i].addActionListener(this);
			spanel.add(button[i]);
		}
		button[1].setEnabled(false);
		button[2].setEnabled(false);
		f.add(panel);
		f.add(spanel,"South");	
	}
	
	
	JTextField txtid = new JTextField();
	JTextField txtdt = new JTextField();
	JButton bfnd;
	
	private void panelDesign()
	{
		panel.setLayout(null);
		panel.setBackground(Style.panelColor);
		JLabel l = new JLabel("Enquiry");
		l.setFont(Style.fhdfont);
		l.setBounds(410, 10, 200, 30);
		panel.add(l);
		
		JLabel leid = new JLabel("Enquiry ID");
		leid.setBounds(70, 50, 120, 30);
		leid.setFont(Style.lblfont);
		f.add(leid);
		
		txtid.setBounds(190, 50, 120, 30);
		txtid.setBorder(Style.border1);
		txtid.addKeyListener(this);
		txtid.addFocusListener(this);
		txtid.setEditable(false);
		f.add(txtid);
		
		JLabel leqd = new JLabel("Enquiry Date");
		leqd.setBounds(540, 50, 150, 30);
		leqd.setFont(Style.lblfont);
		f.add(leqd);
		
		txtdt.setBounds(680, 50, 130, 30);
		txtdt.setFont(Style.txtfont);
		txtdt.setBorder(Style.border1);
		txtdt.setText(Style.getDate(new Date()));
		txtdt.setEditable(false);
		f.add(txtdt);
		
		bfnd = new JButton("Search");
		bfnd.setBounds(330, 50, 120, 30);
		f.add(bfnd);
		bfnd.addActionListener(this);
		
		
		cpanel = new JPanel();
		vpanel = new JPanel();
		
		cpanelDesign();
		vpanelDesign();
		
		panel.add(cpanel);
		panel.add(vpanel);
		
	}


	JLabel vlabel[] = new JLabel[7];
	String vtxt[] = {"Preferred Vehicle Id","Vehicle Name","Vehicle Color","Exp. Purchase Date","Purchase mode","Price","Rs."};
	JTextField vtxtfld[] = new JTextField[4];
	JComboBox cb = new JComboBox();
	JRadioButton s,fn;
	JLabel img;
	JPanel ipanel;
	
	private void vpanelDesign()
	{
		vpanel.setLayout(null);
		vpanel.setOpaque(false);
		vpanel.setBackground(Color.getHSBColor(120, 120, 120));
		vpanel.setBounds(40, 390, 450, 265);
		Border border =BorderFactory.createLineBorder(Color.black,5);
		Border border1 = BorderFactory.createTitledBorder(border, "Vehicle Details",1,2,new Font("Footlight MT Light",Font.BOLD,22));
		vpanel.setBorder(border1);
		
		for (int i = 0; i < 6; i++)
		{
			vlabel[i] = new JLabel(vtxt[i]);
			vlabel[i].setFont(Style.lblfont);
			vlabel[i].setBounds(20, 35+35*i, 200, 30);
			vpanel.add(vlabel[i]);
		}
		
			cb = new JComboBox();
			cb.setBounds(210,35 , 190, 30);
			cb.setFont(Style.txtfont);
			cb.addItemListener(this);
			cb.addItem("----SELECT----");
			vpanel.add(cb);
			
			vtxtfld[3] = new JTextField();
			vtxtfld[3].setBounds(210,210 , 130, 30);
			vtxtfld[3].setBorder(Style.border1);
			vtxtfld[3].setFont(Style.txtfont);
			vtxtfld[3].setEditable(false);
			vpanel.add(vtxtfld[3]);

			vtxtfld[0] = new JTextField();
			vtxtfld[0].setBorder(Style.border1);
			vtxtfld[0].setBounds(210, 70, 180, 30);
			vtxtfld[0].setEditable(false);
			vtxtfld[0].setFont(Style.txtfont);
			vpanel.add(vtxtfld[0]);
			
			vtxtfld[1] = new JTextField();
			vtxtfld[1].setBorder(Style.border1);
			vtxtfld[1].setBounds(210, 105, 100, 30);
			vtxtfld[1].setFont(Style.txtfont);
			vpanel.add(vtxtfld[1]);
		
			vtxtfld[2] = new JTextField();
			vtxtfld[2].setBorder(Style.border1);
			vtxtfld[2].setBounds(210, 140, 120, 30);
			vtxtfld[2].setFont(Style.txtfont);
			vpanel.add(vtxtfld[2]);
			
			s = new JRadioButton("Self");
			fn = new JRadioButton("Finance");
			ButtonGroup bg = new ButtonGroup();
			bg.add(s);
			bg.add(fn);
			
			s.setBounds(200, 175, 70, 30);
			s.setOpaque(false);
			s.setFont(Style.txtfont);
			vpanel.add(s);
			
			fn.setBounds(280, 175, 120, 30);
			fn.setFont(Style.txtfont);
			fn.setOpaque(false);
			vpanel.add(fn);
			
			img = new JLabel();
			img.setBounds(500, 180, 500, 400);
			img.setBackground(Color.white);
			img.setOpaque(false);
			panel.add(img);
			
			ipanel = new JPanel();
			ipanel.setBackground(Color.white);
			ipanel.setBounds(550, 110, 400, 80);
			ipanel.setOpaque(false);
			panel.add(ipanel);
	}
	
	
	JLabel label[] = new JLabel[6];
	String ltext[] = {"Customer Name","Father's Name","Occupation","Mobile No.","E-mail Id","Address"};
	JTextField txtfld[] = new JTextField[5];
	TextArea txtar;

	private void cpanelDesign()
	{
		cpanel.setLayout(null);
		cpanel.setOpaque(false);
		cpanel.setBounds(40, 100, 450, 280);
		Border border =BorderFactory.createLineBorder(Color.black,5);
		Border border1 = BorderFactory.createTitledBorder(border, "Customer Details",1,2,new Font("Footlight MT Light",Font.BOLD,22));
		cpanel.setBorder(border1);
		
		for (int i = 0; i < label.length; i++)
		{
			label[i] = new JLabel(ltext[i]);
			label[i].setFont(Style.lblfont);
			label[i].setBounds(20, 30+30*i, 150, 20);
			cpanel.add(label[i]);
			
		}
		
		for (int i = 0; i < 6; i++)
		{
			if(i==5)
			{
				txtar = new TextArea();
				txtar.setBounds(170, 180, 220, 80);
				txtar.setFont(Style.txtfont);
				cpanel.add(txtar);
			}
			else
			{
				txtfld[i] = new JTextField();
				txtfld[i].setBounds(170, 30+30*i, 130, 25);
				txtfld[i].setBorder(Style.border1);
				txtfld[i].setOpaque(false);
				txtfld[i].setFont(Style.txtfont);
				cpanel.add(txtfld[i]);
				
			}
		}	
		txtfld[0].setSize(200, 25);
		txtfld[0].addKeyListener(this);
		txtfld[3].addKeyListener(this);
		txtfld[3].addFocusListener(this);
		txtfld[1].setSize(200, 25);
		txtfld[4].setSize(200, 25);
	}
	
	
	public static void main(String[] args)
	{
		new Enquiry();
	}
	
	
	JList l = new JList();
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
//		if (arg0.getActionCommand().equals("Search"))
//		{
//			JColorChooser ch = new JColorChooser();
//			Color c = ch.showDialog(null, "My Color Platte", Color.black);
//			cpanel.setBackground(c);
//		}
		
		if(arg0.getSource() == bfnd)
		{
			txtid.setText("");
			txtid.setEditable(true);
			txtdt.setText("");
			txtid.grabFocus();
			for (int i = 0; i < 3; i++)
			{
				button[i].setEnabled(false);
			}
			for (int i = 0; i < txtfld.length; i++)
				txtfld[i].setEditable(false);
			txtar.setEditable(false);
			
			for (int i = 0; i < vtxtfld.length; i++)
				vtxtfld[i].setEditable(false);;
			cb.setEnabled(false);
			vtxtfld[2].removeMouseListener(this);
			
//			txtid.setText("");
//			txtdt.setText("");
//			txtid.setEditable(false);
			for (int i = 0; i < txtfld.length; i++)
				txtfld[i].setText("");
			txtar.setText("");
			
			for (int i = 0; i < vtxtfld.length; i++)
				vtxtfld[i].setText("");
			cb.setSelectedIndex(0);
			
			button[2].setEnabled(true);	
		}
		
		
		if(arg0.getSource() == button[3])
			f.dispose();
		
		
		if(arg0.getSource() == button[2])
		{
			txtid.setText("");
			txtdt.setText("");
			txtid.setEditable(false);
			for (int i = 0; i < txtfld.length; i++)
				txtfld[i].setText("");
			txtar.setText("");
			
			for (int i = 0; i < vtxtfld.length; i++)
				vtxtfld[i].setText("");
			cb.setSelectedIndex(0);
			ipanel.removeAll();
			ipanel.repaint();
			ipanel.revalidate();
			img.setIcon(null);
			
			int n = cpanel.getComponentCount();
			for (int i = 0; i < n; i++)
			{
				Component c = cpanel.getComponent(i);
				
					if( (c instanceof JTextField))
						((JTextField) c).setEditable(false);
					if( (c instanceof TextArea))
						((TextArea) c).setEditable(false);
					
			}
			int m = vpanel.getComponentCount();
			for (int i = 0; i < m; i++)
			{
				Component c = vpanel.getComponent(i);
				
				if( (c instanceof JTextField))
					((JTextField) c).setEditable(false);
				if( (c instanceof JComboBox))
					((JComboBox) c).setEnabled(false);
				if( (c instanceof JRadioButton))
					((JRadioButton) c).setEnabled(false);
			}
			button[0].setEnabled(true);
			button[1].setEnabled(false);
			
//			for (int i = 0; i < n; i++)
//			{
//				Component c = ipanel.getComponent(i);
//				ipanel.remove(c);
//				ipanel.revalidate();
//			}
			
		//	ipanel.revalidate();
		}
		
		if(arg0.getSource() == button[0])
		{
			vtxtfld[2].addMouseListener(this);
			txtdt.setText(Style.getDate(new Date()));
		//	img.setVisible(true);
			int n = cpanel.getComponentCount();
			for (int i = 0; i < n; i++)
			{
				Component c = cpanel.getComponent(i);
				
					if( (c instanceof JTextField))
						((JTextField) c).setEditable(true);
					if( (c instanceof TextArea))
						((TextArea) c).setEditable(true);
			}
			
			int m = vpanel.getComponentCount();
			for (int i = 0; i < m; i++)
			{
				Component c = vpanel.getComponent(i);
				
				if( (c instanceof JTextField))
					((JTextField) c).setEditable(true);
				if( (c instanceof JComboBox))
					((JComboBox) c).setEnabled(true);
				if( (c instanceof JRadioButton))
					((JRadioButton) c).setEnabled(true);
			}
			vtxtfld[0].setEditable(false);
			vtxtfld[3].setEditable(false);
			vtxtfld[1].setEditable(false);
//			 n = cpanel.getComponentCount();
//			for (int i = 0; i < n; i++)
//			{
//				Component c = cpanel.getComponent(i);
//				
//					if( !(c instanceof JLabel))
//						c.setEnabled(true);			
//			}
//			 m = vpanel.getComponentCount();
//			for (int i = 0; i < m; i++)
//			{
//				Component c = vpanel.getComponent(i);
//				
//				if( !(c instanceof JLabel))
//					c.setEnabled(true);			
//			}
			for (int i = 0; i < txtfld.length; i++)
				txtfld[i].setText("");
			txtar.setText("");
			
			for (int i = 0; i < vtxtfld.length; i++)
				vtxtfld[i].setText("");
			cb.setSelectedIndex(0);
			ipanel.removeAll();
			ipanel.repaint();
			ipanel.revalidate();
			
			txtid.setText(utility.getEnqId());
			button[2].setEnabled(true);
			button[0].setEnabled(false);
			
			txtfld[0].grabFocus();
		}
			
		
		if(arg0.getSource() == button[1])
		{
			if(txtfld[0].getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String mode ="";
				mode=s.isSelected()?"Self":"Finance";
				String sql = "insert into v_enquiry values('"+txtid.getText()+"','"+txtdt.getText()+"','"+txtfld[0].getText()+"','"+txtfld[1].getText()+"','"+txtfld[2].getText()+"',"+txtfld[3].getText()+",'"+txtfld[4].getText()+"','"+txtar.getText()+"','"+cb.getSelectedItem()+"','"+vtxtfld[1].getText()+"','"+vtxtfld[2].getText()+"','"+mode+"')";
				
				try
				{
					stmt = con.createStatement();
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Record Saved.");
					utility.setEnqId(txtid.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, e.toString());
				}
				button[1].setEnabled(false);
				int n = cpanel.getComponentCount();
				for (int i = 0; i < n; i++)
				{
					Component c = cpanel.getComponent(i);
					
						if( (c instanceof JTextField))
							((JTextField) c).setEditable(false);
						if( (c instanceof TextArea))
							((TextArea) c).setEditable(false);
						
				}
				int m = vpanel.getComponentCount();
				for (int i = 0; i < m; i++)
				{
					Component c = vpanel.getComponent(i);
					
					if( (c instanceof JTextField))
						((JTextField) c).setEditable(false);
					if( (c instanceof JComboBox))
						((JComboBox) c).setEnabled(false);
					if( (c instanceof JRadioButton))
						((JRadioButton) c).setEnabled(false);
					
				}
				button[0].setEnabled(true);
			}
			else
				JOptionPane.showMessageDialog(null, "Enter a record");
			
		}
		
	}


	@Override
	public void focusGained(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void focusLost(FocusEvent arg0)
	{
		flag = true;
		if(arg0.getSource()==txtid)
		{
			//System.out.println(l);
			if(l!=null)
			{
				l.setVisible(true);
				if(!l.isSelectionEmpty())
				{
					txtid.setText(l.getSelectedValue().toString());
					l.setVisible(false);
				}
			}
			
			if(flag)browseData();
		}
		
		if(arg0.getSource() == txtfld[3])
		{
			txtfld[3].removeKeyListener(this);
			if(txtfld[3].getText().length()>10 || txtfld[3].getText().length()<10)
			{
					JOptionPane.showMessageDialog(null, "Invalid mobile number!!!");
					txtfld[3].grabFocus();			
			}
			txtfld[3].addKeyListener(this);
		}
	}




	private void browseData()
	{
		flag = false;
		Connection con = DataBaseUtility.getConnection();
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from v_enquiry where enq_id='"+txtid.getText()+"'");
			while(rs.next())
			{
				txtdt.setText(Style.getDate(rs.getDate("enq_dt")));
				vtxtfld[1].setText("HI");
				txtfld[0].setText(rs.getString("cname"));
				txtfld[1].setText(rs.getString("fname"));
				txtfld[2].setText(rs.getString("occ"));
				txtfld[3].setText(rs.getString("mob"));
				txtfld[4].setText(rs.getString("email"));
				txtar.setText(rs.getString("address"));
				
				cb.setSelectedItem(rs.getObject("v_id"));
				vtxtfld[1].setText(rs.getString("v_clr"));
				img.setIcon(new ImageIcon("image/"+cb.getSelectedItem()+"-"+vtxtfld[1].getText()+".jpg"));
				vtxtfld[2].setText(Style.getDate(rs.getDate("epdt")));
				String mode = rs.getObject("pmode").toString();
				if(mode.equalsIgnoreCase("self"))
					s.setSelected(true);
				else
					fn.setSelected(true);
				
				for (int i = 0; i < btnclr.length; i++)
				{
					btnclr[i].removeMouseListener(this);
				}
				
			}
			con.close();
		}
			catch (Exception e)
			{
			}
	}
	


	
	boolean flag = true;
	Statement stmt,stmt1,stmt2;
	ResultSet rs,rs1,rs2;
	JButton btnclr[];
	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		if(arg0.getSource() == cb && cb.getSelectedIndex()>0 )
		{
			ipanel.removeAll();
			ipanel.repaint();
			vtxtfld[1].setText("");
		//	flag =false;
			Connection con = DataBaseUtility.getConnection();
			Vector<String> vcolor;
			vcolor = new Vector<String>();
			try
			{
				stmt1 = con.createStatement();
				rs1 = stmt1.executeQuery("select clr from v_color where v_id = '"+cb.getSelectedItem()+"'");
				while(rs1.next())
				{
					String color = rs1.getString(1);
					for (String string : color.split(";"))
					{
						vcolor.add(string);
					}
				}
				
				stmt2 = con.createStatement();
				rs2 = stmt2.executeQuery("select vname,price from v_engine where v_id = '"+cb.getSelectedItem()+"'");
				while(rs2.next())
				{
					vtxtfld[0].setText(rs2.getString("vname"));
					vtxtfld[3].setText(rs2.getString("price"));
				}
				con.close();
				btnclr = new JButton[vcolor.size()];				
			
				for (int i = 0; i < btnclr.length; i++)
				{
					btnclr[i] = new JButton(vcolor.elementAt(i));
					btnclr[i].addMouseListener(this);
					ipanel.add(btnclr[i]);
					
				}
				btnclr[0].grabFocus();
				ipanel.revalidate();
				
			} catch (Exception e)
			{
				e.printStackTrace();
				// TODO: handle exception
			}
			//JOptionPane.showMessageDialog(null, vcolor.elementAt(1));
			
		//	panel.add(ipanel);
		}
		
	}


	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0)
	{
		Vector v = new Vector();
		l.setVisible(true);
		l.addFocusListener(this);
		if(arg0.getSource()==txtid)
		{
			if(txtid.getText().length()>1)
			{
				Connection con = DataBaseUtility.getConnection();
				try
				{
					
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select enq_ID from V_enquiry where enq_ID like '%"+txtid.getText().toUpperCase()+"%'");
					while(rs.next())
					{
						v.add(rs.getObject(1));
					}
					con.close();
					l.setListData(v);
					if(v.size()>0)
					{
						l.setBounds(190, 80, 120, v.size()*20);
					//	l.setBackground(Style.panelColor);
						l.setBackground(Color.LIGHT_GRAY);
						l.setOpaque(true);
						panel.add(l);
					}
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else
			{
				l.setVisible(false);
			}
		}
		
		if(arg0.getSource() == txtfld[0])
		{
			if(txtfld[0].getText().length()>0)
				button[1].setEnabled(true);
			else
				button[1].setEnabled(false);
		}
		
		if(arg0.getSource() == txtfld[3])
		{
			txtfld[3].removeFocusListener(this);
			if(txtfld[3].getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(txtfld[3].getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!! Only numbers 0-9 are allowed.");
//					int n = txtfld[3].getText().length();
//					String s = txtfld[3].getText();
//					for (int i = 0; i < n-1; i++)
//					{
//						char p = s.charAt(i);
//						txtfld[3].setText(s[i]);
//					}
					txtfld[3].setText("");;			
					txtfld[3].grabFocus();		
				}
				txtfld[3].addFocusListener(this);
			}
		}
	}


	@Override
	public void keyTyped(KeyEvent arg0)
	{
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		if(arg0.getSource() == vtxtfld[2])
			vtxtfld[2].setText(new DatePicker(f).setPickedDate());
		
		for (int i = 0; i < btnclr.length; i++)
		{
			if(arg0.getSource() == btnclr[i])
			{	
				vtxtfld[1].setText(btnclr[i].getText());
			}
			btnclr[i].removeMouseListener(this);
		}			
	}


	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		for (int i = 0; i < btnclr.length; i++)
		{
			if(arg0.getSource() == btnclr[i]) //&& cb.getSelectedIndex() == 3)
			{	
				String s=btnclr[i].getText();
				img.setIcon(new ImageIcon("image/"+(cb.getSelectedItem()+"-"+s+".jpg").toString()));
			}	
		}	
	}


	@Override
	public void mouseExited(MouseEvent arg0)
	{
		for (int i = 0; i < btnclr.length; i++)
		{
			img.setIcon(null);
		}
	}


	@Override
	public void mousePressed(MouseEvent arg0)
	{
		
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
