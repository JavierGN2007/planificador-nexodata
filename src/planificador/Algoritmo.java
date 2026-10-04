package planificador;

import java.util.List;
import java.util.Queue;

public interface Algoritmo {
    Proceso elegir(List<Proceso> listos);

}
