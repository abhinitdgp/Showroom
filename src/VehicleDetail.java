import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorChooserUI;
import javax.swing.plaf.FileChooserUI;


public class VehicleDetail implements ActionListener,KeyListener,FocusListener,ItemListener,ListSelectionListener
{
	JFrame f;
	
	public VehicleDetail()
	{
		f = new JFrame("Automobile Showroom Management System");
		frameDesign();
		f.setVisible(true);
		f.setBounds(160, 0, 1024, 725);
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		tvid.grabFocus();
	}

	
	JPanel sp,center;
	JButton button[] = new JButton[3];
	String btext[] = {"Add","Delete","Cancel"};
	
	private void frameDesign()
	{
		center = new JPanel();
	//	center.setBackground(Style.panelColor);
		sp = new JPanel();	
		sp.setBackground(Color.DARK_GRAY);
		
		for (int i = 0; i < button.length; i++)
		{
			button[i] = new JButton(btext[i]);
			button[i].addActionListener(this);
			//button[i].setFont("Kristen ITC");
			sp.add(button[i]);
		}
		button[1].setEnabled(false);
		centerDesign();
		f.add(center);
		f.add(sp,"South");
	
	}

	
	JPanel hpanel,eastpanel,engine,chs,elec,clr,per,wt,wheel,brk;
	JTabbedPane tb;
	
	Image imh = new ImageIcon("head.jpg").getImage();
	Image imen = new ImageIcon("engine1.jpg").getImage();
	Image imdim = new ImageIcon("dim2.jpg").getImage();
	Image imelec = new ImageIcon("elec1.jpg").getImage();
	Image imchs = new ImageIcon("sus1.jpg").getImage();
	Image imwh = new ImageIcon("wheel1.jpg").getImage();
	Image imper = new ImageIcon("per1.jpg").getImage();
	Image imclr = new ImageIcon("clr1.jpg").getImage();
	
	private void centerDesign()
	{
		center.setLayout(null);
		hpanel = new JPanel();
//		{
//			@Override
//			public void paint(Graphics arg0)
//			{
//				arg0.drawImage(imh, 0, 0, 1024, 80, null);
//				super.paint(arg0);
//			}
//		};
		hpanel.setOpaque(false);
		eastpanel = new JPanel();
		eastpanel.setOpaque(false);
		hpanel.setBounds(0, 0, 1024, 80);
		hpanel.setBorder(Style.border1);
		center.add(hpanel);
		
		hpanelDesign();
		
		eastpanel.setBounds(0, 80, 150, 725);
		eastpanel.setBorder(Style.border1);
		center.add(eastpanel);
		
		eastpanelDesign();
		
		engine = new JPanel()
		{
			@Override
			public void paint(Graphics arg0)
			{
				arg0.drawImage(imen, 0, 0, 900, 650, null);
				super.paint(arg0);
			}
		};
		engine.setOpaque(false);
		chs = new JPanel()
		{
			@Override
			public void paint(Graphics arg0)
			{
				arg0.drawImage(imchs, 0, 0, 900, 650, null);
				super.paint(arg0);
			}
		};
		elec = new JPanel()
		{
			@Override
			public void paint(Graphics arg0)
			{
				arg0.drawImage(imelec, 0, 0, 900, 650, null);
				super.paint(arg0);
			}
		};
		clr = new JPanel()
		{
			@Override
			public void paint(Graphics arg0)
			{
				arg0.drawImage(imclr, 0, 0, 900, 650, null);
				super.paint(arg0);
			}
		};
		per = new JPanel()
		{
			@Override
			public void paint(Graphics arg0)
			{
				arg0.drawImage(imper, 0, 0, 900, 650, null);
				super.paint(arg0);
			}
		};
		wt = new JPanel()
		{
			@Override
			public void paint(Graphics arg0)
			{
				arg0.drawImage(imdim, 0, 0, 900, 650, null);
				super.paint(arg0);
			}
		};
		wheel = new JPanel()
		{
			@Override
			public void paint(Graphics arg0)
			{
				arg0.drawImage(imwh, 0, 0, 900, 650, null);
				super.paint(arg0);
			}
		};
		
		engineDesign();
		chsDesign();
		elecDesign();
		clrDesign();
		perDesign();
		wtDesign();
		wheeldesign();
		
		tb = new JTabbedPane();
		tb.setBounds(150, 80, 870, 580);
		//tb.setOpaque(true);
	//	tb.setVisible(false);
		tb.setBackground(Color.darkGray);
		tb.setFont(Style.tabfont);
		tb.setForeground(Color.white);
		center.add(tb);
		tb.addTab("Engine & Transmission", engine);
		tb.addTab("Dimension & Weight", wt);
		tb.addTab("Electricals", elec);
		tb.addTab("Chassis & Suspension", chs);
		tb.addTab("Wheel & Braking", wheel);
	//	tb.addTab("Braking", brk);
		tb.addTab("Performance", per);
		tb.addTab("Colors", clr);	
	}

	
	JLabel elabel[] = new JLabel[19];
	String etxt[] = {"Engine Type","Displacement(cc)","Cylinders","Net Power","Torque","Bore(mm)","Stroke(mm)","Valves per Cylinder","Fuel Delivery System","Ignition","Spark Plugs","Cooling System","Air Filter","Starting System","Gearbox Type","Number of Gears","Clutch","Transmission","Gear Shift pattern"};
	JTextField evalue[] = new JTextField[19];
	JButton btneng;
	
