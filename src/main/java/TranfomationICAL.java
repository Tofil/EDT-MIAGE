import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TranfomationICAL {

    public static Calendar array2calendar(String[][] array){
        Calendar cal = new Calendar();
        cal.getProperties().add(new ProdId("EDT-MIAGE Unique 1.0//FR"));
        cal.getProperties().add(Version.VERSION_2_0);
        cal.getProperties().add(CalScale.GREGORIAN);

        /*TODO: On parcours la première colonne en regardant quand il y a un nom de jour
               => On ajoute les évènements au calendrier (voir : https://ical4j.github.io/ical4j-user-guide/examples/)
         */

        return cal;
    }

    public static Calendar filterIcalUT1(Calendar badCal, String promo){
        Calendar newCal = null;
        //TODO: On enlève les évenements avec marqués "voir l'EDT UT3"
        return newCal;
    }

    public static Calendar fetchCalendar(String url) throws IOException {

        InputStream is = new URL(url).openStream();
        try {
            return  new CalendarBuilder().build(is);
        } catch (ParserException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return null;

    }
}
