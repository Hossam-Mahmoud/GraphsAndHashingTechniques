package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import Hash.ChainHashingTable;
import Hash.DHashingTable;
import Hash.Hash;
import Hash.LPHashingTable;
import Hash.PRHashingTable;
import Hash.QPHashingTable;
import Hash.Symbol;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class ReadFrame extends JFrame {

	private JPanel contentPane;
	private ArrayList<Symbol> data;
	private Hash hashTable;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadFrame frame = new ReadFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public ReadFrame() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		data = new ArrayList<Symbol>();
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton readBtn = new JButton("ReadFile");
		readBtn.setBounds(10, 32, 92, 39);
		readBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					data.clear();
					JFileChooser fc = new JFileChooser();
					fc.showOpenDialog(new JFrame());
					Scanner read = new Scanner(fc.getSelectedFile());
					while (read.hasNext()) {
						Symbol one = new Symbol(read.next(), read.next(),
								Integer.parseInt(read.next()), read.next(),
								Integer.parseInt(read.next()));
						data.add(one);
					}
					read.close();
				} catch (Exception e) {
				}

			}
		});
		contentPane.setLayout(null);
		contentPane.add(readBtn);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 772, 21);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Choose...");
		menuBar.add(mnNewMenu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 177, 772, 276);
		contentPane.add(scrollPane);

		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 15));
		scrollPane.setViewportView(textArea);

		JMenuItem mntmLinearProbing = new JMenuItem("Linear Probing");
		mntmLinearProbing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				LPHashingTable lpHashing = new LPHashingTable(data.size());
				hashTable = lpHashing;
				try {
					for (Symbol sym : data) {
						lpHashing.insert(sym);
					}
					textArea.setText(lpHashing.print());
				} catch (Exception e) {
				}
			}
		});
		mnNewMenu.add(mntmLinearProbing);

		JMenuItem mntmQuadraticProbing = new JMenuItem("Quadratic Probing");
		mntmQuadraticProbing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				QPHashingTable qpHashing = new QPHashingTable(data.size());
				hashTable = qpHashing;
				try {
					for (Symbol sym : data) {
						qpHashing.insert(sym);
					}
					textArea.setText(qpHashing.print());
				} catch (Exception e) {
				}
			}
		});
		mnNewMenu.add(mntmQuadraticProbing);

		JMenuItem mntmSeperateChaining = new JMenuItem("Seperate Chaining");
		mntmSeperateChaining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ChainHashingTable chHashing = new ChainHashingTable(data.size());
				hashTable = chHashing;
				try {
					for (Symbol sym : data) {
						chHashing.insert(sym);
					}
					textArea.setText(chHashing.print());
				} catch (Exception e) {
				}
			}
		});
		mnNewMenu.add(mntmSeperateChaining);

		JMenuItem mntmPsuedoRandomProbing = new JMenuItem(
				"Psuedo Random Probing");
		mntmPsuedoRandomProbing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PRHashingTable prHashing = new PRHashingTable(data.size());
				hashTable = prHashing;
				try {
					for (Symbol sym : data) {
						prHashing.insert(sym);
					}
					textArea.setText(prHashing.print());
				} catch (Exception e) {
				}
			}
		});
		mnNewMenu.add(mntmPsuedoRandomProbing);

		JMenuItem mntmDoubleHashing = new JMenuItem("Double Hashing");
		mntmDoubleHashing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DHashingTable dhHashing = new DHashingTable(data.size());
				hashTable = dhHashing;
				try {
					for (Symbol sym : data) {
						dhHashing.insert(sym);
					}
					textArea.setText(dhHashing.print());
				} catch (Exception e) {
				}
			}
		});
		mnNewMenu.add(mntmDoubleHashing);

		textField = new JTextField();
		textField.setBounds(259, 41, 46, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(315, 41, 46, 20);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(371, 41, 46, 20);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(427, 41, 46, 20);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(483, 41, 46, 20);
		contentPane.add(textField_4);

		JButton insertBtn = new JButton("Insert record");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Symbol sym = new Symbol(textField.getText(), textField_1
						.getText(), Integer.parseInt(textField_2.getText()),
						textField_3.getText(), Integer.parseInt(textField_4
								.getText()));
					hashTable.insert(sym);
					textArea.setText(hashTable.print());
				}catch(Exception e){
				}
			}
		});
		insertBtn.setBounds(144, 36, 105, 30);
		contentPane.add(insertBtn);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(259, 24, 46, 14);
		contentPane.add(lblName);

		JLabel lblNewLabel = new JLabel("Type");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(315, 24, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(371, 24, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblScope = new JLabel("Scope");
		lblScope.setHorizontalAlignment(SwingConstants.CENTER);
		lblScope.setBounds(427, 24, 46, 14);
		contentPane.add(lblScope);

		JLabel lblNewLabel_2 = new JLabel("Length");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(483, 24, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		final JLabel lblAverage = new JLabel("Average comparisons :");
		lblAverage.setBounds(427, 85, 197, 14);
		contentPane.add(lblAverage);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int no = hashTable.Search(textField_5.getText());
				lblAverage.setText("Average comparisons:  "+no);
			}
		});
		btnSearch.setBounds(144, 77, 105, 30);
		contentPane.add(btnSearch);

		textField_5 = new JTextField();
		textField_5.setBounds(259, 82, 158, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					hashTable.delete(textField_6.getText());
					textArea.setText(hashTable.print());
				} catch (Exception e) {
				}
			}
		});
		btnDelete.setBounds(144, 118, 105, 30);
		contentPane.add(btnDelete);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(259, 123, 158, 20);
		contentPane.add(textField_6);
		
		final JLabel label = new JLabel("");
		label.setBounds(613, 136, 120, 30);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("Avg Collisions");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					label.setText(""+hashTable.getAverage());
				}catch(Exception e){
					
				}
			}
		});
		btnNewButton.setBounds(483, 136, 105, 30);
		contentPane.add(btnNewButton);
		
		


	}
}
