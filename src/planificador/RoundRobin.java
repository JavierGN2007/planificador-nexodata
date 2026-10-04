package planificador;
import java.util.List;

public class RoundRobin implements Algoritmo {
    private int quantum;

    public RoundRobin (int quantum){
        this.quantum = quantum;
    }

    public int getQuantum(){
        return quantum;
    }

    public Proceso elegir(List<Proceso> listos){
        return listos.get(0);
    }
}
