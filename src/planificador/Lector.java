package planificador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lector {
    public static List<Proceso> leer(String fichero){

        List<Proceso> procesos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))){

            String linea;

            while ((linea = br.readLine()) !=null){

                if (linea.isEmpty()){
                    continue;
                }

                if (linea.startsWith("#")){
                    continue;
                }

                String[] datos = linea.split(";");

                if (datos.length !=3){
                    System.out.println("ERROR");
                    continue;
                }

                String nombre = datos[0];

                int llegada;
                int rafaga;

                try{
                    llegada = Integer.parseInt(datos[1]);
                    rafaga = Integer.parseInt(datos[2]);
                }catch (NumberFormatException e){
                    System.out.println("ERROR");
                    continue;
                }


                if (llegada < 0){
                    System.out.println("ERROR");
                    continue;
                }
                if (rafaga < 0){
                    System.out.println("ERROR");
                    continue;
                }

                Proceso proceso = new Proceso (nombre, llegada, rafaga);
                procesos.add(proceso);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return procesos;
    }


}
