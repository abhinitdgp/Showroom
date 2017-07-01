import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;


public class DesgDetails implements ActionListener,FocusListener,KeyListener
{
	JFrame f;
	JLabel head;
	JLabel desg,desgid;
	JButton b;
	JTextField txt,txtname;
	
	public DesgDetails()
	{
		f = new JFrame("Automobile Showroom Management System");
		f.setBounds(350, 100, 700, 500);
		frameDesign();
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		head = new JLabel("Designtion Details");
		head.setBounds(155, 10, 450, 30);
		head.setFont(Style.fhdfont);
		panel.add(head);
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","honda","showroom");
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(f, e.toString());
		}
	}
	
	
	JPanel spanel,panel,dpanel;
	JButton button[] = new JButton[5];
	String btext[] = {"Save","Update","Reset","Delete","Exit"};
	
	private void frameDesign()
	{
		spanel = new JPanel();
		panel = new JPanel();
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
	
	private void panelDesign()
	{
		panel.setLayout(null);
		panel.setBackground(Style.panelColor);
		desgid = new JLabel("Designation ID");
		desg = new JLabel("Designation Name");
		desgid.setFont(Style.lblfont);
		//Font font = new Font("Arial",Font.BOLD,16);
		desg.setFont(Style.lblfont);
		desgid.setBounds(70, 80, 160, 30);
		desg.setBounds(70, 120, 160, 30);
		panel.add(desg);
		panel.add(desgid);
		
		txt = new JTextField();
		txt.setBounds(235, 80, 150, 30);
		txt.setFont(Style.txtfont);
		txt.setBorder(Style.border1);
		panel.add(txt);
		
		txtname = new JTextField();
		txtname.setBounds(235, 120, 150, 30);
		txtname.setFont(Style.txtfont);
		txtname.setBorder(Style.border1);
		panel.add(txtname);
		
		b = new JButton("Search");
		b.setBounds(395, 80, 150, 30);
		b.addActionListener(this);
		panel.add(b);
		
		dpanelDesign();	
	}
	
	
	JLabel dlbl[] = new JLabel[7];
	String ltxt[] = {"Basic","T.A","P.F","D.A","H.R.A","M.A","Net Total      Rs."};
	JTextField dtxt[] = new JTextField[7];
	int basic;
	
	private void dpanelDesign()
	{
		dpanel = new  JPanel();
		dpanel.setLayout(null);
		dpanel.setOpaque(false);
		dpanel.setBounds(40, 170, 600, 250);
		Border border =BorderFactory.createLineBorder(Color.black,6);
		Border border1 = BorderFactory.createTitledBorder(border, "",1,2);
		dpanel.setBorder(border1);
		panel.add(dpanel);
		
		for (int i = 0; i < dlbl.length; i++)
		{
			if(i<3)
			{
				dlbl[i] = new JLabel(ltxt[i]);
			//	Font font = new Font("Arial",Font.BOLD,16);
				dlbl[i].setFont(Style.lblfont);
				dlbl[i].setBounds(50, 40+45*i, 150, 30);
				dpanel.add(dlbl[i]);
				
				dtxt[i] = new JTextField();
				dtxt[i].setBounds(110, 40+45*i, 100, 30);
				dtxt[i].setFont(Style.txtfont);
				dtxt[i].setBorder(Style.border1);
				dpanel.add(dtxt[i]);
			}
			
			else if(i==6)
			{
				dlbl[i] = new JLabel(ltxt[i]);
				dlbl[i].setFont(Style.lblfont);
				dlbl[i].setBounds(170, 200, 150, 30);
				dpanel.add(dlbl[i]);
				
				dtxt[i] = new JTextField();
				dtxt[i].setBounds(310, 200, 100, 30);
				dtxt[i].setFont(Style.txtfont);
				dtxt[i].setBorder(Style.border1);
				dpanel.add(dtxt[i]);
			}
			else
			{
				dlbl[i] = new JLabel(ltxt[i]);
				dlbl[i].setFont(Style.lblfont);
				dlbl[i].setBounds(300, 40+45*(i-3), 150, 30);
				dpanel.add(dlbl[i]);
				
				dtxt[i] = new JTextField();
				dtxt[i].setBounds(360, 40+45*(i-3), 100, 30);
				dtxt[i].setFont(Style.txtfont);
				dtxt[i].setBorder(Style.border1);
				dpanel.add(dtxt[i]);
			}
		}
		for (int i = 1; i < dtxt.length; i++)
		{
			dtxt[i].setEditable(false);
		}
		dtxt[0].addFocusListener(this);
		dtxt[0].addKeyListener(this);
	}
	
	public static void main(String[] args)
	{
		new DesgDetails();
	}
	
	
	Connection cn;
	Statement stmt;
	ResultSet rs;
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == button[4])
			f.dispose();
		
		if(ae.getSource() == button[3])
		{
			try
			{
				
					stmt=cn.createStatement();
					int x = stmt.executeUpdate("delete from desg_details where desgid='"+txt.getText()+"'");
					if(x>0)JOptionPane.showMessageDialog(f,x+" row/s Record Deleted");
					else JOptionPane.showMessageDialog(f,"No Record to Delete.");
					stmt.close();
					txt.setText("");
					txtname.setText("");
					for (int i = 0; i < dtxt.length; i++)
					{
						dtxt[i].setText("");
					}
					txt.grabFocus();
				
			}
			catch(Exception ex)
			{
	        	JOptionPane.showMessageDialog(f, ex.toString());
	        }
			
		}
			
			if(ae.getSource()== b)
			{
				boolean flag=false;
				try
				{
					stmt=cn.createStatement();
					rs=stmt.executeQuery("select * from desg_details where desgid='"+txt.getText()+"'");
					while(rs.next())
					{
						flag=true;
						txt.setText(rs.getString("desgid"));
						txtname.setText(rs.getString("desgname"));
						dtxt[0].setText(rs.getString("basic"));
						dtxt[1].setText(rs.getString("ta"));
						dtxt[2].setText(rs.getString("pf"));
						dtxt[3].setText(rs.getString("da"));
						dtxt[4].setText(rs.getString("hra"));
						dtxt[5].setText(rs.getString("ma"));
						dtxt[6].setText(rs.getString("total"));
					
						
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
					txt.setText("");
					txt.grabFocus();
				}
			}
		
		if(ae.getSource() == button[2])
		{
			for (int i = 0; i < dtxt.length; i++)
				dtxt[i].setText("");
			txt.setText("");
			txtname.setText("");
			
			txt.grabFocus();
		}
		
		if(ae.getSource() == button[1])
		{
			if(txt.getText().length()>0)
			{
				try{
					stmt=cn.createStatement();
					stmt.executeUpdate("update desg_details set desgname='"+txtname.getText()+"',basic='"+dtxt[0].getText()+"',ta='"+dtxt[1].getText()+"',pf='"+dtxt[2].getText()+"',da='"+dtxt[3].getText()+"',hra='"+dtxt[4].getText()+"',ma='"+dtxt[5].getText()+"',total='"+dtxt[6].getText()+"' where desgid='"+txt.getText()+"'");
					JOptionPane.showMessageDialog(f, "Record Updated");
					stmt.close();
				}
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        }
			}
			else
			{
				JOptionPane.showMessageDialog(f, "No Record to Update.");
			}
			
		}
		
		if(ae.getSource() == button[0])
		{
			String sql="insert into desg_details values('"+txt.getText()+"','"+txtname.getText()+"','"+dtxt[0].getText()+"','"+dtxt[1].getText()+"','"+dtxt[2].getText()+"','"+dtxt[3].getText()+"','"+dtxt[4].getText()+"','"+dtxt[5].getText()+"','"+dtxt[6].getText()+"')";
			try
			{
				stmt=cn.createStatement();
				stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(f,"Designation Details Saved");
				stmt.close();
			}
			catch(Exception ex)
			{
	        	JOptionPane.showMessageDialog(f, ex.toString());
	        }
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
		if(arg0.getSource() == dtxt[0])
		{
			if(dtxt[0].getText().length()>0)
			{
			try
			{
				basic = Integer.parseInt(dtxt[0].getText());
				double ta = basic*0.1;
				ta = (int)(ta*100);
				 dtxt[1].setText(Double.toString(ta/100));
				 double pf = basic*0.2;
					pf = (int)(pf*100);
					 dtxt[2].setText(Double.toString(pf/100));
					 double da = basic*0.3;
						da = (int)(da*100);
						 dtxt[3].setText(Double.toString(da/100));
						 double hra = basic*0.4;
							hra = (int)(hra*100);
							 dtxt[4].setText(Double.toString(hra/100));
							 double ma = basic*0.5;
								ma = (int)(ma*100);
								 dtxt[5].setText(Double.toString(ma/100));
				
								 double total = basic + Double.parseDouble(dtxt[1].getText()) + Double.parseDouble(dtxt[2].getText()) + Double.parseDouble(dtxt[3].getText()) + Double.parseDouble(dtxt[4].getText()) + Double.parseDouble(dtxt[5].getText());
									total = (int)(total*100);
									 dtxt[6].setText(Double.toString(total/100));
		//		 dtxt[6].setText(Double.toString(basic + Double.parseDouble(dtxt[1].getText()) + Double.parseDouble(dtxt[2].getText()) + Double.parseDouble(dtxt[3].getText()) + Double.parseDouble(dtxt[4].getText()) + Double.parseDouble(dtxt[5].getText())));
				 
			} 
			catch (Exception e)
			{
//				JOptionPane.showMessageDialog(f, "Invalid Entry!!!");
//				dtxt[0].setText("");
//				dtxt[0].grabFocus();
			}
			}
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
		if(arg0.getSource() == dtxt[0])
		{
			if(dtxt[0].getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(dtxt[0].getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!! Only numbers 0-9 are allowed.");
					dtxt[0].setText("");;			
					dtxt[0].grabFocus();		
				}
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
