package com.edisa.formacion.mayo2025.DropWizard;

import com.google.zxing.Result;

import java.io.File;

public class TestWizardResources {

    public static void main(String[] args)  {

         String ruta="C:\\Users\\Luis.blanco\\Desktop\\imgqr.jpg";

         File f=new File(ruta);

         if(f.exists()&&f.canRead()){
             try {
                 for (Result r:ExerciseQRDropWizardLogic.processQR(f)){
                     System.out.printf(r.getBarcodeFormat()+" "+r.getText());
                 }

             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
         }





    }
}
