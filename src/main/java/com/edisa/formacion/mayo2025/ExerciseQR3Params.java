package com.edisa.formacion.mayo2025;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExerciseQR3Params {

    public static Boolean checkNumberArguments(String[] ar) {
        Boolean valid = false;
        if (ar.length == 0) {
            System.out.println("Ningun parámetro proporcionado");

        } else if (ar.length > 3) {
            System.out.printf("Demasiados parametros introducidos, introducidos "+ar.length+".");

        } else {
            valid = true;
        }

        return valid;
    }

    public static BarcodeFormat getBarcodeFormat(String p_varcode){
        BarcodeFormat bf=null;
        try {

            bf=BarcodeFormat.valueOf(p_varcode);

        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Barcode format "+p_varcode+" not found");
        }
        return bf;
    }

    public static BitMatrix createQR(String txt,BarcodeFormat bcf) throws IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix matrix = null;
        try {
            matrix = qrCodeWriter.encode(txt, bcf, 500, 500);

        } catch (WriterException e) {
            throw new IOException("Error al codificar el qr");
        }
        return matrix;
    }

    public static String writeQrAsImg(BitMatrix matrix, String route) throws IOException {

        Path path = Paths.get(route);

        //Escribo la matriz como imagen
        //https://zxing.github.io/zxing/apidocs/com/google/zxing/client/j2se/MatrixToImageWriter.html
        try {
            MatrixToImageWriter.writeToPath(matrix, "jpg", path);
        } catch (IOException e) {
            throw new IOException("Cannot write in file " + route + ", error: \n");
        }

        return "Qr writen succesfully";
    }


    public static void main(String[] args) {
        if (checkNumberArguments(args)) {

            String txtencode = args[0];
            String route = args[1];
            String code=args[2];
            BarcodeFormat bcf=null;
            BitMatrix matrix = null;

            try{

                bcf=getBarcodeFormat(code);

                if(bcf!=null){


                    matrix = createQR(txtencode,bcf);

                    if (matrix != null) {
                        System.out.printf(writeQrAsImg(matrix, route));
                    }

                }


            }catch (Exception e){
                System.out.println("Error en la ejecucion, "+e.getMessage());
            }



        }
    }

}
