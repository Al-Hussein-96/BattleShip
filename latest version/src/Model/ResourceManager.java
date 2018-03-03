package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceManager {

    public static void save(Serializable data, String fileName) throws Exception {

        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("src/" + fileName)))) {

            oos.writeObject(data);
        }

    }

    public static Object load(String fileName) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("src/" + fileName)))) {
            return ois.readObject();
        }
    }
}
