package timetableManager;


import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import javax.management.Query;
import javax.swing.*;
import javax.swing.border.*;

import java.sql.*;


 

public class Statistics extends JPanel
{
    private int histogramHeight = 200;
    private int barWidth = 50;
    private int barGap = 10;

    private JPanel barPanel;
    private JPanel labelPanel;

    private List<Bar> bars = new ArrayList<Bar>();
    private Panel panel_1;
    
    Connection con = DBConnecter.connect();
    //DBConnecter db;
    
    public Statistics() throws SQLException
    {
        setBorder( new EmptyBorder(10, 10, 10, 10) );

        barPanel =  	new JPanel( new GridLayout(1, 0, barGap, 0) );
        barPanel.setBounds(121, 121, 502, 466);
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( compound );

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );
        setLayout(null);

        add(barPanel);
        add(labelPanel, BorderLayout.PAGE_END);
        
        panel_1 = new Panel();
        
        panel_1.setBounds(10, 24, 0, 607);
        add(panel_1);
        panel_1.setLayout(null);
        
        Connection con1 = DBConnecter.connect();
    	Statement state1 = con1.createStatement();
    	ResultSet rs;
    	rs = state1.executeQuery("select count(*) from location");
    	Integer x = rs.getInt("count(*)");
    	
    	Connection con2 = DBConnecter.connect();
    	Statement state2 = con2.createStatement();
    	ResultSet rs1;
    	rs1 = state2.executeQuery("select count(*) from Emplooyee");
    	Integer w = rs1.getInt("count(*)");
    	
    	Connection con3 = DBConnecter.connect();
    	Statement state3 = con3.createStatement();
    	ResultSet rs2;
    	rs2 = state3.executeQuery("select count(*) from studentGroups");
    	Integer y = rs2.getInt("count(*)");
    	
    	Connection con4 = DBConnecter.connect();
    	Statement state4 = con4.createStatement();
    	ResultSet rs3;
    	rs3 = state4.executeQuery("select count(*) from Subject");
    	Integer z = rs3.getInt("count(*)");
        
        JLabel lblNewLabel = new JLabel(String.valueOf(w));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBackground(new Color(255, 215, 0));
        lblNewLabel.setBackground(Color.red);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblNewLabel.setBounds(778, 111, 41, 43);
        add(lblNewLabel);
        
        JLabel lblRegisteredNumberOf = new JLabel("Registered Number Of Lecturers");
        lblRegisteredNumberOf.setFont(new Font("Tekton Pro", Font.PLAIN, 15));
        lblRegisteredNumberOf.setBounds(727, 165, 215, 43);
        add(lblRegisteredNumberOf);
        
        JLabel lblRegisteredNumberOf_3 = new JLabel("Registered Number Of Groups");
        lblRegisteredNumberOf_3.setFont(new Font("Tekton Pro", Font.PLAIN, 15));
        lblRegisteredNumberOf_3.setBounds(727, 288, 215, 43);
        add(lblRegisteredNumberOf_3);
        
        JLabel lblRegisteredNumberOf_1 = new JLabel("Registered Number Of Subjects");
        lblRegisteredNumberOf_1.setFont(new Font("Tekton Pro", Font.PLAIN, 15));
        lblRegisteredNumberOf_1.setBounds(727, 425, 215, 43);
        add(lblRegisteredNumberOf_1);
        
        JLabel lblRegisteredNumberOf_2_1 = new JLabel("Registered Number Of Room ");
        lblRegisteredNumberOf_2_1.setFont(new Font("Tekton Pro", Font.PLAIN, 15));
        lblRegisteredNumberOf_2_1.setBounds(737, 544, 215, 43);
        add(lblRegisteredNumberOf_2_1);
        
        JLabel lblNewLabel_1 = new JLabel(String.valueOf(y));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBackground(new Color(255, 215, 0));
        lblNewLabel_1.setBackground(Color.red);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(789, 234, 41, 43);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel(String.valueOf(z));;
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBackground(new Color(255, 215, 0));
        lblNewLabel_2.setBackground(Color.red);
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblNewLabel_2.setBounds(789, 365, 41, 43);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel(String.valueOf(x));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBackground(new Color(255, 215, 0));
        lblNewLabel_3.setBackground(Color.red);
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblNewLabel_3.setBounds(765, 490, 84, 43);
        add(lblNewLabel_3);
        
        JLabel lblChartAboutRoom = new JLabel("Chart About Room Details ");
        lblChartAboutRoom.setFont(new Font("Minion Pro SmBd", Font.PLAIN, 18));
        lblChartAboutRoom.setBounds(258, 67, 215, 43);
        add(lblChartAboutRoom);
        
        JLabel lblLecturer = new JLabel("Lecturer");
        lblLecturer.setFont(new Font("Tekton Pro", Font.PLAIN, 15));
        lblLecturer.setBounds(190, 598, 49, 43);
        add(lblLecturer);
        
        JLabel lblMiniLecturer = new JLabel("Mini Lecturer");
        lblMiniLecturer.setFont(new Font("Tekton Pro", Font.PLAIN, 15));
        lblMiniLecturer.setBounds(338, 598, 84, 43);
        add(lblMiniLecturer);
        
        JLabel lblLabora = new JLabel("Laboratory");
        lblLabora.setFont(new Font("Tekton Pro", Font.PLAIN, 15));
        lblLabora.setBounds(498, 598, 84, 43);
        add(lblLabora);
        
 
    }

    public void addHistogramColumn(String label, int value, Color color)
    {
        Bar bar = new Bar(label, value, color);
        bars.add( bar );
    }

    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();

        int maxValue = 10;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());

        for (Bar bar: bars)
        {
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            int barHeight = (bar.getValue() * histogramHeight) / maxValue;
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }
    }

    private class Bar
    {
        private String label;
        private int value;
        private Color color;

        public Bar(String label, int value, Color color)
        {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel()
        {
            return label;
        }

        public int getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }
    }

    private class ColorIcon implements Icon
    {
        private int shadow = 3;

        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, int height)
        {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y)
        {
            g.setColor(color);
            g.fillRect(x, y, width - shadow, height);
            g.setColor(Color.GRAY);
            g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
        }
    }

    private static void createAndShowGUI() throws SQLException
    {
    	Statistics panel = new Statistics();
    	Connection con = DBConnecter.connect();
    	Statement state = con.createStatement();
    	ResultSet rs1,rs2,rs3;
    	rs1 = state.executeQuery("select count(*) from location where location.roomType = 'lecture hall' ");
    	Integer x1 = rs1.getInt("count(*)");
    	
    	rs2 = state.executeQuery("select count(*) from location where location.roomType = 'min lecture hall' ");
    	Integer x2 = rs2.getInt("count(*)");
    	
    	rs3 = state.executeQuery("select count(*) from location where location.roomType = 'laboratory' ");
    	Integer x3 = rs3.getInt("count(*)");
    	
    	
    	// (select count(*) from location) 
    	
         
        panel.addHistogramColumn("Lecturer Hall", x1, Color.YELLOW);
        panel.addHistogramColumn("Mini Lecturer Hall", x2, Color.BLUE);
        panel.addHistogramColumn("Laboratory", x3, Color.ORANGE);
     
        panel.layoutHistogram();

        JFrame frame = new JFrame("Histogram Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add( panel );
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try {
					createAndShowGUI();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
}
