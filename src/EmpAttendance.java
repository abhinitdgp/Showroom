import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;


public class EmpAttendance implements ActionListener,ItemListener
{
	JFrame f;
	public EmpAttendance()
	{
		f = new JFrame("Automobile Showroom Management System");
		f.setBounds(400, 200, 500, 400);
		frameDesign();
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","honda","showroom");
			cn1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","honda","showroom");
			
			stmt1 = cn1.createStatement();
			rs1 = stmt1.executeQuery("select empid from employee_details");
			while(rs1.next())
			{
				cbid.addItem(rs1.getString("empid"));
			}
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(f, e.toString());
		}
	}
	
	
	JPanel spanel,panel;
	JButton button[] = new JButton[3];
	String btext[] = {"Ok","Reset","Exit"};
	JLabel hlbl;

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
		hlbl = new JLabel("Attendance");
		hlbl.setBounds(120, 10, 250, 30);
		hlbl.setFont(Style.fhdfont);
		f.add(hlbl);
		
		f.add(panel);
		f.add(spanel,"South");
		
	}
	
	
	JLabel label[] = new JLabel[3];
	String ltxt[] = {"Employee ID","Employee Name","Current Date"};
	JTextField txtfld[] = new JTextField[2];
	JComboBox cbid;
	JButton b;
	JPanel apanel;
	JLabel fhd ;

	private void panelDesign()
	{
		panel.setLayout(null);
		panel.setBackground(Color.getHSBColor(100, 100, 100));
		for (int i = 0; i < label.length; i++)
		{
			label[i] = new JLabel(ltxt[i]);
			label[i].setFont(Style.lblfont);
			label[i].setBounds(30, 60+50*i, 150, 30);
			panel.add(label[i]);
		}
		for (int i = 0; i < txtfld.length; i++)
		{
			txtfld[i] = new JTextField();
			txtfld[i].setBounds(190, 110+50*i, 130, 30);
			txtfld[i].setFont(Style.txtfont);
			txtfld[i].setBorder(Style.border1);
			panel.add(txtfld[i]);
		}
		txtfld[0].setSize(200, 30);
		txtfld[1].setEditable(false);
		txtfld[1].setText(Style.getDate(new Date()));;
		
		cbid = new JComboBox();
		cbid.setBounds(190, 60, 150, 30);
		cbid.addItem("");
		cbid.addItemListener(this);
		panel.add(cbid);
//		
//		b = new JButton("Search");
//		b.setBounds(360, 50, 100, 30);
//		panel.add(b);
		
		apanel = new  JPanel();
		apanel.setBounds(30, 200, 300, 100);
		Border border =BorderFactory.createLineBorder(Color.black,4);
		Border border1 = BorderFactory.createTitledBorder(border, "Status",1,2,new Font("Footlight MT Light",Font.BOLD,22));
		apanel.setBorder(border1);
		panel.add(apanel);
		
		apanelDesign();
	}
	
	
	private String getDate()
	{
		Date d = new Date();
		DateFormat d1 = DateFormat.getDateInstance(DateFormat.LONG);		
		return d1.format(d);
	}


	JRadioButton p,a;

	private void apanelDesign()
	{
		apanel.setLayout(null);
		apanel.setBackground(Color.getHSBColor(100, 100, 100));
		p = new JRadioButton("Present");
		a = new JRadioButton("Absent");
		ButtonGroup bg = new ButtonGroup();
		bg.add(p);
		bg.add(a);
	
		p.setFont(Style.lblfont);
		a.setFont(Style.lblfont);
		p.setBackground(Color.getHSBColor(100, 100, 100));
		a.setBackground(Color.getHSBColor(100, 100, 100));
		p.setBounds(40, 30, 110, 50);
		a.setBounds(150, 30, 110, 50);
		apanel.add(p);
		apanel.add(a);	
	}

	
	public static void main(String[] args)
	{
		new EmpAttendance();

	}

	
	Connection cn,cn1;
	Statement stmt,stmt1;
	ResultSet rs,rs1;
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource() == button[2])
			f.dispose();
		
		if(arg0.getSource() == button[1])
		{
			cbid.setSelectedIndex(0);
			txtfld[0].setText("");
			p.setSelected(false);
			a.setSelected(false);
		}
		
		if(arg0.getSource() == button[0])
		{
			if(cbid.getSelectedIndex()>0)
			{
				String status = p.isSelected()?"Present":"Absent";
				String sql="insert into emp_attendance values('"+cbid.getSelectedItem()+"','"+txtfld[1].getText()+"','"+status+"')";
				try
				{
					stmt=cn.createStatement();
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(f,"Details Saved");
					
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
		
	}


	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		if(arg0.getSource() == cbid)
		{
			try
			{
				stmt1 = cn1.createStatement();
				rs1 = stmt1.executeQuery("select empname from employee_details where empid='"+cbid.getSelectedItem()+"'");
				while(rs1.next())
				{
					txtfld[0].setText(rs1.getString("empname"));
				}
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(f, e.toString());
			}
		}
	}
}
