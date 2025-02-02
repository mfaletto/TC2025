package compiladores;

import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CommonTokenStream;

public class App {
    public static void main(String[] args) throws Exception {
        // Crear un CharStream que lea desde el archivo
        CharStream input = CharStreams.fromFileName("input/programa.txt");

        // Crear un lexer que procese el CharStream
        compiladoresLexer lexer = new compiladoresLexer(input);

        // Crear un buffer de tokens desde el lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Crear un parser que lea desde el buffer de tokens
        compiladoresParser parser = new compiladoresParser(tokens);

        // Crear el Listener y conectarlo al parser
        Escucha escucha = new Escucha();
        parser.addParseListener(escucha);

        ErroresPersonalizados errorListener = new ErroresPersonalizados();
        parser.removeErrorListeners(); // Remover errores estándar
        parser.addErrorListener(errorListener); // Agregar errores personalizados

        // Ejecutar el parser para construir el árbol de análisis
        ParseTree tree = parser.programa();

        // Continuar si solo hay warnings
        if (escucha.hayWarnings()) {
            System.out.println("Advertencias detectadas durante la validación semántica:");
            escucha.mostrarWarnings();
        }

        // Verificar si hubo errores semánticos en el escucha
        if (escucha.hayErrores() || errorListener.hayErrores()) {
            System.out.println("Errores detectados durante la validación semántica. Proceso detenido.");
            errorListener.mostrarErrores();
            escucha.mostrarErrores();
            return;
        }

        // Si no hay errores, proceder con el caminante
        Caminante caminante = new Caminante();
        caminante.visit(tree);
        List<String> codigoC3D = caminante.getC3DCode();

        System.out.println("-----------------------------------------------------");
        System.out.println("Código sin optimizar:");
        for (String linea : codigoC3D) {
            System.out.println(linea);
        }

        // Crear instancia del optimizador y optimizar
        Optimizador optimizador = new Optimizador(codigoC3D);
        List<String> codigoOptimizado = optimizador.optimizar();

        System.out.println("-----------------------------------------------------");
        System.out.println("Código optimizado:");
        for (String linea : codigoOptimizado) {
            System.out.println(linea);
        }
    }
}
