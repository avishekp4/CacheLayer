package KeyValue;

import java.util.concurrent.ConcurrentHashMap;

public class CacheStorage {
    private ConcurrentHashMap<String, String>keyValue;
    private static CacheStorage cacheStorage=null;
    private static PersistanceStorage persStorage;

    private CacheStorage() {
    	System.out.println("constructing cache storage");
        keyValue= new ConcurrentHashMap<>();
        persStorage =PersistanceStorage.getInstance();
    }

    public boolean put(String key, String value) {
    	System.out.println("insertiung key:"+key+" value"+value);
    	keyValue.put(key,value);
        persStorage.addData(key, value, "false");
        return true;
    }
//    public update(String key, String value){
//        keyValue.put(key,value)
//    }

    public boolean removeKey(String key){
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
}
