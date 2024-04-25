import java.util.LinkedList;
import java.util.Queue;

public class ArbolHabilidadesDinamico {
    private Nodo raiz;

    public ArbolHabilidadesDinamico(Habilidad raiz) {
        this.raiz = new Nodo(raiz);
    }

    // Método para agregar habilidades en el árbol. Este es un ejemplo básico y debe ser expandido.
    // Aquí deberías implementar la lógica para encontrar al padre y agregar las habilidades hijas.
    public void agregarHabilidad(String nombre, Habilidad habilidadIzquierda, Habilidad habilidadDerecha) {
    	// Buscar el nodo padre con el nombre proporcionado
        Nodo padre = buscarNodoPorNombre(raiz, nombre);

        // Si no se encuentra el nodo padre, la adición no es posible
        if (padre == null) {
            System.out.println("No se pudo encontrar el nodo padre con el nombre: " + nombre);
            return;
        }

        // Crear nodos para las habilidades hijas
        Nodo nodoIzquierdo = new Nodo(habilidadIzquierda);
        Nodo nodoDerecho = new Nodo(habilidadDerecha);

        // Asignar los nodos hijas al nodo padre
        padre.nodoIzq = nodoIzquierdo;
        padre.nodoDer = nodoDerecho;
    }
    
    private Nodo buscarNodoPorNombre(Nodo actual, String nombre) {
        if (actual == null) {
            return null; // El nodo actual es nulo
        }

        // Si el nombre coincide con el nombre de la habilidad del nodo actual, lo devolvemos
        if (actual.habilidad.getNombre().equals(nombre)) {
            return actual;
        }

        // Buscar en el subárbol izquierdo
        Nodo nodoIzquierdo = buscarNodoPorNombre(actual.nodoIzq, nombre);
        if (nodoIzquierdo != null) {
            return nodoIzquierdo; // Si se encuentra en el subárbol izquierdo, lo devolvemos
        }

        // Buscar en el subárbol derecho
        return buscarNodoPorNombre(actual.nodoDer, nombre);
    }
    
    //funcion que si la habilidad no tiene la mitad del nivel maximo no se pueden añadir hijos y por lo tanto estaría bloqueda
    public void habilidadDisponible(Habilidad habilidad){
        if(habilidad.getNivel() >= habilidad.getMaxNivel()/2) {
        	//la habilidad se puede desbloquear
        	Habilidad habilidadIzq = generacionHabilidadIzq(habilidad);
        	Habilidad habilidadDer = generacionHabilidadDer(habilidad);
        	//Una vez creadas las nuevas habilidades buscamos si estan en el arbol ya metidas, para no pisar las que tenemos
        	Habilidad comprobacionHabilidadIzq = buscarHabilidadIRD(habilidadIzq.getNombre());
        	Habilidad comprobacionHabilidadDer = buscarHabilidadIRD(habilidadDer.getNombre());
        	if(comprobacionHabilidadDer == null && comprobacionHabilidadIzq == null) {
        		//al ser null ambos significa que no se encuentran en el arbol por lo tanto procedemos a intentar meterlos
        		if(habilidadIzq != null && habilidadDer != null) {
    	        	agregarHabilidad(habilidad.getNombre(), habilidadIzq, habilidadDer);
    	        	System.out.println("Se ha desbloqueado la habilidad " + habilidad.toString() + " y tienes las nuevas habilidades de :");
            		System.out.println(habilidadIzq.toString());
                	System.out.println(habilidadDer.toString());
                	System.out.println();
            	}
        	} 
        	
        }
        else {
        	System.out.println("Todavía no puedes mejorar esta habilidad, sigue ganando con ella!");
        }

    }
    

    private Habilidad generacionHabilidadIzq(Habilidad padre) {
    	Habilidad response = null;
    	switch(padre.getNombre()) {
    	case "fuegoAtaque":
    		response =  new Habilidad("Flecha Ardiente", "Ataque");
    		break;
    	case "hieloAtaque":
    		response =  new Habilidad("Flecha gelida", "Ataque");
    		break;
		case "fuegoDefensa":
			response =  new Habilidad("Barrera de llamas", "Defensa");
			break;
		case "hieloDefensa":
			response =  new Habilidad("Barricada helada", "Defensa");
			break;
    	}
    	
    	return response;
    	
    }
    
    private Habilidad generacionHabilidadDer(Habilidad padre) {
    	
    	Habilidad response = null;
    	switch(padre.getNombre()) {
    	case "fuegoAtaque":
    		response =  new Habilidad("Llamarada elfica", "Ataque");
    		break;
    	case "hieloAtaque":
    		response =  new Habilidad("Lanza fria", "Ataque");
    		break;
		case "fuegoDefensa":
			response =  new Habilidad("Manto abrasador", "Defensa");
			break;
		case "hieloDefensa":
			response =  new Habilidad("Refugio gelido", "Defensa");
			break;
    	}
    	
    	return response;
    }
    
    
    //funcion que si la habilidad no tiene la mitad del nivel maximo no se pueden añadir hijos y por lo tanto estaría bloqueda
    public void subirNivelHabilidad(String nombre){
    	buscaYMejoraHabilidad(this.raiz, nombre);
    }
    
