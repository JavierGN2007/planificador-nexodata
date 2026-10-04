package planificador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Simulador {
    private List<Proceso> procesos;
    private int tiempo;
    private Proceso actual;
    private Algoritmo algoritmo;
    private int tiempoQuantum;
    private Queue<Proceso> colaRobin;

    public Simulador (List<Proceso> procesos, Algoritmo algoritmo){
        this.procesos = procesos;
        this.tiempo = 0;
        this.actual = null;
        this.algoritmo = algoritmo;
        this.tiempoQuantum = 0;
        this.colaRobin = new LinkedList<>();
    }

    public void ejecutar(){
        while (procesosPendientes()){
            Llegada();

            comprobarQuantum();

            elegirProceso();

            ejecutarUnidad();

            comprobarFin();

            tiempo++;
        }

    }

    private boolean procesosPendientes(){
        for (Proceso proceso : procesos){
            if (proceso.getEstado() !=EstadoProceso.Terminado){
                return true;
            }
        }
        return false;
    }

    private void Llegada(){
        for (Proceso proceso : procesos){
            if(proceso.getLlegada() == tiempo){
                proceso.setEstado(EstadoProceso.Listo);

                if(RoundRob()){
                    colaRobin.add(proceso);
                }
            }
        }
    }

    private List<Proceso> procesosListos(){
        List<Proceso> listos = new ArrayList<>();

        for (Proceso proceso : procesos){
            if(proceso.getEstado() == EstadoProceso.Listo) {
                listos.add(proceso);
            }
        }

        return listos;
    }

    private void elegirProceso(){
        if (actual == null){

            if(RoundRob()){
                if(!colaRobin.isEmpty()){
                    actual = colaRobin.poll();
                    actual.setEstado(EstadoProceso.Ejecución);
                }
            } else {
                List<Proceso> listos = procesosListos();

                if(!listos.isEmpty()){
                    actual = algoritmo.elegir(listos);
                    actual.setEstado(EstadoProceso.Ejecución);
                }
            }


            if (actual != null && actual.getPrimeraEjecucion() == -1){
                actual.setPrimeraEjecucion(tiempo);
            }
        }
    }

    private void ejecutarUnidad(){
        if(actual != null){
            System.out.println("t = " + tiempo + " | CPU: " + actual.getNombre());
            actual.setTiempoRestante(actual.getTiempoRestante() - 1);
        }else {
            System.out.println("t = " + tiempo + " | CPU: -");
        }

        if (RoundRob()){
            tiempoQuantum++;
        }
    }

    private void comprobarFin(){
        if(actual != null && actual.getTiempoRestante() == 0){
            actual.setEstado(EstadoProceso.Terminado);
            actual.setFin(tiempo + 1);
            tiempoQuantum = 0;
            actual = null;
        }
    }

    private boolean RoundRob(){
        return algoritmo instanceof RoundRobin;
    }

    private void comprobarQuantum(){
        if(RoundRob() && actual != null && tiempoQuantum == ((RoundRobin) algoritmo).getQuantum()){
            if (actual.getTiempoRestante() > 0){
                List<Proceso> listos = procesosListos();

                if(!listos.isEmpty()){
                    actual.setEstado(EstadoProceso.Listo);
                    colaRobin.add(actual);
                    actual = null;
                    tiempoQuantum = 0;

                }else{
                    tiempoQuantum = 0;
                }
            }
        }
    }
}


