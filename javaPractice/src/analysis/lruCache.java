package analysis;

import java.util.LinkedHashMap;
import java.util.Map;

public class lruCache<K,V> extends LinkedHashMap<K,V> {
	
	private int size;
	
	public lruCache(int n){
		super(n);
		this.size=n;
	}
	
	public boolean removeEldestEntry(Map.Entry<K, V> e){
		return size()>size;
	}
}
