import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Ordenamientos {

    private int[] arregloPrincipal;
    private int[] arregloInsercion;
    private int[] arregloBurbuja;
    private int[] arregloQuicksort;

    private static final Logger logger = LogManager.getLogger(Ordenamientos.class);

    public Ordenamientos(int numeroUsuario) {//10000
        this.arregloPrincipal = new int[numeroUsuario];
        logger.debug("EL TAMAÑO DEL ARREGLO ORIGINAL ES: " + arregloPrincipal.length);
        arregloInsercion = new int[(int) (arregloPrincipal.length * 0.3)];//3000
        arregloBurbuja = new int[(int) (arregloPrincipal.length * 0.6 - arregloPrincipal.length * 0.3)];//3000
        arregloQuicksort = new int[(int) (arregloPrincipal.length - arregloPrincipal.length * 0.6)];//4000
        logger.debug("SUBDIVIDIDO EN 3 PARTES EL ARREGLO QUEDARIA DE ESTA MANERA");
        logger.debug("ARREGLO 1 " + arregloInsercion.length);
        logger.debug("ARREGLO 2 " + arregloBurbuja.length);
        logger.debug("ARREGLO 3 " + arregloQuicksort.length);
        logger.debug("SUMANDO DARIA EL MISMO TAMAÑO DEL ARREGLO ORIGINAL ES: " + (arregloInsercion.length + arregloBurbuja.length + arregloQuicksort.length));
        asignacionDeNumeros();
    }

    public void asignacionDeNumeros() {
        for (int i = 0; i < arregloPrincipal.length; i++) {
            arregloPrincipal[i] = (int) (Math.random() * 100000);// 0 - 100.000
        }
        divisorDeArreglos();
    }

    public void divisorDeArreglos() {
        int posicion1 = 0;
        int posicion2 = 0;
        int posicion3 = 0;
        for (int i = 0; i < arregloPrincipal.length; i++) {
            if (posicion1 < arregloInsercion.length) { // 0-3000
                if (arregloPrincipal[i] >= 0 && arregloPrincipal[i] <= 33000) {
                    arregloInsercion[posicion1] = arregloPrincipal[i];
                    posicion1++;
                }
            }
            if (posicion2 < arregloBurbuja.length) {
                if (arregloPrincipal[i] >= 33001 && arregloPrincipal[i] <= 66000) {
                    arregloBurbuja[posicion2] = arregloPrincipal[i];
                    posicion2++;
                }
            }
            if (posicion3 < arregloQuicksort.length) {
                if (arregloPrincipal[i] >= 66001 && arregloPrincipal[i] <= 100000) {
                    arregloQuicksort[posicion3] = arregloPrincipal[i];
                    posicion3++;
                }
            }
        }

        for (int i = 0; i < arregloQuicksort.length; i++) {
            if (arregloQuicksort[i] == 0) {
                int numero = (int) (Math.random() * 100000 + 66001);
                while (numero > 100000) {
                    numero = (int) (Math.random() * 100000 + 66001);
                }
                arregloQuicksort[i] = numero;
            }
        }
    }

    public void ordemientoArreglo() {
        long tiempo_inicial1 = System.nanoTime();//4
        insercion();
        long tiempo_final1 = System.nanoTime();//45
        long tiempoTotal1 = tiempo_final1 - tiempo_inicial1;//45 - 4 = 41

        long tiempo_inicial2 = System.nanoTime();
        burbuja();
        long tiempo_final2 = System.nanoTime();
        long tiempoTotal2 = tiempo_final2 - tiempo_inicial2;

        long tiempo_inicial3 = System.nanoTime();
        quicksort(arregloQuicksort, 0, arregloQuicksort.length - 1);
        long tiempo_final3 = System.nanoTime();
        long tiempoTotal3 = tiempo_final3 - tiempo_inicial3;

        logger.debug("INSERCION TARDA "+TimeUnit.MILLISECONDS.convert(tiempoTotal1, TimeUnit.NANOSECONDS) + " ms");
        logger.debug("BURBUJA TARDA "+TimeUnit.MILLISECONDS.convert(tiempoTotal2, TimeUnit.NANOSECONDS) + " ms");
        logger.debug("QUICKSORT TARDA "+TimeUnit.MILLISECONDS.convert(tiempoTotal3, TimeUnit.NANOSECONDS) + " ms");
    }

    public void mostrarArreglo1() {
        System.out.println("====================METODO INSERCION====================");
        for (int i = 0; i < arregloInsercion.length; i++) {
            System.out.print(arregloInsercion[i] + ", ");
        }
        System.out.println();
    }

    public void mostrarArreglo2() {
        System.out.println("====================METODO BURBUJA====================");
        for (int i = 0; i < arregloBurbuja.length; i++) {
            System.out.print(arregloBurbuja[i] + ", ");
        }
        System.out.println();
    }

    public void mostrarArreglo3() {
        System.out.println("====================METODO QUICKSORT====================");
        for (int i = 0; i < arregloQuicksort.length; i++) {
            System.out.print(arregloQuicksort[i] + ", ");
        }
        System.out.println();
    }


    public void insercion() {
        int posicion;
        int auxiliar;
        for (int i = 0; i < arregloInsercion.length; i++) {
            posicion = i;
            auxiliar = arregloInsercion[i];
            while (posicion > 0 && arregloInsercion[posicion - 1] > auxiliar) {
                arregloInsercion[posicion] = arregloInsercion[posicion - 1];
                posicion--;
            }
            arregloInsercion[posicion] = auxiliar;
        }
    }

    public void burbuja() {
        int auxiliar2;
        for (int i = 0; i < arregloBurbuja.length - 1; i++) {
            for (int j = 0; j < arregloBurbuja.length - 1; j++) {
                if (arregloBurbuja[j] > arregloBurbuja[j + 1]) {
                    auxiliar2 = arregloBurbuja[j];
                    arregloBurbuja[j] = arregloBurbuja[j + 1];
                    arregloBurbuja[j + 1] = auxiliar2;
                }
            }
        }
    }

    public void quicksort(int array[], int primero, int ultimo) {
        int i;
        int j;
        int pivote;
        int auxiliar;
        i = primero;
        j = ultimo;
        pivote = array[(primero + ultimo) / 2];
        do {
            while (array[i] < pivote) {
                i++;
            }
            while (array[j] > pivote) {
                j--;
            }
            if (i <= j) {
                auxiliar = array[i];
                array[i] = array[j];
                array[j] = auxiliar;
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            quicksort(array, primero, j);
        }
        if (i < ultimo) {
            quicksort(array, i, ultimo);
        }
    }


    boolean esVerdadero1 = false;
    boolean esVerdadero2 = false;
    boolean esVerdadero3 = false;

    public void arreglosOrdenados(){
        for (int i = 0; i < arregloInsercion.length; i++) {
            try {
                if(arregloInsercion[i] <= arregloInsercion[i+1]){
                    esVerdadero1 = true;
                } else {
                    esVerdadero1 = false;
                    break;
                }
            } catch(Exception ex){}
        }
        for (int i = 0; i < arregloBurbuja.length; i++) {
            try {
                if(arregloBurbuja[i] <= arregloBurbuja[i+1]){
                    esVerdadero2 = true;
                } else {
                    esVerdadero2 = false;
                    break;
                }
            } catch(Exception ex){}
        }
        for (int i = 0; i < arregloQuicksort.length; i++) {
            try {
                if(arregloQuicksort[i] <= arregloQuicksort[i+1]){
                    esVerdadero3 = true;
                } else {
                    esVerdadero3 = false;
                    break;
                }
            } catch(Exception ex){}
        }
        String mensaje1="";
        String mensaje2="";
        String mensaje3="";
        if(esVerdadero1 == true){
            mensaje1 = "EL ARREGLO 1 ESTA ORDENADO";
        } else {
            mensaje1 = "EL ARREGLO 1 NO ESTA ORDENADO";
        }
        if(esVerdadero2 == true){
            mensaje2 = "EL ARREGLO 2 ESTA ORDENADO";
        } else {
            mensaje2 = "EL ARREGLO 2 NO ESTA ORDENADO";
        }
        if(esVerdadero3 == true){
            mensaje3 = "EL ARREGLO 3 ESTA ORDENADO";
        } else {
            mensaje3 = "EL ARREGLO 3 NO ESTA ORDENADO";
        }
        JOptionPane.showMessageDialog(null, mensaje1 + " - " + mensaje2 + " - " + mensaje3);
    }
}
