package com.hibernate.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hibernate.dao.SerieDAO;
import com.hibernate.model.Serie;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App {
	
	private SerieDAO serieDAO = new SerieDAO();
	private List<Serie> srs;

	private JFrame frame;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldSerie;
	private JTextField textFieldTemporadas;
	private JTextField textFieldCapitulos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
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
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 528, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultTableModel model= new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Temporadas");
		model.addColumn("Capitulos");
		
		
		srs=serieDAO.selectAllSeries();
		
		for(int i=0;i<srs.size();i++) {
			Object[] row = new Object[4];
			row[0] =srs.get(i).getId();
			row[1] =srs.get(i).getNombre();
			row[2] =srs.get(i).getTemporadas();	
			row[3] =srs.get(i).getCapitulos();
				
			
		}
			
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(32,29,333,117);
		
		frame.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setBounds(51, 176, 60, 17);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Serie:");
		lblNewLabel_1.setBounds(51, 221, 60, 17);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Temporadas:");
		lblNewLabel_2.setBounds(51, 272, 83, 17);
		frame.getContentPane().add(lblNewLabel_2);
		JLabel lblNewLabel_3 = new JLabel("Capitulos:");
		lblNewLabel_3.setBounds(51, 328, 60, 17);
		frame.getContentPane().add(lblNewLabel_3);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setBounds(152, 174, 114, 21);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldSerie = new JTextField();
		textFieldSerie.setBounds(152, 219, 114, 21);
		frame.getContentPane().add(textFieldSerie);
		textFieldSerie.setColumns(10);
		
		textFieldTemporadas = new JTextField();
		textFieldTemporadas.setBounds(152, 270, 114, 21);
		frame.getContentPane().add(textFieldTemporadas);
		textFieldTemporadas.setColumns(10);
		
		textFieldCapitulos = new JTextField();
		textFieldCapitulos.setColumns(10);
		textFieldCapitulos.setBounds(152, 326, 114, 21);
		frame.getContentPane().add(textFieldCapitulos);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		btnGuardar.setBounds(347, 186, 105, 27);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(347, 251, 105, 27);
		frame.getContentPane().add(btnActualizar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(347, 323, 105, 27);
		frame.getContentPane().add(btnBorrar);
		
		
	}
}