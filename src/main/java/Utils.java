import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

public class Utils {

    /**
     * Permet de copier une partie d'un tableau
     *
     * @param a    tableau à deux dimension à découper
     * @param rMin ligne min
     * @param cMin colonne min
     * @param rMax ligne max
     * @param cMax colonne max
     * @return copie découpée du tableau
     */
    public static String[][] array2subArray(String[][] a, int rMin, int cMin, int rMax, int cMax) {
        String[][] a2 = new String[rMax - rMin][cMax - cMin];

        for (int r = rMin; r < rMax; r++) {
            for (int c = rMin; c < rMax; c++) {
                a2[r - rMin][c - cMin] = a[r][c];
            }
        }

        return a2;
    }

    /**
     * Sauvegarde un tableau sous la forme d'un fichier html
     *
     * @param a        tableau à deux dimension
     * @param fileName nom du fichier
     */
    public static void saveArray2html(String[][] a, String fileName) {
        try {
            File myFile = new File("src/main/resources/" + fileName + ".html");
            FileWriter myWriter = new FileWriter(myFile);
            myWriter.write(Utils.array2html(a));
            myWriter.close();
            System.out.println("Fichier html créé dans : " + myFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de convertir un tableau en <table> dans une page html complète
     *
     * @param a tableau à deux dimension
     * @return String de la page html
     */
    public static String array2html(String[][] a) {
        String htmlBody = "<table >";
        for (String[] a2 : a) {
            htmlBody += "<tr>";
            for (String s : a2) {
                htmlBody += "<td>" + s + " </td>";
            }
            htmlBody += "<tr>";
        }
        htmlBody += "</table>";
        return "<!DOCTYPE html><html ><head></head><body style=\" font-family: arial; \">" + htmlBody + "<style>table, th, td { border: 1px solid black; border-collapse: collapse;}</style></body></html>";
    }
}
