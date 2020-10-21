import net.fortuna.ical4j.model.Calendar;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class IngestionExcel {
    /**
     * Main de test principal
     */
    public static void main(String[] args) throws IOException {
        //Liens vers les fichiers de test UT3
        String url_UT3_M2 = "src/main/resources/20-21-S9 M2 MIAGE.xlsx";
        String url_UT3_M1 = "src/main/resources/20-21 EDT S7 M1 MIAGE.xlsx";
        String url_UT3_L3 = "src/main/resources/20-21 EDT S5 L3 MIAGE.xlsx";
        //Liens vers les icals de l'UT1
        //TODO:à Remplir
        String url_UT1_M2 = "";
        String url_UT1_M1 = "";
        String url_UT1_L3 = "";


        String[][] arrayM2 = excelToArray(url_UT3_M2, "M2");
        String[][] arrayM1 = excelToArray(url_UT3_M1, "M1");
        String[][] arrayL3 = excelToArray(url_UT3_L3, "L3");

        //Visualisation des tableaux en html
        Utils.saveArray2html(arrayM2, "M2");
        Utils.saveArray2html(arrayM1, "M1");
        Utils.saveArray2html(arrayL3, "L3");


        //Transformation des tableaux en ical
        /*
        Calendar UT3_M2 = TranfomationICAL.array2calendar(arrayM2);
        Calendar UT3_M1 = TranfomationICAL.array2calendar(arrayM1);
        Calendar UT3_L3 = TranfomationICAL.array2calendar(arrayL3);
        */

        //On fetch les icals de l'UT1 et on les filtres
       /*
        Calendar UT1_M2 = TranfomationICAL.filterIcalUT1(TranfomationICAL.fetchCalendar(url_UT1_M2),"M2");
        Calendar UT1_M1 = TranfomationICAL.filterIcalUT1(TranfomationICAL.fetchCalendar(url_UT1_M1),"M1");
        Calendar UT1_L3 = TranfomationICAL.filterIcalUT1(TranfomationICAL.fetchCalendar(url_UT1_L3),"L3");
        */

        //TODO:Merge les icals

        //Todos plus long termes
        //TODO: gérer les url de l'API
        //TODO: gérer le stockage



    }

    /**
     * Ingère le fichier donné et le converti en tableau à deux dimension
     *
     * @param url   lien vers le fichier
     * @param promo nom de la promo
     * @return tableau à deux dimension
     * @throws IOException erreur sur l'import du fichier
     */
    public static String[][] excelToArray(String url, String promo) throws IOException {
        //Import du fichier xlsx
        File excelFile = new File(url);
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //Calcul de la bonne page
        int page = promo.equals("M2") ? 0 : 1;
        XSSFSheet sheet = workbook.getSheetAt(page);

        //Calcul du nb de colonne
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(14).getPhysicalNumberOfCells();

        int newRowStart = promo.equals("M2") ? 12
                : promo.equals("M1") ? 13
                : 1;
        int newRowEnd = promo.equals("L3") ? 32 : 52;

        String[][] cleanArray = new String[newRowEnd - newRowStart][cols];

        XSSFRow row = null;
        XSSFCell cell = null;

        //parcours et copie de la page excel en tableau
        for (int r = newRowStart; r < newRowEnd; r++) {
            row = sheet.getRow(r);
            if (row == null) break;

            for (int c = 0; c < cols; c++) {
                cell = row.getCell(c);
                if (cell == null) break;
                cleanArray[r - newRowStart][c] = cell.toString();
            }

        }
        //Affichage des détails de la conversion
        System.out.println("Indice début fin : " + newRowStart + " / " + newRowEnd);
        System.out.println("Tableau de taille " + cleanArray.length + " x " + cleanArray[0].length);

        workbook.close();
        fis.close();

        return cleanArray;
    }


}