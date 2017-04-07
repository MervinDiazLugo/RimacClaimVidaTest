package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Conectar {
    public Connection conexionBD;
    public PreparedStatement PrepararSentencias;
    Statement stSentencias;
    ResultSet rsDatos;
    

    
    //para Conectarse a la base de datos
    public Conectar() throws ClassNotFoundException, SQLException{
 
     //se Realiza la conexion con la base de datos utilizando el nombre de la Base de Datos, Usuario y Contraseña
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost/testautomationbpm";
            conexionBD=DriverManager.getConnection(url,"testers","QaTest123!");
            stSentencias=conexionBD.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
          //  Catch devuelve los posibles errores en la conexion  
        } catch (ClassCastException ex) {
            throw ex;
            
        }catch(SQLException ex1){
        throw  ex1;
        }
    
    }
            
  //se almacena a traves de una Consulta el resutado del SQL utilizado para extraer los datos de la Tabla. 
  public ResultSet consulta(String sql) throws SQLException
  {
      try {
          //para la seleccion de datos en tabla
          rsDatos=stSentencias.executeQuery(sql);
      } catch (SQLException ex) {
          throw ex;
      }
  return rsDatos;
  }
   
    public void ejecutar (String sql) throws SQLException
  {
      try {
          //para la seleccion de datos en tabla
         stSentencias.execute(sql);
               } catch (SQLException ex) {
          throw ex;
      }

  }
  
public DefaultTableModel RetornarDatos (String SentenciaSQL) throws SQLException //DefaultTableModel temporal que se crea al momento de  ejecutar el siguiente codigo, almacena los registros que vamos a consu1tar
{
DefaultTableModel Modelo= new DefaultTableModel();
{
    try {
        ResultSet RsFormulario= consulta(SentenciaSQL); // crear el resultset que nos hará la consulta sql de nuestro formulario
        ResultSetMetaData metadatos= RsFormulario.getMetaData();// crear resultset para recorrer los registros de la tabla resultante
        int NumeroColumnas= metadatos.getColumnCount();
        Object [] etiquetas= new Object [NumeroColumnas];
        for(int i = 0; i < NumeroColumnas; i++) {
            etiquetas [i]= metadatos.getColumnLabel(i+1); //arreglo para costruir la cantidad de columnas y filas de la tabla
                    
        }
        Modelo.setColumnIdentifiers(etiquetas);
        while (RsFormulario.next()) {            
            Object[] DatosFila= new Object[Modelo.getColumnCount()];
        
        for(int i = 0; i < Modelo.getColumnCount(); i++) {
            DatosFila[i]=RsFormulario.getObject(i+1);
        }
        Modelo.addRow(DatosFila);
        }
    } catch (Exception e) {
        
        System.out.println(e.getMessage());
    }
     return Modelo; //retorna de esta funcion el resultado de registros en una tabla que vamos a llamar a traves del formulario
}

}
 

}

