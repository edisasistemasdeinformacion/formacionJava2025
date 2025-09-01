package com.edisa.formacion.mayo2025.DropWizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ExerciseQRDropWizard extends Application<ConfigurationWizard> {


    public static void main(String[] args) throws Exception {
        //ejecuto el metodo de la propia clase pasandole los argumentos
        new ExerciseQRDropWizard().run(args);
    }

    //Metodo run similar al de la clase thread, lo que ejecurara dropwizard
    @Override
    public void run(ConfigurationWizard configurationWizard, Environment environment) throws Exception {
        //Cargar los recursos
        final ExerciseQRWizardResources resource=new ExerciseQRWizardResources();
        environment.jersey().register(resource);

    }
}
