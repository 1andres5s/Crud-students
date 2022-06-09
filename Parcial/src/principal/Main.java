/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import control.Control;
import modelo.Modelo;
import vista.VistaMenu;

/**
 *
 * @author andre
 */
public class Main {

    public static void main(String[] args) {
        Modelo md = new Modelo();
        VistaMenu vs = new VistaMenu();
        Control cd = new Control(vs, md);

        cd.iniciar();
        vs.setVisible(true);
    }

}
