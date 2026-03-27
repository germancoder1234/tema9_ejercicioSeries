package com.hibernate.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hibernate.dao.SerieDAO;
import com.hibernate.model.Serie;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App {

	SerieDAO serieDAO = new SerieDAO();
	Serie s = null;
	private List<Serie> srs;

	private DefaultTableModel model;

	private JFrame frame;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldNombre;
	private JTextField textFieldTemporadas;
	private JTextField textFieldCapitulos;

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

	public App() {
		initialize();

	}

	private void actualizarTabla() {

		srs = serieDAO.selectAllSeries();
		model.setRowCount(0);
		for (int i = 0; i < srs.size(); i++) {
			
			model.addRow(new Object[] { srs.get(i).getId(), srs.get(i).getNombre(), srs.get(i).getTemporadas(),
					srs.get(i).getCapitulos() });

		}
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 528, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Temporadas");
		model.addColumn("Capitulos");

		actualizarTabla();

		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				textFieldId.setText(model.getValueAt(index, 0).toString());
				textFieldNombre.setText(model.getValueAt(index, 1).toString());
				textFieldTemporadas.setText(model.getValueAt(index, 2).toString());
				textFieldCapitulos.setText(model.getValueAt(index, 3).toString());
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(32, 29, 333, 117);

		frame.getContentPane().add(scrollPane);

		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setBounds(51, 176, 60, 17);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
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

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(152, 219, 114, 21);
		frame.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);

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
				
				if(textFieldNombre.getText()==null) {
					JOptionPane.showMessageDialog(frame, "El nombre está vacío");	
				}
				s = new Serie(textFieldNombre.getText(), Integer.parseInt(textFieldTemporadas.getText()),
						Integer.parseInt(textFieldCapitulos.getText()));
				serieDAO.insertSerie(s);
				actualizarTabla();
				
			}
		});
		btnGuardar.setBounds(347, 186, 105, 27);
		frame.getContentPane().add(btnGuardar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			
			s =	serieDAO.selectSerieById(Integer.parseInt(textFieldId.getText()));
				serieDAO.updateSerie(s);
				
				s.setNombre(textFieldNombre.getText());
				s.setTemporadas(Integer.parseInt(textFieldTemporadas.getText()));
				s.setCapitulos(Integer.parseInt(textFieldCapitulos.getText()));
				
				
				
			serieDAO.updateSerie(s);
				actualizarTabla();

			}
		});
		btnActualizar.setBounds(347, 251, 105, 27);
		frame.getContentPane().add(btnActualizar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				serieDAO.deleteSerie(Integer.parseInt(textFieldId.getText()));
				actualizarTabla();

			}
		});
		btnBorrar.setBounds(347, 323, 105, 27);
		frame.getContentPane().add(btnBorrar);

	}

}