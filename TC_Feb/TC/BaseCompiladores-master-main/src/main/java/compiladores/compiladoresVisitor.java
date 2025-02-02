// Generated from c:/Users/Marcos/Desktop/TC TOMI/BaseCompiladores-master-main/src/main/java/compiladores/compiladores.g4 by ANTLR 4.13.1

package compiladores;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link compiladoresParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface compiladoresVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(compiladoresParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#instrucciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstrucciones(compiladoresParser.InstruccionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(compiladoresParser.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(compiladoresParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(compiladoresParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#mas_parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMas_parametros(compiladoresParser.Mas_parametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(compiladoresParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#declaracion_continua}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion_continua(compiladoresParser.Declaracion_continuaContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(compiladoresParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(compiladoresParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#asignacion_continua}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion_continua(compiladoresParser.Asignacion_continuaContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#mas_asignaciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMas_asignaciones(compiladoresParser.Mas_asignacionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(compiladoresParser.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#op_expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_expresion(compiladoresParser.Op_expresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#oal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOal(compiladoresParser.OalContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#llamada_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamada_funcion(compiladoresParser.Llamada_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos(compiladoresParser.ArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#mas_argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMas_argumentos(compiladoresParser.Mas_argumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(compiladoresParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(compiladoresParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#else_bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_bloque(compiladoresParser.Else_bloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(compiladoresParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(compiladoresParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#inicializacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializacion(compiladoresParser.InicializacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#condicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicion(compiladoresParser.CondicionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#actualizacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualizacion(compiladoresParser.ActualizacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#retorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetorno(compiladoresParser.RetornoContext ctx);
}