
package automatas;

import java.util.*;

public class Estado {
    String nombre;
    boolean eFinal;
    ArrayList<Transicion> transiciones = new ArrayList();

    public String obtenTransicion(int cam){        
        return transiciones.get(cam).objetivo;
    }
    
}
class Transicion {
    String objetivo;
    String cambio;
}