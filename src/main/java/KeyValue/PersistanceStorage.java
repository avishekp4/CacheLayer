package KeyValue;

import java.io.*;
import java.util.Objects;


public class PersistanceStorage {
    File persist = new File("persistance");
    public static PersistanceStorage persistanceStorage = null;

    public static PersistanceStorage getInstance() {
        if (persistanceStorage == null) {
            persistanceStorage = new PersistanceStorage();
        }
        return persistanceStorage;
    }

    public void addData(String key, String value, String isDeleted) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(persist));
            writer.append(key);
            writer.append(value);
            writer.append(isDeleted);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CacheStorage reconstructCache() {
        CacheStorage cacheStorage = CacheStorage.getInstance();
        String key, value, isDeleted;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(persist));
            while (bufferedReader.ready()) {
                key = bufferedReader.readLine();
                value = bufferedReader.readLine();
                isDeleted = bufferedReader.readLine();
                if (Objects.equals(isDeleted, "true")) {
                    cacheStorage.removeKey(key);
                } else {
                    cacheStorage.put(key, value);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cacheStorage;
    }

    public void deleteKeyValue(String key, String value, String isDeleted) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(persist));
            writer.append(key);
            writer.append(value);
            writer.append(isDeleted);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PersistanceStorage() {
        try {
            persist.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

