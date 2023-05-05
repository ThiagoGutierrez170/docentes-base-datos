/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.docentes.base.de.datos;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ASUS
 */
public class registro {
    String id;
    String codigo;
    String nombre;
    String docente;
    String fecha_i;
    String fecha_c;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo=codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
    
    public String getDocente() {
        return docente;
    }
    public void setDocente(String docente) {
        this.docente=docente;
    }

    public String getFecha_i() {
        return fecha_i;
    }
    public void setFecha_i(String fecha_i) {
        this.fecha_i=fecha_i;
    }
    
    public String getFecha_c() {
        return fecha_c;
    }
    public void setFecha_c(String fecha_c) {
        this.fecha_c=fecha_c;
    }
    
    public void InsertarRegistro(JTextField paramid,JTextField paramcodigo,
            JTextField paramnombre,JTextField paramdocente,
            JTextField paramfecha_i,JTextField paramfecha_c){ 
        setId(paramid.getText());
        setCodigo(paramcodigo.getText());
        setNombre(paramnombre.getText());
        setDocente(paramdocente.getText());
        setFecha_i(paramfecha_i.getText());
        setFecha_c(paramfecha_c.getText());
        conexion objetoconexion=new conexion();
        String Consulta="INSERT INTO registro (id,codigo,nombre,docente,fecha_i,fecha_c) VALUES(?,?,?,?,?,?);";
        try{
            CallableStatement cs=objetoconexion.estlecerConexion().prepareCall(Consulta);
            cs.setString(1,getId());
            cs.setString(2, getCodigo());
            cs.setString(3, getNombre());
            cs.setString(4, getDocente());
            cs.setString(5, getFecha_i());
            cs.setString(6, getFecha_c());
            JOptionPane.showMessageDialog(null,"se creo correctamente!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Hubo un error al intentar guardar en la base de datos!"+e.toString());
        } 
    }
    public void MostrarRegistro(JTable paramtabla){
        conexion objetoConexion=new conexion();
        DefaultTableModel model=new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla= new TableRowSorter<TableModel>(model);
        paramtabla.setRowSorter(OrdenarTabla);
        String sql="";
        model.addColumn("Id");
        model.addColumn("Codigo");
        model.addColumn("Nombre");
        model.addColumn("Docente");
        model.addColumn("Fecha de inicio");
        model.addColumn("Fecha de cierre");
        paramtabla.setModel(model);
        sql="select * from registro";
        String []  datos=new String[6];
        Statement st;
        try{
            st=objetoConexion.estlecerConexion().createStatement();
            ResultSet rs =st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                model.addRow(datos);
            }
            paramtabla.setModel(model);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al mostrar los registros"+e.toString());
        }
    }
}