	private void engineDesign()
	{
		engine.setLayout(null);
	//	engine.setBounds(20, 115, 800, 900);
	//	engine.setOpaque(false);
		engine.setBackground(Style.panelColor);
		Border border =BorderFactory.createLineBorder(Color.BLACK,4);
		Border border1 = BorderFactory.createTitledBorder(border, "Engine & Transmission",2,2,new Font("Footlight MT Light",Font.BOLD,26));
		engine.setBorder(border1);
		
		for (int i = 0; i < 10; i++)
		{
			elabel[i] = new JLabel(etxt[i]);
			elabel[i].setBounds(25, 40+45*i, 190, 30);
			elabel[i].setFont(Style.lblfont);
			engine.add(elabel[i]);
		}
		
		for (int i = 10; i < elabel.length; i++)
		{
			elabel[i] = new JLabel(etxt[i]);
			elabel[i].setBounds(440, 40+45*(i-10), 190, 30);
			elabel[i].setFont(Style.lblfont);
			engine.add(elabel[i]);
		}

		
		for (int i = 0; i < 10; i++)
		{
			evalue[i] = new JTextField();
			evalue[i].setBorder(Style.border1);
			evalue[i].setOpaque(false);
			evalue[i].setForeground(Color.black);
			evalue[i].setBounds(230, 40+45*i, 130, 35);
			evalue[i].setFont(Style.txtfont);
			engine.add(evalue[i]);
		}
		
		for (int i = 10; i < evalue.length; i++)
		{
			evalue[i] = new JTextField();
			evalue[i].setBorder(Style.border1);
			evalue[i].setOpaque(false);
			evalue[i].setForeground(Color.black);
			evalue[i].setBounds(610, 40+45*(i-10), 130, 35);
			evalue[i].setFont(Style.txtfont);
			engine.add(evalue[i]);
		}
		evalue[17].setSize(180, 30);	
		
		btneng = new JButton("Save & Continue");
		btneng.setBounds(335, 500, 200, 40);
		btneng.addActionListener(this);
		engine.add(btneng);
	}

	
	JLabel chlabel[] = new JLabel[3];
	String chtxt[] = {"Chassis","Front Suspension","Rear Suspension"};
	JTextField chvalue[] = new JTextField[3];
	JButton btnchs;

	private void chsDesign()
	{
		chs.setLayout(null);
	//	chs.setBounds(375, 115, 350, 300);
		chs.setOpaque(false);
		chs.setBackground(Style.panelColor);
		Border border =BorderFactory.createLineBorder(Color.BLACK,4);
		Border border1 = BorderFactory.createTitledBorder(border, "Chassis & Suspension",2,2,new Font("Footlight MT Light",Font.BOLD,26));
		chs.setBorder(border1);
		
		for (int i = 0; i < chlabel.length; i++)
		{
			chlabel[i] = new JLabel(chtxt[i]);
			chlabel[i].setBounds(30, 40+50*i, 160, 30);
			chlabel[i].setFont(Style.lblfont);
			chs.add(chlabel[i]);
		}
		
		for (int i = 0; i < chvalue.length; i++)
		{
			chvalue[i] = new JTextField();
			chvalue[i].setBorder(Style.border1);
			chvalue[i].setBounds(190, 40+50*i, 300, 30);
			chvalue[i].setOpaque(false);
			chvalue[i].setForeground(Color.black);
			chvalue[i].setFont(Style.txtfont);
			chs.add(chvalue[i]);
		}
		
		btnchs = new JButton("Save & Continue");
		btnchs.setBounds(335, 500, 200, 40);
		btnchs.addActionListener(this);
		chs.add(btnchs);
		
	}


	JLabel ellabel[] = new JLabel[7];
	String eltxt[] = {"Electric System","Battery","Headlight Type","Headlamp","Brake/Tail Light","Turn Signal","Pass Light"};
	JTextField elvalue[] = new JTextField[7];
	JButton btnelec;
	
	private void elecDesign()
	{
		elec.setLayout(null);
	//	elec.setBounds(20, 420, 350, 200);
		elec.setOpaque(false);
		Border border =BorderFactory.createLineBorder(Color.BLACK,4);
		Border border1 = BorderFactory.createTitledBorder(border, "Electricals",2,2,new Font("Footlight MT Light",Font.BOLD,26));
		elec.setBorder(border1);
		elec.setBackground(Style.panelColor);
		
		for (int i = 0; i < ellabel.length; i++)
		{
			ellabel[i] = new JLabel(eltxt[i]);
			ellabel[i].setBounds(30, 40+50*i, 180, 30);
			ellabel[i].setFont(Style.lblfont);
			elec.add(ellabel[i]);
		}
		
		for (int i = 0; i < elvalue.length; i++)
		{
			elvalue[i] = new JTextField();
			elvalue[i].setBorder(Style.border1);
			elvalue[i].setBounds(220, 40+50*i, 150, 30);
			elvalue[i].setOpaque(false);
			elvalue[i].setForeground(Color.black);
			elvalue[i].setFont(Style.txtfont);
			elec.add(elvalue[i]);
		}
		elvalue[2].setSize(200, 30);
		elvalue[3].setSize(200, 30);
		elvalue[4].setSize(200, 30);
		
		btnelec = new JButton("Save & Continue");
		btnelec.setBounds(335, 500, 200, 40);
		btnelec.addActionListener(this);
		elec.add(btnelec);
		
	}


