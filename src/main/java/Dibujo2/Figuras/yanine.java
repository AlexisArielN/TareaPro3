package Dibujo2.Figuras;

import java.util.Scanner;

public class yanine {
    public static void main(String[] args) {
        int numero;
        int resultado = 1;
        String texto = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserte un numero");
        numero = scanner.nextInt();
        // Este for es para calcular resultado
        for (int i = numero; i > 0; i--) {
            resultado = resultado * i;// 5 * 4 = 20 * 3 = 60 * 2 = 120 * 1=120
        }
        // Este for es para hacer el siguiente texto 5 x 4 x 3 x 2 x 1 = 120
        for (int i = numero; i > 0 ; i--) {
            if(i == 1){
                texto += i+" = ";
            } else {
                texto += i+" x ";
            }
        }
        System.out.println("El resultado de " + texto + resultado);
    }
}
