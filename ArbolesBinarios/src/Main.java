import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Bienvenido al juego");
    	System.out.println("Cargando datos......");
    	ArbolHabilidadesSecuencial arbolTroll = fillArbolSecuencial();
    	ArbolHabilidadesDinamico arbolElfo = fillArbolDinamico();
    	System.out.println("Datos cargados, QUE EMPIEZE EL JUEGO!!!!\n\n");
    	
        int max_turnos = 10;
        int turnos = 0;
        int victoriasTroll = 0;
        int victoriasElfo = 0;
        while(turnos <= max_turnos){
        	
            if(turnos%2==0){
                // Ataca un jugador (Por ejemplo troll)
            	System.out.println("Turno " + (turnos +1) + "/" + max_turnos +" , le toca al jugador: Troll");
            	System.out.println("Tu arbol: ");
            	arbolTroll.imprimirArbol();
            	System.out.println();
            	System.out.println("Arbol rival: ");
            	arbolElfo.imprimirArbol();
            	System.out.println();
            	
            	//meter en funcion
            	System.out.print("Elije tu habilidad para atacar");
            	System.out.println();
            	String nombreHabilidadAtaque = scanner.nextLine();
            	boolean habilidadOK = false;
            	Habilidad habilidadAtaque = null;
            	Habilidad habilidadDefensa = null;
                
            	while(!habilidadOK) {
                	habilidadAtaque = arbolTroll.buscarHabilidad(nombreHabilidadAtaque);
                	if(habilidadAtaque != null && habilidadAtaque.getTipo().equals("Ataque")) {
                		habilidadOK = true;
                	}
                	else {
                		if(habilidadAtaque == null)
                			System.out.println("El nombre no corresponde con ninguna habilidad de tu arbol, vuelve a intentarlo");
                		else
                			System.out.println("La habilidad tiene que ser de tipo Ataque");
                		System.out.print("Elije tu habilidad para atacar");
                		System.out.println();
                    	nombreHabilidadAtaque = scanner.nextLine();
                	}
                }
            	
            	System.out.print("Elije la habilidad que vas a atacar de tu rival");
            	System.out.println();
            	String nombreHabilidadDefensa = scanner.nextLine();
            	habilidadOK = false;
            	while(!habilidadOK) {
                	habilidadDefensa = arbolElfo.buscarHabilidadIDR(nombreHabilidadDefensa);
                	if(habilidadDefensa != null && habilidadDefensa.getTipo().equals("Defensa")) {
                		habilidadOK = true;
                	}
                	else {
                		if(habilidadDefensa == null)
                			System.out.println("El nombre no corresponde con ninguna habilidad del arbol de tu enemigo, vuelve a intentarlo");
                		else
                			System.out.println("La habilidad tiene que ser de tipo Defensa");
                		System.out.print("Elije la habilidad que vas a atacar de tu rival");
                		System.out.println();
                		nombreHabilidadDefensa = scanner.nextLine();
                	}
                }
            	
            	//resolucion de la lucha
            	if(habilidadAtaque.getNivel() > habilidadDefensa.getNivel()) {
            		System.out.println("Ha ganado el Troll, se sube un nivel su habilidad");
            		arbolTroll.subirNivelHabilidad(habilidadAtaque);
            		System.out.println(habilidadAtaque.toString());
            		arbolTroll.habilidadDisponible(habilidadAtaque);
            		victoriasTroll++; //se incrementa el contador de victorias del troll
            	}
            	else if(habilidadAtaque.getNivel() < habilidadDefensa.getNivel()){
            		System.out.println("Ha ganado el Elfo, se sube un nivel su habilidad");
            		arbolElfo.subirNivelHabilidad(habilidadDefensa.getNombre());
            		System.out.println(habilidadDefensa.toString());
            		arbolElfo.habilidadDisponible(habilidadDefensa);
            		victoriasElfo++; //se incrementa el contador de victorias del elfo
            	}
            	else {
            		System.out.println("Ha quedado empate, ninguna sube nivel");
            		System.out.println();
            	}
            	turnos++;
            }else{
                //Ataca el otro jugador (Por ejemplo Elfo)
            	System.out.println("Turno " + (turnos +1) + "/" + max_turnos + " , le toca al jugador: Elfo");
            	System.out.println("Tu arbol: ");
            	arbolElfo.imprimirArbol();
            	System.out.println();
            	System.out.println("Arbol rival: ");
            	arbolTroll.imprimirArbol();
            	
            	System.out.print("Elije tu habilidad para atacar");
            	System.out.println();
            	String nombreHabilidadAtaque = scanner.nextLine();
            	boolean habilidadOK = false;
            	Habilidad habilidadAtaque = null;
            	Habilidad habilidadDefensa = null;
                
            	while(!habilidadOK) {
                	habilidadAtaque = arbolElfo.buscarHabilidadAnchura(nombreHabilidadAtaque);
                	if(habilidadAtaque != null && habilidadAtaque.getTipo().equals("Ataque")) {
            			habilidadOK = true;
                	}
                	else {
                		if(habilidadAtaque == null)
                			System.out.println("El nombre no corresponde con ninguna habilidad de tu arbol, vuelve a intentarlo");
                		else
                			System.out.println("La habilidad tiene que ser de tipo Ataque");
                		System.out.print("Elije tu habilidad para atacar");
                		System.out.println();
                    	nombreHabilidadAtaque = scanner.nextLine();
                	}
                }
            	
            	System.out.print("Elije la habilidad que vas a atacar de tu rival");
            	System.out.println();
            	String nombreHabilidadDefensa = scanner.nextLine();
            	habilidadOK = false;
            	while(!habilidadOK) {
                	habilidadDefensa = arbolTroll.buscarHabilidad(nombreHabilidadDefensa);
                	if(habilidadDefensa != null && habilidadDefensa.getTipo().equals("Defensa")) {
                		habilidadOK = true;
                	}
                	else {
                		if(habilidadDefensa == null)
                			System.out.println("El nombre no corresponde con ninguna habilidad del arbol de tu enemigo, vuelve a intentarlo");
                		else
                			System.out.println("La habilidad tiene que ser de tipo Defensa");
                		System.out.print("Elije la habilidad que vas a atacar de tu rival");
                		System.out.println();
                		nombreHabilidadDefensa = scanner.nextLine();
                	}
                }
            	
            	//resolucion de la lucha
            	if(habilidadAtaque.getNivel() > habilidadDefensa.getNivel()) {
            		System.out.println("Ha ganado el Elfo, se sube un nivel su habilidad");
            		arbolElfo.subirNivelHabilidad(habilidadAtaque.getNombre());
            		System.out.println(habilidadAtaque.toString());
            		arbolElfo.habilidadDisponible(habilidadAtaque);
            		victoriasElfo++; //se incrementa el contador de victorias del elfo
            	}
            	else if(habilidadAtaque.getNivel() < habilidadDefensa.getNivel()){
            		System.out.println("Ha ganado el Troll, se sube un nivel su habilidad");
            		arbolTroll.subirNivelHabilidad(habilidadDefensa);
            		System.out.println(habilidadDefensa.toString());
            		arbolTroll.habilidadDisponible(habilidadAtaque);
            		victoriasTroll++; //se incrementa el contador de victorias del troll
            	}
            	else {
            		System.out.println("Ha quedado empate, ninguna sube nivel");
            		System.out.println();
            	}
            	turnos++;
            }

        }

        //Al finalizar la partida imprimir los arboles de cada jugador
        System.out.println("Resumen final de la partida -> Troll: " + victoriasTroll + " vs Elfo: "+ victoriasElfo);
        if(victoriasTroll > victoriasElfo)
        	System.out.println("Ha ganado el Troll!!");
        else if(victoriasElfo > victoriasTroll)
        	System.out.println("Ha ganado el Troll!!");
        else
        	System.out.println("Ha quedado empate la partida");
    }
    
    
    
    public static ArbolHabilidadesDinamico fillArbolDinamico() {
    	// Crear las habilidades
        Habilidad personaje = new Habilidad("Elfo", "Personaje");
        Habilidad ataque = new Habilidad("Ataque", "Ataque");
        Habilidad defensa = new Habilidad("Defensa", "Defensa");
        Habilidad fuegoAtaque = new Habilidad("fuegoAtaque", "Ataque");
        Habilidad hieloAtaque = new Habilidad("hieloAtaque", "Ataque");
        Habilidad fuegoDefensa = new Habilidad("fuegoDefensa", "Defensa");
        Habilidad hieloDefensa = new Habilidad("hieloDefensa", "Defensa");
        Habilidad bolaDeFuego = new Habilidad("Bola de Fuego", "Ataque");
        Habilidad flechaDeFuego = new Habilidad("Flecha de Fuego", "Ataque");
        Habilidad escudoDeFuego = new Habilidad("Escudo de Fuego", "Defensa");

        // Crear el árbol de habilidades
        ArbolHabilidadesDinamico arbol = new ArbolHabilidadesDinamico(personaje);

        // Agregar habilidades al árbol
        arbol.agregarHabilidad("Elfo", ataque, defensa);
        arbol.agregarHabilidad("Ataque", fuegoAtaque, hieloAtaque);
        arbol.agregarHabilidad("Defensa", fuegoDefensa, hieloDefensa);
        //arbol.agregarHabilidad("fuegoAtaque", bolaDeFuego, flechaDeFuego);
        //arbol.agregarHabilidad("fuegoDefensa", escudoDeFuego, null); // Escudo de fuego es hijo izquierdo

        // Visualizar el árbol de habilidades
        /*System.out.println("Árbol de habilidades:");
        arbol.imprimirArbol();
        System.out.println("Busqueda IRD:");
        arbol.buscarHabilidadIRD("Escudo de Fuego");
        System.out.println("Busqueda RID:");
        arbol.buscarHabilidadRID("Escudo de Fuego");
        System.out.println("Busqueda IDR:");
        arbol.buscarHabilidadIDR("Escudo de Fuego");
        System.out.println("Busqueda Amplitud:");
        arbol.buscarHabilidadAnchura("Escudo de Fuego");*/
        
        System.out.println("Arbol elfo cargado!!");
        return arbol;
    	
    }
    public static ArbolHabilidadesSecuencial fillArbolSecuencial() {
    	Habilidad trollHa = new Habilidad("Troll", "Personaje");
        ArbolHabilidadesSecuencial troll = new ArbolHabilidadesSecuencial(trollHa);
        Habilidad ataque = new Habilidad("Ataque", "Personaje");
        Habilidad defensa = new Habilidad("Defensa", "Personaje");
        Habilidad fuegoAtaque = new Habilidad("fuegoAtaque", "Ataque");
        Habilidad hieloAtaque = new Habilidad("hieloAtaque", "Ataque");
        Habilidad fuegoDefensa = new Habilidad("fuegoDefensa", "Defensa");
        Habilidad hieloDefensa = new Habilidad("hieloDefensa", "Defensa");
        /*Habilidad bolaDeFuego = new Habilidad("Bola de Fuego", "Ataque");
        Habilidad flechaDeFuego = new Habilidad("Flecha de Fuego", "Ataque");
        Habilidad escudoDeFuego = new Habilidad("Escudo de Fuego", "Defensa");*/
        
        troll.agregarHabilidad(trollHa, ataque, defensa);
        troll.agregarHabilidad(ataque, fuegoAtaque, hieloAtaque);
        troll.agregarHabilidad(defensa, fuegoDefensa, hieloDefensa);
        //troll.agregarHabilidad(fuegoAtaque, bolaDeFuego, flechaDeFuego);
        //troll.agregarHabilidad(fuegoDefensa, escudoDeFuego, null); // Escudo de fuego es hijo izquierdo
        //troll.imprimirArbol();
        System.out.println("Arbol troll cargado!!");
        return troll;
    }
    
}