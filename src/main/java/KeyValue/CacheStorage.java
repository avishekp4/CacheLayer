package KeyValue;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class CacheStorage {
    private ConcurrentHashMap<String, String>keyValue;
    private static CacheStorage cacheStorage=null;
    public static PersistenceStorage persStorage;

    private CacheStorage() {
    	System.out.println("constructing cache storage");
        keyValue= new ConcurrentHashMap<>();
        persStorage = PersistenceStorage.getInstance();
    }

    public boolean put(String key, String value) {
        if(key == null || value==null){
            return true;
        }
    	System.out.println("inserting key:"+key+" value"+value);
    	keyValue.put(key,value);
        persStorage.addData(key, value, "false");

        return true;
    }
//    public update(String key, String value){
//        keyValue.put(key,value)
//    }

    public boolean removeKey(String key){
        if(key == null ){
            return false;
        }
    	System.out.println("deleting key:"+key);

        String value = keyValue.remove(key);
        
        persStorage.addData(key, value, "true");
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
    public void printValues(){
        keyValue.forEach((k,v)-> System.out.println("key: "+k+", value: "+v));
    }
}