	JLabel clabel[] = new JLabel[2];
	String ctxt[] = {"Color","Image"};
	JButton cbtn[] = new JButton[2];
	JTextField ctxtfld[] = new JTextField[2];
	JButton br = new JButton("Browse");
	JButton up = new JButton("Upload");
	JList list[] = new JList[2];
	JButton btnclr,clradd;
	
	private void clrDesign()
	{
		clr.setLayout(null);
		clr.setBounds(750, 115, 550, 450);
		clr.setOpaque(false);
		Border border =BorderFactory.createLineBorder(Color.BLACK,4);
		Border border1 = BorderFactory.createTitledBorder(border, "Color",2,2,new Font("Footlight MT Light",Font.BOLD,26));
		clr.setBorder(border1);
		clr.setBackground(Style.panelColor);
		
		for (int i = 0; i < clabel.length; i++)
		{
			clabel[i] = new JLabel(ctxt[i]);
			clabel[i].setBounds(560+100*i, 10, 60, 30);
			clabel[i].setFont(Style.lblfont);
			clr.add(clabel[i]);
			
			ctxtfld[i] = new JTextField();
			ctxtfld[i].setBorder(Style.border1);
			ctxtfld[i].setBounds(530+120*i, 40, 100, 30);
			ctxtfld[i].setFont(Style.txtfont);
			clr.add(ctxtfld[i]);
			
			list[i] = new JList();
			list[i].setBounds(530+120*i, 80, 100, 200);
			list[i].setBorder(Style.border1);
			list[i].addListSelectionListener(this);
			clr.add(list[i]);
		}
		
		ctxtfld[0].addKeyListener(this);
				
		list[1].setSize(200, 200);
		ctxtfld[1].setSize(200, 30);
		ctxtfld[1].setEditable(false);
		
		img.setBounds(10, 40, 510, 400);
		img.setBorder(Style.border1);
		clr.add(img);
		
		br.setBounds(140, 455, 100, 30);
		clr.add(br);
		br.addActionListener(this);
		
		up.setBounds(260, 455, 100, 30);
		clr.add(up);
		up.setEnabled(false);
		up.addActionListener(this);
		
		btnclr = new JButton("Save");
		btnclr.setBounds(335, 500, 200, 40);
		btnclr.addActionListener(this);
		clr.add(btnclr);
		
		clradd = new JButton("Add more");
		clradd.setBounds(585, 300, 100, 30);
		clradd.addActionListener(this);
		clradd.setEnabled(false);
		clr.add(clradd);
		
		
		
		
	}


	JLabel perlabel[] = new JLabel[6];
	String pertxt[] = {"0 to 60 kmph (Seconds)","0 to 80 kmph (Seconds)","0 to 40 m (Seconds)","Top Speed (Kmph)","60 to 0 Kmph (Seconds, metres)","80 to 0 kmph (Seconds, metres)"};
	JTextField pervalue[] = new JTextField[6];
	JButton btnper;
	
	private void perDesign()
	{
			per.setLayout(null);
		//	per.setBounds(20, 420, 350, 200);
			per.setOpaque(false);
			Border border =BorderFactory.createLineBorder(Color.BLACK,4);
			Border border1 = BorderFactory.createTitledBorder(border, "Performance",2,2,new Font("Footlight MT Light",Font.BOLD,26));
			per.setBorder(border1);
			per.setBackground(Style.panelColor);
			
			for (int i = 0; i < perlabel.length; i++)
			{
				perlabel[i] = new JLabel(pertxt[i]);
				perlabel[i].setBounds(30, 40+50*i, 280, 30);
				perlabel[i].setFont(Style.lblfont);
				per.add(perlabel[i]);
			}
			
			for (int i = 0; i < pervalue.length; i++)
			{
				pervalue[i] = new JTextField();
				pervalue[i].setBorder(Style.border1);
				pervalue[i].setBounds(320, 40+50*i, 100, 30);
				pervalue[i].setOpaque(false);
				pervalue[i].setForeground(Color.black);
				pervalue[i].setFont(Style.txtfont);
				per.add(pervalue[i]);
			}
			btnper = new JButton("Save & Continue");
			btnper.setBounds(335, 500, 200, 40);
			btnper.addActionListener(this);
			per.add(btnper);
	}


	JLabel dlabel[] = new JLabel[12];
	String dtxt[] = {"Kerb Weight(Kg)","Length(mm)","Width(mm)","Height(mm)","Wheelbase(mm)","Ground Clearance(mm)","Seat Height","Fuel Efficiency & Range","Fuel Tank Capacity(Litre)","Reserve Fuel Capacity (Litres)","Fuel Efficiency Overall (Kmpl)","Fuel Efficiency Range (Km)"};
	JTextField dvalue[] = new JTextField[12];
	JButton btnwt;
	
