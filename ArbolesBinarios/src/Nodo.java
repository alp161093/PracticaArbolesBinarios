
public class Nodo {
	
	public Habilidad habilidad;
	public Nodo nodoIzq;
	public Nodo nodoDer;
	
	public Nodo(Habilidad habilidad) {
        this.habilidad = habilidad;
        this.nodoIzq = null;
        this.nodoDer = null;
    }
}
