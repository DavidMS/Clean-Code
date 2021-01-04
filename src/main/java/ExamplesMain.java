import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ExamplesMain {

    public static void main(String... args) {

    }

    // Variables mal escritas
    int d; // tiempo transcurrido en dias. Este comentario debería evitarse, es una p#%@ mi*%d$

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
    



}

class Cell {
    public boolean isFlagged() {
        return true;
    }
}
