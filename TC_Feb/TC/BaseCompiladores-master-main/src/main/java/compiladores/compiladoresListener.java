// Generated from c:/Users/Marcos/Desktop/TC TOMI/BaseCompiladores-master-main/src/main/java/compiladores/compiladores.g4 by ANTLR 4.13.1

package compiladores;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link compiladoresParser}.
 */
public interface compiladoresListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(compiladoresParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(compiladoresParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#instrucciones}.
	 * @param ctx the parse tree
	 */
	void enterInstrucciones(compiladoresParser.InstruccionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#instrucciones}.
	 * @param ctx the parse tree
	 */
	void exitInstrucciones(compiladoresParser.InstruccionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(compiladoresParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(compiladoresParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(compiladoresParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(compiladoresParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(compiladoresParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(compiladoresParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#mas_parametros}.
	 * @param ctx the parse tree
	 */
	void enterMas_parametros(compiladoresParser.Mas_parametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#mas_parametros}.
	 * @param ctx the parse tree
	 */
	void exitMas_parametros(compiladoresParser.Mas_parametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(compiladoresParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(compiladoresParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#declaracion_continua}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion_continua(compiladoresParser.Declaracion_continuaContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#declaracion_continua}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion_continua(compiladoresParser.Declaracion_continuaContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(compiladoresParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(compiladoresParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(compiladoresParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(compiladoresParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#asignacion_continua}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion_continua(compiladoresParser.Asignacion_continuaContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#asignacion_continua}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion_continua(compiladoresParser.Asignacion_continuaContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#mas_asignaciones}.
	 * @param ctx the parse tree
	 */
	void enterMas_asignaciones(compiladoresParser.Mas_asignacionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#mas_asignaciones}.
	 * @param ctx the parse tree
	 */
	void exitMas_asignaciones(compiladoresParser.Mas_asignacionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(compiladoresParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(compiladoresParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#op_expresion}.
	 * @param ctx the parse tree
	 */
	void enterOp_expresion(compiladoresParser.Op_expresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#op_expresion}.
	 * @param ctx the parse tree
	 */
	void exitOp_expresion(compiladoresParser.Op_expresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#oal}.
	 * @param ctx the parse tree
	 */
	void enterOal(compiladoresParser.OalContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#oal}.
	 * @param ctx the parse tree
	 */
	void exitOal(compiladoresParser.OalContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#llamada_funcion}.
	 * @param ctx the parse tree
	 */
	void enterLlamada_funcion(compiladoresParser.Llamada_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#llamada_funcion}.
	 * @param ctx the parse tree
	 */
	void exitLlamada_funcion(compiladoresParser.Llamada_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(compiladoresParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(compiladoresParser.ArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#mas_argumentos}.
	 * @param ctx the parse tree
	 */
	void enterMas_argumentos(compiladoresParser.Mas_argumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#mas_argumentos}.
	 * @param ctx the parse tree
	 */
	void exitMas_argumentos(compiladoresParser.Mas_argumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(compiladoresParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(compiladoresParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(compiladoresParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(compiladoresParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#else_bloque}.
	 * @param ctx the parse tree
	 */
	void enterElse_bloque(compiladoresParser.Else_bloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#else_bloque}.
	 * @param ctx the parse tree
	 */
	void exitElse_bloque(compiladoresParser.Else_bloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#while}.
	 * @param ctx the parse tree
	 */
	void enterWhile(compiladoresParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#while}.
	 * @param ctx the parse tree
	 */
	void exitWhile(compiladoresParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#for}.
	 * @param ctx the parse tree
	 */
	void enterFor(compiladoresParser.ForContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#for}.
	 * @param ctx the parse tree
	 */
	void exitFor(compiladoresParser.ForContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#inicializacion}.
	 * @param ctx the parse tree
	 */
	void enterInicializacion(compiladoresParser.InicializacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#inicializacion}.
	 * @param ctx the parse tree
	 */
	void exitInicializacion(compiladoresParser.InicializacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#condicion}.
	 * @param ctx the parse tree
	 */
	void enterCondicion(compiladoresParser.CondicionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#condicion}.
	 * @param ctx the parse tree
	 */
	void exitCondicion(compiladoresParser.CondicionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#actualizacion}.
	 * @param ctx the parse tree
	 */
	void enterActualizacion(compiladoresParser.ActualizacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#actualizacion}.
	 * @param ctx the parse tree
	 */
	void exitActualizacion(compiladoresParser.ActualizacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#retorno}.
	 * @param ctx the parse tree
	 */
	void enterRetorno(compiladoresParser.RetornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#retorno}.
	 * @param ctx the parse tree
	 */
	void exitRetorno(compiladoresParser.RetornoContext ctx);
}