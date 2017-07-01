import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class MainForm1 implements ActionListener
{
	JFrame f;
	JButton log;
	public MainForm1()
	{
		f = new JFrame();
		frameDesign();
		menuDesign();
		f.setVisible(true);
		f.setSize(1024, 725);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setTitle("Automobile Showroom Management System");
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	JMenuBar menubar = new JMenuBar();
	JMenu menu[] = new JMenu[11];
	JMenuItem menuitem[][] = new JMenuItem[11][5];
	String mlabel[] = {"Sale","Employee","Vehicle","Accessory","Order","Interaction","Report","Search","Add User","Help","About Us"};
	String mItemLabel[][] = {{"Enquiry","Booking","Registration"},{"Employee Details","Employee Attendance","Employee Salary","Designation Details"},{"Vehicle Details"},{"Accessory Details"},{"Vehicle","Accessory"},{"Complaint","Feedback"},{},{},{},{},{"About Software"}};
	
	private void menuDesign()
	{
		for (int i = 0; i < menu.length; i++)
		{
			menu[i] = new JMenu(mlabel[i]);
			for (int j = 0; j < mItemLabel[i].length; j++)
			{
				menuitem[i][j] = new JMenuItem(mItemLabel[i][j]);
				menuitem[i][j].addActionListener(this);
				menu[i].add(menuitem[i][j]);
			}
			menubar.add(menu[i]);
		}
		f.setJMenuBar(menubar);
		menuitem[1][2].setEnabled(false);
		menuitem[1][3].setEnabled(false);
		
	}

	
	JPanel center;
	Image im = new ImageIcon("Capture.jpg").getImage();
	private void frameDesign()
	{
		center =  new JPanel()
		{
			@Override
			public void paint(Graphics arg0)
			{
				arg0.drawImage(im, 0, 0, 1024, 700, null);
				super.paint(arg0);
			}
		};
		center.setOpaque(false);
		f.add(center);
		center.setLayout(null);
		log = new JButton(new ImageIcon("a.jpg"));
		log.setBounds(920, 10, 70, 70);
		log.addActionListener(this);
		center.add(log);
	}

	public static void main(String[] args)
	{
		new MainForm1();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
		if(arg0.getSource() == log)
		{
			new Login();
			f.dispose();
		}
		if(arg0.getSource() == menuitem[0][0])
			new Enquiry();
		if(arg0.getSource() == menuitem[0][1])
			new Book();
		if(arg0.getSource() == menuitem[0][2])
			new Reg();
		
		if(arg0.getSource() == menuitem[2][0])
			new VehicleDetail();
		
		if(arg0.getSource() == menuitem[1][0])
			new EmployeeDetails();
		if(arg0.getSource() == menuitem[1][1])
			new EmpAttendance();
		if(arg0.getSource() == menuitem[1][2])
			new EmpSalary();
		if(arg0.getSource() == menuitem[1][3])
			new DesgDetails();
		
		if(arg0.getSource() == menuitem[5][0])
			new CustComplaint();
		if(arg0.getSource() == menuitem[5][1])
			new Feedback();		
		if(arg0.getSource() == menuitem[4][0])
			new Vorder();	
		if(arg0.getSource() == menuitem[4][1])
			new Accorder();	
	}

}
