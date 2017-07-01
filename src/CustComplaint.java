import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class CustComplaint implements ActionListener
{
	JFrame f;
//	Dimension d;
	public CustComplaint()
	{
		f = new JFrame("Automobile Showroom Management System");
	//	d = Toolkit.getDefaultToolkit().getScreenSize();
		frameDesign();
		f.setVisible(true);
	//	f.setSize(d.width,d.height-35);
		f.setBounds(350, 100, 700, 600);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	JPanel panel,spanel,epanel;
	JButton button[] = new JButton[3];
	String btext[] = {"Submit","Reset","Cancel"};

	private void frameDesign()
	{
		spanel = new JPanel();
		panel = new JPanel()
		{
//			Image im = new ImageIcon("emp.jpg").getImage();
//			@Override
//			
//			protected void paintComponent(Graphics arg0)
//			{
//				arg0.drawImage(im, 0, 0, d.width, d.height, null);
//				super.paintComponent(arg0);
//			}
		};
		panelDesign();
		
		spanel.setBackground(Color.DARK_GRAY);
		
		for (int i = 0; i < button.length; i++)
		{
			button[i] = new JButton(btext[i]);
			//button[i].addActionListener(this);
			spanel.add(button[i]);
		}
		panel.setOpaque(true);	
		f.add(panel);
		f.add(spanel,"South");
		f.setBackground(Style.panelColor);
		
	}

	
	JLabel label[] = new JLabel[9];
	String ltxt[] = {"Complaint ID","Reg No.(if any)","Name","Mob","Address","Complaint Type","Date","Time","Description"};
	JTextField txtfld[] = new JTextField[6];
	TextArea txtar[] = new TextArea[2];
	JComboBox cb;
	JButton b;
	JLabel head;
	
	private void panelDesign()
	{
		panel.setLayout(null);
		panel.setBackground(Style.panelColor);
		panel.setOpaque(true);
		head = new JLabel("Complaint Form");
		head.setFont(Style.fhdfont);
		head.setBounds(150, 5, 400, 30);
		panel.add(head);
		for (int i = 0; i < 5; i++)
		{
			label[i] = new JLabel(ltxt[i]);
			label[i].setFont(Style.lblfont);
		//	label[i].setFont(font);
			label[i].setBounds(30, 50+40*i, 130, 30);
			panel.add(label[i]);
		}
		
		for (int i = 0; i < 4; i++)
		{
			txtfld[i] = new JTextField();
			txtfld[i].setBounds(180, 50+40*i, 140, 30);
			txtfld[i].setBorder(Style.border1);
			txtfld[i].setFont(Style.txtfont);
			panel.add(txtfld[i]);
		}
		
		b = new JButton("Search");
		b.setBounds(310, 50, 100, 30);
		panel.add(b);
		
		txtfld[2].setSize(200, 30);
		
		txtar[0] = new TextArea();
		txtar[0].setBounds(180, 210, 200, 60);
		panel.add(txtar[0]);
		
		for (int i = 5; i < label.length; i++)
		{
				label[i] = new JLabel(ltxt[i]);
//				Font font = new Font("Arial",Font.BOLD,14);
//				label[i].setFont(font);
				label[i].setFont(Style.lblfont);
				label[i].setBounds(30, 80+40*i, 120, 30);
				panel.add(label[i]);
		}
		
		cb = new JComboBox();
		cb.setBounds(180, 280, 120, 30);
		panel.add(cb);
		
		for (int i = 4; i < 6; i++)
		{
			txtfld[i] = new JTextField();
			txtfld[i].setBounds(180, 160+40*i, 130, 30);
			txtfld[i].setFont(Style.txtfont);
			txtfld[i].setBorder(Style.border1);
			panel.add(txtfld[i]);
			txtfld[i].setEditable(false);
		}
		
		txtfld[4].setText(getDate());
		txtfld[5].setText(getTime());
		
		txtar[1] = new TextArea();
		txtar[1].setBounds(180, 400, 350, 80);
		panel.add(txtar[1]);
	}

	private String getTime()
	{
		Date d = new Date();
	//	DateFormat d1 = DateFormat.getDateInstance(DateFormat.SHORT);
		DateFormat d2 = DateFormat.getTimeInstance(DateFormat.SHORT);
		return d2.format(d);
	}

	private String getDate()
	{
		Date d = new Date();
		DateFormat d1 = DateFormat.getDateInstance(DateFormat.MEDIUM);
	//	DateFormat d2 = DateFormat.getTimeInstance(DateFormat.SHORT);
		return d1.format(d);
	}

	public static void main(String[] args)
	{
		new CustComplaint();

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
