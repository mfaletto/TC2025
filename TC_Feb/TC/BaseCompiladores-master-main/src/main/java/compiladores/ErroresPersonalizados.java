
package compiladores;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.List;

public class ErroresPersonalizados extends BaseErrorListener {
    private List<String> errores = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String error = "Línea " + line + ":" + charPositionInLine + " " + msg;
        if (msg.contains("')'")) {
            errores.add("Error sintáctico: Falta cierre de paréntesis. En linea: " + line + ":" + charPositionInLine);
        } else if (msg.contains("'('")) {
            errores.add("Error sintáctico: Falta apertura de paréntesis. En linea: " + line + ":" + charPositionInLine);
        } else if (msg.contains("';'")) {
            errores.add("Error sintáctico: Falta punto y coma. En linea: " + line + ":" + charPositionInLine);
        } else if (msg.contains("'}'")) {
            errores.add("Error sintáctico: Falta cierre de llaves. En linea: " + line + ":" + charPositionInLine);
        } else if (msg.contains("no viable alternative at input")) {
            errores.add("Error sintáctico: Entrada irreconocible "+ recognizer.getVocabulary().getDisplayName(((Token) offendingSymbol).getType()));
        } else {
            errores.add("Error sintáctico: " + error);
        } 
    }

    public boolean hayErrores() {
        return !errores.isEmpty();
    }

    public void mostrarErrores() {
        for (String error : errores) {
            System.out.println(error);
        }
    }
}