	private void wtDesign()
	{
		wt.setLayout(null);
	//	wt.setBounds(380, 420, 350, 235);
		wt.setOpaque(false);
		Border border =BorderFactory.createLineBorder(Color.BLACK,4);
		Border border1 = BorderFactory.createTitledBorder(border, "Weight/Measurements",2,2,new Font("Footlight MT Light",Font.BOLD,26));
		wt.setBorder(border1);
		wt.setBackground(Style.panelColor);
		
		for (int i = 0; i < 6; i++)
		{
			dlabel[i] = new JLabel(dtxt[i]);
			dlabel[i].setBounds(30, 60+50*i, 225, 30);
			dlabel[i].setFont(Style.lblfont);
			wt.add(dlabel[i]);
		}
		
		for (int i = 0; i < 6; i++)
		{
			dvalue[i] = new JTextField();
			dvalue[i].setBorder(Style.border1);
			dvalue[i].setOpaque(false);
			dvalue[i].setForeground(Color.black);
			dvalue[i].setFont(Style.txtfont);
			dvalue[i].setBounds(245, 60+50*i, 80, 30);
			wt.add(dvalue[i]);
		}
		
		for (int i = 6; i < dlabel.length; i++)
		{
			dlabel[i] = new JLabel(dtxt[i]);
			dlabel[i].setBounds(400, 60+50*(i-6), 265, 25);
			dlabel[i].setFont(Style.lblfont);
			wt.add(dlabel[i]);
		}
		
		for (int i = 6; i < dvalue.length; i++)
		{
			dvalue[i] = new JTextField();
			dvalue[i].setBorder(Style.border1);
			dvalue[i].setBounds(690, 60+50*(i-6), 80, 30);
			dvalue[i].setOpaque(false);
			dvalue[i].setForeground(Color.black);
			dvalue[i].setFont(Style.txtfont);
			wt.add(dvalue[i]);
		}
		
		btnwt = new JButton("Save & Continue");
		btnwt.setBounds(335, 500, 200, 40);
		btnwt.addActionListener(this);
		wt.add(btnwt);
	}


	JLabel whlabel[] = new JLabel[6];
	String whtxt[] = {"Wheel Size(inch)","Front Tyre","Rear Tyre","Tubeless Tyres","Radial Tyres","Alloy Wheels"};
	JTextField whvalue[] = new JTextField[6];
	JLabel img = new JLabel();
	JButton btnwhl;
	
	private void wheeldesign()
	{
			wheel.setLayout(null);
		//	wheel.setBounds(380, 420, 350, 235);
			wheel.setOpaque(false);
			Border border =BorderFactory.createLineBorder(Color.BLACK,4);
			Border border1 = BorderFactory.createTitledBorder(border, "Wheels & Tyres",2,2,new Font("Footlight MT Light",Font.BOLD,26));
			wheel.setBorder(border1);
			wheel.setBackground(Style.panelColor);
			
			for (int i = 0; i < whlabel.length; i++)
			{
				whlabel[i] = new JLabel(whtxt[i]);
				whlabel[i].setBounds(40, 60+50*(i), 265, 25);
				whlabel[i].setFont(Style.lblfont);
				wheel.add(whlabel[i]);
			}
			
			for (int i = 0; i < whvalue.length; i++)
			{
				whvalue[i] = new JTextField();
				whvalue[i].setBorder(Style.border1);
				whvalue[i].setBounds(200, 60+50*(i), 80, 30);
				whvalue[i].setOpaque(false);
				whvalue[i].setForeground(Color.black);
				whvalue[i].setFont(Style.txtfont);
				wheel.add(whvalue[i]);
			}
			
			whvalue[1].setSize(150, 30);
			whvalue[2].setSize(150, 30);
			
			for (int i = 0; i < brklabel.length; i++)
			{
				brklabel[i] = new JLabel(brktxt[i]);
				brklabel[i].setBounds(420, 60+50*(i), 270, 25);
				brklabel[i].setFont(Style.lblfont);
				wheel.add(brklabel[i]);
			}
			
			for (int i = 0; i < brkvalue.length; i++)
			{
				brkvalue[i] = new JTextField();
				brkvalue[i].setBorder(Style.border1);
				brkvalue[i].setBounds(670, 60+50*(i), 150, 30);
				brkvalue[i].setOpaque(false);
				brkvalue[i].setForeground(Color.black);
				brkvalue[i].setFont(Style.txtfont);
				wheel.add(brkvalue[i]);
			}
			brkvalue[2].setSize(80, 30);
			brkvalue[4].setSize(80, 30);
			
			btnwhl = new JButton("Save & Continue");
			btnwhl.setBounds(335, 500, 200, 40);
			btnwhl.addActionListener(this);
			wheel.add(btnwhl);
	}

	JLabel brklabel[] = new JLabel[6];
	String brktxt[] = {"Brake Type","Front Disc","Front Disc/Drum Size (mm)","Rear Disc","Rear Disc/Drum Size (mm)","Calliper Type"};
	JTextField brkvalue[] = new JTextField[6];
	
	private void brkdesign()
	{
			brk.setLayout(null);
		//	brk.setBounds(380, 420, 350, 235);
		//	brk.setOpaque(false);
			Border border =BorderFactory.createLineBorder(Color.gray,6);
			Border border1 = BorderFactory.createTitledBorder(border, "Braking",2,2,new Font("Footlight MT Light",Font.BOLD,26));
			brk.setBorder(border1);
			
			for (int i = 0; i < brklabel.length; i++)
			{
				brklabel[i] = new JLabel(brktxt[i]);
				brklabel[i].setBounds(40, 60+50*(i), 270, 25);
				brklabel[i].setFont(Style.lblfont);
				brk.add(brklabel[i]);
			}
			
			for (int i = 0; i < brkvalue.length; i++)
			{
				brkvalue[i] = new JTextField();
				brkvalue[i].setBounds(290, 60+50*(i), 80, 30);
				brk.add(brkvalue[i]);
			}
		
	}


