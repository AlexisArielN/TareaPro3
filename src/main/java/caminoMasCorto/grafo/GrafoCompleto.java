package caminoMasCorto.grafo;


import caminoMasCorto.listas.Lista;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.TreeMap;

public class GrafoCompleto<E> {

    private HashMap<String, Nodo<E>> nodos;
    private static String camino;
    private static Logger logger = LogManager.getRootLogger();

    public GrafoCompleto() {
        nodos = new HashMap<>();
    }

    public void anadirNodo(String id, E contenido) {
        Nodo<E> nuevo = new Nodo<>(id, contenido);
        nodos.put(id, nuevo);
    }

    public void conectar(String idDesde, String idHacia) {
        int numero = (int)(Math.random()*10+1);
        conectar(idDesde, idHacia, numero);
    }

    public void conectar(String idDesde, String idHacia, int peso) {
        conectar(idDesde, idHacia, peso, true);
    }

    public void conectar(String idDesde, String idHacia, int peso, boolean bidireccional) {
        Nodo<E> desde = nodos.get(idDesde);
        Nodo<E> hacia = nodos.get(idHacia);
        desde.anadirSaliente(hacia, peso);
        if (bidireccional) {
            hacia.anadirSaliente(desde, peso);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Nodo<E> nodo: nodos.values()) {
            sb.append(nodo.toString());
        }
        return sb.toString();
    }

    public Lista<String> dijkstra(String origen, String destino) {
        HashMap<String,Integer> distancias = new HashMap<>();
        HashMap<String,String> anteriores = new HashMap<>();

        TreeMap<String, Nodo> modeloOrdenado = new TreeMap<>();
        modeloOrdenado.putAll(nodos);
        Lista<String> noVisitados = new Lista<>(); // Este es el conjunto Q
        for (String id: modeloOrdenado.keySet()) {
            distancias.put(id, Integer.MAX_VALUE);
            noVisitados.insertar(id);
        }
        distancias.put(origen, 0);
        
        while (noVisitados.getTamano() > 0) {
            String u = encontrarNodoConDistanciaMenor(distancias, noVisitados);
            quitarDeNoVisitados(noVisitados, u);

            if (u.equals(destino)) {
                return reconstruirCamino(anteriores, destino, origen);
            }

            Nodo<E> nodoU = modeloOrdenado.get(u);
            for (Arco<E> saliente: nodoU.getSalientes()) {
                String v = saliente.getHacia().getId();
                int alt = distancias.get(u) + saliente.getPeso();
                if (alt < distancias.get(v)) {
                    distancias.put(v, alt);
                    anteriores.put(v, u);
                }
            }
        }
        return null;
    }

    public Lista<String> reconstruirCamino(HashMap<String, String> anteriores,
                                            String destino, String origen) {
        Lista<String> camino = new Lista<>();
        String actual = destino;
        while (actual != origen) {
            if(actual == null){
                break;
            }
            camino.insertar(actual);
            actual = anteriores.get(actual);
        }
        camino.insertar(origen);
        logger.debug("Se inserto todos los caminos");
        return camino;
    }

    public void quitarDeNoVisitados(Lista<String> noVisitados, String u) {
        noVisitados.eliminar(u);
    }

    public String encontrarNodoConDistanciaMenor(HashMap<String, Integer> distancias, Lista<String> noVisitados) {
        String nodoConDistanciaMenor = null;
        int distanciaMenor = Integer.MAX_VALUE;
        for (String id: noVisitados) {
            int distancia = distancias.get(id);
            if (distancia < distanciaMenor) {
                distanciaMenor = distancia;
                nodoConDistanciaMenor = id;
            }
        }
        return nodoConDistanciaMenor;
    }

    public HashMap<String, Nodo<E>> getNodos() {
        return nodos;
    }

    public static class Nodo<E> {
        private String id;
        private E contenido;
        private Lista<Arco<E>> salientes;

        public Nodo(String id, E contenido) {
            this.contenido = contenido;
            this.id = id;
            salientes = new Lista<>();
        }

        public void setSalientes(Lista<Arco<E>> salientes) {
            this.salientes = salientes;
        }



        public void anadirSaliente(Nodo<E> hacia, int peso) {
            Arco<E> nuevo = null;
            for(Arco<E> arco : salientes) {
                if (arco.getHacia() == hacia) {
                    nuevo = arco;
                    break;
                }
            }
            if (nuevo != null) {
                return;
            }

            nuevo = new Arco<>(this, hacia, peso);
            salientes.insertar(nuevo);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Arco<E> arco: salientes) {
                sb.append(arco.getDesde().getId());
                sb.append("-");
                sb.append(arco.getPeso());
                sb.append("->");
                sb.append(arco.getHacia().getId());
                sb.append("\n");
            }
            return sb.toString();
        }

        public String getId() {
            return id;
        }

        public E getContenido() {
            return contenido;
        }

        public Lista<Arco<E>> getSalientes() {
            return salientes;
        }
    }

    public static class Arco<E> {
        private Nodo<E> desde;
        private Nodo<E> hacia;
        private int peso;

        public Arco(Nodo<E> desde, Nodo<E> hacia, int peso) {
            this.desde = desde;
            this.hacia = hacia;
            this.peso = peso;
        }

        public Nodo<E> getDesde() {
            return desde;
        }

        public Nodo<E> getHacia() {
            return hacia;
        }

        public int getPeso() {
            return peso;
        }

        public void setDesde(Nodo<E> desde) {
            this.desde = desde;
        }

        public void setHacia(Nodo<E> hacia) {
            this.hacia = hacia;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }
    }
}
