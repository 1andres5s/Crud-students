/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Modelo {
    
    public static final String URL = "jdbc:mysql://localhost/notas";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static PreparedStatement ps;
    public static ResultSet rs;
    public String codigo;
    public String nombre;
    public String nota1;
    public String nota2;
    public String nota3;
    public String notaf;
    
    public static Connection getConnetion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            JOptionPane.showMessageDialog(null, "Conexion exitosa!!!");
            return con;
            
        } catch (Exception e) {
            System.out.println("error al conectar");
            System.out.println(e);
        }
        return null;
    }
    
    public String agregar(String codigo, String nombre) {
        Connection con = null;
        try {
            con = getConnetion();
            ps = con.prepareStatement("INSERT INTO alumnos (codigo, nombre) VALUES(?,?)");
            ps.setString(1, codigo);
            ps.setString(2, nombre);
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Registro guardado!!!";
                
            } else {
                return "error en el registro!!!";
            }
        } catch (Exception e) {
            System.out.println(e);
            return String.valueOf(e);
        }
        
    }
    
    public String consultar(String codigo) {
        Connection con = null;
        try {
            con = getConnetion();
            ps = con.prepareStatement("SELECT * FROM `alumnos` WHERE codigo=?");
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre");
                nota1 = rs.getString("nota1");
                nota2 = rs.getString("nota2");
                nota3 = rs.getString("nota3");
                calcular(nota1, nota2, nota3);
                return "Consulta exitosa!!!";
            } else {
                return "REGISTRO NO EXISTE";
            }
        } catch (Exception e) {
            System.out.println(e);
            
            return String.valueOf(e);
        }
        
    }
    
    public String modificar(String codig, String not1, String not2, String not3) {
        Connection con = null;
        try {
            con = getConnetion();
            ps = con.prepareStatement("UPDATE alumnos Set nota1=?,nota2=?, nota3=?, notaf=? WHERE codigo=?");
            ps.setString(1, not1);
            ps.setString(2, not2);
            ps.setString(3, not3);
            calcular(not1, not2, not3);
            ps.setString(4, notaf);
            ps.setString(5, codig);
            
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Registro Modificado";
            } else {
                return "Error al Modificar";
            }
        } catch (Exception e) {
            System.out.println(e);
            
            return String.valueOf(e);
        }
    }
    
    public void limpiar() {
        nombre = null;
        codigo = null;
        nota1 = "0.0";
        nota2 = "0.0";
        nota3 = "0.0";
        notaf = "0.0";
        
    }
    
    public void calcular(String not1, String not2, String not3) {
        double num1 = Double.parseDouble(not1) * 0.35;
        double num2 = Double.parseDouble(not2) * 0.35;
        double num3 = Double.parseDouble(not3) * 0.3;
        double fina = num1 + num2 + num3;
        fina = (double) (Math.round(fina*100.0)/100.0);
        notaf = String.valueOf(fina);
        
    }
    public String borrar(String codigo){
        Connection con = null;
        try {
            con = getConnetion();
            ps = con.prepareStatement("delete from persona where codigo=?");
            ps.setString(1, codigo);
            int res = ps.executeUpdate();

            if (res > 0) {
                limpiar();
                return "Registro Eliminado";
                
            } else {
                limpiar();
                return "Error al eliminar";
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return null;

    }
    
    public String aprobo(String nota) {
        if (Double.parseDouble(nota) >= 3.0) {
            return "aprobado";
        } else {
            return "reprobado";
        }
    }
    
    public void generar(String codigo) {
        consultar(codigo);
        String pasa;
        try {
            BufferedWriter escribir = new BufferedWriter(new FileWriter("Reporte_"+nombre + ".txt"));
            escribir.write("###REPORTE NOTAS ESTUDIANTE: " + nombre + " ###");
            escribir.newLine();
            escribir.write("Codigo del estudiante: " + codigo);
            escribir.newLine();
            
            pasa = aprobo(nota1);
            escribir.write("Nota primer corte: " + nota1 + " " + pasa);
            escribir.newLine();
            
            pasa = aprobo(nota2);
            escribir.write("Nota segundo corte: " + nota2 + " " + pasa);
            escribir.newLine();
            
            pasa = aprobo(nota3);
            escribir.write("Nota tercer corte: " + nota3 + " " + pasa);
            escribir.newLine();
            
            pasa = aprobo(notaf);
            escribir.write("Nota FINAL: " + notaf + " " + pasa);
            escribir.newLine();
            escribir.write("--------------------------------------------");
            escribir.close();
        } catch (Exception e) {
        }
        
    }
}