    private void buscaYMejoraHabilidad(Nodo nodo, String nombre) {
	   if (nodo != null) {
           if (nodo.habilidad.getNombre().equals(nombre)) {
        	   nodo.habilidad.incrementarNivel();
           } else {
               // Si no es la habilidad buscada, buscamos en los nodos hijos
        	   buscaYMejoraHabilidad(nodo.nodoIzq, nombre);
        	   buscaYMejoraHabilidad(nodo.nodoDer, nombre);
           }
       }
   }
    
    // Método para buscar una habilidad por nombre.
    public Habilidad buscarHabilidadIRD(String nombre) {
        System.out.println("Estas en el modo de busqueda IRD");
    	return IRD(this.raiz, nombre);
    }
    
    private Habilidad IRD(Nodo nodo, String nombre) {
    	if(nodo.nodoIzq != null) {
    		Habilidad habIzq = IRD(nodo.nodoIzq, nombre); //null
    		if(habIzq != null)
    			//se ha encontrado la habilidad
    			return habIzq;
    		else {
    			//no se ha encontrado en el lado izquierdo del arbol
    			Habilidad habRaiz = busquedaHab(nodo.habilidad, nombre);
    			if(habRaiz != null)
    				return habRaiz;
    			else {
    				Habilidad habDer = IRD(nodo.nodoDer, nombre);
    				return habDer;
    			}
    		}
    	}
    	else {
    		//no hay nodo izq, por lo tanto es el ultimo hijo
    		return busquedaHab(nodo.habilidad, nombre); 
    	}
    }
    
    private Habilidad busquedaHab(Habilidad habilidad, String nombre) {
    	String texto = "Se va a comprobar esta habilidad -> " + habilidad.getNombre();
    	if(habilidad.getNombre().equals(nombre))
    	{
    		System.out.println(texto + " , ENCOTRADO");
    		return habilidad;
    	}
    	else {
    		System.out.println(texto + " , NO ES");
    		return null;
    	}
    }
    
    public Habilidad buscarHabilidadRID(String nombre) {
    	System.out.println("Estas en el modo de busqueda RID");
    	return RID(raiz, nombre);
    }
    
    private Habilidad RID(Nodo nodo, String nombre) {
    	if(nodo != null) {
    		Habilidad habRaiz = busquedaHab(nodo.habilidad, nombre);
        	if(habRaiz == null) {
        		Habilidad habIzq = RID(nodo.nodoIzq, nombre);
        		if(habIzq == null) {
        			return RID(nodo.nodoDer, nombre);
        		}
        		else {
        			return habIzq;
        		}
        				
        	}
        	else {
        		return habRaiz;
        	}
    	}
    	else {
    		return null;
    	}
    	
    }
    
    public Habilidad buscarHabilidadIDR(String nombre) {
    	System.out.println("Estas en el modo de busqueda IDR");
    	return IDR(raiz, nombre); 
    }
    
    private Habilidad IDR(Nodo nodo, String nombre) {
    	
    	if(nodo.nodoIzq != null) {
    		Habilidad habIzq = IDR(nodo.nodoIzq, nombre); //null
    		if(habIzq != null)
    			//se ha encontrado la habilidad
    			return habIzq;
    		else {
    			//no se ha encontrado en el lado izquierdo del arbol
    			Habilidad habDer = IDR(nodo.nodoDer, nombre);
    			if(habDer != null)
    				return habDer;
    			else {
    				Habilidad habRaiz = busquedaHab(nodo.habilidad, nombre);
    				return habRaiz;
    			}
    		}
    	}
    	else {
    		//no hay nodo izq, por lo tanto es el ultimo hijo
    		return busquedaHab(nodo.habilidad, nombre); 
    	}
    }
    
    public Habilidad buscarHabilidadAnchura(String nombre) {
    	System.out.println("Estas en el modo de busqueda Anchura");
    	Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo nodo = cola.poll();
            if (nodo.habilidad.getNombre().equals(nombre)) {
                System.out.println("Se va a comprobar esta habilidad -> " + nodo.habilidad.getNombre() + " , ENCONTRADO");
            	return nodo.habilidad;
            }
            else 
            	System.out.println("Se va a comprobar esta habilidad -> " + nodo.habilidad.getNombre() + " , NO ES");
            
            if (nodo.nodoIzq != null) 
                cola.add(nodo.nodoIzq);
            if (nodo.nodoDer != null) 
                cola.add(nodo.nodoDer);
        } 
        return null;
    }
    
    
    // Método para visualizar el árbol de habilidades.
    public void imprimirArbol() {
        imprimirNodo(this.raiz, 0);
    }
    
    public void imprimirNodo(Nodo nodo, int nivel) {
    	 if (nodo != null) {
    	        // Imprimir el nombre de la habilidad con un nivel de indentación según su profundidad
    	        for (int i = 0; i < nivel; i++) {
    	            System.out.print("  ");
    	        }
    	        System.out.println(nodo.habilidad.toString());
    	        
    	        // Imprimir los nodos hijos izquierdo y derecho si no son nulos
    	        if (nodo.nodoIzq != null && nodo.nodoIzq.habilidad != null)
    	            imprimirNodo(nodo.nodoIzq, nivel + 1);
    	        
    	        if (nodo.nodoDer != null && nodo.nodoDer.habilidad != null)
    	            imprimirNodo(nodo.nodoDer, nivel + 1);
    	    }
    }
   
    
}
    

