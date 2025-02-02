package compiladores;

import java.util.ArrayList;
import java.util.List;

public class Caminante extends compiladoresBaseVisitor<String> {
    private List<String> c3dCode = new ArrayList<>();
    private int temporalCounter = 0;
    private TablaSimbolos tablaSimbolos = new TablaSimbolos();
    private int labelCounter = 0; // Contador de etiquetas

    // Método para generar un nuevo temporal
    private String nuevoTemporal() {
        return "t" + temporalCounter++;
    }

    // Método para generar una nueva etiqueta
    private String nuevaEtiqueta() {
        return "L" + labelCounter++;
    }
    // Método para agregar una línea al código intermedio
    private void agregarCodigo(String linea) {
        c3dCode.add(linea);
    }

    // Visitador para programa
    @Override
    public String visitPrograma(compiladoresParser.ProgramaContext ctx) {
        visit(ctx.instrucciones());
        return null;
    }

    // Visitador para instrucciones
    @Override
    public String visitInstrucciones(compiladoresParser.InstruccionesContext ctx) {
        if (ctx.instruccion() != null) {
            visit(ctx.instruccion());
            if (ctx.instrucciones() != null) {
                visit(ctx.instrucciones());
            }
        }
        return null;
    }

    // Visitador para declaración
    @Override
    public String visitDeclaracion(compiladoresParser.DeclaracionContext ctx) {
        if (ctx.parametros() == null || ctx.parametros().parametro() == null) {
            return null;
        }

        String tipo = ctx.parametros().parametro().tipo().getText();
        String id = ctx.parametros().parametro().ID().getText();

        // Manejo de funciones
        if (ctx.declaracion_continua().PA() != null && ctx.declaracion_continua().PB() != null) {
            TipoDato tipoDato = obtenerTipoDato(tipo);

            if (tipoDato == TipoDato.VOID) {
                agregarCodigo("function " + id + " (void):");
            } else {
                agregarCodigo("function " + id + ":");
            }

            // Crear función en la tabla de símbolos
            Funcion funcion = new Funcion(id, tipoDato, new ArrayList<>());
            tablaSimbolos.addIdentificador(funcion);

            // Procesar parámetros
            tablaSimbolos.addContexto();
            if (ctx.declaracion_continua().parametros() != null) {
                compiladoresParser.ParametrosContext parametrosCtx = ctx.declaracion_continua().parametros();
                compiladoresParser.ParametroContext parametroCtx = parametrosCtx.parametro();

                if (parametroCtx != null) {
                    String tipoParametro = parametroCtx.tipo().getText();
                    TipoDato tipoDatoParametro = obtenerTipoDato(tipoParametro);

                    funcion.getArgumentos().add(tipoDatoParametro);
                    String nombreParametro = parametroCtx.ID().getText();
                    Variable variable = new Variable(nombreParametro, tipoDatoParametro);
                    variable.setInicializada(true);
                    tablaSimbolos.addIdentificador(variable);
                }

                compiladoresParser.Mas_parametrosContext masParametrosCtx = parametrosCtx.mas_parametros();
                while (masParametrosCtx != null) {
                    parametroCtx = masParametrosCtx.parametro();
                    if (parametroCtx != null) {
                        String tipoParametro = parametroCtx.tipo().getText();
                        TipoDato tipoDatoParametro = obtenerTipoDato(tipoParametro);
                        funcion.getArgumentos().add(tipoDatoParametro);

                        String nombreParametro = parametroCtx.ID().getText();
                        Variable variable = new Variable(nombreParametro, tipoDatoParametro);
                        variable.setInicializada(true);
                        tablaSimbolos.addIdentificador(variable);
                    }
                    masParametrosCtx = masParametrosCtx.mas_parametros();
                }
            }

            // Procesar el bloque
            if (ctx.declaracion_continua().bloque() != null) {
                visit(ctx.declaracion_continua().bloque());
            }

            agregarCodigo("end function");
            tablaSimbolos.delContexto(); // Eliminar el contexto de la función
            return null;
        }

        // Declaración de variables normales
        TipoDato tipoDato = obtenerTipoDato(tipo);
        Variable variable = new Variable(id, tipoDato);
        tablaSimbolos.addIdentificador(variable);

        if (ctx.declaracion_continua().expresion() != null) {
            String valor = visit(ctx.declaracion_continua().expresion());
            agregarCodigo(id + " = " + valor + ";");
            tablaSimbolos.identificadorInicializado(variable);
        }
        return null;
    }

    private TipoDato obtenerTipoDato(String tipo) {
        switch (tipo) {
            case "int":
                return TipoDato.INT;
            case "char":
                return TipoDato.CHAR;
            case "double":
                return TipoDato.DOUBLE;
            case "void":
                return TipoDato.VOID;
            default:
                throw new RuntimeException("Tipo no soportado: " + tipo);
        }
    }

