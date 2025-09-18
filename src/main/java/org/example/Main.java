package org.example;
/*
4. Corrección de cuestionarios

La profesora de Acceso a Datos necesita ayuda para corregir un cuestionario de 20 preguntas tipo true/false. La respuesta correcta, los identificadores de los alumnos y sus respuestas se han guardado en un fichero de texto.

Este sería el contenido de un fichero de ejemplo test.txt:

TTTFFFTTTFTFTFTFTFTF

ABC76543 TTTFFFTTTFTFTFTFTFTF

ABC43526 TTTTTTTTTTTTTTTTTTTT

ABC12423 TTTFF TTTFTFTFTFTFTT

ABC12345 FFFFFFFFFFFFFFFFFFFF

Donde la 1ª fila contiene las respuestas correctas del cuestionario y las siguientes filas contienen el código de cada alumno y sus respuestas, una por fila. Cada respuesta correcta puntuará 0,5, cada respuesta incorrecta restará 0,15 y las respuestas en blanco no puntuarán ni penalizarán.

Escribe un programa que procese el fichero y para cada alumno (código identificador) se indique el resultado en su cuestionario.

Mejoras:

En lugar de mostrar la nota, indica su calificación dentro de esta escala:

• Entre 10 y 8.5: excelente

• Entre 8.49-7: notable

• Entre 6-6.99: bien

• Entre 5-5.99: aprobado

• Entre 0-4.99: suspenso

 Añade una tabla resumen con el porcentaje de alumnos con cada calificación.
 */


import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Escribe la dirección del archivo: ");

        String fileString = sc.nextLine();
        Path filePath = Path.of(fileString);
        List<String> contenidoLista = Files.readAllLines(filePath);

        correct(contenidoLista);


    }

    static String calificacion(Double nota){
        if (nota >= 0 && nota <= 4.99){
            return "Suspenso";
        } else if (nota >= 5.0 && nota <= 5.99){
            return "Aprobado";
        } else if (nota >= 6.0 && nota <= 6.99) {
            return "Bien";
        } else if (nota >= 7.0 && nota <= 8.4) {
            return "Notable";
        } else if (nota >= 8.5 && nota <= 10) {
            return "Excelente";
        } else {
            return "Nulo";
        }
    }

    static void correctTest(String alumno, String test, String correctAnswers){
        HashMap<String, String> alumnoCorregido = new HashMap<>();
        double nota = 0.0;
        for (int i = 0; i < correctAnswers.length(); i++) {
            if (test.charAt(i) == ' '){
                nota = nota + 0.0;
            } else if (test.charAt(i) == correctAnswers.charAt(i)){
                nota = nota + 0.5;
            } else {
                nota = nota - 0.15;

            }
        }
        System.out.println("El alumno " + alumno + " tiene un " + calificacion(nota));
    }


    static void correct(List<String> contenidoLista) {
        String correctAnswers = contenidoLista.get(0);
        contenidoLista.remove(0);
        contenidoLista.removeIf(l -> l.trim().isEmpty());
        HashMap<String, String> userAnswers = new HashMap();

        for(String contenido: contenidoLista){
            String[] separate = contenido.split(" ", 2);
            userAnswers.put(separate[0], separate[1]);

        }

        for (Map.Entry<String,String> testAlumno : userAnswers.entrySet()){
            correctTest(testAlumno.getKey(), testAlumno.getValue(), correctAnswers);

        }

    }

}
