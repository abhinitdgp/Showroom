import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
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
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Book implements ActionListener,KeyListener,AdjustmentListener,FocusListener
{
	JFrame f;
	public Book()
	{
		f = new JFrame("Automobile Showroom Management System");
		frameDesign();
		f.setVisible(true);
		f.setSize(1024, 725);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
	//	f.setBackground(Style.panelColor);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	JPanel sp,center,support,cp;
	JButton button[] = new JButton[6];
	String btext[] = {"Add","Save","Update","Delete","Reset","Exit"};
	
	private void frameDesign()
	{
		cp = new JPanel();
//		center.setOpaque(false);
//		support.setBackground(Style.panelColor);
//		cp.setOpaque(false);
//		support.setOpaque(false);
		center = new JPanel(new BorderLayout());
		support = new JPanel();
		sp = new JPanel();
			
		sp.setBackground(Color.DARK_GRAY);
		
		for (int i = 0; i < button.length; i++)
		{
			button[i] = new JButton(btext[i]);
			button[i].addActionListener(this);
			sp.add(button[i]);
		}
	//	button[1].setEnabled(false);
		button[2].setEnabled(false);
		button[3].setEnabled(false);
		
		centerDesign();
		vsb.addAdjustmentListener(this);
		center.add(vsb,"East");
		f.add(center);
		f.add(sp,"South");
		
		int n = support.getComponentCount();
		for (int i = 0; i < n; i++)
		{
			Component c = support.getComponent(i);
			if(c instanceof JTextField)
			{
				((JTextField) c).setText("");
				((JTextField) c).setEditable(false);
			}
			if(c instanceof JComboBox)
			{
				((JComboBox) c).setSelectedIndex(0);
				((JComboBox) c).setEnabled(false);
			}	
			if(c instanceof TextArea)
			{
				((TextArea) c).setText("");
				((TextArea) c).setEditable(false);
			}	
			if( (c instanceof JRadioButton))
				((JRadioButton) c).setEnabled(false);
		}
	}
	
	private JScrollBar vsb = new JScrollBar(JScrollBar.VERTICAL, 0,0, 0, 1200);
	int y = 0;

	private void centerDesign()
	{
		supportDesign();
		center.add(cp);
		cp.setLayout(null);
		cp.add(support);	
	}

	JLabel sno,eno,bno,dt;
	JTextField tsno,teno,tbno,tdt;
	
	private void supportDesign()
	{
		support.setLayout(null);
		support.setBounds(0, 0, 1024, 1900);
		support.setBackground(Style.panelColor);
		htab = new JLabel("BOOKING FORM");
		htab.setFont(Style.fhdfont);
		htab.setBounds(400, 20, 300, 30);
		support.add(htab);
		
		sno = new JLabel("Serial No.");
		eno = new JLabel("Enquiry ID");
		bno = new JLabel("Booking ID");
		dt = new JLabel("Date");
		
	//	sno.setFont(Style.lblfont);
//		sno.setBounds(150, 60, 180, 30);
//		support.add(sno);
		
		eno.setFont(Style.lblfont);
		eno.setBounds(515, 60, 180, 30);
		support.add(eno);
		
		bno.setFont(Style.lblfont);
		bno.setBounds(275, 60, 180, 30);
		support.add(bno);
		
		dt.setFont(Style.lblfont);
		dt.setBounds(750, 60, 180, 30);
		support.add(dt);
		
	//	tsno = new JTextField();
		teno = new JTextField();
		tbno = new JTextField();
		tdt = new JTextField();
		
//		tsno.setBounds(130, 100, 130, 30);
//		support.add(tsno);
		
		teno.setBounds(495, 100, 130, 30);
		support.add(teno);
		
		tbno.setBounds(255, 100, 130, 30);
		tbno.setEditable(false);
		support.add(tbno);
		
		tdt.setBounds(730, 100, 130, 30);
		tdt.setEditable(false);
		tdt.setFont(Style.txtfont);
		tdt.setText(Style.getDate(new Date()));
		support.add(tdt);
		
		cDesign();
		dataDesign();
		vDesign();
		calDesign();
		
		ptab = new JLabel("Personal Details");
		ptab.setFont(Style.lblfont);
	//	support.setOpaque(true);
		ptab.setOpaque(true);
		ptab.setBackground(Color.darkGray);
		ptab.setForeground(Color.red);
		ptab.setBounds(80, 160, 850, 40);
		support.add(ptab);
		
		int n = support.getComponentCount();
		for (int i = 0; i < n; i++)
		{
			Component c = support.getComponent(i);
			if(c instanceof JTextField)
			{
				((JTextField) c).setBorder(Style.border1);
				((JTextField) c).setFont(Style.txtfont);
			}
			if(c instanceof JComboBox)
				c.setFont(Style.txtfont);
		}	
	}
	
	
	private String getDate()
	{
		Date d = new Date();
		DateFormat d1 = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return d1.format(d);
	}


	JLabel caltab,exsh,insu,rfee,hyp,oth,exv,total,adv,bal,dod;
	JTextField texsh,tinsu,trfee,thyp,toth,texv,ttotal,tadv,tbal,tdod;
	JButton cal;
	
	private void calDesign()
	{
		caltab = new JLabel("Calculation Items");
		exsh = new JLabel("Ex-Showroom Price");
		insu = new JLabel("Insurance");
		rfee = new JLabel("Registration Fee & Road Tax");
		hyp = new JLabel("Hypothecation");
		oth = new JLabel("Others");
		exv = new JLabel("Less Purchase Value(if exchange)");
		total = new JLabel("Total Amount");
		adv = new JLabel("Advance");
		bal = new JLabel("Balance");
		dod = new JLabel("Tentative Date of Delivery");
		
		caltab.setOpaque(true);
		caltab.setFont(Style.lblfont);
		caltab.setBackground(Color.darkGray);
		caltab.setForeground(Color.red);
		caltab.setBounds(80, 1370, 850, 40);
		support.add(caltab);
		
		exsh.setFont(Style.lblfont);
		exsh.setBounds(200, 1420, 180, 30);
		support.add(exsh);
		
		insu.setFont(Style.lblfont);
		insu.setBounds(200, 1460, 120, 30);
		support.add(insu);
		
		rfee.setFont(Style.lblfont);
		rfee.setBounds(200, 1500, 270, 30);
		support.add(rfee);
		
		hyp.setFont(Style.lblfont);
		hyp.setBounds(200, 1540, 150, 30);
		support.add(hyp);
		
		oth.setFont(Style.lblfont);
		oth.setBounds(200, 1580, 180, 30);
		support.add(oth);
		
		exv.setFont(Style.lblfont);
		exv.setBounds(200, 1620, 320, 30);
		support.add(exv);
		
		total.setFont(Style.lblfont);
		total.setBounds(200, 1660, 180, 30);
		support.add(total);
		
		adv.setFont(Style.lblfont);
		adv.setBounds(200, 1700, 180, 30);
		support.add(adv);
		
		bal.setFont(Style.lblfont);
		bal.setBounds(200, 1740, 180, 30);
		support.add(bal);
		
		dod.setFont(Style.lblfont);
		dod.setBounds(200, 1780, 240, 30);
		support.add(dod);
		
		
		texsh = new JTextField();
		tinsu = new JTextField();
		trfee = new JTextField();
		thyp = new JTextField();
		toth = new JTextField();
		texv = new JTextField();
		ttotal = new JTextField();
		tadv = new JTextField();
		tbal = new JTextField();
		tdod = new JTextField();
		
		texsh.setBounds(515, 1420, 120, 30);
		texsh.addKeyListener(this);
		support.add(texsh);
		
		tinsu.setBounds(515, 1460, 120, 30);
		tinsu.addKeyListener(this);
		support.add(tinsu);
		
		trfee.setBounds(515, 1500, 120, 30);
		trfee.addKeyListener(this);
		support.add(trfee);
		
		thyp.setBounds(515, 1540, 120, 30);
		thyp.addKeyListener(this);
		support.add(thyp);
		
		toth.setBounds(515, 1580, 120, 30);
		toth.addKeyListener(this);
		support.add(toth);
		
		texv.setBounds(515, 1620, 120, 30);
		texv.addKeyListener(this);
		support.add(texv);
		
		ttotal.setBounds(515, 1660, 120, 30);
		ttotal.addKeyListener(this);
		support.add(ttotal);
		
		tadv.setBounds(515, 1700, 120, 30);
		tadv.addKeyListener(this);
		support.add(tadv);
		
		tbal.setBounds(515, 1740, 120, 30);
		tbal.addKeyListener(this);
		support.add(tbal);
		
		tdod.setBounds(515, 1780, 120, 30);
		support.add(tdod);
		
		cal = new JButton(new ImageIcon("cal.png"));
		cal.setBounds(650, 1780, 30, 30);
		cal.addActionListener(this);
		support.add(cal);
	}


	JLabel vtab,modelid,mname,clr,fno,pmode,fname,famt,tos,ex,yr,rno;
	JComboBox cmid,cclr;
	JTextField tmname,tfno,tfamt,tfname,tyr,trno,cex;
	JRadioButton slf,fn,nw,exc;
	
	private void vDesign()
	{
		vtab = new JLabel("Vehicle Details");
		modelid = new JLabel("Model ID");
		mname = new JLabel("Model Name");
		clr = new JLabel("Color");
		fno = new JLabel("Frame No.");
		pmode = new JLabel("Purchase Mode");
		fname = new JLabel("Financer Name");
		famt = new JLabel("Finance Amount");
		tos = new JLabel("Type of Sale");
		ex = new JLabel("If Exchange, Model");
		yr = new JLabel("Year of Manufacture");
		rno = new JLabel("Registration No.");
		
		vtab.setOpaque(true);
		vtab.setFont(Style.lblfont);
		vtab.setBackground(Color.darkGray);
		vtab.setForeground(Color.red);
		vtab.setBounds(80, 920-y, 850, 40);
		support.add(vtab);
		
		modelid.setFont(Style.lblfont);
		modelid.setBounds(200, 980, 150, 30);
		support.add(modelid);
		
		mname.setFont(Style.lblfont);
		mname.setBounds(580, 980, 150, 30);
		support.add(mname);
		
		clr.setFont(Style.lblfont);
		clr.setBounds(200, 1020, 150, 30);
		support.add(clr);
		
		fno.setFont(Style.lblfont);
		fno.setBounds(200, 1060, 150, 30);
		support.add(fno);
		
		pmode.setFont(Style.lblfont);
		pmode.setBounds(200, 1100, 150, 30);
		support.add(pmode);
		
		fname.setFont(Style.lblfont);
		fname.setBounds(200, 1140, 150, 30);
		support.add(fname);
		
		famt.setFont(Style.lblfont);
		famt.setBounds(200, 1180, 150, 30);
		support.add(famt);
		
		tos.setFont(Style.lblfont);
		tos.setBounds(200, 1220, 150, 30);
		support.add(tos);
		
		ex.setFont(Style.lblfont);
		ex.setBounds(200, 1260, 180, 30);
		support.add(ex);
		
		yr.setFont(Style.lblfont);
		yr.setBounds(200, 1300, 220, 30);
		support.add(yr);
		
		rno.setFont(Style.lblfont);
		rno.setBounds(580, 1300, 150, 30);
		support.add(rno);
		
		cmid = new JComboBox();
		cmid.setBounds(360, 980, 180, 30);
		cmid.addItem("select");
		support.add(cmid);
		
		cclr = new JComboBox();
		cclr.setBounds(360, 1020, 150, 30);
		cclr.addItem("");
		support.add(cclr);
		
		cex = new JTextField();
		cex.setBounds(380, 1260, 180, 30);
		support.add(cex);
		
		tmname = new JTextField();
		tfno = new JTextField();
		tfname = new JTextField();
		tfamt = new JTextField();
		tyr = new JTextField();
		trno = new JTextField();
		
		tmname.setBounds(700, 980, 150, 30);
		support.add(tmname);
		
		tfno.setBounds(360, 1060, 200, 30);
		support.add(tfno);
		
		tfname.setBounds(360, 1140, 200, 30);
		support.add(tfname);
		
		tfamt.setBounds(360, 1180, 150, 30);
		support.add(tfamt);
		
		tyr.setBounds(400, 1300, 90, 30);
		support.add(tyr);
		
		trno.setBounds(730, 1300, 150, 30);
		support.add(trno);
		
		slf = new JRadioButton("Self");
		fn = new JRadioButton("Finance");
		slf.setBounds(360, 1100, 70, 30);
		slf.setFont(Style.lblfont);
		support.add(slf);
		fn.setBounds(430, 1100, 100, 30);
		fn.setFont(Style.lblfont);
		support.add(fn);
		ButtonGroup bg = new ButtonGroup();
		bg.add(slf);
		bg.add(fn);
		slf.setOpaque(false);
		fn.setOpaque(false);
		
		nw = new JRadioButton("New");
		exc = new JRadioButton("Exchange");
		nw.setBounds(360, 1220, 70, 30);
		nw.setFont(Style.lblfont);
		support.add(nw);
		exc.setBounds(430, 1220, 120, 30);
		exc.setFont(Style.lblfont);
		support.add(exc);
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(nw);
		bg1.add(exc);
		nw.setOpaque(false);
		exc.setOpaque(false);
	}


	JLabel chd,othr,model,mfg,reg,gen,dob,prf,se;
	JTextField tmodel,tmfg,treg,tdob,tse;
	JComboBox cprf;
	JRadioButton ys,n,m,fe;
	JButton cal1;
	
	private void dataDesign()
	{
		chd = new JLabel("Customer Data");
		chd.setOpaque(true);
		chd.setFont(Style.lblfont);
		chd.setBackground(Color.darkGray);
		chd.setForeground(Color.red);
		chd.setBounds(80, 570, 850, 40);
		support.add(chd);
		
		
		othr = new JLabel("Other 2-wheeler Owned");
		model = new JLabel("Make/Model");
		mfg = new JLabel("Year of Purchase");
		reg = new JLabel("Registration No.");
		gen = new JLabel("Gender");
		dob = new JLabel("Date of Birth");
		prf = new JLabel("Profession");
		se = new JLabel("Salesman Name");
		
		othr.setFont(Style.lblfont);
		othr.setBounds(200, 630, 230, 30);
		support.add(othr);
		
		model.setFont(Style.lblfont);
		model.setBounds(200, 670, 150, 30);
		support.add(model);
		
		mfg.setFont(Style.lblfont);
		mfg.setBounds(550, 670, 180, 30);
		support.add(mfg);
		
		reg.setFont(Style.lblfont);
		reg.setBounds(200, 710, 150, 30);
		support.add(reg);
		
		gen.setFont(Style.lblfont);
		gen.setBounds(200, 770, 150, 30);
		support.add(gen);
		
		dob.setFont(Style.lblfont);
		dob.setBounds(550, 770, 150, 30);
		support.add(dob);
		
		cal1 = new JButton(new ImageIcon("cal.png"));
		cal1.setBounds(810, 770, 30, 30);
		cal1.addActionListener(this);
		support.add(cal1);
		
		prf.setFont(Style.lblfont);
		prf.setBounds(200, 810, 150, 30);
		support.add(prf);
		
		se.setFont(Style.lblfont);
		se.setBounds(200, 850, 150, 30);
		support.add(se);
		
		tmodel = new JTextField();
		tmfg = new JTextField();
		treg = new JTextField();
		tdob = new JTextField();
		tse = new JTextField();
		
		tmodel.setBounds(360, 670, 150, 30);
		support.add(tmodel);
		
		tmfg.setBounds(720, 670, 100, 30);
	//	tmfg.setBorder(Style.border2);
		support.add(tmfg);
		
		treg.setBounds(360, 710, 150, 30);
		support.add(treg);
		
		tdob.setBounds(680, 770, 120, 30);
		support.add(tdob);
		
		tse.setBounds(360, 850, 180, 30);
		support.add(tse);
		
		cprf = new JComboBox();
		cprf.setBounds(360, 810, 150, 30);
		cprf.addItem("---SELECT---");
		support.add(cprf);
		
		ys = new JRadioButton("Yes");
		n = new JRadioButton("No");
		m = new JRadioButton("Male");
		fe = new JRadioButton("Female");
		
		ys.setBounds(440, 630, 80, 30);
		ys.setFont(Style.lblfont);
		support.add(ys);
		n.setBounds(520, 630, 80, 30);
		n.setFont(Style.lblfont);
		support.add(n);
		ys.setOpaque(false);
		n.setOpaque(false);
		
		m.setBounds(350, 770, 70, 30);
		m.setFont(Style.lblfont);
		support.add(m);
		fe.setBounds(420, 770, 100, 30);
		fe.setFont(Style.lblfont);
		support.add(fe);
		m.setOpaque(false);
		fe.setOpaque(false);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(ys);
		bg.add(n);
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(m);
		bg1.add(fe);
		
	}


	JLabel ptab,htab,cname,so,add,local,city,state,pin,phn,res,off,mob;
	JTextField tname,tso,tlocal,tcity,tpin,tres,toff,tmob;
	TextArea tadd;
	JComboBox tstate;

	private void cDesign()
	{
		cname = new JLabel("Name");
		so = new JLabel("S/O,D/o,W/o");
		add = new JLabel("Address");
		local = new JLabel("Locality");
		city = new JLabel("City");
		state = new JLabel("State");
		pin = new JLabel("Pin");
		phn = new JLabel("Phone");
		res = new JLabel("(Res)");
		off = new JLabel("(Off)");
		mob = new JLabel("Mobile");
		
		cname.setFont(Style.lblfont);
		cname.setBounds(200, 220, 100, 30);
		support.add(cname);
		
		so.setFont(Style.lblfont);
		so.setBounds(200, 260, 150, 30);
		support.add(so);
		
		add.setFont(Style.lblfont);
		add.setBounds(200, 300, 150, 30);
		support.add(add);
		
		local.setFont(Style.lblfont);
		local.setBounds(200, 380, 150, 30);
		support.add(local);
		
		city.setFont(Style.lblfont);
		city.setBounds(600, 380, 150, 30);
		support.add(city);
		
		state.setFont(Style.lblfont);
		state.setBounds(200, 420, 150, 30);
		support.add(state);
		
		pin.setFont(Style.lblfont);
		pin.setBounds(600, 420, 150, 30);
		support.add(pin);
		
		phn.setFont(Style.lblfont);
		phn.setBounds(200, 460, 150, 30);
		support.add(phn);
		
		res.setFont(Style.lblfont);
		res.setBounds(310, 460, 100, 30);
		support.add(res);
		
		off.setFont(Style.lblfont);
		off.setBounds(600, 460, 100, 30);
		support.add(off);
		
		mob.setFont(Style.lblfont);
		mob.setBounds(200, 500, 150, 30);
		support.add(mob);
		
		tname = new JTextField();
		tso = new JTextField();
		tlocal = new JTextField();
		tcity = new JTextField();
		tpin = new JTextField();
		tres = new JTextField();
		toff = new JTextField();
		tmob = new JTextField();
		
		tadd = new TextArea();
		
		String state[] = {"-----SELECT-----","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"};
		tstate = new JComboBox(state);
		
		
		tname.setBounds(360, 220, 250, 30);
		tname.setFont(Style.txtfont);
		support.add(tname);
		
		tso.setBounds(360, 260, 250, 30);
		support.add(tso);
		
		tadd.setBounds(360, 300, 300, 70);
		tadd.setFont(Style.txtfont);
		support.add(tadd);
		
		tlocal.setBounds(360, 380, 150, 30);
		support.add(tlocal);
		
		tcity.setBounds(650, 380, 100, 30);
		tcity.setFont(Style.lblfont);
		support.add(tcity);
		
		tstate.setBounds(360, 420, 215, 30);
		support.add(tstate);
		
		tpin.setBounds(650, 420, 80, 30);
		tpin.addKeyListener(this);
		support.add(tpin);
		
		tres.setBounds(360, 460, 130, 30);
		tres.addKeyListener(this);
		support.add(tres);
		
		toff.setBounds(650, 460, 130, 30);
		toff.addKeyListener(this);
		support.add(toff);
		
		tmob.setBounds(360, 500, 150, 30);
		tmob.addKeyListener(this);
		support.add(tmob);

	}

	public static void main(String[] args)
	{
		new Book();

	}
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		if(arg0.getSource() == tpin)
		{
		//	tpin.removeFocusListener(this);
			if(tpin.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(tpin.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!! Only numbers 0-9 are allowed.");	
					tpin.setText("");			
					tpin.grabFocus();
				}
				}
		//		txtfld[3].addFocusListener(this);
			}
	
		if(arg0.getSource() == tres)
		{
			//	tpin.removeFocusListener(this);
			if(tres.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(tres.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!! Only numbers 0-9 are allowed.");	
					tres.setText("");			
					tres.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == toff)
		{
			//	tpin.removeFocusListener(this);
			if(toff.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(toff.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!! Only numbers 0-9 are allowed.");	
					toff.setText("");			
					toff.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == tmob)
		{
			//	tpin.removeFocusListener(this);
			if(tmob.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(tmob.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!! Only numbers 0-9 are allowed.");	
					tmob.setText("");			
					tmob.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == texsh)
		{
			//	tpin.removeFocusListener(this);
			if(texsh.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(texsh.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!!");	
					texsh.setText("");			
					texsh.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == tinsu)
		{
			//	tpin.removeFocusListener(this);
			if(tinsu.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(tinsu.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!!");	
					tinsu.setText("");			
					tinsu.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == trfee)
		{
			//	tpin.removeFocusListener(this);
			if(trfee.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(trfee.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!!");	
					trfee.setText("");			
					trfee.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == thyp)
		{
			//	tpin.removeFocusListener(this);
			if(thyp.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(thyp.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!!");	
					thyp.setText("");			
					thyp.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == toth)
		{
			//	tpin.removeFocusListener(this);
			if(toth.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(toth.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!!");	
					toth.setText("");			
					toth.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == texv)
		{
			//	tpin.removeFocusListener(this);
			if(texv.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(texv.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!!");	
					texv.setText("");			
					texv.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == texsh)
		{
			//	tpin.removeFocusListener(this);
			if(ttotal.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(ttotal.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!!");	
					ttotal.setText("");			
					ttotal.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == tadv)
		{
			//	tpin.removeFocusListener(this);
			if(tadv.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(tadv.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!!");	
					tadv.setText("");			
					tadv.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
		
		if(arg0.getSource() == tbal)
		{
			//	tpin.removeFocusListener(this);
			if(tbal.getText().length()>0)
			{
				try
				{
					long mob = Long.parseLong(tbal.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Invalid entry!!!");	
					tbal.setText("");			
					tbal.grabFocus();
				}
			}
			//		txtfld[3].addFocusListener(this);
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	Statement stmt,stmt1,stmt2;
	ResultSet rs,rs1,rs2;
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource() == cal1)
		{
			tdob.setText(new DatePicker(f).setPickedDate());
		}
		
		if(arg0.getSource() == cal)
		{
			tdod.setText(new DatePicker(f).setPickedDate());
		}
		
		if(arg0.getSource() == button[5])
			f.dispose();
		
		if(arg0.getSource() == button[4])
		{
			int n = support.getComponentCount();
			for (int i = 0; i < n; i++)
			{
				Component c = support.getComponent(i);
				if(c instanceof JTextField)
				{
					((JTextField) c).setText("");
					((JTextField) c).setEditable(false);
				}
				if(c instanceof JComboBox)
				{
					((JComboBox) c).setSelectedIndex(0);
					((JComboBox) c).setEnabled(false);
				}	
				if(c instanceof TextArea)
				{
					((TextArea) c).setText("");
					((TextArea) c).setEditable(false);
				}	
				if( (c instanceof JRadioButton))
					((JRadioButton) c).setEnabled(false);
				button[2].setEnabled(false);
				button[3].setEnabled(false);
			}
		}
		
		if(arg0.getSource() == button[3])
		{
//			Connection con = DataBaseUtility.getConnection();
//			if(tname.getText().length()>0)
//			{
//				try
//				{
//					stmt=con.createStatement();
//					
//					int x = JOptionPane.showConfirmDialog(f,"Are you sure that you want to delete this record?");
//					if(x == 0)
//					{
//						stmt.executeUpdate("delete from book where bk_id='"+tbno.getText()+"'");
//						JOptionPane.showMessageDialog(null, "Record deleted");
//						stmt.close();
//						txtar.setText(null);
//						m.setSelected(false);
//						fe.setSelected(false);
//						cb.setSelectedIndex(-1);
//						for (int i = 0; i < txtfld.length; i++)
//						{
//							txtfld[i].setText("");
//						}
//						
//						txtfld[0].grabFocus();
//					}
//					else
//					{
//						
//					}
//						
//				}
//				catch(Exception ex)
//				{
//		        	JOptionPane.showMessageDialog(f, ex.toString());
//		        }
//			}
//			else
//				JOptionPane.showMessageDialog(f, "No record to Delete.");
//		
		}
		
		if(arg0.getSource() == button[2])
		{
			
		}
		
		if(arg0.getSource() == button[1])
		{

			if(cname.getText().length()>0)
			{
				Connection con = DataBaseUtility.getConnection();
				String othrc ="";
				othrc=ys.isSelected()?"Yes":"No";
				String genc = "";
				genc=m.isSelected()?"Male":"Female";
				String pmode ="";
				pmode=slf.isSelected()?"Self":"Finance";
				String tos = "";
				tos=nw.isSelected()?"New":"Exchange";
				String sql = "insert into book values('"+tbno.getText()+"','"+teno.getText()+"','"+tdt.getText()+"','"+tso.getText()+"','"+tlocal.getText()+"',"+tcity.getText()+",'"+tstate.getSelectedItem()+"','"+tpin.getText()+"','"+tres.getText()+"','"+toff.getText()+"','"+othrc+"','"+tmodel.getText()+"','"+tmfg.getText()+"','"+treg.getText()+"','"+genc+"''"+tdob.getText()+"','"+cprf.getSelectedItem()+"','"+tse.getText()+"''"+cmid.getSelectedItem()+"','"+cclr.getSelectedItem()+"','"+tfno.getText()+"','"+pmode+"','"+tfname.getText()+"','"+tfamt.getText()+"','"+tos+"','"+cex.getText()+"','"+tyr.getText()+"','"+trno.getText()+"','"+tinsu.getText()+"','"+trfee.getText()+"','"+thyp.getText()+"','"+toth.getText()+"','"+texv.getText()+"','"+ttotal.getText()+"','"+tadv.getText()+"','"+tbal.getText()+"','"+tdod.getText()+"')";
				
				try
				{
					stmt = con.createStatement();
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Record Saved.");
					utility.setBukId(tbno.getText());
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, e.toString());
				}
				button[1].setEnabled(false);
				int n = support.getComponentCount();
				for (int i = 0; i < n; i++)
				{
					Component c = support.getComponent(i);
					
						if( (c instanceof JTextField))
							((JTextField) c).setEditable(false);
						if( (c instanceof TextArea))
							((TextArea) c).setEditable(false);
						if( (c instanceof JComboBox))
							((JComboBox) c).setEnabled(false);
						if( (c instanceof JRadioButton))
							((JRadioButton) c).setEnabled(false);
						
				}
//				int m = vpanel.getComponentCount();
//				for (int i = 0; i < m; i++)
//				{
//					Component c = vpanel.getComponent(i);
//					
//					if( (c instanceof JTextField))
//						((JTextField) c).setEditable(false);
//					if( (c instanceof JComboBox))
//						((JComboBox) c).setEnabled(false);
//					if( (c instanceof JRadioButton))
//						((JRadioButton) c).setEnabled(false);
//					
//				}
				button[0].setEnabled(true);
			}
			else
				JOptionPane.showMessageDialog(null, "Enter a record");
		}
		
		if(arg0.getSource() == button[0])
		{
			int n = support.getComponentCount();
			for (int i = 0; i < n; i++)
			{
				Component c = support.getComponent(i);
				if(c instanceof JTextField)
				{
					((JTextField) c).setText("");
					((JTextField) c).setEditable(true);
				}
				tbno.setEditable(false);
				tdt.setEditable(false);
				if(c instanceof JComboBox)
				{
					((JComboBox) c).setSelectedIndex(0);
					((JComboBox) c).setEnabled(true);
				}	
				if(c instanceof TextArea)
				{
					((TextArea) c).setText("");
					((TextArea) c).setEditable(true);
				}	
				if( (c instanceof JRadioButton))
					((JRadioButton) c).setEnabled(true);
			}
			tdt.setText(Style.getDate(new Date()));
			tbno.setText(utility.getBukId());
			if(tname.getText().length()>0)
				button[1].setEnabled(true);
		}
	}
	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0)
	{
		if(arg0.getSource()==vsb)
		{
		//	t[0].setText(String.valueOf(arg0.getValue()));
			y = arg0.getValue();
			
			support.setLocation(0, -y);
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
		// TODO Auto-generated method stub
		
	}

}
