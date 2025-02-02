
package compiladores;

import java.util.*;

enum TipoDato {
    INT,
    CHAR,
    DOUBLE,
    VOID
}

class Identificador {
    protected String nombre;
    protected TipoDato tipoDato;
    protected boolean inicializada;
    protected boolean utilizada;

    public Identificador(String nombre, TipoDato tipoDato) {
        this.nombre = nombre;
        this.tipoDato = tipoDato;
        this.inicializada = false;
        this.utilizada = false;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoDato getTipoDato() {
        return tipoDato;
    }

    public void setInicializada(boolean inicializada) {
        this.inicializada = inicializada;
    }

    public void setUtilizada(boolean utilizada) {
        this.utilizada = utilizada;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Tipo: " + tipoDato + ", Inicializada: " + inicializada + ", Utilizada: " + utilizada;
    }
}

class Variable extends Identificador {
    public Variable(String nombre, TipoDato tipoDato) {
        super(nombre, tipoDato);
    }
}

class Funcion extends Identificador {
    private List<TipoDato> argumentos;

    public Funcion(String nombre, TipoDato tipoDato, List<TipoDato> argumentos) {
        super(nombre, tipoDato);
        this.argumentos = argumentos;
    }

    public List<TipoDato> getArgumentos() {
        return argumentos;
    }

    @Override
    public String toString() {
        return super.toString() + ", Argumentos: " + argumentos;
    }
}

class Contexto {
    private Map<String, Identificador> identificadores;

    public Contexto() {
        this.identificadores = new HashMap<>();
    }

    public void addIdentificador(Identificador identificador) {
        identificadores.put(identificador.getNombre(), identificador);
    }

    public Identificador buscarIdentificador(Identificador id) {
        for (Identificador identificador : identificadores.values()) {
            if (identificador.getNombre().equals(id.getNombre()) && identificador.getTipoDato() == id.getTipoDato()) {
                return identificador;
            }else if(identificador.getNombre().equals(id.getNombre()) && id.getTipoDato() == null){
                return identificador;
            }

        }
        return null;
    }

    public Map<String, Identificador> getIdentificadores() {
        return identificadores;
    }
}

class TablaSimbolos {
    private List<Contexto> contextos;

    public TablaSimbolos() {
        this.contextos = new ArrayList<>();
        this.contextos.add(new Contexto());
    }

    public void addContexto() {
        contextos.add(new Contexto());
    }

    public void delContexto() {
        if (contextos.size() > 1) {
            contextos.remove(contextos.size() - 1);
        }
    }
    

    public Identificador buscarIdentificador(Identificador id) {
        for (int i = contextos.size() - 1; i >= 0; i--) {
            Identificador identificador = contextos.get(i).buscarIdentificador(id);
            if (identificador != null) { 
                return identificador;
            }
        }
        return null;
    }

    public TipoDato buscarTipoIdentificador(String nombre) {
        Identificador id = new Identificador(nombre, null);
        
        for (int i = contextos.size() - 1; i >= 0; i--) {
            Identificador encontrado = contextos.get(i).buscarIdentificador(id);
            if (encontrado != null) {
                return encontrado.getTipoDato();
            }
        }
        
        return null;
    }

    public void identificadorInicializado(Identificador id) {
        for (int i = contextos.size() - 1; i >= 0; i--) {
            Identificador identificador = contextos.get(i).buscarIdentificador(id);
            if (identificador != null) {
                identificador.setInicializada(true);
                return;
            }
        }
    }

    public List<Contexto> getContextos() {
        return contextos;
    }
    

    public void identificadorUtilizado(Identificador id) {
        for (int i = contextos.size() - 1; i >= 0; i--) {
            Identificador identificador = contextos.get(i).buscarIdentificador(id);
            if (identificador != null) {
                identificador.setUtilizada(true);
                return;
            }
        }
    }


    public Identificador buscarIdentificadorLocal(Identificador id) {
        return contextos.get(contextos.size() - 1).buscarIdentificador(id);
    }

    public void addIdentificador(Identificador identificador) {
        contextos.get(contextos.size() - 1).addIdentificador(identificador);
    }
}
