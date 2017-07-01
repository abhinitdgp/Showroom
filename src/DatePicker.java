import java.awt.BorderLayout;
   import java.awt.Color;
   import java.awt.Dimension;
   import java.awt.GridLayout;
   import java.awt.event.ActionEvent;
   import java.awt.event.ActionListener;

   import javax.swing.JButton;
   import javax.swing.JDialog;
   import javax.swing.JFrame;
   import javax.swing.JLabel;
   import javax.swing.JPanel;
   import javax.swing.JTextField;
import javax.swing.JWindow;


public class DatePicker 
{
     int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
     int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
    JLabel l = new JLabel("", JLabel.CENTER);
    String day = "";
    JDialog d;
    JButton[] button = new JButton[49];

 public DatePicker(JFrame parent) {
         d = new JDialog();
         d.setModal(true);
         String[] header = { "S", "M", "T", "W", "Th", "F", "Sa" };
         JPanel p1 = new JPanel(new GridLayout(7, 7));
         p1.setPreferredSize(new Dimension(350, 120));

         for (int x = 0; x < button.length; x++) {
                 final int selection = x;
                 button[x] = new JButton();
                 button[x].setFocusPainted(false);
                 button[x].setBackground(Color.white);
                 if (x > 6)
                         button[x].addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent ae) {
                                         day = button[selection].getActionCommand();
                                         d.dispose();
                                 }
                         });
                 if (x < 7) {
                         button[x].setText(header[x]);
                         button[x].setForeground(Color.red);
                 }
                 p1.add(button[x]);
         }
         JPanel p2 = new JPanel(new GridLayout(1, 5));
         JButton previous = new JButton("<< Previous");
         previous.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                         month--;
                         displayDate();
                 }
         });
         p2.add(previous);
        
         JButton previousyear = new JButton("<< Previous Year");
         previousyear.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                         year--;
                         displayDate();
                 }
         });
         p2.add(previousyear);
         p2.add(l);
         JButton nextyr = new JButton("Next year >>");
         nextyr.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                         year++;
                         displayDate();
                 }
         });
         p2.add(nextyr);
         JButton next = new JButton("Next >>");
         next.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent ae) {
                         month++;
                         displayDate();
                 }
         });
         p2.add(next);
         d.add(p1, BorderLayout.CENTER);
         d.add(p2, BorderLayout.SOUTH);
         d.pack();
         d.setLocationRelativeTo(parent);
         displayDate();
         d.setVisible(true);
 }

 public void displayDate() {
         for (int x = 7; x < button.length; x++)
                 button[x].setText("");
         java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                         "MMMM yyyy");
         java.util.Calendar cal = java.util.Calendar.getInstance();
         cal.set(year, month, 1);
         int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
         int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
         for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
                 button[x].setText("" + day);
         l.setText(sdf.format(cal.getTime()));
         d.setTitle("Date Picker");
 }

 public String setPickedDate() {
         if (day.equals(""))
                 return day;
         java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                         "dd/MMM/yyyy");
         java.util.Calendar cal = java.util.Calendar.getInstance();
         cal.set(year, month, Integer.parseInt(day));
         String S =  sdf.format(cal.getTime());
         String dd =  S.substring(0,S.indexOf('/'));
         String mmm =  S.substring(S.indexOf('/')+1,S.lastIndexOf('/'));
         String	yyyy =  S.substring(S.lastIndexOf('/')+1);
         
         return dd+"-"+mmm+"-"+yyyy;
         
 }
}
