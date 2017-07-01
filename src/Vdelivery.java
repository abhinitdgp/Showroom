import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Vdelivery implements ActionListener
{
	JFrame f;
	
	public Vdelivery()
	{
		f = new JFrame("Automobile Showroom Management System");
		frameDesign();
		f.setVisible(true);
		f.setLocation(160, 0);
		f.setSize(1024, 725);
		f.setResizable(false);
	//	f.setBackground(Style.panelColor);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	JPanel sp,center;
	JButton button[] = new JButton[5];
	String btext[] = {"OK","Update","Cancel","Reset","Exit"};

	private void frameDesign()
	{
		center = new JPanel();
		sp = new JPanel();	
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
		head = new JLabel("Vehicle Delivery");
		head.setBounds(340, 20, 400, 30);
		head.setFont(Style.fhdfont);
		center.setBackground(Style.panelColor);
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
		todt.setText(Style.getDate(new Date()));
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
	
	JLabel sno,mdlid,mdlname,clr,prc,qty,ttl,net;
	JComboBox cmdlid,cclr;
	JTextField tsno,tmdlname,tprc,tqty,tttl,netqty,netttl;
	JList lsno,lmdlid,lmdlname,lclr,lprc,lqty,lttl;
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
		mdlid = new JLabel("Model ID");
		mdlname = new JLabel("Model Name");
		clr = new JLabel("Color");
		prc = new JLabel("Price(Rs.)");
		qty = new JLabel("Quantity");
		ttl = new JLabel("Total(Rs.)");
		net = new JLabel("Net Total");
		
		tsno = new JTextField();
		tmdlname = new JTextField();
		tprc = new JTextField();
		tqty = new JTextField();
		tttl = new JTextField();
		netqty = new JTextField();
		netttl = new JTextField();
		
		cmdlid = new JComboBox();
		cclr = new JComboBox();
		
		lsno = new JList();
		lmdlid = new JList();
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
		
		mdlid.setFont(Style.lblfont);
		mdlid.setBounds(100, 10, 100, 30);
		opanel.add(mdlid);
		
		cmdlid.setBounds(80, 40, 120, 30);
		cmdlid.setFont(Style.txtfont);
		opanel.add(cmdlid);
		
		lmdlid.setBounds(80, 90, 120, 200);
		lmdlid.setFont(Style.txtfont);
		lmdlid.setBorder(Style.border1);
		opanel.add(lmdlid);
		
		mdlname.setFont(Style.lblfont);
		mdlname.setBounds(250, 10, 130, 30);
		opanel.add(mdlname);
		
		tmdlname.setBounds(230, 40, 150, 30);
		tmdlname.setFont(Style.txtfont);
		opanel.add(tmdlname);
		
		lmdlname.setBounds(230, 90, 150, 200);
		lmdlname.setFont(Style.txtfont);
		lmdlname.setBorder(Style.border1);
		opanel.add(lmdlname);
		
		clr.setFont(Style.lblfont);
		clr.setBounds(420, 10, 100, 30);
		opanel.add(clr);
		
		cclr.setBounds(400, 40, 100, 30);
		cclr.setFont(Style.txtfont);
		opanel.add(cclr);
		
		lclr.setBounds(400, 90, 100, 200);
		lclr.setFont(Style.txtfont);
		lclr.setBorder(Style.border1);
		opanel.add(lclr);
		
		prc.setFont(Style.lblfont);
		prc.setBounds(530, 10, 100, 30);
		opanel.add(prc);
		
		tprc.setBounds(520, 40, 90, 30);
		tprc.setFont(Style.txtfont);
		opanel.add(tprc);
		
		lprc.setBounds(520, 90, 90, 200);
		lprc.setFont(Style.txtfont);
		lprc.setBorder(Style.border1);
		opanel.add(lprc);
		
		net.setFont(Style.lblfont);
		net.setBounds(540, 320, 100, 30);
		opanel.add(net);
		
		qty.setFont(Style.lblfont);
		qty.setBounds(650, 10, 100, 30);
		opanel.add(qty);
		
		tqty.setBounds(640, 40, 90, 30);
		tqty.setFont(Style.txtfont);
		
		opanel.add(tqty);
		
		lqty.setBounds(640, 90, 90, 200);
		lqty.setFont(Style.txtfont);
		lqty.setBorder(Style.border1);
		opanel.add(lqty);
		
		netqty.setBounds(640, 320, 100, 30);
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
		new Vdelivery();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
