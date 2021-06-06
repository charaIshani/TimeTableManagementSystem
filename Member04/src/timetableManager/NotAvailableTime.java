package timetableManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class NotAvailableTime {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotAvailableTime window = new NotAvailableTime();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NotAvailableTime() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 812, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Not Vailable Time");
		lblNewLabel.setFont(new Font("Source Sans Pro Light", Font.BOLD, 18));
		lblNewLabel.setBounds(332, 11, 149, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("For Groups");
		btnNewButton.setBounds(21, 71, 120, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnForLecturer = new JButton("For Lecturer");
		btnForLecturer.setBounds(21, 136, 120, 38);
		frame.getContentPane().add(btnForLecturer);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(162, 71, 156, 38);
		frame.getContentPane().add(comboBox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(238, 127, 443, 322);
		frame.getContentPane().add(scrollPane_1);
	}
}
