public class Habilidad {
    private String nombre;
    private String tipo;//Ataque, Defensa o Personaje para Raiz
    private int nivel;
    private final int maxNivel = 14;

    public Habilidad(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = (int)(Math.random()*10);
    }

    public void incrementarNivel() {
        if (nivel < maxNivel) {
            nivel++;
        }
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public int getMaxNivel() {
        return maxNivel;
    }

    public void setNivel(int nivel){
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return nombre + " (Nivel " + nivel + "/" + maxNivel + "): " +  tipo;
    }
}
