package EventManagement_system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login1 extends JFrame {
    private JLabel l1, l2;
    private JTextField t1;
    private JPasswordField p1;
    private JButton b1, b2;
    private String type;

    public Login1(String s1) {
        this.type = s1;

        getContentPane().setBackground(Color.WHITE);
        l1 = new JLabel("Username");
        l2 = new JLabel("Password");
        l1.setBounds(40, 20, 100, 30);
        l2.setBounds(40, 70, 100, 30);
        add(l1);
        add(l2);

        t1 = new JTextField();
        t1.setBounds(150, 20, 150, 30);
        add(t1);

        p1 = new JPasswordField();
        p1.setBounds(150, 70, 150, 30);
        add(p1);

        b1 = new JButton("Login");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(40, 120, 100, 30);
        add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    conn c1 = new conn();
                    String u = t1.getText();
                    String v = new String(p1.getPassword());
                    ResultSet rs = null;

                    if (type.equals("admin")) { 
                        String q = "select * from user where name='" + u + "' and password='" + v + "' and type='admin'";
                        rs = c1.s.executeQuery(q);
                        System.out.print(q);
                    } else if (type.equals("customer")) { 
                        String q = "select * from user where name='" + u + "' and password='" + v + "' and type='customer'";
                        rs = c1.s.executeQuery(q);
                        System.out.print(q);
                    }

                    if (rs != null && rs.next()) {
                        if (type.equals("admin")) { 
                            new Dashboard().setVisible(true);
                        } else if (type.equals("customer")) { 
                            new Dashboard2().setVisible(true);
                        }
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid login");
                        new Event_Management_System().setVisible(true);
                        setVisible(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        b2 = new JButton("Cancel");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(150, 120, 100, 30);
        add(b2);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        setLayout(null);
        setBounds(500, 300, 600, 400);
        setVisible(true);

        if (type.equals("customer")) { 
            Customer();
        }
    }

    private void Customer() {
        JButton b3 = new JButton("Register");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setBounds(260, 120, 100, 30);
        add(b3);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Register().setVisible(true);
            }
        });
    }

    // Getter and setter methods
   
    
    public JTextField getUsernameField() {
        return t1;
    }

    public JPasswordField getPasswordField() {
        return p1;
    }
    
    public JButton getLoginButton() {
        return b1;
    }

    public JButton getCancelButton() {
        return b2;
    }
}
