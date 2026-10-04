package planificador;

import java.util.List;

public class FCFS implements Algoritmo{
    public Proceso elegir(List<Proceso> procesosListos){
        return procesosListos.get(0);
    }
}
