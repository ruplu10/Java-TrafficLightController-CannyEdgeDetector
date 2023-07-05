package GUI;

import CannyEdgedetection.CannyEdgeDetector;
import CannyEdgedetection.ImageCompare;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TLC extends JFrame implements ActionListener {

    JButton loadtest1btn, loadtest2btn, matchTest1btn, matchTest2btn;
    JLabel nrl, ncl, srl, scl, wrl, wcl, ecl, erl;
    JPanel p1;
    A p;

    public TLC() {

        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p1 = new JPanel();
        p1.setBounds(100, 10, 200, 100);
        p1.setBackground(Color.BLACK);
        p1.setLayout(new GridLayout(0, 2, 10, 10));

        loadtest1btn = new JButton("Load_1");
        loadtest1btn.setBounds(100, 20, 100, 50);
        loadtest1btn.addActionListener(this);
        p1.add(loadtest1btn);

        loadtest2btn = new JButton("Load_2");
        loadtest2btn.setBounds(100, 20, 100, 50);
        loadtest2btn.addActionListener(this);
        p1.add(loadtest2btn);

        matchTest1btn = new JButton("Test_1");
        matchTest1btn.setBounds(100, 20, 100, 50);
        matchTest1btn.addActionListener(this);
        p1.add(matchTest1btn);

        matchTest2btn = new JButton("Test_2");
        matchTest2btn.setBounds(100, 20, 100, 50);
        matchTest2btn.addActionListener(this);
        p1.add(matchTest2btn);

        add(p1);
        setVisible(true);

    }

    public void imageLoad(String nr, String nc, String sr, String sc, String er, String ec, String wr, String wc) {
        nrl = new JLabel(new ImageIcon(nr));
        nrl.setBounds(450, 20, 200, 200);
        add(nrl);
        ncl = new JLabel(new ImageIcon(nc));
        ncl.setBounds(675, 20, 200, 200);
        add(ncl);
        srl = new JLabel(new ImageIcon(sr));
        srl.setBounds(450, 500, 200, 200);
        add(srl);
        scl = new JLabel(new ImageIcon(sc));
        scl.setBounds(675, 500, 200, 200);
        add(scl);
        erl = new JLabel(new ImageIcon(er));
        erl.setBounds(245, 260, 200, 200);
        add(erl);
        ecl = new JLabel(new ImageIcon(ec));
        ecl.setBounds(40, 260, 200, 200);
        add(ecl);
        wrl = new JLabel(new ImageIcon(wr));
        wrl.setBounds(880, 260, 200, 200);
        add(wrl);
        wcl = new JLabel(new ImageIcon(wc));
        wcl.setBounds(1085, 260, 200, 200);
        add(wcl);

    }

    public static void main(String[] args) {
        TLC tlc = new TLC();
    }

    public void getEdgeDect(String nr, String nc, String sr, String sc, String er, String ec, String wr, String wc) {
        CannyEdgeDetector canny = new CannyEdgeDetector();
        canny.edgeDec("NORTH_ref_edge.jpg", new File(nr));
        canny.edgeDec("NORTH_capture_edge.jpg", new File(nc));
        nrl.setIcon(new ImageIcon("NORTH_ref_edge.jpg"));
        ncl.setIcon(new ImageIcon("NORTH_capture_edge.jpg"));
        ImageCompare com = new ImageCompare();
        double result_north = 100.0 - com.compareImg("NORTH_ref_edge.jpg", "NORTH_capture_edge.jpg");

        canny.edgeDec("SOUTH_ref_edge.jpg", new File(sr));
        canny.edgeDec("SOUTH_capture_edge.jpg", new File(sc));
        srl.setIcon(new ImageIcon("SOUTH_ref_edge.jpg"));
        scl.setIcon(new ImageIcon("SOUTH_capture_edge.jpg"));
        ImageCompare com1 = new ImageCompare();
        double result_south = 100.0 - com1.compareImg("SOUTH_ref_edge.jpg", "SOUTH_capture_edge.jpg");

        canny.edgeDec("EAST_ref_edge.jpg", new File(er));
        canny.edgeDec("EAST_capture_edge.jpg", new File(ec));
        erl.setIcon(new ImageIcon("EAST_ref_edge.jpg"));
        ecl.setIcon(new ImageIcon("EAST_capture_edge.jpg"));
        ImageCompare com2 = new ImageCompare();
        double result_east = 100.0 - com2.compareImg("EAST_ref_edge.jpg", "EAST_capture_edge.jpg");

        canny.edgeDec("WEST_ref_edge.jpg", new File(wr));
        canny.edgeDec("WEST_capture_edge.jpg", new File(wc));
        wrl.setIcon(new ImageIcon("WEST_ref_edge.jpg"));
        wcl.setIcon(new ImageIcon("WEST_capture_edge.jpg"));
        ImageCompare com3 = new ImageCompare();
        double result_west = 100.0 - com3.compareImg("WEST_ref_edge.jpg", "WEST_capture_edge.jpg");

        p = new A(result_north, result_south, result_east, result_west);
        p.setBounds(450, 225, 425, 270);
        p.setBackground(Color.BLACK);
        add(p);

        repaint();

        JOptionPane.showMessageDialog(null, "North : " + result_north + "\n" + "SOUTH :"
                + result_south + "\n" + "EAST :" + result_east + "\n" + "WEST :" + result_west);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadtest1btn) {

            imageLoad("NORTH_ref.jpg", "NORTH_capture.jpg", "SOUTH_ref.jpg", "SOUTH_capture.jpg", "EAST_ref.jpg", "EAST_capture.jpg", "WEST_ref.jpg", "WEST_capture.jpg");
            repaint();
        } else if (e.getSource() == matchTest1btn) {

            getEdgeDect("NORTH_ref.jpg", "NORTH_capture.jpg", "SOUTH_ref.jpg", "SOUTH_capture.jpg", "EAST_ref.jpg", "EAST_capture.jpg", "WEST_ref.jpg", "WEST_capture.jpg");
            repaint();
        }
        if (e.getSource() == loadtest2btn) {

            imageLoad("NORTH_ref2.jpg", "NORTH_capture2.jpg", "SOUTH_ref2.jpg", "SOUTH_capture2.jpg", "EAST_ref2.jpg", "EAST_capture2.jpg", "WEST_ref2.jpg", "WEST_capture2.jpg");
            repaint();
        } else if (e.getSource() == matchTest2btn) {

            getEdgeDect("NORTH_ref2.jpg", "NORTH_capture2.jpg", "SOUTH_ref2.jpg", "SOUTH_capture2.jpg", "EAST_ref2.jpg", "EAST_capture2.jpg", "WEST_ref2.jpg", "WEST_capture2.jpg");
            repaint();
        }
    }

}

