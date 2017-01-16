package by.saidanov.bank.utility.io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * FileCleaner
 *
 * @version 1.0
 *
 * Date 15.01.2017
 */
public final class FileCleaner {

    /**@param filePath is the path of file which will be cleaned*/
    public static void cleanFile(String filePath) throws IOException {
        try (FileWriter fileCleaner = new FileWriter(filePath)) {
            fileCleaner.write("");
        }
    }
}
