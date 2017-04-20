package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import lib.Conectar;
import lib.ExcelDataConfig;
import lib.VarGlobals;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;

public class TestCases extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdProject;
	private JTextField txtProcessCenter;
	private JTextField txtNameProject;
	private JTextField txtPathProject;
	private JTextField txtPathReportes;
	Integer Id_proyecto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestCases frame = new TestCases();
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
	public TestCases() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				String ExcelPath = "C:\\workspace\\Repo\\DataProvider\\inputData.xlsx";
				String configuracion= "config";
				
				
				if (VarGlobals.PathProyecto!= null) {
					
					try {
						System.out.println("Se leyeron las variables desde la Clase");
						txtIdProject.setText(VarGlobals.idProject);
						txtNameProject.setText(VarGlobals.nombreproject);
						txtPathProject.setText(VarGlobals.PathProyecto);
						txtPathReportes.setText(VarGlobals.PathReportes);
						txtProcessCenter.setText(VarGlobals.ProcessCenter);
						Id_proyecto= Integer.valueOf(VarGlobals.idProject);
						
				        
						
					} catch (Exception f) {
						
						System.out.println(f.getMessage());
						
					}
					
				} else {
					try {
						ExcelDataConfig LeerConfig= new ExcelDataConfig(ExcelPath);

						System.out.println("Se leyeron las variables desde el Excel");
						txtIdProject.setText(LeerConfig.GetConfig(configuracion,1,1));
						txtNameProject.setText(LeerConfig.GetConfig(configuracion,2,1));
						txtPathProject.setText(LeerConfig.GetConfig(configuracion,3,1));
						txtPathReportes.setText(LeerConfig.GetConfig(configuracion,4,1));
						txtProcessCenter.setText(LeerConfig.GetConfig(configuracion,5,1));
	
				        
					} catch (Exception f) {
						System.out.println(f.getMessage());
					}
					
					
					
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNuevoTestCase = new JLabel("Nuevo Test Case");
		lblNuevoTestCase.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		lblNuevoTestCase.setBounds(375, 164, 126, 22);
		contentPane.add(lblNuevoTestCase);
		
		JLabel label_1 = new JLabel("ID del Proyecto");
		label_1.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label_1.setBounds(12, 40, 88, 22);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Nombre del Proyecto");
		label_2.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label_2.setBounds(110, 40, 126, 22);
		contentPane.add(label_2);
		
		txtIdProject = new JTextField();
		txtIdProject.setEditable(false);
		txtIdProject.setColumns(10);
		txtIdProject.setBounds(12, 65, 87, 20);
		contentPane.add(txtIdProject);
		
		JLabel label_3 = new JLabel("URL Process Center");
		label_3.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label_3.setBounds(10, 94, 113, 22);
		contentPane.add(label_3);
		
		txtProcessCenter = new JTextField();
		txtProcessCenter.setEditable(false);
		txtProcessCenter.setColumns(10);
		txtProcessCenter.setBounds(133, 97, 185, 20);
		contentPane.add(txtProcessCenter);
		
		txtNameProject = new JTextField();
		txtNameProject.setEditable(false);
		txtNameProject.setColumns(10);
		txtNameProject.setBounds(108, 65, 210, 20);
		contentPane.add(txtNameProject);
		
		txtPathProject = new JTextField();
		txtPathProject.setEditable(false);
		txtPathProject.setColumns(10);
		txtPathProject.setBounds(429, 65, 280, 20);
		contentPane.add(txtPathProject);
		
		txtPathReportes = new JTextField();
		txtPathReportes.setEditable(false);
		txtPathReportes.setColumns(10);
		txtPathReportes.setBounds(429, 97, 280, 20);
		contentPane.add(txtPathReportes);
		
		JLabel label_4 = new JLabel("Path de Reportes");
		label_4.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label_4.setBounds(328, 94, 91, 22);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Path del Proyecto");
		label_5.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label_5.setBounds(329, 62, 97, 22);
		contentPane.add(label_5);
	}
}
