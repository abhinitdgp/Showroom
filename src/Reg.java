import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;


public class Reg implements ActionListener,FocusListener
{
	JFrame f;
	
	public Reg()
	{
		f = new JFrame();
		frameDesign();
		f.setVisible(true);
		f.setSize(1024, 725);
		f.setLocationRelativeTo(null);
		f.setTitle("Automobile Showroom Management System");
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	

	JPanel center,spanel,opanel,cpanel;
	JButton button[] = new JButton[6];
	String btext[] = {"Add","Save","Update","Reset","Delete","Exit"};
	
	private void frameDesign()
	{
		spanel = new JPanel();
		center = new JPanel();
		centerDesign();
		
		spanel.setBackground(Color.DARK_GRAY);
		for (int i = 0; i < button.length; i++)
		{
			button[i] = new JButton(btext[i]);
			button[i].addActionListener(this);
			spanel.add(button[i]);
		}
//		button[1].setIcon(new ImageIcon(""));
		f.add(center);
		f.add(spanel,"South");
	}

	
	JLabel head,bkid,regid,dt;
	JTextField tbkid,tregid,tdt;
	
	private void centerDesign()
	{
		opanel = new JPanel();
		cpanel = new JPanel();
		
		center.setLayout(null);
		center.setBackground(Style.panelColor);
		cpanelDesign();
		opanelDesign();
		center.add(cpanel);
		center.add(opanel);
			
		head = new JLabel("Registration");
		head.setBounds(370, 10, 380, 30);
		head.setFont(Style.fhdfont);
		center.add(head);
		
		bkid = new JLabel("Booking ID");
		bkid.setBounds(110, 60, 180, 30);
		bkid.setFont(Style.lblfont);
		center.add(bkid);
		
		tbkid = new JTextField();
		tbkid.setBounds(220, 60, 120, 30);
		tbkid.setFont(Style.txtfont);
		tbkid.setBorder(Style.border1);
		center.add(tbkid);
		
		regid = new JLabel("Registration ID");
		regid.setBounds(410, 60, 180, 30);
		regid.setFont(Style.lblfont);
		center.add(regid);
		
		tregid = new JTextField();
		tregid.setBounds(560, 60, 120, 30);
		tregid.setFont(Style.txtfont);
		tregid.setEditable(false);
		tregid.setBorder(Style.border1);
		center.add(tregid);	
		
		dt = new JLabel("Date");
		dt.setBounds(750, 60, 180, 30);
		dt.setFont(Style.lblfont);
		center.add(dt);
		
		tdt = new JTextField();
		tdt.setBounds(810, 60, 130, 30);
		tdt.setFont(Style.txtfont);
		tdt.setText(Style.getDate(new Date()));
		tdt.setEditable(false);
		tdt.setBorder(Style.border1);
		center.add(tdt);	
	}
	
	JLabel olbl[] = new JLabel[10];
	String otxt[] = {"For Office Use...","Chassis No.","Engine No.","Battery No.","Key No.","Book No.","DD No.","Ex-showroom Price","Insurance No.","Registration No."};
	JTextField ovalue[] = new JTextField[9];

	private void opanelDesign()
	{
		opanel.setLayout(null);
		opanel.setBounds(40, 390, 900, 300);
		opanel.setOpaque(false);
//		BORDER BORDER =BORDERFACTORY.CREATELINEBORDER(COLOR.GRAY,6);
//		BORDER BORDER1 = BORDERFACTORY.CREATETITLEDBORDER(BORDER, "BRAKING",2,2,NEW FONT("FOOTLIGHT MT LIGHT",FONT.BOLD,26));
//		Opanel.setBorder(border1);
		
		olbl[0] = new JLabel("(For Office Use Only)");
		olbl[0].setBounds(350, 25, 300, 30);
		olbl[0].setFont(Style.lblfont);
		opanel.add(olbl[0]);
		
		for (int i = 1; i < 6; i++)
		{
			olbl[i] = new JLabel(otxt[i]);
			olbl[i].setBounds(40, 30+35*(i), 150, 25);
			olbl[i].setFont(Style.lblfont);
			opanel.add(olbl[i]);
		}
		
		
		for (int i = 0; i < 5; i++)
		{
			ovalue[i] = new JTextField();
			ovalue[i].setBounds(200, 30+35*(i+1), 150, 30);
			ovalue[i].setBorder(Style.border1);
			ovalue[i].setFont(Style.txtfont);
			opanel.add(ovalue[i]);
		}
		
		for (int i = 6; i < olbl.length; i++)
		{
			olbl[i] = new JLabel(otxt[i]);
			olbl[i].setBounds(500, 80+35*(i-6), 150, 25);
			olbl[i].setFont(Style.lblfont);
			opanel.add(olbl[i]);
		}
		
		for (int i = 5; i < ovalue.length; i++)
		{
			ovalue[i] = new JTextField();
			ovalue[i].setBounds(700, 80+35*(i-5), 150, 30);
			ovalue[i].setBorder(Style.border1);
			ovalue[i].setFont(Style.txtfont);
			opanel.add(ovalue[i]);
		}
		
	}

	
	JLabel clbl[] = new JLabel[7];
	String ctxt[] = {"Name","S/o,W/o,D/o","Address","Mobile","Model","Color","Documents"};
	JTextField cvalue[] = new JTextField[6];
	TextArea txtar;
	String doc[] = {"-----SELECT-----","Electricity Bill","Bank Passbook","Telephone Bill","Passport","Voter I-card","Driving License"};
	JComboBox cbdoc = new JComboBox(doc);
	JCheckBox chb[] = new JCheckBox[3];
	
	private void cpanelDesign()
	{
		cpanel.setLayout(null);
		cpanel.setBounds(40, 100, 900, 300);
		cpanel.setOpaque(false);
//		Border border =BorderFactory.createLineBorder(Color.gray,6);
//		Border border1 = BorderFactory.createTitledBorder(border, "Braking",2,2,new Font("Footlight MT Light",Font.BOLD,26));
//		cpanel.setBorder(border1);
		
		for (int i = 0; i < 3; i++)
		{
			clbl[i] = new JLabel(ctxt[i]);
			clbl[i].setBounds(40, 30+35*i, 150, 25);
			clbl[i].setFont(Style.lblfont);
			cpanel.add(clbl[i]);
		}
				
		for (int i = 0; i < 2; i++)
		{
			cvalue[i] = new JTextField();
			cvalue[i].setBounds(200, 30+35*i, 150, 30);
			cvalue[i].setBorder(Style.border1);
			cvalue[i].setFont(Style.txtfont);
			cvalue[i].setEditable(false);
			cpanel.add(cvalue[i]);
		}
		cvalue[0].setSize(200, 30);
		cvalue[1].setSize(200, 30);
		
		txtar = new TextArea();
		txtar.setBounds(200, 100, 250, 80);
		txtar.setFont(Style.txtfont);
		txtar.setEditable(false);
		cpanel.add(txtar);
		
		for (int i = 3; i < 6; i++)
		{
			clbl[i] = new JLabel(ctxt[i]);
			clbl[i].setBounds(40, 80+35*i, 150, 25);
			clbl[i].setFont(Style.lblfont);
			cpanel.add(clbl[i]);
		}
		
		for (int i = 3; i < 6; i++)
		{
			cvalue[i] = new JTextField();
			cvalue[i].setBounds(200, 80+35*i, 150, 30);
			cvalue[i].setBorder(Style.border1);
			cvalue[i].setFont(Style.txtfont);
			cvalue[i].setEditable(false);
			cpanel.add(cvalue[i]);
		}
		
		clbl[6] = new JLabel(ctxt[6]);
		clbl[6].setBounds(500, 30, 150, 30);
		clbl[6].setFont(Style.lblfont);
		cpanel.add(clbl[6]);
		
		cbdoc.setBounds(620, 30, 210, 30);
		cbdoc.setFont(Style.txtfont);
		cpanel.add(cbdoc);
		
		JLabel sel = new JLabel("If you want, tick the checkbox below:");
		sel.setBounds(550, 80, 370, 30);
		sel.setFont(Style.lblfont);
		cpanel.add(sel);
		
		chb[0] = new JCheckBox("Insurance");
		chb[0].setBounds(500, 120, 150, 30);
		chb[0].setOpaque(false);
		chb[0].setFont(Style.lblfont);
		cpanel.add(chb[0]);
		
		chb[1] = new JCheckBox("Hypothetication");
		chb[1].setBounds(500, 170, 220, 30);
		chb[1].setOpaque(false);
		chb[1].setFont(Style.lblfont);
		cpanel.add(chb[1]);
		
		chb[2] = new JCheckBox("Registration");
		chb[2].setBounds(500, 220, 150, 30);
		chb[2].setOpaque(false);
		chb[2].setFont(Style.lblfont);
		cpanel.add(chb[2]);
	}

	public static void main(String[] args)
	{
		new Reg();

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
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
//		if(arg0.getSource() == txtfld[3])
//		{
//			txtfld[3].removeKeyListener(this);
//			if(txtfld[3].getText().length()>10 || txtfld[3].getText().length()<10)
//			{
//					JOptionPane.showMessageDialog(null, "Invalid mobile number!!!");
//					txtfld[3].grabFocus();			
//			}
//			txtfld[3].addKeyListener(this);
//		
	}

}