class A extends JPanel {

    double result_north, result_south, result_east, result_west;

    public A(double result_north, double result_south, double result_east, double result_west) {
        this.result_north = result_north;
        this.result_south = result_south;
        this.result_east = result_east;
        this.result_west = result_west;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;

        g1.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        if (result_north >= 10 && result_north <= 50) {
            g1.setColor(Color.red);
            g1.drawOval(getWidth() / 2 - 50, 10, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(getWidth() / 2, 10, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(getWidth() / 2 + 50, 10, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green light on for 60 seconds", getWidth() / 2 - 75, 50);
        } else if (result_north > 50 && result_north <= 70) {
            g1.setColor(Color.red);
            g1.drawOval(getWidth() / 2 - 50, 10, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(getWidth() / 2, 10, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(getWidth() / 2 + 50, 10, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green light on for 30 seconds", getWidth() / 2 - 70, 50);
        } else if (result_north > 70 && result_north <= 90) {
            g1.setColor(Color.red);
            g1.drawOval(getWidth() / 2 - 50, 10, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(getWidth() / 2, 10, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(getWidth() / 2 + 50, 10, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green light on for 20 seconds", getWidth() / 2 - 75, 50);
        } else if (result_north > 90 && result_north <= 100) {
            g1.setColor(Color.RED);
            g1.fillOval(getWidth() / 2 - 50, 10, 30, 30);
            g1.setColor(Color.RED);
            g1.drawOval(getWidth() / 2, 10, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(getWidth() / 2 + 50, 10, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Red light on for 60 seconds", getWidth() / 2 - 75, 50);
        }

        // for result south
        if (result_south >= 10 && result_south <= 50) {
            g1.setColor(Color.red);
            g1.drawOval(getWidth() / 2 - 50, 238, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(getWidth() / 2, 238, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(getWidth() / 2 + 50, 238, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green light on for 60 seconds", getWidth() / 2 - 75, 230);
        } else if (result_south > 50 && result_south <= 70) {
            g1.setColor(Color.red);
            g1.drawOval(getWidth() / 2 - 50, 238, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(getWidth() / 2, 238, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(getWidth() / 2 + 50, 238, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green light on for 30 seconds", getWidth() / 2 - 75, 230);
        } else if (result_south > 70 && result_south <= 90) {
            g1.setColor(Color.red);
            g1.drawOval(getWidth() / 2 - 50, 238, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(getWidth() / 2, 238, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(getWidth() / 2 + 50, 238, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green light on for 20 seconds", getWidth() / 2 - 75, 230);
        } else if (result_south > 90 && result_south <= 100) {
            g1.setColor(Color.RED);
            g1.fillOval(getWidth() / 2 - 50, 238, 30, 30);
            g1.setColor(Color.GREEN);
            g1.drawOval(getWidth() / 2, 238, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(getWidth() / 2 + 50, 238, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Red light on for 60 seconds", getWidth() / 2 - 75, 230);
        }

        // for result East
        if (result_east >= 10 && result_east <= 50) {

            g1.setColor(Color.red);
            g1.drawOval(10, 60, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(10, 100, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(10, 140, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green 60 seconds", 40, 120);
        } else if (result_east > 50 && result_east <= 70) {
            g1.setColor(Color.red);
            g1.drawOval(10, 60, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(10, 100, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(10, 140, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green 30 seconds", 40, 120);

        } else if (result_east > 70 && result_east <= 90) {
            g1.setColor(Color.red);
            g1.drawOval(10, 60, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(10, 100, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(10, 140, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green 20 seconds", 40, 120);

        } else if (result_east > 90 && result_east <= 100) {
            g1.setColor(Color.red);
            g1.fillOval(10, 60, 30, 30);
            g1.setColor(Color.GREEN);
            g1.drawOval(10, 100, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(10, 140, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("RED 60 seconds", 40, 120);

        }

        // for result west
        if (result_west >= 10 && result_west <= 50) {

            g1.setColor(Color.red);
            g1.drawOval(380, 60, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(380, 100, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(380, 140, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green 60 seconds", 280, 120);
        } else if (result_west > 50 && result_west <= 70) {
            g1.setColor(Color.red);
            g1.drawOval(380, 60, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(380, 100, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(380, 140, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green 30 seconds", 280, 120);
        } else if (result_west > 70 && result_west <= 90) {
            g1.setColor(Color.red);
            g1.drawOval(380, 60, 30, 30);
            g1.setColor(Color.GREEN);
            g1.fillOval(380, 100, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(380, 140, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("Green 20 seconds", 280, 120);
        } else if (result_west > 90 && result_west <= 100) {
            g1.setColor(Color.red);
            g1.fillOval(380, 60, 30, 30);
            g1.setColor(Color.GREEN);
            g1.drawOval(380, 100, 30, 30);
            g1.setColor(Color.ORANGE);
            g1.drawOval(380, 140, 30, 30);
            g1.setColor(Color.BLACK);
            g1.drawString("RED 60 seconds", 280, 120);
        }
    }
}
