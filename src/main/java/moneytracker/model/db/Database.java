package moneytracker.model.db;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Sigfried Seldeslachts
 */
public abstract class Database {


    public abstract String databasePath();
    protected abstract boolean fromJSON(String json);

    protected abstract String toJSON();

    public boolean saveDatabase() {
        String json = toJSON();

        // Check if the file exists


        try {
            Writer writer = new BufferedWriter(new FileWriter(databasePath()));
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean loadDatabase() {
        if (!fileExists()) return false;

        try {
            String json = Files.readString(Path.of(databasePath()));

            return fromJSON(json);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean fileExists() {
        return Files.exists(Path.of(databasePath()));
    }
}
