package compiladores;


import java.util.*;

public class Escucha extends compiladoresBaseListener {
    private TablaSimbolos tablaSimbolos = new TablaSimbolos();
    private List<String> errores = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();

    @Override
    public void enterBloque(compiladoresParser.BloqueContext ctx) {
        tablaSimbolos.addContexto();
    }

    @Override
    public void exitBloque(compiladoresParser.BloqueContext ctx) {
        tablaSimbolos.delContexto();
    }

    @Override
    public void exitPrograma(compiladoresParser.ProgramaContext ctx) {
        Set<Identificador> allIdentificadores = new HashSet<>();
        for (Contexto contexto : tablaSimbolos.getContextos()) {
            allIdentificadores.addAll(contexto.getIdentificadores().values());
        }

        // Verificar si han sido utilizados
        for (Identificador id : allIdentificadores) {
            System.out.println(id.getNombre());
        }

        tablaSimbolos.delContexto();
    }


    @Override
    public void exitParametro(compiladoresParser.ParametroContext ctx){
        String tipoDatoStr = ctx.tipo().getText();

        TipoDato td;
        try {
            td = TipoDato.valueOf(tipoDatoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return;
        }

        Identificador id = new Identificador(ctx.ID().toString(), td);

        if(tablaSimbolos.buscarIdentificadorLocal(id) == null){
            tablaSimbolos.addIdentificador(id);
        } else {
            errores.add("Error semántico, La variable " + id.tipoDato + " " + id.nombre + " ya existe. linea: " + ctx.ID().getSymbol().getLine());
        }
    }

    @Override
    public void exitDeclaracion(compiladoresParser.DeclaracionContext ctx) {
        if(ctx.declaracion_continua() != null && ctx.declaracion_continua().asignacion_continua() != null && ctx.parametros().parametro() != null){
            String nombre = ctx.parametros().parametro().ID().toString();
            TipoDato tipo = tablaSimbolos.buscarTipoIdentificador(nombre);

            if (tipo != null) {
                Identificador id = new Identificador(nombre, tipo);
                tablaSimbolos.identificadorInicializado(id);
            } else {
                errores.add("Error semántico, no se puede asignar un valor a una variable no creada. Identificador: " + nombre + " línea: " + ctx.parametros().parametro().ID().getSymbol().getLine());
            }
        }
    }


    @Override
    public void exitAsignacion(compiladoresParser.AsignacionContext ctx) {
        String nombre = ctx.ID().getText();

        TipoDato tipo = tablaSimbolos.buscarTipoIdentificador(nombre);

        if (tipo != null) {
            Identificador id = new Identificador(nombre, tipo);
            tablaSimbolos.identificadorInicializado(id);
        } else {
            errores.add("Error semántico, no se puede asignar un valor a una variable no creada. Identificador: " + nombre + " línea: " + ctx.ID().getSymbol().getLine());
        }
    }

    @Override
    public void exitLlamada_funcion(compiladoresParser.Llamada_funcionContext ctx) {
        String nombre = ctx.ID().getText();
        boolean encontrado = false;

        for (TipoDato tipo : TipoDato.values()) {
            Identificador id = new Identificador(nombre, tipo);
            if (tablaSimbolos.buscarIdentificador(id) != null && tablaSimbolos.buscarIdentificador(id).inicializada == false) {
                encontrado = true;
                tablaSimbolos.identificadorUtilizado(id);
                break;
            }
        }
        if (!encontrado) {
            errores.add("Error semántico, no se puede usar una funcion no creada. Identificador: " + nombre + " línea: " + ctx.ID().getSymbol().getLine());
        }
    }

    @Override
    public void exitArgumentos(compiladoresParser.ArgumentosContext ctx) {
        if(ctx.expresion() != null){
            Identificador id = salirExpresion(ctx.expresion());
            if(id == null){
                if(ctx.expresion().oal().ID() != null && ctx.expresion().oal().ID().getSymbol() != null)
                    errores.add("Error semántico en argumentos, no se puede comparar variables no creadas. Identificador:  " + ctx.expresion().oal().ID() + " línea: " + ctx.expresion().oal().ID().getSymbol().getLine());
            } else
            if(id.inicializada == false){
                if(ctx.expresion().oal().ID() != null && ctx.expresion().oal().ID().getSymbol() != null)
                    errores.add("Error semántico en argumentos, no se puede comparar variables no inicializada. Identificador:  " + ctx.expresion().oal().ID() + " línea: " + ctx.expresion().oal().ID().getSymbol().getLine());
            }else tablaSimbolos.identificadorUtilizado(id);
        }
    }

    @Override
    public void exitIf(compiladoresParser.IfContext ctx) {
        Identificador id = salirExpresion(ctx.expresion());
        if(id == null){
            if(ctx.expresion().oal().ID() != null && ctx.expresion().oal().ID().getSymbol() != null)
                errores.add("Error semántico en If, no se puede comparar variables no creadas. Identificador:  " + ctx.expresion().oal().ID() + " línea: " + ctx.expresion().oal().ID().getSymbol().getLine());
        } else
        if(id.inicializada == false){
            if(ctx.expresion().oal().ID() != null && ctx.expresion().oal().ID().getSymbol() != null)
                errores.add("Error semántico en If, no se puede comparar variables no inicializada. Identificador:  " + ctx.expresion().oal().ID() + " línea: " + ctx.expresion().oal().ID().getSymbol().getLine());
        }else tablaSimbolos.identificadorUtilizado(id);
    }

    @Override
    public void exitExpresion(compiladoresParser.ExpresionContext ctx) {
        if (ctx.oal() != null && ctx.oal().ID() != null) {
            String nombre = ctx.oal().ID().getText();
            Identificador id = tablaSimbolos.buscarIdentificador(new Identificador(nombre, null));

            if (id != null) {
                tablaSimbolos.identificadorUtilizado(id);
            }
        }

        if (ctx.op_expresion() != null) {
            procesarOpExpresion(ctx.op_expresion());
        }
    }

    private void procesarOpExpresion(compiladoresParser.Op_expresionContext ctx) {
        if (ctx == null || ctx.oal() == null || ctx.oal().ID() == null) {
            return;
        }

        String nombre = ctx.oal().ID().getText();
        Identificador id = tablaSimbolos.buscarIdentificador(new Identificador(nombre, null));

        if (id != null) {
            tablaSimbolos.identificadorUtilizado(id);
        }

        if (ctx.op_expresion() != null) {
            procesarOpExpresion(ctx.op_expresion());
        }
    }

    Identificador salirExpresion(compiladoresParser.ExpresionContext ctx){
        Identificador id = null;
        String nombre = null;
        if(ctx.oal().ID() != null) nombre = ctx.oal().ID().getText();
        else if(ctx.oal().ID() != null) nombre = ctx.oal().ID().getText();

        for (TipoDato tipo : TipoDato.values()) {
            id = tablaSimbolos.buscarIdentificador(new Identificador(nombre, tipo));
            if(id != null)
                return id;
        }
        return null;
    }

    public boolean hayErrores() {
        return !errores.isEmpty();
    }

    public boolean hayWarnings() {
        return !warnings.isEmpty();
    }

    public void mostrarWarnings() {
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

    public void mostrarErrores() {
        for (String error : errores) {
            System.out.println(error);
        }
    }
}