	JLabel vid,vname,price;
	JTextField tvname,tvid,tprice;
//	JComboBox cvid;
	private void eastpanelDesign()
	{
		eastpanel.setLayout(null);
		eastpanel.setOpaque(true);
		eastpanel.setBackground(Color.darkGray);
		vid = new JLabel("Vehicle ID");
		vname = new JLabel("Vehicle Model");
		price = new JLabel("Price(Rs.)");
		
		tvname = new JTextField();
		tvid = new JTextField();
		tprice = new JTextField();
	//	cvid = new JComboBox();
		
		vid.setBounds(30, 50, 150, 30);
		vid.setFont(Style.lblfont);
		vid.setForeground(Color.white);
		eastpanel.add(vid);
		
		tvid.setBounds(10, 80, 130, 30);
		tvid.setFont(Style.txtfont);
		tvid.setBorder(Style.border1);
		tvid.addKeyListener(this);
		tvid.addFocusListener(this);
		eastpanel.add(tvid);
		
		vname.setBounds(15, 130, 150, 30);
		vname.setFont(Style.lblfont);
		vname.setForeground(Color.white);
		eastpanel.add(vname);
		
		tvname.setBounds(10, 160, 130, 30);
		tvname.setFont(Style.txtfont);
		tvname.setBorder(Style.border1);
		eastpanel.add(tvname);
		
		price.setBounds(35, 200, 150, 30);
		price.setFont(Style.lblfont);
		price.setForeground(Color.white);
		eastpanel.add(price);
		
		tprice.setBounds(15, 230, 120, 30);
		tprice.setBorder(Style.border1);
		tprice.setFont(Style.txtfont);
		tprice.addKeyListener(this);
		eastpanel.add(tprice);
	}

	JLabel head;
	private void hpanelDesign()
	{
		hpanel.setLayout(null);
		hpanel.setBackground(Color.DARK_GRAY);
		hpanel.setOpaque(true);
		head = new JLabel("Vehicle Details");
		head.setFont(Style.fhdfont);
		head.setForeground(Color.white);
		head.setBounds(340, 20, 400, 40);
		hpanel.add(head);
		
	}

