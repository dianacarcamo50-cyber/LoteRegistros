/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import excepciones.limiteAgotado;

/**
 *
 * @author Garme
 */
public  class procesarDatos implements Runnable{
    private int tiempoLim; 
    private DescargaListener listener;

   public interface DescargaListener {
        void onInicio();
        void onProgreso(int porcentaje);
        void onExito(String mensaje);
        void onError(String error);
    }
   
   public procesarDatos(int tiempoLim, DescargaListener listener) {
        this.tiempoLim = tiempoLim;
        this.listener = listener;
    }
   
   @Override
    public void run() {
        if (listener != null) {
            listener.onInicio();
        }

        try {
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(tiempoLim * 100); 
                
                double tiempoActual = i * (tiempoLim/ 10.0);
                if (tiempoActual > tiempoLim) {
                    throw new limiteAgotado ("La descarga supero el limite de " + tiempoLim+ " segundos");
                }

                if (listener != null) {
                    listener.onProgreso(i * 10);
                }
            }

            if (listener != null) {
                listener.onExito("Descarga completada");
            }

        } catch (limiteAgotado e) {
            if (listener != null) {
                listener.onError(e.getMessage());
            }
        } catch (InterruptedException e) {
            if (listener != null) {
                listener.onError("El proceso de descarga fue interrumpido.");
            }
            Thread.currentThread().interrupt();
        }
    }
}
