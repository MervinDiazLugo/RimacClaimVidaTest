package vistas;

import java.awt.BorderLayout;
import lib.Conectar;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Configuracion extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreProject;
	private JTextField txtPath;
	String vID, PathReportes, PathProyecto,DataProviderPath, resp;
	int respuesta;
	private JTextField txtProcessCenter;
	private JTextField txtPathreportes;
	PreparedStatement PrepararSentencias;
	Connection conexionBD;
	private JTextField txtDataProvider;

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
		        if (txtNombreProject.getText().equals("")){
		            JOptionPane.showMessageDialog(null,"Introduzca el ID del Proyecto","",JOptionPane.PLAIN_MESSAGE);
		            txtNombreProject.requestFocus();
		        }else

		            try {
		                Conectar conexionBD= new Conectar();
		                vID= String.valueOf(txtNombreProject.getText());
		                System.out.println(vID);
		                String Cons="select * from testconfig where id="+(vID);
		                ResultSet consulta=conexionBD.consulta(Cons);
		                if(consulta.next()){
			                txtPath.setText(consulta.getString(2));

		                } else 
		                respuesta= JOptionPane.showConfirmDialog(null, "ID de proyecto no Existe", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		                if(respuesta==0){
		              
		                }else
		                	txtNombreProject.requestFocus();
		                
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
		btnBuscar.setBounds(296, 290, 89, 23);
		contentPane.add(btnBuscar);
		
		txtNombreProject = new JTextField();
		txtNombreProject.setBounds(229, 70, 245, 22);
		contentPane.add(txtNombreProject);
		txtNombreProject.setColumns(10);
		
		txtPath = new JTextField();
		txtPath.setEnabled(false);
		txtPath.setBounds(229, 109, 245, 23);
		contentPane.add(txtPath);
		txtPath.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nuevo Test Project");
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		lblNewLabel.setBounds(278, 37, 126, 22);
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
			      txtPath.setText(String.valueOf(chooser.getSelectedFile()));
			      PathProyecto=String.valueOf(chooser.getSelectedFile());
			      PathProyecto= PathProyecto.replace("\\", "\\\\");
			      ComprobarDirectorio();
	
			    } else {
			    	JOptionPane.showMessageDialog(null,"Debes seleccionar un directorio","",JOptionPane.PLAIN_MESSAGE);
			    }
			    
			    
			  }
				

		});
		btnSetPathProyecto.setBounds(481, 109, 89, 23);
		contentPane.add(btnSetPathProyecto);
		
		JLabel lblNombreDeProyecto = new JLabel("Nombre de Proyecto");
		lblNombreDeProyecto.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblNombreDeProyecto.setBounds(112, 68, 113, 22);
		contentPane.add(lblNombreDeProyecto);
		
		JLabel lblPathDelProyecto = new JLabel("URL Process Center");
		lblPathDelProyecto.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblPathDelProyecto.setBounds(106, 209, 113, 22);
		contentPane.add(lblPathDelProyecto);
		
		JLabel label = new JLabel("Path del Proyecto");
		label.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label.setBounds(122, 107, 97, 22);
		contentPane.add(label);
		
		txtProcessCenter = new JTextField();
		txtProcessCenter.setColumns(10);
		txtProcessCenter.setBounds(229, 211, 341, 23);
		contentPane.add(txtProcessCenter);
		
		JLabel lblNombreDelReportes = new JLabel("Path de Reportes");
		lblNombreDelReportes.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblNombreDelReportes.setBounds(128, 139, 91, 22);
		contentPane.add(lblNombreDelReportes);
		
		txtPathreportes = new JTextField();
		txtPathreportes.setEnabled(false);
		txtPathreportes.setColumns(10);
		txtPathreportes.setBounds(229, 143, 245, 23);
		contentPane.add(txtPathreportes);
		
		JButton btnCrearProyecto = new JButton("Crear Proyecto");
		btnCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		if (txtNombreProject.getText().equals("") | txtPath.getText().equals("") | txtPathreportes.getText().equals("")| txtProcessCenter.getText().equals(""))
		{
		    JOptionPane.showMessageDialog(null,"Debe completar todos los campos","",JOptionPane.PLAIN_MESSAGE);
		    txtNombreProject.requestFocus();
		}else{	
				
			    try {
			        Conectar conn = new Conectar();
			        String NuevoSt="insert into testconfig values(?,?,?,?,?,?)";
			        conn.PrepararSentencias=conn.conexionBD.prepareStatement(NuevoSt);
			        conn.PrepararSentencias.setString(1,null);
			        conn.PrepararSentencias.setString(2,txtNombreProject.getText());
			        conn.PrepararSentencias.setString(3,PathProyecto);
			        conn.PrepararSentencias.setString(4,txtProcessCenter.getText());
			        conn.PrepararSentencias.setString(5,PathReportes);
			        conn.PrepararSentencias.setString(6,DataProviderPath);
			        conn.PrepararSentencias.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Nuevo Proyecto Creado");
			        
			        CopiarAssets();
			        
			        cancelar();
			        
			    } catch (Exception e) {
			        System.out.println(e.getCause());
			    }
			}   
		

			}
		});
		btnCrearProyecto.setBounds(436, 245, 134, 23);
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
			      PathReportes= PathReportes.replace("\\", "\\\\");
	
			    } else {
			    	JOptionPane.showMessageDialog(null,"Debes seleccionar un directorio","",JOptionPane.PLAIN_MESSAGE);
			    }
			  }
				
			
		});
		btnPathReportes.setBounds(481, 143, 89, 23);
		contentPane.add(btnPathReportes);
		
		JLabel lblPathDeDataprovider = new JLabel("Path de DataProvider");
		lblPathDeDataprovider.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblPathDeDataprovider.setBounds(106, 175, 119, 22);
		contentPane.add(lblPathDeDataprovider);
		
		txtDataProvider = new JTextField();
		txtDataProvider.setEnabled(false);
		txtDataProvider.setColumns(10);
		txtDataProvider.setBounds(229, 177, 245, 23);
		contentPane.add(txtDataProvider);
		
		JButton btnDataProvider = new JButton("Set Path");
		btnDataProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("EXCEL FILES", "xlsx", "xls");
				chooser.setFileFilter(filter);
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("Seleccionar Path");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			      txtDataProvider.setText(String.valueOf(chooser.getSelectedFile()));
			      DataProviderPath=String.valueOf(chooser.getSelectedFile());
			      DataProviderPath= PathReportes.replace("\\", "\\\\");
	
			    } else {
			    	JOptionPane.showMessageDialog(null,"Debes seleccionar un directorio","",JOptionPane.PLAIN_MESSAGE);
			    }
			  }
			
		});
		btnDataProvider.setBounds(481, 177, 89, 23);
		contentPane.add(btnDataProvider);
	}
	
	public void cancelar()
	{
		txtNombreProject.setText("");
		txtPath.setText("");
		txtProcessCenter.setText("");
		txtPathreportes.setText("");
		txtDataProvider.setText("");
		
	}
	

	    public void ComprobarDirectorio()
	    {

		File file = new File(PathProyecto);

		if(file.isDirectory()){

			if(file.list().length>0){

				int respuesta = JOptionPane.showOptionDialog(null,"El directorio seleccionado no esta vacio, ¿Desea Continuar?", "Directorio en uso", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
								if(respuesta == 0 ){
									
								}else{
									txtPath.setText("");
								}
			}
		}
	    }
	    
	    public void CopiarAssets()
	    {
			File source = new File("C:\\workspace\\Repo\\AssetsTest");
			File dest = new File(PathProyecto);
			try {
			    FileUtils.copyDirectory(source, dest);
			    
			    
			} catch (IOException e) {
			    e.printStackTrace();
			}
	    	
	    }
}
