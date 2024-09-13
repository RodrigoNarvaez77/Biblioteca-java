package biblioteca2;
//Utilidades
import biblioteca2.Conexion;
import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Biblioteca2 { 
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Aplicación de Registro de Clientes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Este me da la vista
            Index panelIndex = new Index();
            frame.add(panelIndex);
            frame.pack();
            frame.setVisible(true);
        });
    }
    
public boolean RegistrarLibro(String titulo,String autor,String genero,String disponible ){
    String sql = "INSERT INTO libros(titulo,autor,genero,disponible) VALUES (?,?,?,?)";
    Connection conectar;
    PreparedStatement pst;
     try
        {
            conectar = (Connection) Conexion.getConnection();
            
            pst = conectar.prepareStatement(sql);
            
            pst.setString(1, titulo);
            
            pst.setString(2, autor);
            
            pst.setString(3, genero);
            
            pst.setString(4, disponible);
            
            int i = pst.executeUpdate();
            
            if (i != 0)
            {
                JOptionPane.showMessageDialog(null,"Los datos se han guardado satisfactoriamente");
                
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error en la transaccion");
                
                return false;
            }
            
        }
    catch(SQLException e)
        {            
            
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
     
     return false;
    }
    
public boolean RegistrarCliente(String nombre,String direccion,String telefono){
 String sql = "INSERT INTO usuarios (nombre,direccion,telefono) VALUES (?,?,?) ";  
 Connection conectar;
 PreparedStatement pst;
 
 try//?
        {
            conectar = (Connection) Conexion.getConnection();
            
            pst = conectar.prepareStatement(sql);
            
            pst.setString(1, nombre);
            
            pst.setString(2, direccion);
            
            pst.setString(3, telefono);
            
            int i = pst.executeUpdate();
            
            if (i != 0)
            {
                JOptionPane.showMessageDialog(null,"Los datos se han guardado satisfactoriamente");
                
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error en la transaccion");
                
                return false;
            }
            
        }
    catch(SQLException e)
        {            
            
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
     
     return false;
 
}

public boolean AsignarLibro(String id_libro,String id_usuario,String fecha_prestamo,String fecha_devolucion){
 String sql = "INSERT INTO prestamos (id_libro,id_usuario,fecha_prestamo,fecha_devolucion,prestado) VALUES (?,?,?,?,1)";
 Connection conectar;
 PreparedStatement pst;
 try
        {
            conectar = (Connection) Conexion.getConnection();
            
          //  String id_usuario = obtenerIdUsuario();
            
            pst = conectar.prepareStatement(sql);
            
            pst.setString(1,id_libro);
            
            pst.setString(2, id_usuario);
            
            pst.setString(3, fecha_prestamo);
            
            pst.setString(4, fecha_devolucion);
            
            int i = pst.executeUpdate();
            
            if (i != 0)
            {
                JOptionPane.showMessageDialog(null,"Los datos se han guardado satisfactoriamente");
                
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error en la transaccion");
                
                return false;
            }
            
        }
    catch(SQLException e)
        {            
            
            JOptionPane.showMessageDialog(null, e.getMessage());
        }   
    
return false;
}

public boolean EstadoPrestado(){

String sql = "INSERT INTO prestamos (prestado) VALUES (1)";
Connection conectar;
PreparedStatement pst;
 try
        {
            conectar = (Connection) Conexion.getConnection();
            
          //  String id_usuario = obtenerIdUsuario();
            
            pst = conectar.prepareStatement(sql);
            int i = pst.executeUpdate();
            System.out.println("Paso");
            if (i != 0)
            {
              //  JOptionPane.showMessageDialog(null,"Los datos se han guardado satisfactoriamente");
                
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error en la transaccion");
                
                return false;
            }
            
        }
    catch(SQLException e)
        {            
            
            JOptionPane.showMessageDialog(null, e.getMessage());
        }  

    
return false;   
}

public String obtenerIdUsuario(String nombreUsuario) {
    String idUsuario = null;
    Connection conectar = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        conectar = Conexion.getConnection();
        String sql = "SELECT id FROM usuarios WHERE nombre = ?";
        pst = conectar.prepareStatement(sql);
        pst.setString(1, nombreUsuario);
        rs = pst.executeQuery();
        
        if (rs.next()) {
            idUsuario = rs.getString("id");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el usuario con nombre: " + nombreUsuario);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener ID del usuario: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conectar != null) conectar.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
        }
    }
    
    return idUsuario;
}

public String obtenerIdLibro(String nombreLibro){
    String idLibro = null;
    Connection conectar = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        conectar = Conexion.getConnection();
        String sql = "SELECT id FROM libros WHERE titulo = ?";
        pst = conectar.prepareStatement(sql);
        pst.setString(1, nombreLibro);
        rs = pst.executeQuery();
        
        if (rs.next()) {
            idLibro = rs.getString("id");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ID con nombre: " + nombreLibro);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener ID del Lilbro: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conectar != null) conectar.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
        }
    }
    
    return idLibro;
}

public String obtenerNombreLibro(String idLibro){
    String nombreLibro = null;
    Connection conectar = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        conectar = Conexion.getConnection();
        String sql = "SELECT titulo FROM libros WHERE id = ?";
        pst = conectar.prepareStatement(sql);
        pst.setString(1, idLibro);
        rs = pst.executeQuery();
        
        if (rs.next()) {
            nombreLibro = rs.getString("titulo");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el Libro con ID: " + idLibro);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener nombre del Libro: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conectar != null) conectar.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
        }
    }
    
    return nombreLibro;
}

