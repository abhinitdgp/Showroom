
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Accdelivery implements ActionListener
{
	JFrame f;
	public Accdelivery()
	{
		f = new JFrame("Automobile Showroom Management System");
		frameDesign();
		f.setVisible(true);
		f.setSize(1024, 725);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	JPanel sp,center;
	JButton button[] = new JButton[5];
	String btext[] = {"OK","Update","Cancel","Reset","Exit"};
	Image im = new ImageIcon("motorcycle-background-1867523.jpg").getImage();

	private void frameDesign()
	{
		center = new JPanel();
		//{
//			@Override
//			public void paint(Graphics arg0)
//			{
//				arg0.drawImage(im, 0, 0, 1000, 700, null);
//				super.paint(arg0);
//			}
//		};
		sp = new JPanel();
//		center.setOpaque(false);
		center.setBackground(Style.panelColor);
		sp.setBackground(Color.DARK_GRAY);
		
		for (int i = 0; i < button.length; i++)
		{
			button[i] = new JButton(btext[i]);
			button[i].addActionListener(this);
			//button[i].setFont("Kristen ITC");
			sp.add(button[i]);
		}
		centerDesign();
		f.add(center);
		f.add(sp,"South");
	}
	
	
	JLabel head,oid,odt,otdt,did,ddt,dtq;
	JTextField toid,todt,totdt,tdid,tddt,tdtq;
	JPanel opanel;
	
	private void centerDesign()
	{
		center.setLayout(null);
		head = new JLabel("Accessories Delivery");
		head.setBounds(315, 20, 450, 30);
		head.setFont(Style.fhdfont);
	//	center.setBackground(Style.panelColor);
		center.add(head);
		
		oid = new JLabel("Order ID");
		odt = new JLabel("Order Date");
		otdt = new JLabel("Tentative Date of Delivery");
		did = new JLabel("Delivery ID");
		ddt = new JLabel("Delivery Date");
		dtq = new JLabel("Total Quantity");
		
		oid.setFont(Style.lblfont);
		oid.setBounds(400, 80, 150, 30);
		center.add(oid);
		
		odt.setFont(Style.lblfont);
		odt.setBounds(150, 130, 150, 30);
		center.add(odt);
		
		otdt.setFont(Style.lblfont);
		otdt.setBounds(550, 130, 230, 30);
		center.add(otdt);
		
		did.setFont(Style.lblfont);
		did.setBounds(80, 180, 150, 30);
		center.add(did);
		
		ddt.setFont(Style.lblfont);
		ddt.setBounds(380, 180, 150, 30);
		center.add(ddt);
		
		dtq.setFont(Style.lblfont);
		dtq.setBounds(700, 180, 150, 30);
		center.add(dtq);
		
		toid = new JTextField();
		todt = new JTextField();
		totdt = new JTextField();
		tdid = new JTextField();
		tddt = new JTextField();
		tdtq = new JTextField();
		
		
		toid.setBounds(480, 80, 150, 30);
		center.add(toid);
		
		todt.setBounds(250, 130, 130, 30);
		todt.setFont(Style.txtfont);
		todt.setEditable(false);
		todt.setText(getDate());
		center.add(todt);
		
		totdt.setBounds(790, 130, 100, 30);
		totdt.setFont(Style.txtfont);
		totdt.setEditable(false);
		center.add(totdt);
		
		tdid.setBounds(185, 180, 100, 30);
		tdid.setFont(Style.txtfont);
		center.add(tdid);
		
		tddt.setBounds(510, 180, 100, 30);
		tddt.setFont(Style.txtfont);
		center.add(tddt);
		
		tdtq.setBounds(835, 180, 100, 30);
		tdtq.setFont(Style.txtfont);
		center.add(tdtq);
		
		int n = center.getComponentCount();
		for (int i = 0; i < n; i++)
		{
			Component c = center.getComponent(i);
			if(c instanceof JTextField)
				((JTextField) c).setBorder(Style.border1);
		}
		
		opanelDesign();	
	}
	
	
	JLabel sno,accid,accname,mdlname,clr,prc,qty,ttl,net;
	JComboBox caccid,cclr;
	JTextField tsno,taccname,tmdlname,tprc,tqty,tttl,netqty,netttl;
	JList lsno,laccid,laccname,lmdlname,lclr,lprc,lqty,lttl;
	JButton add,sub;
	
	private void opanelDesign()
	{
		opanel = new JPanel();
		opanel.setLayout(null);
		opanel.setBorder(Style.border1);
		opanel.setOpaque(false);
		opanel.setBounds(40, 240, 950, 400);
		center.add(opanel);
		
		sno = new JLabel("S.No.");
		accid = new JLabel("Accesory ID");
		accname = new JLabel("Accessory Name");
		mdlname = new JLabel("Model Name");
		clr = new JLabel("Color");
		prc = new JLabel("Price(Rs.)");
		qty = new JLabel("Quantity");
		ttl = new JLabel("Total(Rs.)");
		net = new JLabel("Net Total");
		
		tsno = new JTextField();
		taccname = new JTextField();
		tmdlname = new JTextField();
		tprc = new JTextField();
		tqty = new JTextField();
		tttl = new JTextField();
		netqty = new JTextField();
		netttl = new JTextField();
		
		caccid = new JComboBox();
		cclr = new JComboBox();
		
		lsno = new JList();
		laccid = new JList();
		laccname = new JList();
		lmdlname = new JList();
		lclr = new JList();
		lprc = new JList();
		lqty = new JList();
		lttl = new JList();
		
		add = new JButton("+");
		sub = new JButton("-");
		
		sno.setFont(Style.lblfont);
		sno.setBounds(10, 10, 50, 30);
		opanel.add(sno);
		
		tsno.setBounds(10, 40, 40, 30);
		tsno.setFont(Style.txtfont);
		opanel.add(tsno);
		
		lsno.setBounds(10, 90, 40, 200);
		lsno.setFont(Style.txtfont);
		lsno.setBorder(Style.border1);
		opanel.add(lsno);
		
		accid.setFont(Style.lblfont);
		accid.setBounds(80, 10, 120, 30);
		opanel.add(accid);
		
		caccid.setBounds(70, 40, 120, 30);
		caccid.setFont(Style.txtfont);
		opanel.add(caccid);
		
		laccid.setBounds(70, 90, 120, 200);
		laccid.setFont(Style.txtfont);
		laccid.setBorder(Style.border1);
		opanel.add(laccid);
		
		accname.setFont(Style.lblfont);
		accname.setBounds(200, 10, 150, 30);
		opanel.add(accname);
		
		taccname.setBounds(210, 40, 130, 30);
		taccname.setFont(Style.txtfont);
		opanel.add(taccname);
		
		laccname.setBounds(210, 90, 130, 200);
		laccname.setFont(Style.txtfont);
		laccname.setBorder(Style.border1);
		opanel.add(laccname);
		
		mdlname.setFont(Style.lblfont);
		mdlname.setBounds(355, 10, 120, 30);
		opanel.add(mdlname);
		
		tmdlname.setBounds(360, 40, 100, 30);
		tmdlname.setFont(Style.txtfont);
		opanel.add(tmdlname);
		
		lmdlname.setBounds(360, 90, 100, 200);
		lmdlname.setFont(Style.txtfont);
		lmdlname.setBorder(Style.border1);
		opanel.add(lmdlname);
		
		clr.setFont(Style.lblfont);
		clr.setBounds(500, 10, 100, 30);
		opanel.add(clr);
		
		cclr.setBounds(470, 40, 100, 30);
		cclr.setFont(Style.txtfont);
		opanel.add(cclr);
		
		lclr.setBounds(470, 90, 100, 200);
		lclr.setFont(Style.txtfont);
		lclr.setBorder(Style.border1);
		opanel.add(lclr);
		
		prc.setFont(Style.lblfont);
		prc.setBounds(575, 10, 100, 30);
		opanel.add(prc);
		
		tprc.setBounds(575, 40, 90, 30);
		tprc.setFont(Style.txtfont);
		opanel.add(tprc);
		
		lprc.setBounds(575, 90, 90, 200);
		lprc.setFont(Style.txtfont);
		lprc.setBorder(Style.border1);
		opanel.add(lprc);
		
		net.setFont(Style.lblfont);
		net.setBounds(585, 320, 100, 30);
		opanel.add(net);
		
		qty.setFont(Style.lblfont);
		qty.setBounds(685, 10, 100, 30);
		opanel.add(qty);
		
		tqty.setBounds(675, 40, 90, 30);
		tqty.setFont(Style.txtfont);
		opanel.add(tqty);
		
		lqty.setBounds(675, 90, 90, 200);
		lqty.setFont(Style.txtfont);
		lqty.setBorder(Style.border1);
		opanel.add(lqty);
		
		netqty.setBounds(675, 320, 90, 30);
		netqty.setFont(Style.txtfont);
		opanel.add(netqty);
		
		ttl.setFont(Style.lblfont);
		ttl.setBounds(780, 10, 100, 30);
		opanel.add(ttl);
		
		tttl.setBounds(770, 40, 100, 30);
		tttl.setFont(Style.txtfont);
		opanel.add(tttl);
		
		lttl.setBounds(770, 90, 100, 200);
		lttl.setFont(Style.txtfont);
		lttl.setBorder(Style.border1);
		opanel.add(lttl);
		
		netttl.setBounds(770, 320, 100, 30);
		netttl.setFont(Style.txtfont);
		opanel.add(netttl);
		
		add.setBounds(885, 70, 50, 50);
		opanel.add(add);
		
		sub.setBounds(885, 130, 50, 50);
		opanel.add(sub);
		
		int n = opanel.getComponentCount();
		for (int i = 0; i < n; i++)
		{
			Component c = opanel.getComponent(i);
			if(c instanceof JTextField)
				((JTextField) c).setBorder(Style.border1);
		}
		
	}
	private String getDate()
	{
		Date d = new Date();
		DateFormat d1 = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return d1.format(d);
	}
	public static void main(String[] args)
	{
		new Accdelivery();

	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
