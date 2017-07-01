import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class EmployeeDetails implements ActionListener,MouseListener,ItemListener,FocusListener
{
	JFrame f;
	
	public EmployeeDetails()
	{
		f = new JFrame();
		frameDesign();
		f.setVisible(true);
		f.setResizable(false);
		f.setSize(1024, 725);
		f.setLocationRelativeTo(null);
		f.setTitle("Automobile Showroom Management System");
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","honda","showroom");
			cn1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","honda","showroom");
			
			stmt1 = cn1.createStatement();
			rs1 = stmt1.executeQuery("select desgid from desg_details");
			while(rs1.next())
			{
				cb.addItem(rs1.getString("desgid"));
			}
			
			stmt = cn.createStatement();
			rs = stmt.executeQuery("select empid from employee_details");
			while(rs.next())
			{
				cbid.addItem(rs.getObject("empid"));
			}
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(f, e.toString());
		}
	}
	
	
	JPanel panel,spanel,epanel;
	JButton button[] = new JButton[5];
	String btext[] = {"Save","Update","Reset","Delete","Exit"};
//	Image im = new ImageIcon("emp.jpg").getImage();
	
	private void frameDesign()
	{
		spanel = new JPanel();
		panel = new JPanel();
//		{
//			@Override
//			public void paint(Graphics arg0)
//			{
//				arg0.drawImage(im, 0, 0, 1010, 650, null);
//				super.paint(arg0);
//			}
//		};
		panel.setOpaque(true);
		panelDesign();
		
		spanel.setBackground(Color.DARK_GRAY);
		
		for (int i = 0; i < button.length; i++)
		{
			button[i] = new JButton(btext[i]);
			button[i].addActionListener(this);
			spanel.add(button[i]);
		}
			
		f.add(panel);
		f.add(spanel,"South");	
	}
	
	
	JLabel head;
	
	private void panelDesign()
	{
		panel.setLayout(null);
	//	panel.setBackground(Color.getHSBColor(1250, 1250, 1250));
		panel.setBackground(Style.panelColor);
		epanel = new JPanel();
		epanelDesign();
		panel.add(epanel);
		
		head = new JLabel("Employee Details");
		head.setBounds(370, 10, 380, 30);
		head.setFont(Style.fhdfont);
		panel.add(head);
	}
	
	JLabel label[] = new JLabel[13];
	String ltxt[] = {"Employee ID","Employee Name","Father's Name","Gender","Address","D.O.B","Mobile No.","E-mail ID","Qualification","D.O.J","Designation Id","Designation","Salary"};
	JTextField txtfld[] = new JTextField[10];
	TextArea txtar;
	JComboBox cb,cbid;
	JRadioButton m,fe;
	JButton b;
	JButton btncal1;
	JButton btncal2;
	
 	private void epanelDesign()
	{
		epanel.setLayout(null);
		epanel.setBounds(275, 30, 600, 650);
		epanel.setOpaque(false);
		b = new JButton("Search");
		b.setBounds(370, 35, 100, 30);
		b.addActionListener(this);
		epanel.add(b);
		
		cbid = new JComboBox();
		cbid.setBounds(480, 35, 100, 30);
		cbid.setEnabled(false);
		epanel.add(cbid);
		
		for (int i = 0; i < 5; i++)
		{
				label[i] = new JLabel(ltxt[i]);
				label[i].setFont(Style.lblfont);
				label[i].setBounds(50, 40+40*i, 150, 30);
				epanel.add(label[i]);	
		}
		for (int i = 5; i < label.length; i++)
		{
				label[i] = new JLabel(ltxt[i]);
				label[i].setFont(Style.lblfont);
				label[i].setBounds(50, 105+40*i, 150, 30);
				epanel.add(label[i]);	
		}
		
		for (int i = 0; i < 3; i++)
		{
			txtfld[i] = new JTextField();
		//	if(i==0)txtfld[i].setText(getDate());
			txtfld[i].setBounds(200, 35+40*i, 130, 30);
			txtfld[i].setFont(Style.txtfont);
			txtfld[i].setBorder(Style.border1);
			epanel.add(txtfld[i]);
		}
		txtfld[1].setSize(200, 30);
		txtfld[2].setSize(200, 30);
		
		btncal1=new JButton(new ImageIcon("cal1.gif"));
		btncal2=new JButton(new ImageIcon("cal1.gif"));
		
		cb = new JComboBox();
		cb.setBounds(200, 505, 150, 30);
		cb.addItem("--Select--");
		cb.setFont(Style.txtfont);
		epanel.add(cb);
		cb.addItemListener(this);
				
		for (int i = 3; i < 8; i++)
		{
				txtfld[i] = new JTextField();
				txtfld[i].setBounds(200, 180+40*i, 150, 30);
				txtfld[i].setFont(Style.txtfont);
				txtfld[i].setBorder(Style.border1);
				epanel.add(txtfld[i]);	
		}
		txtfld[3].setSize(130, 30);
		txtfld[3].addMouseListener(this);
	//	txtfld[3].addFocusListener(this);
		
		btncal1.setBounds(335,302,25,25);
		epanel.add(btncal1);
		btncal1.addActionListener(this);
		
		txtfld[7].setSize(130, 30);
		txtfld[7].addMouseListener(this);
	//	txtfld[7].addFocusListener(this);
		
		txtfld[5].setSize(300, 30);
		
		for (int i = 8; i< 10; i++)
		{
				txtfld[i] = new JTextField();
				txtfld[i].setBounds(200, 305+40*(i-2), 150, 30);
				txtfld[i].setFont(Style.txtfont);
				txtfld[i].setBorder(Style.border1);
				epanel.add(txtfld[i]);	
		}
		btncal2.setBounds(335,462,25,25);
		epanel.add(btncal2);
		btncal2.addActionListener(this);
		
		m = new JRadioButton("Male");
		fe = new JRadioButton("Female");
		ButtonGroup bg = new ButtonGroup();
		bg.add(m);
		bg.add(fe);
		m.setBounds(200, 165, 100, 30);
		m.setOpaque(false);
		fe.setOpaque(false);
		m.setFont(Style.txtfont);
		fe.setFont(Style.txtfont);
		fe.setBounds(310, 165, 100, 30);
		epanel.add(m);
		epanel.add(fe);
		
		txtar = new TextArea();
		txtar.setFont(Style.txtfont);
		txtar.setBounds(200, 200, 250, 80);
		epanel.add(txtar);
	}
	private String getDate()
	{
		Date d = new Date();
		DateFormat d1 = DateFormat.getDateInstance(DateFormat.SHORT);		
		return d1.format(d);
	}

	public static void main(String[] args)
	{
		new EmployeeDetails();
	}
	
	Connection cn,cn1;
	Statement stmt,stmt1;
	ResultSet rs,rs1;
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == b)
		{
			cbid.setEnabled(true);
			txtfld[0].setEnabled(false);
			if(cbid.getSelectedIndex()>=0)
			{
				boolean flag=false;
				try
				{
					stmt=cn.createStatement();
					rs=stmt.executeQuery("select * from employee_details where empid='"+cbid.getSelectedItem()+"'");
				
					while(rs.next())
					{
						flag=true;
						cbid.setSelectedItem(rs.getObject("empid"));
						txtfld[0].setText(rs.getString("empid"));
						txtfld[1].setText(rs.getString("empname"));
						txtfld[2].setText(rs.getString("fname"));
						txtar.setText(rs.getString("address"));
						String gen = rs.getObject("gender").toString();
						if(gen.equalsIgnoreCase("male"))
							m.setSelected(true);
						else
							fe.setSelected(true);
						txtfld[3].setText(Style.getDate(rs.getDate("dob")));
						txtfld[4].setText(rs.getString("mob"));
						txtfld[5].setText(rs.getString("email"));
						txtfld[6].setText(rs.getString("qual"));
						txtfld[7].setText(Style.getDate(rs.getDate("doj")));
						cb.setSelectedItem(rs.getObject("desgid"));
//						txtfld[8].setText(rs.getString("desg"));
//						txtfld[9].setText(rs.getString("salary"));					
					}
					stmt.close();
				}
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        }
				
				if(flag==false)
				{
					JOptionPane.showMessageDialog(f, "Record Not Found.");
					cbid.setSelectedIndex(0);
					cbid.grabFocus();
				}
			}
			else
				JOptionPane.showMessageDialog(f, "Enter an ID to search");
			
		}
		
		if(ae.getSource() == button[4])
		//	System.exit(0);
			f.dispose();
		
		if(ae.getSource() == button[3])
		{
			if(txtfld[0].getText().length()>0)
			{
				try
				{
					stmt=cn.createStatement();
					
					int x = JOptionPane.showConfirmDialog(f,"Are you sure that you want to delete this record?");
					if(x == 0)
					{
						stmt.executeUpdate("delete from employee_details where empid='"+txtfld[0].getText()+"'");
						JOptionPane.showMessageDialog(null, "Record deleted");
						stmt.close();
						txtar.setText(null);
						m.setSelected(false);
						fe.setSelected(false);
						cb.setSelectedIndex(-1);
						for (int i = 0; i < txtfld.length; i++)
						{
							txtfld[i].setText("");
						}
						
						txtfld[0].grabFocus();
					}
					else
					{
						
					}
						
				}
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        }
			}
			else
				JOptionPane.showMessageDialog(f, "No record to Delete.");
		}
		
		if(ae.getSource() == button[2])
		{
			cbid.setEnabled(false);
			txtfld[0].setEnabled(true);
			for (int i = 0; i < txtfld.length; i++)
				txtfld[i].setText("");
			txtar.setText("");
			m.setSelected(false);
			fe.setSelected(false);
			cb.setSelectedIndex(0);
			txtfld[0].grabFocus();
		}
		
		if(ae.getSource() == button[1])
		{
			if(txtfld[0].getText().length()>0)
			{
				try{
					stmt=cn.createStatement();
					String gen = m.isSelected()?"Male":"Female";
					stmt.executeUpdate("update employee_details set empname='"+txtfld[1].getText()+"',fname='"+txtfld[2].getText()+"',gender='"+gen+"',address='"+txtar.getText()+"',dob='"+txtfld[3].getText()+"',mob='"+txtfld[4].getText()+"',email='"+txtfld[5].getText()+"',qual='"+txtfld[6].getText()+"',doj='"+txtfld[7].getText()+"',desgid='"+cb.getSelectedItem()+"'where empid='"+cbid.getSelectedItem()+"'");
					JOptionPane.showMessageDialog(f, "Record Updated");
					stmt.close();
				}
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        }
			}
			else
				JOptionPane.showMessageDialog(f, "No record to Update.");
		}
		
		if(ae.getSource() == button[0])
		{
			if(txtfld[0].getText().length()>0)
			{
				String gen = m.isSelected()?"Male":"Female";
				String sql="insert into employee_details values('"+txtfld[0].getText()+"','"+txtfld[1].getText()+"','"+txtfld[2].getText()+"','"+gen+"','"+txtar.getText()+"','"+txtfld[3].getText()+"','"+txtfld[4].getText()+"','"+txtfld[5].getText()+"','"+txtfld[6].getText()+"','"+txtfld[7].getText()+"','"+cb.getSelectedItem()+"')";
				try
				{
					stmt=cn.createStatement();
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(f,"Employee Details Saved");
					stmt.close();
				}
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        }
			}
			else
				JOptionPane.showMessageDialog(f, "Enter a Record to Save.");
		}
		
		if(ae.getSource() == btncal1)
		{
			txtfld[3].setText(new DatePicker(f).setPickedDate());
		}
		if(ae.getSource() == btncal2)
		{
			txtfld[7].setText(new DatePicker(f).setPickedDate());
		}
	}
	@Override
	public void mouseClicked(MouseEvent mc)
	{
//		if(mc.getSource() == txtfld[3])
//		{
//			txtfld[3].setText(new DatePicker(f).setPickedDate());
//		}
//		if(mc.getSource() == txtfld[7])
//		{
//			txtfld[7].setText(new DatePicker(f).setPickedDate());
//		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		if (arg0.getSource() == cb)
		{
			try
			{
				stmt1 = cn1.createStatement();
				rs1 = stmt1.executeQuery("select desgname,total from desg_details where desgid='"+cb.getSelectedItem()+"'");
				while(rs1.next())
				{
					txtfld[8].setText(rs1.getString("desgname"));
					txtfld[9].setText(rs1.getString("total"));
				}
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(f, e.toString());
			}
		}
		
		
	}
	@Override
	public void focusGained(FocusEvent arg0)
	{
		if(arg0.getSource() == txtfld[3])
		{
			txtfld[3].setText(new DatePicker(f).setPickedDate());;
		}
		if(arg0.getSource() == txtfld[7])
		{
			txtfld[7].setText(new DatePicker(f).setPickedDate());;
		}
		
	}
	@Override
	public void focusLost(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
