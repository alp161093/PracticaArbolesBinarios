
public class ArbolHabilidadesSecuencial {
    
	private Habilidad[] tree;
    
    public ArbolHabilidadesSecuencial(Habilidad raiz) {
        this.tree = new Habilidad[15];
        this.tree[0] = raiz;
    }
    
    public void agregarHabilidad(Habilidad padre, Habilidad habilidadIzquierda, Habilidad habilidadDerecha) {	
        addHabilidad(padre, habilidadIzquierda, habilidadDerecha);
    }
    
    //se busca el indice del padre y se añaden las habilidades hijas
    private void addHabilidad(Habilidad padre, Habilidad habilidadIzquierda, Habilidad habilidadDerecha) {
    	int indexPadre = glosarioHabilidades(padre.getNombre());
        this.tree[2*indexPadre+1] = habilidadIzquierda;
        this.tree[2*indexPadre+2] = habilidadDerecha;
    }
    
    private Habilidad generacionHabilidadIzq(Habilidad padre) {
    	Habilidad response = null;
    	switch(padre.getNombre()) {
    	case "fuegoAtaque":
    		response =  new Habilidad("Bola de Fuego", "Ataque");
    		break;
    	case "hieloAtaque":
    		response =  new Habilidad("Rayo de Hielo", "Ataque");
    		break;
		case "fuegoDefensa":
			response =  new Habilidad("Escudo de Fuego", "Defensa");
			break;
		case "hieloDefensa":
			response =  new Habilidad("Escudo de Hielo", "Defensa");
			break;
    	}
    	
    	return response;
    	
    }
    
    private Habilidad generacionHabilidadDer(Habilidad padre) {
    	
    	Habilidad response = null;
    	switch(padre.getNombre()) {
    	case "fuegoAtaque":
    		response =  new Habilidad("Flecha de Fuego", "Ataque");
    		break;
    	case "hieloAtaque":
    		response =  new Habilidad("Maza Helada", "Ataque");
    		break;
		case "fuegoDefensa":
			response =  new Habilidad("Piel de Lava", "Defensa");
			break;
		case "hieloDefensa":
			response =  new Habilidad("Coraza Helada", "Defensa");
			break;
    	}
    	
    	return response;
    }
    
    //devuelve el indice donde se encuentra la habilidad que estamos buscando
    private int glosarioHabilidades(String nombre){
        for (int i = 0; i < this.tree.length; i++) {
            if (this.tree[i] != null) {
            	if (this.tree[i].getNombre().equals(nombre))
            		return i;
            }
        }
        return -1;
    }
    
    //si no es eso  hay que ver si el padre tiene nivel >= nivelMaximo/2
    public void habilidadDisponible(Habilidad habilidad){
        if(habilidad.getNivel() >= habilidad.getMaxNivel() / 2) {
        	//la habilidad se puede desbloquear
        	Habilidad habilidadIzq = generacionHabilidadIzq(habilidad);
        	Habilidad habilidadDer = generacionHabilidadDer(habilidad);
        	if(habilidadIzq != null && habilidadDer != null)
        	{
        		agregarHabilidad(habilidad, habilidadIzq, habilidadDer);
	        	System.out.println("Se ha desbloqueado la habilidad " + habilidad.toString() + " y tienes las nuevas habilidades de :");
	        	System.out.println(habilidadIzq.toString());
	        	System.out.println(habilidadDer.toString());
	        	System.out.println();
        	}
        }
        else {
        	System.out.println("Todavía no puedes mejorar esta habilidad, sigue ganando con ella!");
        }
    }
    
    public void subirNivelHabilidad(Habilidad habilidad) {
    	int indice = glosarioHabilidades(habilidad.getNombre());
    	this.tree[indice].incrementarNivel();
    } 
    
    public Habilidad buscarHabilidad (String nombre) {
        int glosario = glosarioHabilidades(nombre);
        if (glosario != -1)
            return this.tree[glosario];
        return null;
    }
    
    
   /* public void imprimirArbol() {
        int level = 1;
        int counter = 0;
        for(int i = 0; i < this.tree.length; i++) {
            if(this.tree[i] != null)
            	System.out.println(this.tree[i] + " ");
            counter++;
            if(counter == level) {
                System.out.println();
                level *= 2;
                counter = 0;
            }
        }
    }*/
    
    
    public void imprimirArbol() {
        imprimirNodo(this.tree[0], 0);
    }
    
    public void imprimirNodo(Habilidad padre, int nivel) {
    	 if (padre != null) {
    	        // Imprimir el nombre de la habilidad con un nivel de indentación según su profundidad
    	        for (int i = 0; i < nivel; i++) {
    	            System.out.print("  ");
    	        }
    	        System.out.println(padre.toString());
    	        
    	        int indexPadre = glosarioHabilidades(padre.getNombre());
    	        // Imprimir los nodos hijos izquierdo y derecho si no son nulos
    	        if ((2*indexPadre+1) < this.tree.length ) {
    	        	if(this.tree[2*indexPadre+1] != null) 
    	        		imprimirNodo(this.tree[2*indexPadre+1], nivel + 1);
    	        
    	        }
    	        
    	        if ((2*indexPadre+2) < this.tree.length) {
    	        	if(this.tree[2*indexPadre+2] != null) 
    	        		imprimirNodo(this.tree[2*indexPadre+2], nivel + 1);
    	        } 
    	    }
    }
}
 