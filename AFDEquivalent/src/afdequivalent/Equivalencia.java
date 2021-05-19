package afdequivalent;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Equivalencia {

    private AFD m1;
    private AFD m2;
    private String alfabeto;
    private int k = 0;
    private String cadenaM = "";

    protected Map<Integer, Node[]> columna0;
    protected Comparacion[] columnas;

    private String listaAlfabeto = "";

    private boolean compatible = true;
    private boolean completo = false;
    int z = 0;

    public Equivalencia(AFD a1, AFD a2, String alfa) {
        this.m1 = a1;
        this.m2 = a2;
        this.alfabeto = alfa;
    }

    public String cadenaAlfabeto() {
        String cad = "  ALFABETO        \n|";
        int i = 0;
        for (String s : this.alfabeto.split(",")) {
            cad += "    " + s + "    |";
            i++;
        }
        return cad;
    }

    public String cadenaColumna0() {
        String cad = "  ESTADOS\n| M1   M2 |\n";
        for (int r = 0; r < columna0.size(); r++) {
            Node[] t = columna0.get(r);
            cad += (" [" + t[0].nombre + ", " + t[1].nombre + "]\n");
        }
        return cad;
    }

    public void compararAutomatas(Node iM1, Node iM2) {
        Node[] aux = new Node[2];
        aux[0] = iM1;
        aux[1] = iM2;
        this.z = this.alfabeto.split(",").length;

        this.columna0 = new TreeMap();
        boolean b = false;

        columna0.put(0, aux);

        int i = 0;
        while (this.compatible == true && completo == false) {
            cadenaM = "";
            cadenaM = cadenaColumna0();

            /*for (int r = 0; r < columna0.size(); r++) {
                Node[] t = columna0.get(r);
                System.out.println("1- " + t[0].nombre + "\n2- " + t[1].nombre);       //Impresion para el desarrollador
            }*/
            aux = columna0.get(i);
            //System.out.println("AUXILIAR ACTUAL  -->  " + aux[0].nombre + ", " + aux[1].nombre);       //Impresion para el desarrollador
            b = verificarEstadosEnColumna0(aux[0], aux[1]);
            i = i + 1;
        }

        if (this.compatible == true) {
            System.out.println("\nLOS AUTOMATAS SI SON EQUIVALENTES\n");
            aux[0] = columna0.get(columna0.size() - 1)[0];
            aux[1] = columna0.get(columna0.size() - 1)[1];

            llenarFilaDeSimbolos(aux[0], aux[1]);

            System.out.println(cadenaM);
            System.out.println(cadenaAlfabeto());
            System.out.println(listaAlfabeto);
        } else {
            System.out.println("\nLOS AUTOMATAS NO SON EQUIVALENTES");
        }

    }

    public boolean verificarEstadosEnColumna0(Node iM1, Node iM2) {
        Node[] aux = new Node[2];
        //System.out.println("ENTRA A VERIFICAR ESTADOS EN COLUMNA");       //Impresion para el desarrollador
        aux[0] = iM1;
        aux[1] = iM2;
//        int k = 0;
        int t = 0;
        int w = 0;
        boolean c = true;

        c = llenarFilaDeSimbolos(aux[0], aux[1]);
        boolean v = true;

        if (c == true) {
            for (int i = 0; i < z; i++) {
                for (int y = 0; y < columna0.size(); y++) {
                    //System.out.println("CC : " + Arrays.toString(columna0.get(y)));       //Impresion para el desarrollador
                    //System.out.println("CO : " + Arrays.toString(columnas[i].nodos));       //Impresion para el desarrollador
                    v = Arrays.equals(columna0.get(y), columnas[i].nodos);
                    if (v == true) {
                        w = w + 1;
                    }
                    //System.out.println("--- W=" + w + " - " + v);
                }
                //System.out.println("LO CONTIENE? : " + v);
                if (w == 0) {
                    //System.out.println("LO AGREGO A COLUMNA 0");       //Impresion para el desarrollador
                    k = k + 1;
                    columna0.put(k, columnas[i].nodos);
                } else {
                    //System.out.println("NO LO AGREGO A COLUMNA 0");       //Impresion para el desarrollador
                    t = t + 1;
                    //System.out.println("T= " + t);
                }
            }
        }
        if (t == z) {
            completo = true;
        }
        return completo;
    }

    public boolean llenarFilaDeSimbolos(Node m1, Node m2) {
        columnas = new Comparacion[z];
        Node[] aux = new Node[2];
        Node a = null;
        Node b = null;
        aux[0] = m1;
        aux[1] = m2;
        int x = 0;

        for (String simbolo : this.alfabeto.split(",")) {

            a = this.m1.obtenerNodo(aux[0].getTransicion(simbolo));
            b = this.m2.obtenerNodo(aux[1].getTransicion(simbolo));

            if ((a.fina && b.fina) || (!a.fina && !b.fina)) {
                columnas[x] = new Comparacion(simbolo, a, b);
                //System.out.println(simbolo + "  -->  " + a.nombre + ", " + b.nombre);       //Impresion para el desarrollador
                x++;
            } else {
                System.out.println("En el simbolo " + simbolo + " los estados "
                        + a.nombre + " y " + b.nombre + " NO son compatibles");
                this.compatible = false;
                //break;
            }

            listaAlfabeto += (" [" + a.nombre + ", " + b.nombre + "]  ");
        }

        listaAlfabeto += "\n";
        //en este for se genera el array de columnas del alfabeto UNA FILA en moore
        //      a       b       c
        //    q2,r2   q1,r1
        return this.compatible;
    }
}
