package com.edisa.formacion.mayo2025;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExerciseQR {

    public static Boolean checkNumberArguments(String[] ar) {
        Boolean valid = false;
        if (ar.length == 0) {
            System.out.println("Ningun parámetro proporcionado");

        } else if (ar.length > 2) {
            System.out.printf("Demasiados parametros introducidos.");

        } else {
            valid = true;
        }

        return valid;
    }

    public static BitMatrix createQR(String txt) {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix matrix = null;
        try {
            matrix = qrCodeWriter.encode(txt, BarcodeFormat.QR_CODE, 500, 500);

        } catch (WriterException e) {
            System.out.printf("Error al codificar el qr");
        }
        return matrix;
    }

    public static String writeQrAsImg(BitMatrix matrix, String route) {

        String response = "Qr writen succesfully";

        Path path = Paths.get(route);


        //Escribo la matriz como imagen
        //https://zxing.github.io/zxing/apidocs/com/google/zxing/client/j2se/MatrixToImageWriter.html
        try {
            MatrixToImageWriter.writeToPath(matrix, "jpg", path);
        } catch (IOException e) {
            response = "Cannot write in file " + route + ", error: \n";
            throw new RuntimeException(e);
        }


        return response;
    }


    public static void main(String[] args) {
        if (checkNumberArguments(args)) {

            String txtencode = args[0];
            String route = args[1];

            BitMatrix matrix = null;


            matrix = createQR(txtencode);

            System.out.printf(matrix.toString());

            if (matrix != null) {
                System.out.printf(writeQrAsImg(matrix, route));
            }


        }
    }
}