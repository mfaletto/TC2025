package compiladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Optimizador {
    private List<String> codigoOriginal; // Código original a optimizar
    private List<String> codigoOptimizado; // Código optimizado resultante
    private Map<String, String> operacionesRealizadas; // Operaciones ya realizadas

    public Optimizador(List<String> codigoOriginal) {
        this.codigoOriginal = new ArrayList<>(codigoOriginal);
        this.codigoOptimizado = new ArrayList<>();
        this.operacionesRealizadas = new HashMap<>();
    }

    public List<String> optimizar() {
        eliminarOperacionesRepetidas(); // Primera pasada: eliminar operaciones redundantes
        simplificarExpresionesConstantes(); // Segunda pasada: simplificar expresiones constantes
        return codigoOptimizado;
    }

    private void eliminarOperacionesRepetidas() {
        List<String> codigoSinRepetidas = new ArrayList<>();

        for (String linea : codigoOriginal) {
            if (esAsignacion(linea) && contieneOperacion(linea)) {
                String[] partes = linea.split(" = ");
                String izquierda = partes[0].trim();
                String derecha = partes[1].trim().replace(";", ""); // Eliminar ';'

                if (operacionesRealizadas.containsKey(derecha)) {
                    // Reutilizar el resultado de una operación previa
                    String temporalAnterior = operacionesRealizadas.get(derecha);
                    codigoSinRepetidas.add(izquierda + " = " + temporalAnterior + ";");
                } else {
                    // Nueva operación: Guardar en el mapa y añadir al código
                    operacionesRealizadas.put(derecha, izquierda);
                    codigoSinRepetidas.add(linea);
                }
            } else {
                // Mantener líneas no relacionadas con operaciones
                codigoSinRepetidas.add(linea);
            }
        }

        codigoOptimizado = new ArrayList<>(codigoSinRepetidas); // Actualizar la lista optimizada
    }

    private void simplificarExpresionesConstantes() {
        List<String> codigoSimplificado = new ArrayList<>();

        for (String linea : codigoOptimizado) {
            if (esAsignacion(linea) && contieneExpresionConstante(linea)) {
                // Simplificar expresiones constantes
                String lineaSimplificada = simplificarExpresionConstante(linea);

                // Actualizar el mapa de operaciones realizadas con la nueva constante
                String[] partes = lineaSimplificada.split(" = ");
                if (partes.length == 2) {
                    operacionesRealizadas.put(partes[1].replace(";", "").trim(), partes[0].trim());
                }

                codigoSimplificado.add(lineaSimplificada);
            } else {
                codigoSimplificado.add(linea);
            }
        }

        codigoOptimizado = new ArrayList<>(codigoSimplificado); // Actualizar la lista optimizada
    }

    private boolean esAsignacion(String linea) {
        return linea.contains("=") && !linea.contains("if") && !linea.contains("goto");
    }

    private boolean contieneOperacion(String linea) {
        String[] partes = linea.split(" = ");
        if (partes.length == 2) {
            String derecha = partes[1].trim();
            return derecha.matches("[a-zA-Z0-9]+\\s*[+\\-*/%]\\s*[a-zA-Z0-9]+\\s*"); // Detectar operaciones
        }
        return false;
    }

    private boolean contieneExpresionConstante(String linea) {
        String[] partes = linea.split(" = ");
        if (partes.length == 2) {
            String derecha = partes[1].trim();
            return derecha.matches("\\d+\\s*[+\\-*/]\\s*\\d+\\s*;?");
        }
        return false;
    }

    private String simplificarExpresionConstante(String linea) {
        String[] partes = linea.split(" = ");
        String izquierda = partes[0].trim();
        String derecha = partes[1].trim().replace(";", ""); // Eliminar ';'

        // Evalúa la expresión constante
        int resultado = evaluarExpresion(derecha);
        return izquierda + " = " + resultado + ";";
    }

    private int evaluarExpresion(String expresion) {
        String[] tokens;
        if (expresion.contains("+")) {
            tokens = expresion.split("\\+");
            return Integer.parseInt(tokens[0].trim()) + Integer.parseInt(tokens[1].trim());
        } else if (expresion.contains("-")) {
            tokens = expresion.split("-");
            return Integer.parseInt(tokens[0].trim()) - Integer.parseInt(tokens[1].trim());
        } else if (expresion.contains("*")) {
            tokens = expresion.split("\\*");
            return Integer.parseInt(tokens[0].trim()) * Integer.parseInt(tokens[1].trim());
        } else if (expresion.contains("/")) {
            tokens = expresion.split("/");
            return Integer.parseInt(tokens[0].trim()) / Integer.parseInt(tokens[1].trim());
        }
        return 0; // En caso de que no haya operador
    }
}
