import KeyValue.CacheStorage;
import KeyValue.PersistanceStorage;

/*
 CREATE A CASH LAYER
   * APIS ->  get(key), put(key, value)
   * Persist Data in a file
   *


 */




public class Driver {
    public static void main(String[] args) {
  CacheStorage cachestore = CacheStorage.getInstance();
  cachestore.put("a", "1");
  cachestore.put("b", "2");
  cachestore.put("c", "3");
  cachestore.put("d", "4");
  
  cachestore.removeKey("a");
  cachestore.removeKey("c");

  
    }
}
