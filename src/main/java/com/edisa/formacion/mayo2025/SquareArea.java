package com.edisa.formacion.mayo2025;

public class SquareArea {

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

    public static void main(String[] args) {
        double side1,side2;


        try{
            //Compruebo el numero de argumentos es el correcto
            if(args.length==2){

                try{
                    //Checkeo que los argumentos suministrados sean validos
                    side1= checkArgument(args[0],0);
                    side2=checkArgument(args[1],1);

                }catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Error, los argumentos deben de ser doubles validos.\n"+e.getMessage());
                }catch (Exception e){
                    throw new Exception("Excepcion por error desconocido:"+e.getMessage());
                }

                //Una vez se son validos calculo el area y muestro por consola
                System.out.println("El area del rectángulo de lados "+side1+" , "+side2+" es "+side1*side2);


            }else if(args.length<2){
                System.out.println("El programa debe tener 2 argumentos, faltan "+(2-args.length)+" argumentos. \n" +
                        "Ejemplo de como deberia haberse lanzado el programa compilado:\n" +
                        "java SquareArea 1 1");
            }

        } catch (Exception e) {
            System.out.println("Error al ejecutar el programa SquareArea: \n"+e.getMessage());
        }

    }
}