public DefaultTableModel MostrarLibros(){
    String[] nombresColumnas = {"Titulo", "Autor", "Genero","Disponible"}; //Array de los nombres
    String[] registros = new String[4];//Array
    DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
    String sql = "SELECT * FROM libros";
    
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
            cn = Conexion.getConnection();

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    registros[0] = rs.getString("titulo");
                    registros[1] = rs.getString("autor");
                    registros[2] = rs.getString("genero");
                    registros[3] = rs.getString("disponible");
                    modelo.addRow(registros);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    return modelo;
    }

public DefaultTableModel MostrarClientes(){
    String[] nombresColumnas = {"Nombre", "Direccion", "Telefono"}; //Array
    String[] registros = new String[3];//Array
    DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
    String sql = "SELECT * FROM usuarios";
    
    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
            cn = Conexion.getConnection();

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    registros[0] = rs.getString("nombre");
                    registros[1] = rs.getString("direccion");
                    registros[2] = rs.getString("telefono");
                    modelo.addRow(registros);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    return modelo;
    }    

public void ComboboxClientes(String id,String nombre,String valor, JComboBox combo ) {
    String sql = "Select * from usuarios";
    
    Connection cn = null;
    PreparedStatement pst = null;
    Statement st;
    ResultSet rs = null;
    
    try {
            cn = Conexion.getConnection();

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();//todos los registros
                
                while (rs.next()) {
                    combo.addItem(rs.getString(valor));
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    

}

public void ComboboxLibros(String id,String titulo,String valor,JComboBox combo){
    String sql = "SELECT * FROM libros";
    Connection cn = null;
    PreparedStatement pst = null;
    Statement st;
    ResultSet rs = null;
    
    try {
        cn = Conexion.getConnection();

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();//todos los registros
                
                while (rs.next()) {
                    combo.addItem(rs.getString(valor));
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        
    } catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Error al ejecutar consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        
    }
    
}

public DefaultTableModel MostrarAsignacion(){
    String[] nombresColumnas = {"Nombre Libro", "Nombre Usuario", "Fecha Prestamo", "Fecha Devolucion","Multa"}; // Nombres correctos de las columnas
    DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas); // Inicialización del modelo
    
    String sql = "SELECT titulo, nombre, fecha_prestamo, fecha_devolucion,multa FROM prestamos " +
                 "INNER JOIN libros ON prestamos.id_libro = libros.id " +
                 "INNER JOIN usuarios ON prestamos.id_usuario = usuarios.id";

    try (Connection cn = Conexion.getConnection();
         PreparedStatement pst = cn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            String[] registros = new String[5]; // Array para almacenar los valores de cada fila
            registros[0] = rs.getString("titulo");
            registros[1] = rs.getString("nombre");
            registros[2] = rs.getString("fecha_prestamo");
            registros[3] = rs.getString("fecha_devolucion");
            registros[4] = rs.getString("multa");
            modelo.addRow(registros); // Agrega la fila al modelo
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al ejecutar consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    return modelo;
}

private void agregarMulta(String FechaFinal, int multa) {
        //System.out.println("POR AQUI IGUAL");
        Connection conectar = null;
        PreparedStatement pst = null;
        
        try {
            conectar = Conexion.getConnection(); // Obtener conexión a la base de datos
            // Consulta SQL para insertar la multa en la tabla libros
            String sql = "UPDATE prestamos SET multa = ? WHERE fecha_devolucion = ?";
            
            pst = conectar.prepareStatement(sql);
            pst.setInt(1, multa);
            pst.setString(2, FechaFinal);
            
            int resultado = pst.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Se agregó una multa de $" + multa + " para la fecha de devolución: " + FechaFinal);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la multa para la fecha de devolución: " +FechaFinal);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la multa del libro: " + e.getMessage());
        } finally {
            // Cerrar conexiones y liberar recursos
            try {
                if (pst != null) pst.close();
                if (conectar != null) conectar.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar conexiones: " + ex.getMessage());
            }
        }
    }

public List<String> ObtenerFechasDevolucion() {
    List<String> fechasDevolucion = new ArrayList<>();
    // Declaración de objetos de conexión y consulta
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
        try {
            // Establecer la conexión
            conn = Conexion.getConnection(); // Implementa tu lógica para obtener la conexión

            // Consulta SQL para obtener las fechas de devolución
            String sql = "SELECT fecha_devolucion FROM prestamos";

            // Preparar la consulta
            pstmt = conn.prepareStatement(sql);

            // Ejecutar la consulta y obtener resultados
            rs = pstmt.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                String fechaDevolucion = rs.getString("fecha_devolucion");
                fechasDevolucion.add(fechaDevolucion);
            }

        }catch (SQLException e) {
            e.printStackTrace();    
        }
        return fechasDevolucion;
}

public void VerificarYAgregarMulta(List<String> Fechas) {
       LocalDate fechaActual = LocalDate.now();
        for (String fechaStr : Fechas) {
            LocalDate fechaDevolucionLD = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
           // System.out.println("La fecha actual es: " + fechaActual);
           //System.out.println("La fecha de devolución es: " + fechaDevolucionLD);
            
            if (fechaActual.isAfter(fechaDevolucionLD)) {
                // Calcular días de retraso
                long diasRetraso = ChronoUnit.DAYS.between(fechaDevolucionLD, fechaActual);
                
                // Calcular la multa
                int multa = (int) diasRetraso * 100; // 100 pesos por día de retraso
                
                // Agregar la multa a la base de datos
                //System.out.println("Se aplicó una multa de $" + multa + " por retraso.");
                 agregarMulta(fechaStr, multa);
            } else {
                //System.out.println("No se aplica multa para la fecha de devolución: " + fechaDevolucionLD);
            }
        }
}

}


    

