package afdequivalent;

import javax.swing.JOptionPane;

public class AFD {

    protected String alfabeto;
    protected int noEstados;
    protected String estadoInicial;
    protected String estadosFinales;
    protected Node[] estados;

    public AFD(String alfabeto, int noEstados) {
        this.alfabeto = alfabeto;
        this.noEstados = noEstados;
        this.estadoInicial = "";
        this.estadosFinales = "";
        estados = new Node[noEstados];
    }

    public void llenarEstados() {
        for (int i = 0; i < this.noEstados; i++) {
            String name = JOptionPane.showInputDialog("Nombre del Estado No. " + (i + 1));
            boolean ini, fina;
            if (this.estadoInicial.equals("")) {
                ini = JOptionPane.showConfirmDialog(null, "El Estado '" + name
                        + "' ¿Es Inicial?", "Estado " + (i + 1), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                this.estadoInicial = ini ? name : "";
            } else {
                ini = false;
            }

            fina = JOptionPane.showConfirmDialog(null, "El Estado '" + name
                    + "' ¿Es Final?", "Estado " + i, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            this.estadosFinales += fina ? name + "," : "";

            estados[i] = new Node(name, ini, fina);
            
            estados[i].llenarTransiciones(this.alfabeto);
        }
    }

    public Node obtenerNodo(String nombreNodo) {
        Node estado = null;
        for (Node tmp : this.estados) {
            if (tmp.nombre.equals(nombreNodo)) {
                estado = tmp;
            }
        }
        return estado;
    }

    public Node obtenerNodoInicial() {
        Node estado = null;
        for (Node tmp : this.estados) {
            if (tmp.inicial) {
                estado = tmp;
            }
        }
        return estado;
    }

    public boolean nodoEsFinal(Node n) {
        boolean f = false;
        for (Node tmp : this.estados) {
            if (n.fina == true) {
                f = true;
            }
        }
        return f;
    }
}
