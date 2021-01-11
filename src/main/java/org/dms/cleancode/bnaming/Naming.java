package org.dms.cleancode.bnaming;

import java.util.*;

public class Naming {

    // Variables mal escritas
    int d; // tiempo transcurrido en dias. Este comentario debería evitarse.

    // Variables bien escritas
    int elapsedTimeInDays;
    int daysSinceCreation;
    int daysSinceModification;
    int fileAgeInDays;

    /*
    1. ¿Qué contiene theList?
    2. ¿Qué significado tiene el subindice cero de un elemento de theList?
    3. ¿Qué importancia tiene el valor 4?
    4. ¿Cómo se usa la lista devuelta?
     */
    private static List<int[]> getThem(List<int[]> theList) {
        List<int[]> list1 = new ArrayList<int[]>();
        for(int[] x : theList)
            if(x[0] == 4)
                list1.add(x);
            return list1;
    }

    // BUSCAMINAS
    private static final int STATUS_VALUE = 0;
    private static final int FLAGGED = 4;

    private static List<int[]> getFlaggedCells(List<int[]> gameBoard) {
        List<int[]> flaggedCells = new ArrayList<int[]>();
        for(int[] cell: gameBoard) {
            if(cell[STATUS_VALUE] == FLAGGED) {
                flaggedCells.add(cell);
            }
        }
        return flaggedCells;
    }

    private static List<Cell> getFlaggedCellsWithCellClass(List<Cell> gameBoard) {
        List<Cell> flaggedCells = new ArrayList<Cell>();
        for(Cell cell: gameBoard) {
            if(cell.isFlagged()) {
                flaggedCells.add(cell);
            }
        }
        return flaggedCells;
    }

    // DESINFORMACIÓN
    // Nombres de variables MAL
    int hp, aix, sco; // Nombres de plataformas o variantes de Unix
    HashSet accountList; // Mal, es un set. Usar accountGroup, accounts...

    // No usar nombres con variaciones mínimas.
    int XYZControllerForEfficientHandlingOfStrings;
    int XYZControllerForEfficientStorageOfStrings;

    // l y O son muy parecidos a 1 y 0... no usar cosas de este tipo.

    // REALIZAR DISTINCIONES CON SENTIDO
    public static void copyChars(char a1[], char a2[]) {
        for(int i = 0; i < a1.length; i++) {
            a2[i] = a1[i];
        }
    }

    public static void copyCharsWellNamed(char source[], char destination[]) {
        for(int i = 0; i < source.length; i++) {
            destination[i] = source[i];
        }
    }

    // Ejemplos de distinciones pobres
    // Product ==> ProductInfo, ProductData

    // No incluir el nombre variable en el nombre de una variables
    // NameString?? no tiene sentido
    // getActiveAccount();
    // getActiveAccounts();
    // getActiveAccountInfo();

    // USAR NOMBRES QUE SE PUEDAN PRONUNCIAR
    class DtaRcrd102 {
        private Date geymdhms;
        private Date modymdhms;
        private final String pszqint = "102";
    }

    class Customer {
        private Date generationTimestamp;
        private Date modificationTimestamp;
        private final String recordId = "102";
    }


    // USAR NOMBRES QUE SE PUEDAN PRONUNCIAR
    // La longitud de un nombre debe corresponderse al tamaño de su ámbito
    int s;
    int[] t;
    public void example() {
        for(int j = 0; j < 34; j++) {
            s += (t[j]*4/5);
        }
    }

    int realDaysPerIdealDay = 4;
    final int WORK_DAYS_PER_WEEK = 5;
    final int NUMBER_OF_TASKS = 34;
    int[] taskEstimate;
    int sum = 0;
    public void exampleOK() {
        for(int j = 0; j < NUMBER_OF_TASKS; j++) {
            int realTaskDays = taskEstimate[j] + realDaysPerIdealDay;
            int realTaskWeeks = (realTaskDays / WORK_DAYS_PER_WEEK);
            sum += realTaskWeeks;
        }
    }

    // NOTACIÓN HÚNGARA


    // PREFIJOS DE MIEMBROS

