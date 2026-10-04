package planificador;

import java.util.List;

public class SJF implements Algoritmo{
    public Proceso elegir(List<Proceso> procesosListos){
        Proceso elegido = procesosListos.get(0);

        for (Proceso proceso : procesosListos){
            if(proceso.getRafaga() < elegido.getRafaga()){
                elegido = proceso;
            }
        }

        return elegido;
    }

}
