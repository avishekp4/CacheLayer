package KeyValue;

import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

public class CacheStorage {
    private ConcurrentHashMap<String, String>keyValue;
    private static CacheStorage cacheStorage=null;

    private CacheStorage() {
        keyValue= new ConcurrentHashMap<>();
    }

    public boolean put(String key, String value) {
        keyValue.put(key,value);
        return true;
    }
//    public update(String key, String value){
//        keyValue.put(key,value)
//    }

    public boolean removeKey(String key){
        keyValue.remove(key);
        return true;
    }


    public String get(String key){
        return keyValue.get(key);
    }

    public static CacheStorage getInstance(){
        if(cacheStorage == null){
            cacheStorage= new CacheStorage();
        }
        return cacheStorage;
    }
}
