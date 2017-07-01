import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class EmpSalary implements ActionListener,FocusListener,ItemListener
{
	JFrame f;
	JLabel head;
	public EmpSalary()
	{
		f = new JFrame("Automobile Showroom Management System");
		f.setBounds(360, 100, 700, 400);
		frameDesign();
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		head = new JLabel("Employee Salary");
		head.setBounds(175, 10, 380, 30);
		head.setFont(Style.fhdfont);
		panel.add(head);	
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","honda","showroom");
			cn1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","honda","showroom");
			cn2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","honda","showroom");
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(f, e.toString());
		}
	}
	
	
	JPanel panel,spanel;
	JButton button[] = new JButton[3];
	String btext[] = {"Save","Reset","Cancel"};
	
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
		
	
	JLabel label[] = new JLabel[8];
	String ltxt[] = {"Employee ID","Employee Name","Designation","Month","Present Days","Absent Days","Current Date","Net Amount  Rs."};
	JTextField txtfld[] = new JTextField[7];
	String cbitem[] = {"","January","February","March","April","May","June","July","August","September","October","November","December"};
	JComboBox cb = new JComboBox(cbitem);
	
	JButton b;
	
	private void panelDesign()
	{
		panel.setLayout(null);
		panel.setBackground(Style.panelColor);
		for (int i = 0; i < label.length; i++)
		{
			label[i] = new JLabel(ltxt[i]);
			label[i].setFont(Style.lblfont);
			panel.add(label[i]);
			
			if(i<4)
			{
				label[i].setBounds(30, 70+50*i, 150, 30);
			}
			else
			{
				label[i].setBounds(405, 70+50*(i-4), 150, 30);
			}
		}
		for (int i = 0; i < 3; i++)
		{
			txtfld[i] = new JTextField();
			txtfld[i].setBounds(170, 70+50*i, 150, 30);
			txtfld[i].setBorder(Style.border1);
			txtfld[i].setFont(Style.txtfont);
			panel.add(txtfld[i]);
		}
		txtfld[0].setSize(100, 30);
		txtfld[0].addFocusListener(this);
		
		b = new JButton("Search");
		b.setBounds(280, 70, 80, 30);
		panel.add(b);
		
		txtfld[1].setSize(200, 30);
		
		
		cb.setBounds(170, 220, 150, 30);
		panel.add(cb);
		
//		txtfld[3] = new JTextField();
//		txtfld[3].setBounds(170, 270, 100, 30);
//		panel.add(txtfld[3]);
		
		for (int i = 3; i < 6; i++)
		{
			txtfld[i] = new JTextField();
			txtfld[i].setBounds(540, 70+50*(i-3), 130, 30);
			txtfld[i].setBorder(Style.border1);
			txtfld[i].setFont(Style.txtfont);
			panel.add(txtfld[i]);
		}
		
		txtfld[6] = new JTextField();
		txtfld[6].setBounds(540, 220, 100, 30);
		panel.add(txtfld[6]);
		
		txtfld[5].setText(Style.getDate(new Date()));
		txtfld[5].setEditable(false);
	}
	
	private String getDate()
	{
		Date d = new Date();
		DateFormat d1 = DateFormat.getDateInstance(DateFormat.LONG);		
		return d1.format(d);
	}

	public static void main(String[] args)
	{
		new EmpSalary();

	}
	
	
	Connection cn,cn1,cn2;
	Statement stmt,stmt1,stmt2;
	ResultSet rs,rs1,rs2;
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource() == button[2])
			f.dispose();
		
		if(arg0.getSource() == button[1])
		{
			for (int i = 0; i < txtfld.length; i++)
				txtfld[i].setText("");
			cb.setSelectedIndex(0);
		}
		
		if(arg0.getSource() == button[0])
		{
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		if(e.getSource() == txtfld[0])
		{
			try
			{
				stmt1 = cn1.createStatement();
				rs1 = stmt1.executeQuery("select empname from employee_details where empid='"+txtfld[0].getText()+"'");
				while(rs1.next())
				{
					txtfld[1].setText(rs1.getString("empname"));
				}
				
				stmt2 = cn2.createStatement();
				rs2 = stmt2.executeQuery("select desgname from desg_details where desgid=(select desgid from employee_details where empid='"+txtfld[0].getText()+"')");
				while(rs2.next())
				{
					txtfld[2].setText(rs2.getString("desgname"));
				}
			} catch (Exception e2)
			{
				JOptionPane.showMessageDialog(f, e2.toString());
			}
		}
		
	}

}