	public static void main(String[] args)
	{
		new VehicleDetail();

	}
	
	
	Statement stmt;
	ResultSet rs;
	Vector<String> vcolor = new Vector<String>();
	Vector<String> vimage = new Vector<String>();
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource() == br)
		{
		//	clradd.setEnabled(true);
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(f);
			File fl = fc.getSelectedFile();
			img.setIcon(new ImageIcon(fl.getPath()));
			ctxtfld[0].grabFocus();			
		}
		
		if(arg0.getSource() == up)
		{
			if(tvid.getText().length()>0)
			{
				Icon im = img.getIcon();
				String filename = im.toString();
				FileInputStream fi;
				FileOutputStream fo;
				try
				{
					 fi = new FileInputStream(filename);
					 fo = new FileOutputStream("image/"+tvid.getText()+"-"+ctxtfld[0].getText()+".jpg");
					 int ch = fi.read();
					 while(ch!=-1)
					 {
						 fo.write(ch);
						 ch = fi.read();
					 }
					 ctxtfld[1].setText(tvid.getText()+"-"+ctxtfld[0].getText()+".jpg");
					 clradd.setEnabled(true);
					 JOptionPane.showMessageDialog(null, "Pic uploaded");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Enter Vehicle ID");
				tvid.grabFocus();
			}	
		}
		
		if(arg0.getSource() == clradd)
		{
			vcolor.add(ctxtfld[0].getText());
			list[0].setListData(vcolor);
			ctxtfld[0].setText("");
			
			vimage.add(ctxtfld[1].getText());
			list[1].setListData(vimage);
			ctxtfld[1].setText("");
			
		//	img.setIcon(new ImageIcon("image/blanckimage.jpg"));
			img.setIcon(null);
			clradd.setEnabled(false);
			up.setEnabled(false);
			
			br.grabFocus();
		}
		
		if(arg0.getSource() == button[2])
			f.dispose();
		
		if(arg0.getSource() == button[1])
		{
			if(tvid.getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String sql="delete from v_engine where v_id='"+tvid.getText()+"'";
				String sql1="delete from v_dimension where v_id='"+tvid.getText()+"'";
				String sql2="delete from v_chassis where v_id='"+tvid.getText()+"'";
				String sql3="delete from v_electrical where v_id='"+tvid.getText()+"'";
				String sql4="delete from v_color where v_id='"+tvid.getText()+"'";
				String sql5="delete from v_wheel where v_id='"+tvid.getText()+"'";
				String sql6="delete from v_performance where v_id='"+tvid.getText()+"'";
				try
				{
					int x = JOptionPane.showConfirmDialog(f,"Are you sure that you want to delete this record?");
					if(x == 0)
					{
						stmt = con.createStatement();
						stmt.executeUpdate(sql);
						stmt.executeUpdate(sql1);
						stmt.executeUpdate(sql2);
						stmt.executeUpdate(sql3);
						stmt.executeUpdate(sql4);
						stmt.executeUpdate(sql5);
						stmt.executeUpdate(sql6);
						JOptionPane.showMessageDialog(f,"Vehicle Details Deleted");
//						tvid.setEditable(false); 
//						tvname.setEditable(false); 
//						tprice.setEditable(false);
//						
//						for (int i = 0; i < evalue.length; i++)
//							evalue[i].setEditable(false);
//						
						stmt.close();
						con.close();
						tvname.setText("");
						tvid.setText("");
						tprice.setText("");
						tb.setSelectedIndex(0);
				
						tvid.setEditable(true);
						tvname.setEditable(true);
						tprice.setEditable(true);			
						
						for (int i = 0; i < evalue.length; i++)
							evalue[i].setText("");
						
						for (int i = 0; i < dvalue.length; i++)
							dvalue[i].setText("");
						
						for (int i = 0; i < elvalue.length; i++)
							elvalue[i].setText("");
						
						for (int i = 0; i < whvalue.length; i++)
							whvalue[i].setText("");
						
						for (int i = 0; i < brkvalue.length; i++)
							brkvalue[i].setText("");
						
						for (int i = 0; i < pervalue.length; i++)
							pervalue[i].setText("");
						
						for (int i = 0; i < ctxtfld.length; i++)
						{				
							ctxtfld[i].setText("");
							
							list[i].removeAll();	
						}
						
						for (int i = 0; i < chvalue.length; i++)
							chvalue[i].setText("");
						
						tvid.grabFocus();
						
						tb.setSelectedIndex(0);
					}					
				}
			
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        	ex.getStackTrace();
		        }
			}
				else
					JOptionPane.showMessageDialog(f, "Enter a Valid Vehicle ID");
		}
		
		if(arg0.getSource() == button[0])
		{
			tvname.setText("");
			tvid.setText("");
			tprice.setText("");
			tb.setSelectedIndex(0);
			br.setEnabled(true);
	
			tvid.setEditable(true);
			tvname.setEditable(true);
			tprice.setEditable(true);
			
			btnchs.setEnabled(true);
			btnclr.setEnabled(true);
			btnelec.setEnabled(true);
			btneng.setEnabled(true);
			btnper.setEnabled(true);
			btnwhl.setEnabled(true);
			btnwt.setEnabled(true);
			list[0].removeAll();
			list[1].removeAll();
			
			
			for (int i = 0; i < evalue.length; i++)
				evalue[i].setText("");
			
			for (int i = 0; i < dvalue.length; i++)
				dvalue[i].setText("");
			
			for (int i = 0; i < elvalue.length; i++)
				elvalue[i].setText("");
			
			for (int i = 0; i < whvalue.length; i++)
				whvalue[i].setText("");
			
			for (int i = 0; i < brkvalue.length; i++)
				brkvalue[i].setText("");
			
			for (int i = 0; i < pervalue.length; i++)
				pervalue[i].setText("");
			
			for (int i = 0; i < ctxtfld.length; i++)
			{				
				ctxtfld[i].setText("");
				
				list[i].removeAll();	
			}
			
			for (int i = 0; i < chvalue.length; i++)
				chvalue[i].setText("");
			
			tvid.grabFocus();
		}
		
		if(arg0.getSource() == btneng)
		{
			if(tvid.getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String sql="insert into v_engine values('"+tvid.getText()+"','"+tvname.getText()+"','"+tprice.getText()+"','"+evalue[0].getText()+"','"+evalue[1].getText()+"','"+evalue[2].getText()+"','"+evalue[3].getText()+"','"+evalue[4].getText()+"','"+evalue[5].getText()+"','"+evalue[6].getText()+"','"+evalue[7].getText()+"','"+evalue[8].getText()+"','"+evalue[9].getText()+"','"+evalue[10].getText()+"','"+evalue[11].getText()+"','"+evalue[12].getText()+"','"+evalue[13].getText()+"','"+evalue[14].getText()+"','"+evalue[15].getText()+"','"+evalue[16].getText()+"','"+evalue[17].getText()+"','"+evalue[18].getText()+"')";
				try
				{
					stmt = con.createStatement();
					stmt.executeUpdate(sql);
					tvid.setEditable(false); 
					tvname.setEditable(false); 
					tprice.setEditable(false);
					JOptionPane.showMessageDialog(f,"Details Saved");
					for (int i = 0; i < evalue.length; i++)
						evalue[i].setEditable(false);
					btneng.setEnabled(false);
					
					stmt.close();
					con.close();
					
					tb.setSelectedIndex(1);
					dvalue[0].grabFocus();
					
				}
			
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        	ex.getStackTrace();
		        }
			}
				else
					JOptionPane.showMessageDialog(f, "Enter a Record to Save.");
			
		}
		
		if(arg0.getSource() == btnwt)
		{
			if(tvid.getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String sql="insert into v_dimension values('"+tvid.getText()+"','"+dvalue[0].getText()+"','"+dvalue[1].getText()+"','"+dvalue[2].getText()+"','"+dvalue[3].getText()+"','"+dvalue[4].getText()+"','"+dvalue[5].getText()+"','"+dvalue[6].getText()+"','"+dvalue[7].getText()+"','"+dvalue[8].getText()+"','"+dvalue[9].getText()+"','"+dvalue[10].getText()+"','"+dvalue[11].getText()+"')";
				try
				{
					stmt = con.createStatement();
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(f,"Details Saved");
					for (int i = 0; i < dvalue.length; i++)
						dvalue[i].setEditable(false);
					btnwt.setEnabled(false);
					
					stmt.close();
					con.close();
					
					tb.setSelectedIndex(2);
					elvalue[0].grabFocus();
				}
			
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        	ex.getStackTrace();
		        }
			}
				else
					JOptionPane.showMessageDialog(f, "Enter a Record to Save.");
			
		}
		
		if(arg0.getSource() == btnelec)
		{
			if(tvid.getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String sql="insert into v_electrical values('"+tvid.getText()+"','"+elvalue[0].getText()+"','"+elvalue[1].getText()+"','"+elvalue[2].getText()+"','"+elvalue[3].getText()+"','"+elvalue[4].getText()+"','"+elvalue[5].getText()+"','"+elvalue[6].getText()+"')";
				try
				{
					stmt = con.createStatement();
					stmt.executeUpdate(sql);

					JOptionPane.showMessageDialog(f,"Details Saved");
					for (int i = 0; i < elvalue.length; i++)
						elvalue[i].setEditable(false);
					btnelec.setEnabled(false);
					
					stmt.close();
					con.close();
					
					tb.setSelectedIndex(3);
					chvalue[0].grabFocus();
					
				}
			
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        	ex.getStackTrace();
		        }
			}
				else
					JOptionPane.showMessageDialog(f, "Enter a Record to Save.");
		}
		
		if(arg0.getSource() == btnchs)
		{
			if(tvid.getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String sql="insert into v_chassis values('"+tvid.getText()+"','"+chvalue[0].getText()+"','"+chvalue[1].getText()+"','"+chvalue[2].getText()+"')";
				try
				{
					stmt = con.createStatement();
					stmt.executeUpdate(sql);

					JOptionPane.showMessageDialog(f,"Details Saved");
					for (int i = 0; i < chvalue.length; i++)
						chvalue[i].setEditable(false);
					btnchs.setEnabled(false);
					
					stmt.close();
					con.close();
					
					tb.setSelectedIndex(4);
					whvalue[0].grabFocus();
					
				}
			
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        	ex.getStackTrace();
		        }
			}
				else
					JOptionPane.showMessageDialog(f, "Enter a Record to Save.");
		}
		
		if(arg0.getSource() == btnwhl)
		{
			if(tvid.getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String sql="insert into v_wheel values('"+tvid.getText()+"','"+whvalue[0].getText()+"','"+whvalue[1].getText()+"','"+whvalue[2].getText()+"','"+whvalue[3].getText()+"','"+whvalue[4].getText()+"','"+whvalue[5].getText()+"','"+brkvalue[0].getText()+"','"+brkvalue[1].getText()+"','"+brkvalue[2].getText()+"','"+brkvalue[3].getText()+"','"+brkvalue[4].getText()+"','"+brkvalue[5].getText()+"')";
				try
				{
					stmt = con.createStatement();
					stmt.executeUpdate(sql);

					JOptionPane.showMessageDialog(f,"Details Saved");
					for (int i = 0; i < whvalue.length; i++)
						whvalue[i].setEditable(false);
					for (int i = 0; i < brkvalue.length; i++)
						brkvalue[i].setEditable(false);
					
					btnwhl.setEnabled(false);
					stmt.close();
					con.close();
					
					tb.setSelectedIndex(5);
					pervalue[0].grabFocus();
				}
			
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        	ex.getStackTrace();
		        }
			}
				else
					JOptionPane.showMessageDialog(f, "Enter a Record to Save.");
		}
		
		if(arg0.getSource() == btnper)
		{
			if(tvid.getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String sql="insert into v_performance values('"+tvid.getText()+"','"+pervalue[0].getText()+"','"+pervalue[1].getText()+"','"+pervalue[2].getText()+"','"+pervalue[3].getText()+"','"+pervalue[4].getText()+"','"+pervalue[5].getText()+"')";
				try
				{
					stmt = con.createStatement();
					stmt.executeUpdate(sql);

					JOptionPane.showMessageDialog(f,"Details Saved");
					for (int i = 0; i < pervalue.length; i++)
						pervalue[i].setEditable(false);
					btnper.setEnabled(false);
					
					stmt.close();
					con.close();
					
					tb.setSelectedIndex(6);
					br.grabFocus();	
				}
			
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        	ex.getStackTrace();
		        }
			}
				else
					JOptionPane.showMessageDialog(f, "Enter a Record to Save.");
		}
		
		if(arg0.getSource() == btnclr)
		{
			String scolor="";
			String simage="";
			for (String color : vcolor)
			{
				if(scolor.length()>1)scolor = scolor +";"+ color;
				else scolor = color;
			}
			for (String image : vimage)
			{
				if(simage.length()>1)simage = simage +";"+ image;
				else simage = image;
			}
			if(tvid.getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String sql="insert into v_color values('"+tvid.getText()+"','"+scolor+"','"+simage+"')";
				try
				{
					stmt = con.createStatement();
					stmt.executeUpdate(sql);

					JOptionPane.showMessageDialog(f,"Details Saved");
//					for (int i = 0; i < whvalue.length; i++)
//						whvalue[i].setEditable(false);
//					for (int i = 0; i < brkvalue.length; i++)
//						brkvalue[i].setEditable(false);
					
					stmt.close();
					con.close();
					
					btnclr.setEnabled(false);
				}
			
				catch(Exception ex)
				{
		        	JOptionPane.showMessageDialog(f, ex.toString());
		        	ex.getStackTrace();
		        }
			}
				else
					JOptionPane.showMessageDialog(f, "Enter a Record to Save.");
		}
			
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		
	}
	JList l =  new JList();
	
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		//l = new JList();
		Vector v = new Vector();
		l.setVisible(true);
		l.addFocusListener(this);
		if(arg0.getSource()==tvid)
		{
			if(tvid.getText().length()>1)
			{
				Connection con = DataBaseUtility.getConnection();
				try
				{
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select V_ID from V_engine where V_ID like '%"+tvid.getText().toUpperCase()+"%'");
					while(rs.next())
					{
						
						v.add(rs.getObject(1));
					}
					con.close();
					l.setListData(v);
					if(v.size()>0)
					{
						l.setBounds(10, 111, 130, v.size()*20);
						l.setBackground(Color.white);
						l.setOpaque(true);
						eastpanel.add(l);
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
		
		if(arg0.getSource() == ctxtfld[0])
		{
			if(ctxtfld[0].getText().length()>0)
				up.setEnabled(true);
			else
				up.setEnabled(false);
		}
		
		if(arg0.getSource() == tprice)
		{
			if(tprice.getText().length()>0)
			{
				try
				{
					long price = Long.parseLong(tprice.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!! Only numbers 0-9 are allowed.");

					tprice.setText("");;			
					tprice.grabFocus();		
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
	public void focusGained(FocusEvent arg0)
	{
		// TODO Auto-generated method stub
		if(arg0.getSource()==tvid)
		{
			if(tvid.getText().length()==0)flag=true;
		}
	}

	@Override
	public void focusLost(FocusEvent arg0)
	{
		if(arg0.getSource()==tvid)
		{
			//System.out.println(l);
			if(l!=null)
			{
				l.setVisible(false);
				if(!l.isSelectionEmpty())
				{
					tvid.setText(l.getSelectedValue().toString());
					button[1].setEnabled(true);
					br.setEnabled(false);
				}
			}
			
			if(flag)browseData();	
		}	
	}
	
	
	boolean flag =true;
	private void browseData()
	{
		flag = false;
		Connection con = DataBaseUtility.getConnection();
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from v_engine where v_id='"+tvid.getText()+"'");
			while(rs.next())
			{
				tvname.setText(rs.getString("vname"));
				tprice.setText(rs.getString("price"));
				String s = "";
				JTextField t;
				for (int i = 0,j=4; i < 38 ;i++ )
				{
					Component c = engine.getComponent(i);
					try
					{
						 t = (JTextField)c;
						t.setText(rs.getObject(j).toString());
						j++;
					} catch (NullPointerException e)
					{
						j++;
					}
					catch (ClassCastException e)
					{
					}
				}
				//stmt.close();
			}
			rs = stmt.executeQuery("select * from v_dimension where v_id='"+tvid.getText()+"'");
			while(rs.next())
			{
				JTextField t;
				for (int i = 0,j=2; i < 24 ;i++ )
				{
					Component c = wt.getComponent(i);
					try
					{
						t = (JTextField)c;
						t.setText(rs.getObject(j).toString());
						j++;
					} catch (NullPointerException e)
					{
						j++;
						//e.printStackTrace();
					}
					catch (ClassCastException e) 
					{
						// TODO: handle exception
					}
				}
			}
				rs = stmt.executeQuery("select * from v_electrical where v_id='"+tvid.getText()+"'");
				while(rs.next())
				{
					JTextField t;
					for (int i = 0,j=2; i < 14 ;i++ )
					{
						Component c = elec.getComponent(i);
						try
						{
							t = (JTextField)c;
							t.setText(rs.getObject(j).toString());
							j++;
						} catch (NullPointerException e)
						{
							j++;
							//e.printStackTrace();
						}
						catch (ClassCastException e) 
						{
							// TODO: handle exception
						}
					}
			}
				rs = stmt.executeQuery("select * from v_chassis where v_id='"+tvid.getText()+"'");
				while(rs.next())
				{
					JTextField t;
					for (int i = 0,j=2; i < 6 ;i++ )
					{
						Component c = chs.getComponent(i);
						try
						{
							t = (JTextField)c;
							t.setText(rs.getObject(j).toString());
							j++;
						} catch (NullPointerException e)
						{
							j++;
							//e.printStackTrace();
						}
						catch (ClassCastException e) 
						{
							// TODO: handle exception
						}
					}
				}
				rs = stmt.executeQuery("select * from v_wheel where v_id='"+tvid.getText()+"'");
				while(rs.next())
				{
					JTextField t;
					for (int i = 0,j=2; i < 24 ;i++ )
					{
						Component c = wheel.getComponent(i);
						try
						{
							t = (JTextField)c;
							t.setText(rs.getObject(j).toString());
							j++;
						} catch (NullPointerException e)
						{
							j++;
							//e.printStackTrace();
						}
						catch (ClassCastException e) 
						{
							// TODO: handle exception
						}
					}
				}
				rs = stmt.executeQuery("select * from v_performance where v_id='"+tvid.getText()+"'");
				while(rs.next())
				{
					JTextField t;
					for (int i = 0,j=2; i < 12 ;i++ )
					{
						Component c = per.getComponent(i);
						try
						{
							t = (JTextField)c;
							t.setText(rs.getObject(j).toString());
							j++;
						} catch (NullPointerException e)
						{
							j++;
							//e.printStackTrace();
						}
						catch (ClassCastException e) 
						{
							// TODO: handle exception
						}
					}
				}
				rs = stmt.executeQuery("select * from v_color where v_id='"+tvid.getText()+"'");
				Vector<String> vcolor = new Vector<String>();
				Vector<String> vimage = new Vector<String>();
				while(rs.next())
				{
					String color = rs.getString(2);
					for (String string : color.split(";"))
					{
						vcolor.add(string);
					}
					String image = rs.getString(3);
					for (String string : image.split(";"))
					{
						vimage.add(string);
					}
					list[0].setListData(vcolor);
					list[1].setListData(vimage);
				}
		} catch (Exception e)
		{
			// TODO: handle exception
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0)
	{
		if(arg0.getSource() == list[1])
		{
			img.setIcon(new ImageIcon("image/"+list[1].getSelectedValue().toString()));
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
