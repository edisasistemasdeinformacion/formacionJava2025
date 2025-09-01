package com.edisa.formacion.mayo2025;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class APIRequestCustom {



    public static Document ObtainXMLFromApi() throws IOException, ParserConfigurationException, SAXException {
        String urlStr="https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";


        Document document=null;
        try{
            //Creo un objeto URL de la librería java.net
            URL url=new URL(urlStr);
            //Me conecto con java.net
            HttpURLConnection connetc= (HttpURLConnection) url.openConnection();

            //Si la peticion ha ido bien
            connetc.getResponseCode();

            //Obtengo la factoria del constructor de documentos
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();

            //De dicha factoria obtengo mi constructor
            DocumentBuilder builder=factory.newDocumentBuilder();

            //Genero un stream de arrays de la respuesta de la api (Mi xml)
            ByteArrayInputStream bais=new ByteArrayInputStream(connetc.getInputStream().readAllBytes());

            //Con el builder a partir de dicho flujo creo la variable de tipo document de la libreria org.w3c
            document=  builder.parse(bais);



        }catch(IOException e){
            throw new IOException("Error en la peticion del xml: \n"+e.getMessage());
        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationException("Error en la creacion del document Builder: \n"+e.getMessage());
        } catch (SAXException e) {
            throw new SAXException("Error en el parseado del bytearray con el xml a un objeto tipo document: \n"+e.getMessage());
        }

        return document;

    }





    public static HashMap<String,Double> getRatesFromXML(Document document){

        //Lista de resultados del xml
        HashMap<String,Double> currencyList=new HashMap<String,Double>();

        //Lista de elementos cube
        NodeList nodelist=document.getElementsByTagName("Cube");

        for (int i=0; i<nodelist.getLength();i++){

            //Obtengo cada uno de esos elementos
            Node cube=document.getElementsByTagName("Cube").item(i);

            //Cojo los atributos currency y rate
            Node currency=cube.getAttributes().getNamedItem("currency");
            Node rate=cube.getAttributes().getNamedItem("rate");

            //Por si alguno de los elementos cube no tiene los  atributos
            if(currency!=null&&rate!=null){
                Double rateDouble=Double.parseDouble(rate.getTextContent());

                currencyList.put(currency.getTextContent(),rateDouble);

            }

        }


        return currencyList;
    }


    public static void checkArguments(String[] args) throws IllegalArgumentException{

        if(args.length<3 || args.length>3){
            throw new IllegalArgumentException("Error en el numero de argumentos");
        }




    }

    public static void main(String[] args) {

        HashMap<String,Double> currencyList=null;
        try {

            currencyList=getRatesFromXML(ObtainXMLFromApi());
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }

        if(currencyList!=null){

            try{
                checkArguments(args);

                String origen="";
                String destino="";
                double importe;


                if(args[0].length()>3){
                    throw new IllegalArgumentException("El numero enviado como paramaetro "+0+" debe de ser un string de tamanho 3, suministrado un "+args[0]);
                }
                if(args[1].length()>3){
                    throw new IllegalArgumentException("El numero enviado como paramaetro "+1+" debe de ser un string de tamanho 3, suministrado un "+args[1]);
                }

                try{
                    importe=Double.parseDouble(args[2]);
                } catch (Exception e) {
                    throw new IllegalArgumentException("El numero enviado como paramaetro "+2+" debe de ser un double, suministrado un "+args[2]);
                }

                origen=args[0];
                destino=args[1];

                if(currencyList.containsKey(origen)&&currencyList.containsKey(destino)){

                    System.out.printf("El valor de el importe "+args[2]+" pasado de "+origen+" a "+destino+" es "+(importe*currencyList.get(destino))/currencyList.get(origen));
                }




            } catch (Exception e) {
                throw new RuntimeException(e);
            }




        }
    }
}
