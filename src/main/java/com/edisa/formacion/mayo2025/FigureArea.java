package com.edisa.formacion.mayo2025;

import java.util.Arrays;

public class FigureArea {

    public static final String[] avaliableFig=new String[] {"circulo","rectangulo"};


    public static Double checkArgument(String argument,int number) throws IllegalArgumentException{
        Double res=null;
        try{
            res= Double.parseDouble(argument);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El numero enviado como paramaetro "+number+" debe de ser un double, suministrado un "+argument);
        }
        if(res<=0){
            throw new IllegalArgumentException("El valor suministrado como parametro "+number+" debe de ser un double mayor a 0 para ser valido, valor suministrado:"+argument);
        }

        return res;
    }

    public static void checkNumberArguments(String[] ar){
        boolean valid=false;
        if(ar.length==0) {
            throw new IllegalArgumentException("Ningun parámetro proporcionado");

        }else{

            if (ar[0].trim().equals(avaliableFig[0])){
                if(ar.length<2){
                    throw new IllegalArgumentException("Figura escogida el circulo pero radio no suminstrado");
                }else{
                    System.out.println("Figura escogida el circulo,cantidad de argumentos correcta :)");
                }

            }else if (ar[0].trim().equals(avaliableFig[1])){
                if(ar.length<3){
                    throw new IllegalArgumentException("Figura escogida el rectangulo pero radio no suminstrado");
                }else{
                    System.out.println("Figura escogida el rectangulo,cantidad de argumentos correcta :)");
                }
            }else{
                throw new IllegalArgumentException("Figura escogida como parametro no valida. Suminstrado "+ar[0].trim()+". Parametros validos: " +
                        Arrays.toString(avaliableFig));
            }

        }
    }

    public static void main(String[] args) {
        try{

            checkNumberArguments(args);

            if(args[0].trim().equals(avaliableFig[0])){
                Double radio;
                radio=checkArgument(args[1],1);
                System.out.println("El area del circulo de radio "+radio+" es:"+Math.PI*Math.pow(radio,2));
            }else if(args[0].trim().equals(avaliableFig[1])){
                Double l1,l2;
                l1=checkArgument(args[1],1 );
                l2=checkArgument(args[2],2 );
                System.out.println("El area del rectangulo de lados "+l1+" y "+l2+" es: "+l1*l2);
            }



        } catch (Exception e) {
            System.out.println("Error al ejecutar el programa FigureArea: \n"+e.getMessage());
        }


    }
}
