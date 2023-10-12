package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Prop {
    Properties prop;
    public Prop(){
        prop = new Properties();
        this.readConfig();
    }
    public void readConfig() {
        try {
            tryReadConfig();
        } catch (FileNotFoundException e) {
            System.err.println("Nem található a config file.");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Hiba.");
            System.err.println(e.getMessage());
        }
    }

    private void tryReadConfig() throws IOException, FileNotFoundException {
        InputStream stream = new FileInputStream("jclient.config");
        prop.load(stream);
    }
    public Properties getProp(){
        return this.prop;
    }
}
