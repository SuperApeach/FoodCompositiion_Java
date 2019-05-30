import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GraphicUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private NumberField textField_1;
	
	private Vector<String> column1;
	private DefaultTableModel model1;
	private Vector<String> row1;
	
	private String[][] data = {};
	DefaultTableModel model;
	
	
	JTable jt;
	
	

	
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicUI frame = new GraphicUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public GraphicUI(HashMap<String,String[]> foodMap, InsertHash foodHash) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 1200); // 100,100,750,500
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label = new JLabel("\uC2DD\uD488\uBA85");
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.weightx = 0.15;
		gbc_label.insets = new Insets(0, 0, 10, 10); //0 0 5 5
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		textField = new JTextField();
		textField.setFont(new Font("Serif", Font.PLAIN, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.weightx = 0.45;
		gbc_textField.insets = new Insets(0, 0, 10, 10);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\uC6A9\uB7C9(g)");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.weightx = 0.15;
		gbc_lblNewLabel.insets = new Insets(0, 0, 10, 10);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_1 = new NumberField();
		textField_1.setFont(new Font("Serif", Font.PLAIN, 20));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.weightx = 0.2;
		gbc_textField_1.insets = new Insets(0, 0, 10, 10);
		gbc_textField_1.gridx = 7;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\uCC3E\uAE30");
		btnNewButton.setFont(new Font("Serif", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				
				model.setNumRows(0);
				
				
				String nameT = textField.getText();
				String gramT = textField_1.getText();
				
				//  �̸� �̸� �ɷ��� ���;ߵ�. or ����ó�� �ؾߵ�.
				
				
				double gram = Double.parseDouble(gramT);
				
				ArrayList<String[]> searchRes = foodHash.search(foodMap, nameT);
				ArrayList<ArrayList<Double>> numRes = foodHash.convertNum(searchRes, gram);
				
				
				// String[][] data
				int rowL = searchRes.size();
				int colL = numRes.get(0).size() + 1;
				
				//data = new String[rowL][colL];
				data = new String[rowL][colL+1];//����
				
				String[][] result = null;
				//String[] inData = new String[colL];
				
				
				for(int i=0;i<rowL;i++) {
					data[i][0] = searchRes.get(i)[0];
					data[i][1] = textField_1.getText();//�߰�
					for(int j=0;j<colL-1;j++) { 
						data[i][j+2] = numRes.get(i).get(j).toString(); //����   ���� : data[i][j+1] = ....
					}
					
				}
				for(int i=0;i<data.length;i++) {
					model.addRow(data[i]);
				}
				
				/*
				if(textField.getText().equals("��"))
					b.setText("��");
				else
					b.setText("Action");
				*/
				
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 10, 10);
		gbc_btnNewButton.weightx = 0.25;
		gbc_btnNewButton.gridx = 9;
		gbc_btnNewButton.gridy = 1;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		
		
		
		
		
		
		
		
		
		String[] columns = {"��ǰ��","�뷮(g)","������","����","�ܹ���","����","ȸ��","ź��ȭ��","�� ���̼���","���뼺 ���̼���","�ҿ뼺 ���̼���","Į��","��","ö","Į��","��Ʈ��","��Ÿ��A"
				,"������","��Ÿī��ƾ","��Ÿ��B1","��Ÿ��B2","���̾ƽ�","��Ÿ��C"}; // �߰�
		
		
		//String[][] data = {};//{{"�͸�","450","11","203","112"}};
		
		//DefaultTableModel model = new DefaultTableModel(data, columns);
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
			
		
		jt = new JTable(model) {// new JTable(data, columns) {
			public boolean isCellEditable(int data, int columns) {
				return false;
			}
			
			public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
				Component c = super.prepareRenderer(r, data, columns);
				
				if(data % 2 == 0)
					c.setBackground(Color.WHITE);
				else
					c.setBackground(Color.LIGHT_GRAY);
				
				if(isCellSelected(data, columns))
					c.setBackground(Color.orange);
				
				return c;
			}
		};
		
		
		model.fireTableDataChanged();
		//
		
		jt.getTableHeader().setFont(new Font("Serif", Font.PLAIN, 15));
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.RIGHT);
		
		DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.LEFT);
		
		jt.getColumn("��ǰ��").setPreferredWidth(300);
		jt.getColumn("��ǰ��").setCellRenderer(celAlignCenter);
		
		jt.setRowHeight(30);
		
		
		jt.setFont(new Font("Serif", Font.PLAIN, 20));

		jt.setBackground(Color.WHITE);
		
		jt.setPreferredScrollableViewportSize(new Dimension(1400,700));
		jt.setFillsViewportHeight(true);
		
		
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(jt);
		
	//	scrollPane.add(jt);
		
		contentPane.add(scrollPane);
		
		scrollPane.setVisible(true);
		
		
	}

}
