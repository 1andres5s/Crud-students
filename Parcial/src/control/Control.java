package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Modelo;
import vista.VistaMenu;

public class Control implements ActionListener {

    public VistaMenu view;
    private Modelo model;

    public Control(VistaMenu view, Modelo model) {
        this.model = model;
        this.view = view;
        this.view.registrar.addActionListener(this);
        this.view.cambiar.addActionListener(this);
        this.view.consultar.addActionListener(this);
        this.view.generar.addActionListener(this);
        this.view.limpiar.addActionListener(this);
        this.view.eliminar.addActionListener(this);
    }

    public void iniciar() {
        view.setTitle("Registro mvc");
        view.setLocationRelativeTo(null);
    }

    public void registrar() {
        view.mensaje(model.agregar(view.codigo.getText(), view.nombre.getText()));
    }

    public void consultar() {
        view.mensaje(model.consultar(view.codigo.getText()));
        view.nombre.setText(model.nombre);
        view.nota1.setText(model.nota1);
        view.nota2.setText(model.nota2);
        view.nota3.setText(model.nota3);
        view.notaF.setText(model.notaf);
        view.mensaj.setText(model.aprobo(model.notaf));
    }

    public void cambiar() {
        model.modificar(view.codigo.getText(), view.nota1.getText(), view.nota2.getText(), view.nota3.getText());
        consultar();
      
    }

    public void generar() {
        model.generar(view.codigo.getText());
    }
    public void limpiar() {
        model.limpiar();
        view.nombre.setText(model.nombre);
        view.codigo.setText(model.codigo);
        view.nota1.setText(model.nota1);
        view.nota2.setText(model.nota2);
        view.nota3.setText(model.nota3);
        view.notaF.setText(model.notaf);
        view.mensaj.setText(null);
    }
    public void eliminar(){
        view.mensaje(model.borrar(view.codigo.getText()));
    }

    public void actionPerformed(ActionEvent e) {
        if ("Registrar alumno".equals(e.getActionCommand())) {
            registrar();
        } else if ("Consultar alumno".equals(e.getActionCommand())) {
            consultar();
        } else if ("Cambiar notas".equals(e.getActionCommand())) {
            cambiar();
        } else if ("Generar comprobante".equals(e.getActionCommand())) {
            generar();
        } else if ("Limpiar".equals(e.getActionCommand())) {
            limpiar();
        }else if ("Eliminar".equals(e.getActionCommand())) {
            eliminar();
        }
        
        System.out.println(e.getActionCommand());
    }

}
