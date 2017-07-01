import java.awt.*;

import javax.swing.*;
public class Feedback extends JFrame 
{
	JPanel panel;
    ImageIcon img;
    Container c;
    JLabel lblhead = new JLabel("Feedback Form",JLabel.CENTER);
    JTextField txtcid,txtregno,txtname,txtmob,txtemail,txtdate,txttime;
    JButton btnsave,btnreset,btnclose;
 
    TextArea txtadd,txtdesc;
	public Feedback() 
	{
		txtcid=new JTextField();
		txtname=new JTextField();
		txtregno=new JTextField();
		txtmob=new JTextField();
		txtemail=new JTextField();
		txtadd=new TextArea();
		txtdesc=new TextArea();
		txtdate=new JTextField();
		txttime=new JTextField();
		//txtvat=new JTextField();
		btnsave=new JButton("SAVE");
		btnreset=new JButton("RESET");
		btnclose=new JButton("CLOSE");
		//btnlof=new JButton("LOG OFF...");
		// img=new ImageIcon("",null);     
	        panel=new JPanel(){
	      //      protected void paintComponent(Graphics g){
	        //        g.drawImage(img.getImage(), 0, 0,1024,768, null);
	          //      super.paintComponent(g);
	           //}
	       };
	        panel.setOpaque(false);
	        panel.setPreferredSize(new Dimension(0,0));        
	        c=getContentPane();
	        panel.setLayout(null);
	       c.add(panel);
		
	   addcomp(0,0,800,50,lblhead);
	    lblhead.setFont(new Font("Cooper Black",Font.BOLD,30));
	    addcomp(30,90,150,25,new JLabel("Feedback Id"));
	    addcomp(200,90,100,25,txtcid);
	   // addcomp(350,60,150,50,btnfind);
	
	    addcomp(30,120,155,25,new JLabel("Registration No (if)"));
	    addcomp(200,120,125,25,txtregno);
	    addcomp(30,150,155,25,new JLabel("Name"));
	    addcomp(200,150,160,25,txtname);
	    addcomp(30,180,155,25,new JLabel("Address"));
	    addcomp(200,180,200,80,txtadd);
	    addcomp(30,265,155,25,new JLabel("Mobile"));
	    addcomp(200,265,155,25,txtmob);
	    addcomp(30,295,155,25,new JLabel("E-mail"));
	    addcomp(200,295,155,25,txtemail);
	    //addcomp(420,90,155,25,new JLabel("Feedback Type"));
	    //addcomp(560,90,155,25,ctype);
	    addcomp(420,90,140,30,new JLabel("Description"));
	    addcomp(560,90,200,80,txtdesc);
	    addcomp(30,330,155,25,new JLabel("Date"));
	    addcomp(200,330,150,25,txtdate);
	    addcomp(30,360,155,25,new JLabel("Time"));
	    addcomp(200,360,150,25,txttime);
	    addcomp(80,400,60,60,btnsave);
	    //btnsave.setIcon(new ImageIcon("s.png"));
	    addcomp(165,400,60,60,btnreset);
	   // btnreset.setIcon(new ImageIcon("reset.png"));
	    addcomp(250,400,60,60,btnclose);
	    //btnhome.setIcon(new ImageIcon("home.png"));
	    //addcomp(320,400,60,60,btnlof);
	   // btnlof.setIcon(new ImageIcon("lof.png"));    
		setBounds(130,150, 800,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.setBounds(0,0,Integer.MAX_VALUE,Integer.MAX_VALUE);
		setTitle("Feedback");
		setVisible(true);
	}
	public void addcomp(int x,int y, int w, int h,Component com)
	{
		com.setBounds(x, y, w, h);
		panel.add(com);
		 if(com instanceof JLabel){
	            ((JLabel)com).setFont(new Font("Kristen ITC",Font.BOLD,14));
	            ((JLabel)com).setForeground(Color.black);
	          
	        }
		 if(com instanceof JButton){
	           // ((JButton)com).addActionListener(this);
	            ((JButton)com).setBorder(null);
	            ((JButton)com).setBorderPainted(false);
	            ((JButton)com).setContentAreaFilled(false);
	            ((JButton)com).setOpaque(false);
	        }
	}
	public static void main(String[] args) {
		new Feedback();
	}

}
