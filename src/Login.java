import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Login implements ActionListener,KeyListener
{
	JFrame f;
	public Login()
	{
		f = new JFrame("Automobile Showroom Management System");
		frameDesign();
		f.setVisible(true);
		f.setSize(1024, 725);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		txtid.grabFocus();
	}

	
	JPanel center;
	Image im = new ImageIcon("login3.jpg").getImage();
	private void frameDesign()
	{
		center = new JPanel()
		{
			@Override
			public void paint(Graphics arg0)
			{
				arg0.drawImage(im, 0, 0, 1024, 700, null);
				super.paint(arg0);
			}
		};
		center.setOpaque(false);
		centerDesign();
		f.add(center);
	}

	
	JPanel loginpanel;
	JLabel lblaccess;
	
	private void centerDesign()
	{
		center.setLayout(null);
		loginpanel = new JPanel();
		loginpanel.setBounds(550, 460, 400, 250);
		loginpanelDesign();
		center.add(loginpanel);
		
	}
	
	
	JLabel lblid,lblpass;
	JTextField txtid,txtpass;
	JPasswordField pf;
	JButton btnlogin;

	private void loginpanelDesign()
	{
		loginpanel.setLayout(null);
		loginpanel.setOpaque(false);;
		
		lblid = new JLabel("User name");
		lblpass = new JLabel("Password");
		
		txtid = new JTextField();
		txtpass = new JTextField();
		
		btnlogin = new JButton("Login");
		btnlogin.addActionListener(this);
		
		lblid.setBounds(20, 30, 180, 30);
		lblid.setFont(new Font("Copperplate Gothic Bold",Font.BOLD,26));
		lblid.setForeground(Color.white);;
		loginpanel.add(lblid);
		
		txtid.setBounds(200, 30, 190, 30);
		txtid.setBorder(Style.border1);
		txtid.setFont(Style.txtfont);
		loginpanel.add(txtid);
		
		lblpass.setBounds(20, 80, 200, 30);
		lblpass.setFont(new Font("Copperplate Gothic Bold",Font.BOLD,26));
		lblpass.setForeground(Color.white);
		loginpanel.add(lblpass);
		
		pf = new JPasswordField();
		pf.setBounds(200, 80, 190, 30);
		pf.setBorder(Style.border1);
		pf.setFont(Style.txtfont);
		pf.setEchoChar('*');
		loginpanel.add(pf);
		
		btnlogin.setBounds(120, 140, 100, 40);
		loginpanel.add(btnlogin);
		btnlogin.addKeyListener(this);
	}

	public static void main(String[] args)
	{
		new Login();

	}

	Statement stmt;
	ResultSet rs;
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource() == btnlogin)
		{
			if(txtid.getText().length()>0 && pf.getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String sql = "select username,pass,acc_level from login where username='"+txtid.getText()+"'";
				try
				{
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next())
					{
						String id = rs.getString(1);
						String acc = rs.getString("acc_level");
						
						if(txtid.getText().equals(id)==true)
						{
							if(rs.getString(2).equals(pf.getText()))
							{
								if("admin".equals(acc))
								{
									JOptionPane.showMessageDialog(null, "You have administrative privileges. ");
									new MainForm();
								}
									
								else if("user".equals(acc))
								{
									JOptionPane.showMessageDialog(null, "You have user privileges.");
									new MainForm1();
								}
								f.dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Incorrect password.");
								pf.setText("");
								pf.grabFocus();
							}
						}
						else //if(id.equals(txtid.getText())== false)
						{
							System.out.println("incorrect");
							JOptionPane.showMessageDialog(null, "Invalid username.");
							txtid.setText("");
							pf.setText("");
							txtid.grabFocus();
						}
					}
					con.close();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Enter username and password");
				pf.setText("");
				txtid.grabFocus();
			}		
		}	
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		if(arg0.getSource() == btnlogin)
		{
			if(arg0.getKeyCode() == arg0.VK_ENTER)
			{
				if(arg0.getSource() == btnlogin)
				{
					if(txtid.getText().length()>0 && pf.getText().length()>0)
					{
						Connection con = DataBaseUtility.getConnection();
						String sql = "select username,pass,acc_level from login where username='"+txtid.getText()+"'";
						try
						{
							stmt = con.createStatement();
							rs = stmt.executeQuery(sql);
							while(rs.next())
							{
								String id = rs.getString(1);
								String acc = rs.getString("acc_level");
								
								if(txtid.getText().equals(id)==true)
								{
									if(rs.getString(2).equals(pf.getText()))
									{
										if("admin".equals(acc))
										{
											JOptionPane.showMessageDialog(null, "You have administrative privileges. ");
											new MainForm();
										}
											
										else if("user".equals(acc))
										{
											JOptionPane.showMessageDialog(null, "You have user privileges.");
											new MainForm1();
										}
										f.dispose();
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Incorrect password.");
										pf.setText("");
										pf.grabFocus();
									}
								}
								else if(id.equals(txtid.getText())== false)
								{
									System.out.println("incorrect");
									JOptionPane.showMessageDialog(null, "Invalid username.");
									txtid.setText("");
									pf.setText("");
									txtid.grabFocus();
								}
							}
							con.close();
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Enter username and password");
						pf.setText("");
						txtid.grabFocus();
					}		
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
