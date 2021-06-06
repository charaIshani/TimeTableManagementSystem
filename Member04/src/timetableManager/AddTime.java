package timetableManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

public class AddTime {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTime window = new AddTime();
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
	public AddTime() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 859, 609);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Time");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 17));
		lblNewLabel.setBounds(307, 28, 232, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		lblDay.setBounds(33, 125, 101, 43);
		frame.getContentPane().add(lblDay);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));
		comboBox.setBounds(70, 125, 155, 43);
		frame.getContentPane().add(comboBox);
		
		JLabel lblStartingTime = new JLabel("Starting Time");
		lblStartingTime.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		lblStartingTime.setBounds(33, 198, 101, 43);
		frame.getContentPane().add(lblStartingTime);
		
		textField = new JTextField();
		textField.setBounds(132, 198, 118, 43);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblStartingTime_1 = new JLabel("Starting Time");
		lblStartingTime_1.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		lblStartingTime_1.setBounds(33, 272, 101, 43);
		frame.getContentPane().add(lblStartingTime_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(132, 272, 118, 43);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(86, 343, 118, 37);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(10, 413, 118, 37);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(157, 413, 118, 37);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(287, 135, 546, 257);
		frame.getContentPane().add(scrollPane_1);
	}
}