    // Visitador para asignación
    @Override
    public String visitAsignacion(compiladoresParser.AsignacionContext ctx) {
        String id = ctx.ID().getText();
        String valor = visit(ctx.asignacion_continua().expresion());

        // Verificar si la variable está declarada
        Identificador identificador = tablaSimbolos.buscarIdentificador(new Identificador(id, null));
        if (identificador == null) {
            throw new RuntimeException("Variable no declarada: " + id);
        }

        // Generar el código de la asignación
        agregarCodigo(id + " = " + valor + ";");
        tablaSimbolos.identificadorInicializado(identificador);
        return null;
    }

    // Visitador para expresión
    @Override
    public String visitExpresion(compiladoresParser.ExpresionContext ctx) {
        // Procesar el primer operando
        String operandoIzq = visit(ctx.oal());

        // Si existe una operación adicional
        if (ctx.op_expresion() != null) {
            String resultado = procesarOpExpresion(ctx.op_expresion(), operandoIzq);
            return resultado;
        }

        // Si no hay operación adicional, devolver el operando izquierdo
        return operandoIzq;
    }

    // Método auxiliar para procesar op_expresion recursivamente
    private String procesarOpExpresion(compiladoresParser.Op_expresionContext ctx, String operandoIzq) {
        // Validar que op_expresion no esté vacío
        if (ctx == null || ctx.getChildCount() == 0) {
            return operandoIzq; // No hay más operaciones, devolver el operando izquierdo
        }

        // Obtener el operador y el segundo operando
        String operador = ctx.getChild(0).getText(); // El operador (+, -, *, etc.)
        String operandoDer = visit(ctx.oal()); // El segundo operando

        // Generar un temporal para esta operación
        String temporal = nuevoTemporal();
        agregarCodigo(temporal + " = " + operandoIzq + " " + operador + " " + operandoDer + ";");

        // Si hay más operaciones en op_expresion, continuar procesando
        if (ctx.op_expresion() != null && ctx.op_expresion().getChildCount() > 0) {
            return procesarOpExpresion(ctx.op_expresion(), temporal);
        }

        // Devolver el resultado de la operación
        return temporal;
    }


    // Visitador para operandos (oal)
    @Override
    public String visitOal(compiladoresParser.OalContext ctx) {
        if (ctx.NUMERO() != null) {
            return ctx.NUMERO().getText();
        } else if (ctx.ID() != null) {
            String id = ctx.ID().getText();
            Identificador identificador = tablaSimbolos.buscarIdentificador(new Identificador(id, null));
            if (identificador == null) {
                throw new RuntimeException("Variable no declarada: " + id);
            }
            if (!identificador.inicializada) {
                // Inicialización predeterminada si no está inicializada
                agregarCodigo(id + " = 0;");
                tablaSimbolos.identificadorInicializado(identificador);
            }
            return id;
        } else if (ctx.llamada_funcion() != null) {
            return visit(ctx.llamada_funcion());
        } else if (ctx.PA() != null && ctx.PB() != null) {
            return visit(ctx.expresion());
        }

        throw new RuntimeException("Operando no reconocido: " + ctx.getText());
    }


    @Override
    public String visitIf(compiladoresParser.IfContext ctx) {
        tablaSimbolos.addContexto(); // Crear un nuevo contexto para el bloque if
        String etiquetaVerdadero = nuevaEtiqueta();
        String etiquetaFalso = nuevaEtiqueta();
        String etiquetaFin = nuevaEtiqueta();

        // Evaluar la condición
        String condicion = visit(ctx.expresion());
        agregarCodigo("if " + condicion + " goto " + etiquetaVerdadero);
        agregarCodigo("goto " + etiquetaFalso);

        // Bloque verdadero
        agregarCodigo(etiquetaVerdadero + ":");
        visit(ctx.bloque());

        // Salto al final
        agregarCodigo("goto " + etiquetaFin);

        // Bloque falso
        agregarCodigo(etiquetaFalso + ":");
        if (ctx.else_bloque() != null && ctx.else_bloque().bloque() != null) {
            visit(ctx.else_bloque().bloque());
        }

        // Etiqueta de fin
        agregarCodigo(etiquetaFin + ":");
        tablaSimbolos.delContexto(); // Eliminar el contexto del if
        return null;
    }



