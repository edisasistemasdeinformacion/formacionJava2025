package com.edisa.formacion.mayo2025.DropWizard;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class ExerciseQRDropWizardLogic {

    public static BufferedImage createBufferedImage(File f) throws IOException {
        BufferedImage buffimg=null;
        try{
            buffimg= ImageIO.read(f);
        }catch(IOException e) {
            throw new IOException("Error al abrir la imagen con Image.IO creando la buffered image: \n"+e.getMessage());
        }



        return buffimg;
    }

    public static BufferedImage createBufferedImage(String barcodebase64) throws IOException {

        BufferedImage buffimg=null;

        //Con una librería propia de Java.io transformo el String con el base64 de ja imagen en array de bytes
        byte[] byteArray= Base64.getDecoder().decode(barcodebase64);

        //Paso dicho array de bytes a un flujo
        ByteArrayInputStream bais=new ByteArrayInputStream(byteArray);


        try {
            //Con la libreria Image.IO creo la buffered image que usa la libreria
            buffimg=ImageIO.read(bais);
        } catch (IOException e) {
            throw new IOException("Error al crear la buffered image:\n"+e.getMessage());
        }

        return buffimg;
    }

    public static Result[] processQR(File f) throws Exception {

        BufferedImage buffimg=null;
        BinaryBitmap bitmap=null;
        Result[] results=null;

        try {
            buffimg= createBufferedImage(f);


        } catch (IOException e) {
            throw new IOException("Error IO al cargar del archivo con la imagen a deccodificar:\n"+e);
        }
        try{

            //Uso de la clase luminanceSource que segun la descripcion
            //The purpose of this class hierarchy is to abstract different bitmap implementations across platforms into a standard interface
            BufferedImageLuminanceSource lumina=new BufferedImageLuminanceSource(buffimg);
            //Lo paso por el hibridBin binarizer
            HybridBinarizer binarizer=new HybridBinarizer(lumina);
            //Genero un bitmap a partir del binarizer
            bitmap=new BinaryBitmap(binarizer);

        } catch (Exception e) {
            throw new Exception("Error al convertir la buffered image a leer en un bitmap:\n"+e.getMessage());
        }

        try {
            results=new QRCodeMultiReader().decodeMultiple(bitmap);
        } catch (NotFoundException e) {
            throw new javassist.NotFoundException("Error al decodificar el qr:\n"+e);
        }


        return results;


    }



   public static Result[] processQR(String barcodebase64) throws Exception {

        BufferedImage buffimg=null;
        BinaryBitmap bitmap=null;
        Result[] results=null;


        try {
             buffimg= createBufferedImage(barcodebase64);


        } catch (IOException e) {
            throw new IOException("Error IO al cargar del archivo con la imagen a deccodificar:\n"+e);
        }

        try{

            //Uso de la clase luminanceSource que segun la descripcion
            //The purpose of this class hierarchy is to abstract different bitmap implementations across platforms into a standard interface
            BufferedImageLuminanceSource lumina=new BufferedImageLuminanceSource(buffimg);
            //Lo paso por el hibridBin binarizer, que es una subclase de binarizer que transforma luminance data en 1bit data
            HybridBinarizer binarizer=new HybridBinarizer(lumina);
            //Genero un bitmap a partir del binarizer
            bitmap=new BinaryBitmap(binarizer);

        } catch (Exception e) {
            throw new Exception("Error al convertir la buffered image a leer en un bitmap:\n"+e.getMessage());
        }

       try {
           results=new QRCodeMultiReader().decodeMultiple(bitmap);
       } catch (NotFoundException e) {
           throw new javassist.NotFoundException("Error al decodificar el qr:\n"+e);
       }


       return results;


    }



}
