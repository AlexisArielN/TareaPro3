package PracticoDao.graficoarboles;


import PracticoDao.listas.Lista;

public class Organigrama<E> {
    private Nodo<E> raiz;

    public Organigrama() {
        raiz = null;
    }

    public void anadir(E o, String id, String idpadre) {
        Nodo<E> nuevo = new Nodo<>(id, o);

        if (idpadre == null) {
            raiz = nuevo;
            return;
        }

        Nodo<E> padre = raiz.buscar(idpadre);
        if (padre == null) {
            // excepcion horrible
            return;
        }

        padre.anadirHijo(nuevo);
    }

    public void anadir(E id, String nombre, String idpadre, String color) {//1 - gerencia - null - rojo
        Nodo<E> nuevo = new Nodo<>(nombre, id, color);// gerencia, 1, rojo
        if (idpadre == null) {
            raiz = nuevo;
            return;
        }

        Nodo<E> padre = raiz.buscar(idpadre);
        if (padre == null) {
            // excepcion horrible
            return;
        }

        padre.anadirHijo(nuevo);
    }

    @Override
    public String toString() {
        if (raiz == null)
            return "[VACIO]";
        return raiz.toString();
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<E> raiz) {
        this.raiz = raiz;
    }

    public void resetVisita() {
        Nodo<E> actual = raiz;
        resetVisita(actual);
    }

    private void resetVisita(Nodo<E> actual) {
        actual.resetVisita();
        for (Nodo<E> hijo:
                actual.getHijos()) {
            resetVisita(hijo);
        }
    }

    public static class Nodo<E> {
        private E id;
        private String nombre;
        private String color;
        private Lista<Nodo<E>> hijos;
        private Nodo<E> padre;
        private int visitado;

        public Nodo(String id, E o) {
            nombre = id;
            this.id = o;
            hijos = new Lista<>();
            padre = null;
            visitado = 0;
        }

        public Nodo(String nombre,E id,  String color) {//nombre, id, color
            this.nombre = nombre;
            this.id = id;
            this.color = color;
            hijos = new Lista<>();
            padre = null;
            visitado = 0;
        }

        public int getVisitado() {
            return visitado;
        }

        public void visitar() {
            this.visitado += 1;
        }

        public void resetVisita() {
            this.visitado = 0;
        }

        public E getId() {
            return id;
        }

        public void setId(E id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public Lista<Nodo<E>> getHijos() {
            return hijos;
        }

        public Nodo<E> getPadre() {
            return padre;
        }

        public void setPadre(Nodo<E> padre) {
            this.padre = padre;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void anadirHijo(Nodo<E> nuevo) {
            nuevo.setPadre(this);
            this.hijos.adicionar(nuevo);
        }

        public Nodo<E> buscar(String idpadre) {
            if (this.id.equals(idpadre)) {
                return this;
            }
            for (Nodo<E> hijo:
                    hijos) {
                Nodo<E> encontrado = hijo.buscar(idpadre);
                if (encontrado != null)
                    return encontrado;
            }

            return null;
        }

        @Override
        public String toString() {
            StringBuilder resultado = new StringBuilder();
            resultado.append(nombre);

            if (hijos.getTamano() == 0)
                return resultado.toString();

            //  A->(B->(E,F),C)
            resultado.append("->(");
            String conector = "";
            for (Nodo<E> hijo :
                    hijos) {
                resultado.append(conector).append(hijo.toString());
                conector = ",";
            }
            resultado.append(")");

            return resultado.toString();
        }

        public Lista<Nodo<E>> getHijosNoVisitadosYNoEnLista(Lista<Nodo<E>> visita) {
            Lista<Nodo<E>> result = new Lista<>();
            for (Nodo<E> hijo:
                    hijos) {
                boolean enLista = false;
                for (Nodo<E> nodo:
                        visita) {
                    if (hijo.getNombre().equals(nodo.getNombre())) {
                        enLista = true;
                        break;
                    }
                }
                if (hijo.getVisitado() == 0 && !enLista) {
                    result.insertar(hijo);
                }
            }

            return result;
        }
    }
}
