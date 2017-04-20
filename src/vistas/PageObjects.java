package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mysql.jdbc.ResultSetMetaData;

import lib.Conectar;
import lib.ExcelDataConfig;
import lib.VarGlobals;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;

import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Insets;

public class PageObjects extends JFrame {

	public JPanel contentPane;
	public JTextField txtIdProject;
	public JTextField txtNameProject;
	public JTextField txtProcessCenter;
	public JTextField txtPathProject;
	public JTextField txtPathReportes;
	public JTextField txtIdObjects;
	public JTextField txtWebElement;
	public JTextField txtObjectName;
	public JTextField txtIdPage;
	private JTextField txtPrefix;
	JComboBox<String> txtPageName = new JComboBox<String>();
	JComboBox<String> txtObjectType = new JComboBox<String>();
	static PageObjects frame;
	JButton btnTerminar;
	JButton btnEmpezar;
	JButton btnAgregar;
	int autoIncKeyFromFunc, autoIncKeyFromPage, Id_proyecto;
	String ListProject, nuevaPO, Id_page, Id_Object, ObjectType, WebElement, ObjectName, PathProject;
	int estado;
	private JTable tabladatos1;
	PrintWriter writer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    frame = new PageObjects();
					frame.setVisible(true);
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PageObjects() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {

				
			}
			@Override
			public void windowOpened(WindowEvent e) {
				
				String ExcelPath = "C:\\workspace\\Repo\\DataProvider\\inputData.xlsx";
				String configuracion= "config";
				btnTerminar.setVisible(false);
				
				
				if (VarGlobals.PathProyecto!= null) {
					
					try {
						System.out.println("Se leyeron las variables desde la Clase");
						txtIdProject.setText(VarGlobals.idProject);
						txtNameProject.setText(VarGlobals.nombreproject);
						txtPathProject.setText(VarGlobals.PathProyecto);
						txtPathReportes.setText(VarGlobals.PathReportes);
						txtProcessCenter.setText(VarGlobals.ProcessCenter);
						Id_proyecto= Integer.valueOf(VarGlobals.idProject);
						
						Conectar conn = new Conectar();
						String cons="SELECT COUNT(*) AS rowcount FROM paginas";
						ResultSet rs=conn.consulta(cons);
						rs.next();
						autoIncKeyFromFunc = rs.getInt("rowcount")+1;
						txtIdPage.setText(String.valueOf(autoIncKeyFromFunc));
						
						
						Cargar_id_paginas();
						Cargar_id_WebElements();
						table();
						DesactivarObjetos();
				        
						
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
						Id_proyecto= Integer.valueOf(txtIdProject.getText());
						
						Conectar conn = new Conectar();
						String cons="SELECT COUNT(*) AS rowcount FROM paginas";
						ResultSet rs=conn.consulta(cons);
						rs.next();
						autoIncKeyFromFunc = rs.getInt("rowcount")+1;
						txtIdPage.setText(String.valueOf(autoIncKeyFromFunc));
						
						
						Cargar_id_paginas();
						Cargar_id_WebElements();
						table();
						DesactivarObjetos();
				        
					} catch (Exception f) {
						System.out.println(f.getMessage());
					}
					
					
					
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 11, 794, 641);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ObjectType = (String) txtObjectType.getSelectedItem();
				
				if (txtIdObjects.getText().equals("") || txtObjectName.getText().equals("") || txtWebElement.getText().equals("") || ObjectType==null)
				{
					
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "", JOptionPane.PLAIN_MESSAGE);
					
				}else 
				{
					try {
						Conectar conn = new Conectar();
						String NuevoSt = "insert into objects values(?,?,?,?,?,?)";
						conn.PrepararSentencias = conn.conexionBD.prepareStatement(NuevoSt);
						conn.PrepararSentencias.setInt(1, Id_proyecto);
						conn.PrepararSentencias.setInt(2, Integer.valueOf(txtIdPage.getText()));
						conn.PrepararSentencias.setString(3, null);
						conn.PrepararSentencias.setString(4, ObjectType);
						conn.PrepararSentencias.setString(5, txtObjectName.getText());
						conn.PrepararSentencias.setString(6, txtWebElement.getText());
						conn.PrepararSentencias.executeUpdate();
						txtIdObjects.setText("");
						txtWebElement.setText("");
						txtObjectName.setText("");
						ContadorObjetos();
						JOptionPane.showMessageDialog(null, "Se ha agregado el webElement", null, JOptionPane.PLAIN_MESSAGE);
						table();
						
						
						
						//JOptionPane.showMessageDialog(null, "Se ha creado la nueva Page Objects", "",JOptionPane.PLAIN_MESSAGE);
						
					} catch (HeadlessException e) {

						e.printStackTrace();
					} catch (ClassNotFoundException e) {

						e.printStackTrace();
					} catch (SQLException e) {

						e.printStackTrace();
					}
					
				}
			}
		});
		btnAgregar.setBounds(678, 277, 89, 23);
		contentPane.add(btnAgregar);
		
		txtIdProject = new JTextField();
		txtIdProject.setEditable(false);
		txtIdProject.setBounds(27, 119, 87, 20);
		contentPane.add(txtIdProject);
		txtIdProject.setColumns(10);
		
		JLabel lblIdDelProyecto = new JLabel("ID del Proyecto");
		lblIdDelProyecto.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblIdDelProyecto.setBounds(26, 94, 88, 22);
		contentPane.add(lblIdDelProyecto);
		
		JLabel label_1 = new JLabel("Nuevo Test Project");
		label_1.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		label_1.setBounds(296, 67, 126, 22);
		contentPane.add(label_1);
		
		JLabel lblNombreDelProyecto = new JLabel("Nombre del Proyecto");
		lblNombreDelProyecto.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblNombreDelProyecto.setBounds(124, 94, 126, 22);
		contentPane.add(lblNombreDelProyecto);
		
		txtNameProject = new JTextField();
		txtNameProject.setEditable(false);
		txtNameProject.setColumns(10);
		txtNameProject.setBounds(122, 119, 210, 20);
		contentPane.add(txtNameProject);
		
		JLabel label = new JLabel("URL Process Center");
		label.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label.setBounds(24, 148, 113, 22);
		contentPane.add(label);
		
		txtProcessCenter = new JTextField();
		txtProcessCenter.setEditable(false);
		txtProcessCenter.setBounds(147, 151, 185, 20);
		contentPane.add(txtProcessCenter);
		txtProcessCenter.setColumns(10);
		
		JLabel label_2 = new JLabel("Path del Proyecto");
		label_2.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label_2.setBounds(343, 116, 97, 22);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Path de Reportes");
		label_3.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		label_3.setBounds(342, 148, 91, 22);
		contentPane.add(label_3);
		
		txtPathProject = new JTextField();
		txtPathProject.setEditable(false);
		txtPathProject.setBounds(443, 119, 280, 20);
		contentPane.add(txtPathProject);
		txtPathProject.setColumns(10);
		
		txtPathReportes = new JTextField();
		txtPathReportes.setEditable(false);
		txtPathReportes.setColumns(10);
		txtPathReportes.setBounds(443, 151, 280, 20);
		contentPane.add(txtPathReportes);
		
		txtIdObjects = new JTextField();
		txtIdObjects.setEditable(false);
		txtIdObjects.setBounds(28, 278, 63, 20);
		contentPane.add(txtIdObjects);
		txtIdObjects.setColumns(10);
		
		JLabel lblIdobject = new JLabel("ID Object");
		lblIdobject.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblIdobject.setBounds(27, 252, 64, 22);
		contentPane.add(lblIdobject);
		
		txtWebElement = new JTextField();
		txtWebElement.setColumns(10);
		txtWebElement.setBounds(368, 278, 300, 20);
		contentPane.add(txtWebElement);
		
		JLabel lblXpathId = new JLabel("Web Element");
		lblXpathId.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblXpathId.setBounds(368, 252, 126, 22);
		contentPane.add(lblXpathId);
		
		JLabel lblDefinirPageObjects = new JLabel("Definir Page Objects");
		lblDefinirPageObjects.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		lblDefinirPageObjects.setBounds(311, 190, 138, 22);
		contentPane.add(lblDefinirPageObjects);
		
		
		txtObjectType.setModel(new DefaultComboBoxModel(new String[] {"Xpath", "ID", "css"}));
		txtObjectType.setBounds(101, 278, 76, 20);
		contentPane.add(txtObjectType);
		
		JLabel lblTipoDeObjeto = new JLabel("Object Type");
		lblTipoDeObjeto.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblTipoDeObjeto.setBounds(101, 252, 64, 22);
		contentPane.add(lblTipoDeObjeto);
		
		txtObjectName = new JTextField();
		txtObjectName.setColumns(10);
		txtObjectName.setBounds(188, 278, 170, 20);
		contentPane.add(txtObjectName);
		
		JLabel lblObjectName = new JLabel("Object Name");
		lblObjectName.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblObjectName.setBounds(187, 252, 81, 22);
		contentPane.add(lblObjectName);
		
		JLabel lblIdPageObjects = new JLabel("ID Page");
		lblIdPageObjects.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblIdPageObjects.setBounds(27, 219, 50, 22);
		contentPane.add(lblIdPageObjects);
		
		txtIdPage = new JTextField();
		txtIdPage.setColumns(10);
		txtIdPage.setBounds(76, 221, 64, 20);
		contentPane.add(txtIdPage);
		
		JLabel lblPageName = new JLabel("Page Name");
		lblPageName.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblPageName.setBounds(256, 223, 63, 22);
		contentPane.add(lblPageName);
		txtPageName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		txtPageName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListProject = (String) txtPageName.getSelectedItem();
				nuevaPO = ListProject;
				table();
				if (ListProject == "Nueva Page Objects") {
					
					
					
				} else if(estado==1){
					
					txtIdPage.setEditable(false);
					txtPrefix.setEditable(false);
					txtPageName.setEnabled(false);

					btnEmpezar.setVisible(false);
					btnTerminar.setVisible(true);
				}
				
				else if(estado==0){
					
					try {
						int respuesta = JOptionPane.showOptionDialog(null,
								"Se cargaran los datos de la Page Objects Existente, ¿Desea Continuar?", "Continuar",
								JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
						if (respuesta == 0) {
							txtIdPage.setEditable(false);
							txtPrefix.setEditable(false);
							txtPageName.setEnabled(false);

							cargarNameItems();

							btnEmpezar.setVisible(false);
							btnTerminar.setVisible(true);
							ActivarObjetos();
						} else {

						}

					} catch (ClassNotFoundException e1) {

						e1.printStackTrace();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
				
			}
		});
		txtPageName.setBounds(321, 223, 273, 20);
		contentPane.add(txtPageName);
		
		JLabel lblPrefijo = new JLabel("Prefijo");
		lblPrefijo.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblPrefijo.setBounds(147, 219, 43, 22);
		contentPane.add(lblPrefijo);
		
		txtPrefix = new JTextField();
		txtPrefix.setColumns(10);
		txtPrefix.setBounds(188, 222, 58, 20);
		contentPane.add(txtPrefix);
		
		btnTerminar = new JButton("Terminar");
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtIdPage.setEditable(true);
				txtPageName.setEnabled(true);
				txtPrefix.setEditable(true);
				
				btnEmpezar.setVisible(true);
		        btnTerminar.setVisible(false);
		        DesactivarObjetos();


			}
		});
		btnTerminar.setBounds(599, 221, 89, 23);
		contentPane.add(btnTerminar);
				
		btnEmpezar = new JButton("Empezar");
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ListProject = (String) txtPageName.getSelectedItem();

				if (txtIdPage.getText().equals("") | txtPrefix.getText().equals("") | ListProject == null) {
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "", JOptionPane.PLAIN_MESSAGE);
					txtNameProject.requestFocus();
				} else if (ListProject == "Nueva Page Objects") {
					nuevaPO = JOptionPane.showInputDialog("Introduzca el Nombre del Nuevo Projecto");
					if(nuevaPO == null || nuevaPO.isEmpty() || !nuevaPO.matches("[A-Za-z-0-9]*"))
				    {
						JOptionPane.showMessageDialog(null, "Por Favor Asignele un Nombre Valido", "",JOptionPane.PLAIN_MESSAGE);
				    }else {
						ListProject = nuevaPO;
						String mostrar = nuevaPO;
						
						try {
							Conectar conn = new Conectar();
							String NuevoSt = "insert into paginas values(?,?,?,?)";
							conn.PrepararSentencias = conn.conexionBD.prepareStatement(NuevoSt);
							conn.PrepararSentencias.setString(1, null);
							conn.PrepararSentencias.setString(2, txtPrefix.getText());
							conn.PrepararSentencias.setString(3, ListProject);
							conn.PrepararSentencias.setInt(4, Id_proyecto);
							conn.PrepararSentencias.executeUpdate();
							JOptionPane.showMessageDialog(null, "Se ha creado la nueva Page Objects", "",JOptionPane.PLAIN_MESSAGE);

							autoIncKeyFromFunc = -1;
							ResultSet rs = conn.consulta("SELECT LAST_INSERT_ID()");

							txtIdPage.setEditable(false);
							txtPrefix.setEditable(false);
							txtPageName.setEnabled(false);
							
							estado=1;
							cargarNameProjects();
							ActivarObjetos();
							
							
							txtPageName.addItem(mostrar);	
							txtPageName.setSelectedItem(mostrar);
							mostrar="";
							
							

							btnEmpezar.setVisible(false);
							btnTerminar.setVisible(true);
							estado= 0;	
							if (rs.next()) {
								autoIncKeyFromFunc = rs.getInt(1);
							}

						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
					

				}
			}
		});
		btnEmpezar.setBounds(599, 221, 89, 23);
		contentPane.add(btnEmpezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
		});
		scrollPane.setBounds(10, 330, 757, 192);
		contentPane.add(scrollPane);
		
		tabladatos1 = new JTable();
		tabladatos1.setFont(new Font("Tahoma", Font.BOLD, 10));
		tabladatos1.setBackground(new Color(255, 255, 255));
		tabladatos1.setForeground(new Color(0, 0, 0));
		tabladatos1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					
					
					Id_page= 		tabladatos1.getValueAt(tabladatos1.getSelectedRow(), 0).toString();
					Id_Object= 		tabladatos1.getValueAt(tabladatos1.getSelectedRow(), 1).toString();
					ObjectType= 	(String) txtObjectType.getSelectedItem();
					ObjectName = 	tabladatos1.getValueAt(tabladatos1.getSelectedRow(), 3).toString();
					WebElement= 	tabladatos1.getValueAt(tabladatos1.getSelectedRow(), 4).toString();
					
		                try {
		                    Conectar conn = new Conectar();
		                    String SentenciaModificar="update objects set type=?,name=?,xpath=? where Id_Objeto="+Id_Object+" And id_page="+Id_page;
		                    System.out.println(SentenciaModificar);
		                    conn.PrepararSentencias=conn.conexionBD.prepareStatement(SentenciaModificar);

		                    conn.PrepararSentencias.setString(1,ObjectType);
		                    conn.PrepararSentencias.setString(2,ObjectName);
		                    conn.PrepararSentencias.setString(3,WebElement);
		                    conn.PrepararSentencias.executeUpdate();
		                    
		                    table();
		                    JOptionPane.showMessageDialog(null, "Registro Actualizado");
	
		                    
		                } catch (Exception e) {
		                    System.out.println(e.getCause());
		                }


				   }
			}
		});
		scrollPane.setViewportView(tabladatos1);
		tabladatos1.setCellSelectionEnabled(true);
		tabladatos1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabladatos1.setColumnSelectionAllowed(true);
		tabladatos1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				
			}
		});
		tabladatos1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Xpath - ID - CSS", "Name", "Type"
			}
		));
		
		JButton btnConstruir = new JButton("Construir");
		btnConstruir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs;
				PathProject= txtPathProject.getText();
				ListProject = (String) txtPageName.getSelectedItem();

				if (ListProject!="Nueva Page Objects") {
					try {
						DriverManager.registerDriver(new com.mysql.jdbc.Driver());
						java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/testautomationbpm","testers", "QaTest123!");
						java.sql.Statement s = conexion.createStatement();
						String SQL = "SELECT * FROM objects WHERE id_page=" + txtIdPage.getText();
						rs = s.executeQuery(SQL);
						ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
						writer = new PrintWriter(PathProject + "\\src\\pageObjects\\"+ ListProject + ".java", "UTF-8");
						 
							writer.println("package pageObjects;");
							writer.println("import org.openqa.selenium.By;");
							writer.println("import org.openqa.selenium.WebDriver;");
							writer.println("import org.openqa.selenium.WebElement;");

							writer.println("public class "+ListProject+"{");
							writer.println("public static WebElement element = null;");
						
							while (rs.next()) {
								String nombre = rs.getString("name");
								System.out.println(nombre);
								String Tipo = rs.getString("type");
								System.out.println(Tipo);
								String xpaths = rs.getString("xpath");
								System.out.println(xpaths);
							    writer.println("public static WebElement "+ nombre + Tipo + "(WebDriver driver){");
							    writer.println("element = "+ xpaths +";");
							    writer.println("return element;");
							    writer.println("}");
							}
							
							writer.println("}");
							writer.close();
							
							JOptionPane.showMessageDialog(null, "Se ha creado", "",JOptionPane.PLAIN_MESSAGE);


					} catch (SQLException | FileNotFoundException | UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Seleccione una Page Object antes de generar el Archivo", "",JOptionPane.PLAIN_MESSAGE);
					txtPageName.requestFocus();
				}
		

				
				
			}
		});
		btnConstruir.setBounds(678, 533, 89, 23);
		contentPane.add(btnConstruir);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(1, 1, 1, 1));
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 0, 778, 23);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Test Cases");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo Caso de Prueba");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestCases testCases = new TestCases();
				frame.setVisible(false);
				testCases.setVisible(true);
			}
		});
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

	}
	
	public void cargarNameProjects() throws ClassNotFoundException, SQLException{

		Conectar conn = new Conectar();
		String cons="SELECT * FROM paginas WHERE id_project=" + Id_proyecto;
        ResultSet rs=conn.consulta(cons);
		try {
			txtPageName.removeAllItems();
			txtPageName.addItem("Nueva Page Objects");
		while(rs.next())
			{
				txtPageName.addItem(rs.getString("PageName"));
			}
		

		} catch (SQLException ex) {
			ex.getMessage();
			
		}

		}
	
	public void cargarNameItems() throws ClassNotFoundException, SQLException{

		Conectar conn = new Conectar();
		String cons="select * from paginas where PageName="+"'"+(ListProject)+"'" + "AND id_project="+Id_proyecto;
		System.out.println(cons);
        ResultSet rs=conn.consulta(cons);
        if (rs.next()) {
        	txtIdPage.setText(rs.getString(1));
        	txtPrefix.setText(rs.getString(2));
        	txtPageName.setSelectedItem(ListProject);
        	estado= 0;
        	
		}
 

		}

