package KeyValue;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PersistenceStorage {
	static File persist;
	String fileName = "/Users/samrat/persistence.txt";
	public static PersistenceStorage persistenceStorage = null;
	ExecutorService exService = new ThreadPoolExecutor(5, 5, 5000L, TimeUnit.MILLISECONDS,
			new ArrayBlockingQueue<Runnable>(100, true));

	public static PersistenceStorage getInstance() {
		System.out.println("Persistence Storage  storage");
		if (persistenceStorage == null) {
			persistenceStorage = new PersistenceStorage();
		}
		return persistenceStorage;
	}

	public void addData(String key, String value, String isDeleted) {

		//exService.submit(new FileWriterObj(fileName, key, value, isDeleted));

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

	public static CacheStorage reconstructCache() {
		CacheStorage cacheStorage = CacheStorage.getInstance();
		String key, value, isDeleted;
		try {
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(persist))) {
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

	private PersistenceStorage() {
		try {
			persist = new File(fileName);
			if (persist.createNewFile()) {
				System.out.println("Created a new File");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
