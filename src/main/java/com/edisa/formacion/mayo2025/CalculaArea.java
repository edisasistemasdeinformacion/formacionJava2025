package com.edisa.formacion.mayo2025;

public class CalculaArea {


    public static void main(String[] args) {

            Double ancho = Double.parseDouble(args[0]);
            Double alto = Double.parseDouble(args[1]);
            Double area;

            try {
                if(ancho > 0){
                    System.out.println("Introduce el ancho: " + ancho);
                }
                else {
                    throw new IllegalArgumentException("El ancho no puede ser negativo.");
                }

                if(ancho > 0){
                    System.out.println("Introduce el alto: " + alto);
                }
                else {
                    throw new IllegalArgumentException("El alto no puede ser negativo.");
                }
                area = ancho * alto;
                System.out.println("El área es: " + area);
            }
            catch(IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            finally{}

    }
}
