package vistas;

import java.awt.BorderLayout;
import lib.Conectar;
import lib.ExcelDataConfig;
import lib.VarGlobals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import com.sun.jna.platform.win32.Shell32;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

public class Configuracion extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreProject;
	private JTextField txtPath;
	public String vID, PathReportes, PathProyecto, resp, ExcelPath, nombreproject, ProcessCenter;
	int respuesta, autoIncKeyFromFunc;
	private JTextField txtProcessCenter;
	private JTextField txtPathreportes;
	PreparedStatement PrepararSentencias;
	Connection conexionBD;
	ExcelDataConfig EscribirConfig;
	static Configuracion frame = new Configuracion();
	JButton btnSetPathProyecto, btnSiguiente, btnPathReportes, btnCrearProyecto;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		
		
		btnSetPathProyecto = new JButton("Set Path");
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
		lblPathDelProyecto.setBounds(112, 172, 113, 22);
		contentPane.add(lblPathDelProyecto);
		
		JLabel label = new JLabel("Path del Proyecto");
		label.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label.setBounds(122, 107, 97, 22);
		contentPane.add(label);
		
		txtProcessCenter = new JTextField();
		txtProcessCenter.setColumns(10);
		txtProcessCenter.setBounds(229, 174, 341, 23);
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
		

		btnCrearProyecto = new JButton("Crear Proyecto");
		btnCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		if (txtNombreProject.getText().equals("") | txtPath.getText().equals("") | txtPathreportes.getText().equals("")| txtProcessCenter.getText().equals(""))
		{
		    JOptionPane.showMessageDialog(null,"Debe completar todos los campos","",JOptionPane.PLAIN_MESSAGE);
		    txtNombreProject.requestFocus();
		}else{	
				
			    try {
			        Conectar conn = new Conectar();
			        String NuevoSt="insert into testconfig values(?,?,?,?,?)";
			        conn.PrepararSentencias=conn.conexionBD.prepareStatement(NuevoSt);
			        conn.PrepararSentencias.setString(1,null);
			        conn.PrepararSentencias.setString(2,txtNombreProject.getText());
			        nombreproject =txtNombreProject.getText();
			        conn.PrepararSentencias.setString(3,PathProyecto);
			        conn.PrepararSentencias.setString(4,txtProcessCenter.getText());
			        ProcessCenter = txtProcessCenter.getText();
			        conn.PrepararSentencias.setString(5,PathReportes);
			        conn.PrepararSentencias.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Nuevo Proyecto Creado");
			        
			        autoIncKeyFromFunc = -1;
			        ResultSet rs= conn.consulta("SELECT LAST_INSERT_ID()");

			        if (rs.next()) {
			            autoIncKeyFromFunc = rs.getInt(1);
			        } else {
			            // throw an exception from here
			        }
			              
			        CopiarAssets();

			        cancelar();
			        
			        btnSiguiente = new JButton("Siguiente");
					btnSiguiente.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							VarGlobals();
							PageObjects siguiente = new PageObjects();
							frame.setVisible(false);
							siguiente.setVisible(true);

						}
					});
					btnSiguiente.setBounds(385, 269, 89, 23);
					contentPane.add(btnSiguiente);
					btnSiguiente.setEnabled(true);
			        
			        SetExcel();
			        RespaldoAExcel();
			        
			    } catch (Exception e) {
			        System.out.println(e.getCause());
			    }
			    
			    
			    
			    
			}   
			

		
			}
		});
		btnCrearProyecto.setBounds(438, 208, 134, 23);
		contentPane.add(btnCrearProyecto);
		
		btnPathReportes = new JButton("Set Path");
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
	
		
	}
	
	public void cancelar()
	{
		txtNombreProject.setText("");
		txtPath.setText("");
		txtProcessCenter.setText("");
		txtPathreportes.setText("");
		
		txtNombreProject.setEnabled(false);
		txtPath.setEnabled(false);
		txtProcessCenter.setEnabled(false);
		txtPathreportes.setEnabled(false);
		
		btnSetPathProyecto.setEnabled(false);
		btnPathReportes.setEnabled(false);
		btnCrearProyecto.setEnabled(false);
		
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
	    
	    
	    public void VarGlobals(){
	    	
	    	VarGlobals VariableGlobal = new VarGlobals();
	    	VariableGlobal.idProject= String.valueOf(autoIncKeyFromFunc);
		    VariableGlobal.PathProyecto= PathProyecto;
		    VariableGlobal.PathReportes=PathReportes;
		    VariableGlobal.ProcessCenter= ProcessCenter;
		    VariableGlobal.nombreproject= nombreproject;	
	    	
		    System.out.println(VariableGlobal.idProject);
		    System.out.println(VariableGlobal.PathProyecto);
		    System.out.println(VariableGlobal.PathReportes);
		    System.out.println(VariableGlobal.ProcessCenter);
		    System.out.println(VariableGlobal.nombreproject);
		    

	    }
	    
	    
	    public void SetExcel(){
	    	
			// Inicializar Excel
			ExcelPath = PathProyecto + "\\DataProvider\\inputData.xlsx";
			String configuracion= "config";
			try {
				EscribirConfig = new ExcelDataConfig(ExcelPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			}
			
			try {
				EscribirConfig.WriteConfig(configuracion, 1, 1, String.valueOf(autoIncKeyFromFunc));
				EscribirConfig.WriteConfig(configuracion, 2, 1, nombreproject);
				EscribirConfig.WriteConfig(configuracion, 3, 1, PathProyecto);
				EscribirConfig.WriteConfig(configuracion, 4, 1, PathReportes);
				EscribirConfig.WriteConfig(configuracion, 5, 1, ProcessCenter);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    public void RespaldoAExcel(){
	    	
			// Inicializar Excel
	    	ExcelPath = "C:\\workspace\\Repo\\DataProvider\\inputData.xlsx";
			String configuracion= "config";
			try {
				EscribirConfig = new ExcelDataConfig(ExcelPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			}
			
			try {
				EscribirConfig.WriteConfig(configuracion, 1, 1, String.valueOf(autoIncKeyFromFunc));
				EscribirConfig.WriteConfig(configuracion, 2, 1, nombreproject);
				EscribirConfig.WriteConfig(configuracion, 3, 1, PathProyecto);
				EscribirConfig.WriteConfig(configuracion, 4, 1, PathReportes);
				EscribirConfig.WriteConfig(configuracion, 5, 1, ProcessCenter);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }	
	    
	    
}
