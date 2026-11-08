/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import vista.ProcesamientoForm;
import modelo.procesarDatos;
import javax.swing.SwingUtilities;

/**
 *
 * @author Garme
 */
public class procesamientoControl {
    private ProcesamientoForm vista;

    public procesamientoControl(ProcesamientoForm vista) {
        this.vista = vista;
    }

    public void iniciarSimulacion(int tiempoLim) {
        procesarDatos.DescargaListener listener = new procesarDatos.DescargaListener() {
            @Override
            public void onInicio() {
                System.out.println("Estado: Proceso iniciado...");
            }

            @Override
            public void onProgreso(int porcentaje) {
                System.out.println("Progreso: " + porcentaje + "%");
            }

            @Override
            public void onExito(String mensaje) {
                System.out.println("Resultado: " + mensaje);
            }

            @Override
            public void onError(String error) {
                System.err.println("Error: " + error);
            }
        };

        procesarDatos tarea = new procesarDatos (tiempoLim, listener);
        new Thread(tarea).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProcesamientoForm form = new ProcesamientoForm();
            procesamientoControl controlador = new procesamientoControl(form);
            
            controlador.iniciarSimulacion(3);
            
            form.setVisible(true);
        });
    }
}