    // INTERFACES E IMPLEMENTACIONES
    // El autor prefiere codificar las implementaciones añadiendo Impl a las interfaces añadiendo I delante

    // Nombres de clases
    // OK: Customer, WikiPage, Account, AddressParser
    // Evitar: Manager, Processor, Data o Info. El nombre de una clase no debe ser un verbo

    // NOMBRES DE MÉTODOS
    // Los métodos deben tener nombres de verbo como postPayment, deletePage o save
    // Al sobrecargar constructores, use métodos de factoría estáticos con nombres que describan los argumentos
    // p. Ej: Complex fulcrumPoint = new Complex.FromRealNumber(23.0);

    // UNA PALABRA POR CONCEPTO
    // Es confuso usar fetch, retrieve y get
    // Es confuso tener un controlador, un administrador y un manager en el mismo codebase

    // NO USAR JUEGOS DE PALABRAS
    // Imagina que en un codebase tienes un método en distintas clases que se llama add() y lo que hace es
    // crear un nuevo valor, sumando o concatenando dos valores existentes. Si alguien necesita crear un método
    // en otra clase para añadir un elemento a una colección, podría decidir llamarle add por coherencia, ya que
    // hay muchas clases con un método add en el código, pero no sería correcto, porque son diferentes semánticamente
    // En este caso deberíamos crear un método llamado insert o append

    // USAR NOMBRES DE DOMINIOS DE SOLUCIONES
    // Terminos informáticos, algoritmos, nombres de patrones
    // AccountVisitor --> patrón VISITOR // TODO: Buscar la definición del patrón visitor

    // USAR NOMBRES DE DOMINIOS DE PROBLEMAS
    // Separar los conceptos de dominio de soluciones y de problemas es parte del trabajo de un buen programador
    // y diseñador. El código que tenga más relación con los conceptos del dominio de problemas tendrá
    // nombres extraídos de dicho dominio.

    // AÑADIR CONTEXTO CON SENTIDO
    // Ejemplo, una clase address
    private void printGuessStatistics(char candidate, int count) {
        String number;
        String verb;
        String pluralModifier;
        if(count == 0) {
            number = "no";
            verb = "are";
            pluralModifier = "s";
        } else if(count == 1) {
            number = "1";
            verb = "is";
            pluralModifier = "";
        } else {
            number = Integer.toString(count);
            verb = "are";
            pluralModifier = "s";
        }
        String guessMessage = String.format("There %s %s %s%s", verb, number, candidate, pluralModifier);
        System.out.println(guessMessage);
    }

    private void printGuessStatisticsCustomClass(char candidate, int count) {
        GuessStatisticsMessage.make(candidate, count);
    }

    // NO AÑADIR CONTEXTOS INNECESARIOS
    // Gas Station Deluxe. ¿Añadimos GSD como prefijo a todas las clases? No tiene ningún sentido. No añade info util.
    // accountAddress y customerAddress perfectos para instancias de clase Address pero no sirven como nombres de clase
    // Address podría referirse tb a MAC o URI, en ese caso deberíamos usar PostalAddress como nombre de clase




}

class Cell {
    public boolean isFlagged() {
        return true;
    }
}

class GuessStatisticsMessage {
    private static String number;
    private static String verb;
    private static String pluralModifier;

    public static String make(char candidate, int count) {
        createPluralDependentMessageParts(count);
        return String.format("There %s %s %s%s", verb, number, candidate, pluralModifier);
    }

    private static void createPluralDependentMessageParts(int count) {
        if(count == 0) {
            thereAreNoLetters();
        } else if(count == 1) {
            thereIsOneLetter();
        } else {
            thereAreManyLetters(count);
        }
    }

    private static void thereAreManyLetters(int count) {
        number = Integer.toString(count);
        verb = "are";
        pluralModifier = "s";
    }

    private static void thereIsOneLetter() {
        number = "1";
        verb = "is";
        pluralModifier = "";
    }

    private static void thereAreNoLetters() {
        number = "no";
        verb = "are";
        pluralModifier = "s";
    }
}
