package vistas;

import java.awt.BorderLayout;
import lib.Conectar;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Configuracion extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtPath;
	String vID, PathReportes, PathProyecto;
	int respuesta;
	private JTextField txtProcessCenter;
	private JTextField txtPathreportes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuracion frame = new Configuracion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Configuracion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  //Consultar en BD
		        if (txtID.getText().equals("")){
		            JOptionPane.showMessageDialog(null,"Introduzca el ID del Proyecto","",JOptionPane.PLAIN_MESSAGE);
		            txtID.requestFocus();
		        }else

		            try {
		                Conectar conexionBD= new Conectar();
		                vID= String.valueOf(txtID.getText());
		                System.out.println(vID);
		                String Cons="select * from testconfig where id="+(vID);
		                ResultSet consulta=conexionBD.consulta(Cons);
		                if(consulta.next()){
			                txtPath.setText(consulta.getString(2));

		                } else 
		                respuesta= JOptionPane.showConfirmDialog(null, "ID de proyecto no Existe", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		                if(respuesta==0){
		              
		                }else
		                	txtID.requestFocus();
		                
		            }     catch (SQLException error)
		              {
		            	  error.printStackTrace();
		                System.exit(2);
		             
		              } catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

		        
		        
		        
		        
			}
		});
		btnBuscar.setBounds(558, 13, 89, 23);
		contentPane.add(btnBuscar);
		
		txtID = new JTextField();
		txtID.setBounds(226, 44, 245, 22);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtPath = new JTextField();
		txtPath.setEnabled(false);
		txtPath.setBounds(226, 83, 245, 23);
		contentPane.add(txtPath);
		txtPath.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nuevo Test Project");
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		lblNewLabel.setBounds(275, 11, 126, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnSetPathProyecto = new JButton("Set Path");
		btnSetPathProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("Seleccionar Path");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			      txtPathreportes.setText(String.valueOf(chooser.getSelectedFile()));
			      PathProyecto=String.valueOf(chooser.getSelectedFile());
			      System.out.println(PathProyecto);
			      PathProyecto= PathProyecto.replace("\\", "\\\\");
			      System.out.println(PathProyecto);
	
			    } else {
			    	JOptionPane.showMessageDialog(null,"Debes seleccionar un directorio","",JOptionPane.PLAIN_MESSAGE);
			    }
			  }
				
			
		});
		btnSetPathProyecto.setBounds(478, 83, 89, 23);
		contentPane.add(btnSetPathProyecto);
		
		JLabel lblNombreDeProyecto = new JLabel("Nombre de Proyecto");
		lblNombreDeProyecto.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblNombreDeProyecto.setBounds(109, 42, 113, 22);
		contentPane.add(lblNombreDeProyecto);
		
		JLabel lblPathDelProyecto = new JLabel("URL Process Center");
		lblPathDelProyecto.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblPathDelProyecto.setBounds(109, 149, 113, 22);
		contentPane.add(lblPathDelProyecto);
		
		JLabel label = new JLabel("Path del Proyecto");
		label.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label.setBounds(119, 81, 97, 22);
		contentPane.add(label);
		
		txtProcessCenter = new JTextField();
		txtProcessCenter.setColumns(10);
		txtProcessCenter.setBounds(226, 151, 341, 23);
		contentPane.add(txtProcessCenter);
		
		JLabel lblNombreDelReportes = new JLabel("Path de Reportes");
		lblNombreDelReportes.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblNombreDelReportes.setBounds(125, 113, 91, 22);
		contentPane.add(lblNombreDelReportes);
		
		txtPathreportes = new JTextField();
		txtPathreportes.setEnabled(false);
		txtPathreportes.setColumns(10);
		txtPathreportes.setBounds(226, 117, 245, 23);
		contentPane.add(txtPathreportes);
		
		JButton btnCrearProyecto = new JButton("Crear Proyecto");
		btnCrearProyecto.setBounds(470, 241, 105, 23);
		contentPane.add(btnCrearProyecto);
		
		JButton btnPathReportes = new JButton("Set Path");
		btnPathReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("Seleccionar Path");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			      txtPathreportes.setText(String.valueOf(chooser.getSelectedFile()));
			      PathReportes=String.valueOf(chooser.getSelectedFile());
			      System.out.println(PathReportes);
			      PathReportes= PathReportes.replace("\\", "\\\\");
			      System.out.println(PathReportes);
	
			    } else {
			    	JOptionPane.showMessageDialog(null,"Debes seleccionar un directorio","",JOptionPane.PLAIN_MESSAGE);
			    }
			  }
				
			
		});
		btnPathReportes.setBounds(478, 117, 89, 23);
		contentPane.add(btnPathReportes);
	}

}
