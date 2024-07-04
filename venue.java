package EventManagement_system;

import java.sql.*;
import java.util.logging.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Venue extends AbstractVenue {
    private JLabel[] labels = new JLabel[]{new JLabel("Name"), new JLabel("price"), new JLabel("capacity"), new JLabel("facilities"), new JLabel("description")};
    private String name, image_url, facilities, description, capacity, price;
    private JPanel contentPane;
    private JTextField t1, t2, t3, t4, t5, t6, t7;
    private JButton b1, b2;
    private Choice c1;

    public Venue(String name) {
        initializeUI();
        displayVenueDetails(name);
    }

    private void initializeUI() {
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Venues");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(1366, 900);
        getContentPane().setLayout(null);
        JScrollPane sb = new JScrollPane();
        add(sb);
        setSize(1366, 900);
        setVisible(true);
        setLocation(0, 0);
    }

    @Override
    void displayVenueDetails(String name) {
        conn c = new conn();
        String query = "select * from venue where Name ='" + name + "'";
        try {
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                this.name = rs.getString(2);
                this.image_url = rs.getString(3);
                this.capacity = rs.getString(4);
                this.price = rs.getString(5);
                this.facilities = rs.getString(6);
                this.description = rs.getString(7);

                JLabel[] details = new JLabel[]{new JLabel(this.name), new JLabel(this.price), new JLabel(this.capacity), new JLabel(this.facilities), new JLabel(this.description)};
                int j = 0;
                for (int i = 0; i < 5; i++) {
                    if (i > 2) {
                        j += 50;
                        labels[i].setBounds(40, 20 + j, 100, 30);
                    } else {
                        labels[i].setBounds(40, 20 + j, 100, 30);
                    }
                    add(labels[i]);
                    j += 50;
                }
                j = 0;
                for (int i = 0; i < 5; i++) {
                    if (i > 2) {
                        j += 50;
                        details[i].setBounds(150, 20 + j, 500, 500);
                    } else {
                        details[i].setBounds(150, 20 + j, 100, 30);
                    }
                    add(details[i]);
                    j += 50;
                }
                try {
                    URL url = new URL(image_url);
                    Image img = ImageIO.read(url);
                    ImageIcon i2 = new ImageIcon(img);
                    Image q1 = i2.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
                    ImageIcon q2 = new ImageIcon(q1);
                    JLabel l3 = new JLabel(q2);
                    l3.setBounds(450, 50, 300, 300);
                    add(l3);
                } catch (IOException ex) {
                    Logger.getLogger(Venue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Venue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    void addVenue(String name, String image_url, String capacity, String price, String facilities, String description) {
        conn c = new conn();
        String query = "insert into venue values('" + name + "', '" + image_url + "', " + capacity + ", " + price + ", '" + facilities + "', '" + description + "' )";
        try {
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Venue Successfully Added");
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(Venue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
