package by.saidanov.bank;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>This class was made with one goal - help me to test some java features</p>
 * And this class will be deleted soon
 */
public class Test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm, dd.MM.yyyy");

        System.out.println(sdf.format(new GregorianCalendar().getTime()));

    }
}