public void Cargar_id_WebElements() throws ClassNotFoundException, SQLException{
	
	try {
		Conectar conn = new Conectar();
		String cons="SELECT COUNT(*) AS rowcount FROM objects";
		ResultSet rs=conn.consulta(cons);
		rs.next();
		autoIncKeyFromFunc = rs.getInt("rowcount")+1;
		txtIdObjects.setText(String.valueOf(autoIncKeyFromFunc));
		cargarNameProjects();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void Cargar_id_paginas(){
	
	try {
		Conectar conn = new Conectar();
		String cons="SELECT COUNT(*) AS rowcount FROM paginas";
		ResultSet rs=conn.consulta(cons);
		rs.next();
		autoIncKeyFromPage = rs.getInt("rowcount")+1;
		txtIdPage.setText(String.valueOf(autoIncKeyFromPage));
	} catch (ClassNotFoundException e) {

		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void table(){
		Id_page= txtIdPage.getText();
		// Areglo para cargar datos a la tabla
		try {

			DefaultTableModel modelo = new DefaultTableModel();
			tabladatos1.setModel(modelo);
			
			
			//ocultar columnas

			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/testautomationbpm","testers", "QaTest123!");
			java.sql.Statement s = conexion.createStatement();
			String SQL = "SELECT * FROM objects WHERE id_page=" + String.valueOf(Integer.valueOf(autoIncKeyFromPage)-1);
			ResultSet rs = s.executeQuery(SQL);
			ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
			
			int cantidadColumnas = rsMd.getColumnCount();
			for (int i = 1; i <= cantidadColumnas; i++) {
				modelo.addColumn(rsMd.getColumnLabel(i));
			}
			
			while (rs.next()) {
				Object[] fila = new Object[cantidadColumnas];
				for (int i = 0; i < cantidadColumnas; i++) {
					fila[i] = rs.getObject(i + 1);
				}
				modelo.addRow(fila);
				
			}
			
			//Formatear Tabla
			TableColumnModel tcm = tabladatos1.getColumnModel();
			tcm.removeColumn(tcm.getColumn(0));
			
			tcm.getColumn(0).setHeaderValue("ID page");
			tcm.getColumn(0).setMinWidth(5);
			tcm.getColumn(0).setMaxWidth(50);
			tcm.getColumn(0).setPreferredWidth(50);
			tcm.getColumn(0).setWidth(50);
			
			tcm.getColumn(1).setHeaderValue("Object");
			tcm.getColumn(1).setMinWidth(5);
			tcm.getColumn(1).setMaxWidth(50);
			tcm.getColumn(1).setPreferredWidth(50);
			tcm.getColumn(1).setWidth(50);
			
			tcm.getColumn(2).setHeaderValue("Type");
			tcm.getColumn(2).setMinWidth(5);
			tcm.getColumn(2).setMaxWidth(80);
			tcm.getColumn(2).setPreferredWidth(80);
			tcm.getColumn(2).setWidth(80);
			
			tcm.getColumn(3).setHeaderValue("Name");
			tcm.getColumn(3).setMinWidth(5);
			tcm.getColumn(3).setMaxWidth(150);
			tcm.getColumn(3).setPreferredWidth(150);
			tcm.getColumn(3).setWidth(150);
			
			tcm.getColumn(4).setHeaderValue("Xpath / ID / CSS");
			tcm.getColumn(4).setMinWidth(5);
			tcm.getColumn(4).setMaxWidth(500);
			tcm.getColumn(4).setPreferredWidth(500);
			tcm.getColumn(4).setWidth(500);
			
			
			
			rs.close();
			conexion.close();
		} catch (Exception ex) {
		}

	}

public void ContadorObjetos() throws ClassNotFoundException, SQLException{
	
	Conectar conn = new Conectar();
	String cons="SELECT COUNT(*) AS rowcount FROM objects";
	ResultSet rs=conn.consulta(cons);
	rs.next();
	autoIncKeyFromFunc = rs.getInt("rowcount")+1;
	txtIdObjects.setText(String.valueOf(autoIncKeyFromFunc));
	
}

public void DesactivarObjetos(){
	
	txtObjectType.setEnabled(false);
	txtObjectName.setEnabled(false);
	txtWebElement.setEnabled(false);
	btnAgregar.setEnabled(false);
	
}

public void ActivarObjetos(){
	
	txtObjectType.setEnabled(true);
	txtObjectName.setEnabled(true);
	txtWebElement.setEnabled(true);
	btnAgregar.setEnabled(true);
	
}
}
