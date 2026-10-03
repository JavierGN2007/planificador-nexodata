package planificador;

public class Proceso {
    private String nombre;
    private int llegada;
    private int rafaga;
    private int tiempoRestante;
    private EstadoProceso estado;

    private int fin;
    private int primeraEjecucion;

    public Proceso (String nombre, int llegada, int rafaga){
        this.nombre = nombre;
        this.llegada = llegada;
        this.rafaga = rafaga;
        this.tiempoRestante = rafaga;
        this.estado = EstadoProceso.Nuevo;

        this.fin = -1;
        this.primeraEjecucion = -1;

    }

    public String getNombre(){
        return nombre;
    }

    public int getLlegada(){
        return llegada;
    }

    public int getRafaga(){
        return rafaga;
    }

    public int getTiempoRestante(){
        return tiempoRestante;
    }

    public EstadoProceso getEstado(){
        return estado;
    }

    public int getFin(){
        return fin;
    }

    public int getPrimeraEjecucion(){
        return primeraEjecucion;
    }

    public void setTiempoRestante(int tiempoRestante){
        this.tiempoRestante = tiempoRestante;
    }

    public void setEstado(EstadoProceso estado){
        this.estado = estado;
    }

    public void setFin(int fin){
        this.fin = fin;
    }

    public void setPrimeraEjecucion(int primeraEjecucion){
        this.primeraEjecucion = primeraEjecucion;
    }

    @Override
    public String toString() {
        return nombre + " - llegada: " + llegada + " - ráfaga: " + rafaga;
    }
}
