/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    public static Connection getConnection() {
        Connection con = null;
        
        String bd = "biblioteca"; // Nombre de la base de datos
        String url = "jdbc:mysql://localhost:3306/" + bd;
        String user = "root"; // Usuario de la base de datos
        String pass = ""; // Contraseña del usuario
        
        try {
            // Establecer la conexión sin cargar manualmente la clase del driver
            con = DriverManager.getConnection(url, user, pass);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar conectar a la base de datos: " + e.getMessage());
        }
        
        return con;
    }
}
    