    @Override
    public String visitWhile(compiladoresParser.WhileContext ctx) {
        String etiquetaInicio = nuevaEtiqueta();
        String etiquetaCondicion = nuevaEtiqueta();
        String etiquetaFin = nuevaEtiqueta();

        // Etiqueta de inicio
        agregarCodigo(etiquetaInicio + ":");

        // Evaluar la condición
        String condicion = visit(ctx.expresion());
        agregarCodigo("if " + condicion + " goto " + etiquetaCondicion);
        agregarCodigo("goto " + etiquetaFin);

        // Bloque del bucle
        agregarCodigo(etiquetaCondicion + ":");
        visit(ctx.bloque());
        agregarCodigo("goto " + etiquetaInicio);

        // Etiqueta de fin
        agregarCodigo(etiquetaFin + ":");
        return null;
    }

    @Override
    public String visitFor(compiladoresParser.ForContext ctx) {
        tablaSimbolos.addContexto(); // Crear un nuevo contexto para las variables del for

        String etiquetaInicio = nuevaEtiqueta();   // Inicio del bucle
        String etiquetaCondicion = nuevaEtiqueta(); // Evaluación de la condición
        String etiquetaFin = nuevaEtiqueta();      // Salida del bucle

        // Procesar inicialización
        if (ctx.inicializacion() != null) {
            visit(ctx.inicializacion());
        }

        agregarCodigo(etiquetaInicio + ":");

        // Evaluar condición
        if (ctx.condicion() != null) {
            String condicion = visit(ctx.condicion());
            agregarCodigo("if " + condicion + " goto " + etiquetaCondicion);
            agregarCodigo("goto " + etiquetaFin); // Salir si la condición es falsa
        }

        // Cuerpo del bucle
        agregarCodigo(etiquetaCondicion + ":");
        visit(ctx.bloque());

        // Actualización del iterador
        if (ctx.actualizacion() != null) {
            visit(ctx.actualizacion());
        }

        // Regresar al inicio del bucle
        agregarCodigo("goto " + etiquetaInicio);

        // Etiqueta de salida del bucle
        agregarCodigo(etiquetaFin + ":");

        tablaSimbolos.delContexto(); // Eliminar el contexto del for
        return null;
    }



    @Override
    public String visitLlamada_funcion(compiladoresParser.Llamada_funcionContext ctx) {
        String nombreFuncion = ctx.ID().getText();
        Funcion funcion = (Funcion) tablaSimbolos.buscarIdentificador(new Identificador(nombreFuncion, null));

        if (funcion == null) {
            throw new RuntimeException("Función no declarada: " + nombreFuncion);
        }

        // Procesar los argumentos de la función
        List<String> argumentos = new ArrayList<>();
        if (ctx.argumentos() != null && ctx.argumentos().expresion() != null) {
            argumentos.add(visit(ctx.argumentos().expresion()));

            compiladoresParser.Mas_argumentosContext masArgumentosCtx = ctx.argumentos().mas_argumentos();
            while (masArgumentosCtx != null) {
                if (masArgumentosCtx.argumentos() != null && masArgumentosCtx.argumentos().expresion() != null) {
                    argumentos.add(visit(masArgumentosCtx.argumentos().expresion()));
                }
                masArgumentosCtx = masArgumentosCtx.argumentos() != null ? masArgumentosCtx.argumentos().mas_argumentos() : null;
            }
        }

        // Generar la llamada a la función
        agregarCodigo("call " + nombreFuncion + ", " + String.join(", ", argumentos));

        if (funcion.getTipoDato() != TipoDato.VOID) {
            String temporal = nuevoTemporal();
            agregarCodigo(temporal + " = return;");
            return temporal;
        }

        return null; // Las funciones void no devuelven valores
    }


    @Override
    public String visitRetorno(compiladoresParser.RetornoContext ctx) {
        TipoDato tipoFuncion = obtenerTipoFuncionActual();

        if (tipoFuncion == TipoDato.VOID) {
            if (ctx.expresion() != null) {
                throw new RuntimeException("No se puede retornar un valor en una función void.");
            }
            agregarCodigo("return;");
        } else {
            if (ctx.expresion() != null) {
                String valorRetorno = visit(ctx.expresion());
                agregarCodigo("return " + valorRetorno + ";");
            } else {
                throw new RuntimeException("Se requiere un valor de retorno para una función no void.");
            }
        }
        return null;
    }


    private TipoDato obtenerTipoFuncionActual() {
        // Buscar la función actual en la tabla de símbolos
        for (int i = tablaSimbolos.getContextos().size() - 1; i >= 0; i--) {
            for (Identificador id : tablaSimbolos.getContextos().get(i).getIdentificadores().values()) {
                if (id instanceof Funcion) {
                    return ((Funcion) id).getTipoDato();
                }
            }
        }
        throw new RuntimeException("No se encontró una función en el contexto actual.");
    }



    // Método para obtener el código generado
    public List<String> getC3DCode() {
        return c3dCode;
    }
}