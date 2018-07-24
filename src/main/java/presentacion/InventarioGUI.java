/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import aplicacion.ExcepcionInventario;
import aplicacion.InformacionPC;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ngarcia
 */
public class InventarioGUI extends JFrame {

    private static Container contenedor;
    private static CardLayout card = new CardLayout();
    private InformacionPC infoPC;
    private MenuInventario menuInventarioOficina;
    private MenuPrincipal menuPrincipal;
    private MenuConfirmacionDatosOficina menuConfirmacionDatosOficina;

    public static void main(String[] args) throws IOException {
        new InventarioGUI();
       

    }

    public InventarioGUI() {
        prepareElementos();
        prepareAcciones();
        this.setVisible(true);
    }

    private void prepareElementos() {

        infoPC = new InformacionPC();
        setTitle("Inventario Computadores Constructora Capital");
        contenedor = getContentPane();
        menuConfirmacionDatosOficina = new MenuConfirmacionDatosOficina(this);
        card.addLayoutComponent(menuConfirmacionDatosOficina, "menuConfirmacionDatosOficina");
        contenedor.add(menuConfirmacionDatosOficina);
        menuPrincipal = new MenuPrincipal(this);
        card.addLayoutComponent(menuPrincipal, "menuPrincipal");
        contenedor.add(menuPrincipal);
        menuInventarioOficina = new MenuInventario(this);
        card.addLayoutComponent(menuInventarioOficina, "menuInventarioOficina");
        contenedor.add(menuInventarioOficina);
        contenedor.setLayout(card);
        card.show(contenedor, "menuPrincipal");
        setSize(928, 680);
        centrar();

    }

    private void centrar() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
    }

    private void prepareAcciones() {
        setFocusable(true);
        addWindowListener(
                new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                salga();

            }
        }
        );
    }

    private void salga() {
        int siNo = JOptionPane.showConfirmDialog(null, "Realmente desea salir del programa?");
        if (siNo == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    public InformacionPC getInfoPC() {
        return infoPC;
    }

    public void mostrarMenuPrincipal() {
        setSize(928, 680);
        infoPC.resetearCampos();
        card.show(contenedor, "menuPrincipal");
        centrar();
    }

    public void mostrarMenuInventarioOficina() {
        setSize(1000, 700);
        menuInventarioOficina.actualizarCampos();
        card.show(contenedor, "menuInventarioOficina");
        centrar();

    }

    public void motrarMenuConfirmacionDatosOficina() {
        setSize(1000, 600);
        menuConfirmacionDatosOficina.actualizarCampos();
        card.show(contenedor, "menuConfirmacionDatosOficina");
        centrar();
    }

    public MenuInventario getMenuInventarioOficina() {
        return menuInventarioOficina;
    }

}
