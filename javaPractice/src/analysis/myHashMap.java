package analysis;
import java.util.*;

class entry<K,V>{
    private K key;
    private V value;
    public entry(K key, V val){
        this.key=key;
        this.value=val;
    }
    
    public V getValue(){
        return this.value;
    }
    
    public K getKey(){
        return this.key;
    }
    
    public void setValue(V val){
        this.value=val;
    }
}


public class myHashMap<K,V>{
    private LinkedList<entry<K,V>> hashTable[];
    private int noOfEntries;
    
    public myHashMap(){
        hashTable=new LinkedList[10];
        for(int i=0;i<10;i++)
        	hashTable[i]=new LinkedList<entry<K,V>>();
        noOfEntries=0;
    }
    
    public myHashMap(int n){
        hashTable=new LinkedList[n];
        for(int i=0;i<n;i++)
        	hashTable[i]=new LinkedList<entry<K,V>>();
        noOfEntries=0;
    }
    
    
    public int getBucketNo(int n){
        return n & hashTable.length;
    }
    
    public void put(K key,V val){
        entry<K,V> newEntry=new entry<K,V>(key,val);
        int hash=key.hashCode();
        int bucketNo=getBucketNo(hash);
        
        boolean flag=false;
        for(entry<K,V> e:hashTable[bucketNo]){
            if(key==e.getKey()){
                e.setValue(val);
                flag=true;
            }
        }
        if(!flag){
            hashTable[bucketNo].add(newEntry);
            noOfEntries++;
        }
    }
    
    public V get(K key){
        int hash=key.hashCode();
        int bucketNo=getBucketNo(hash);
        
        for(entry<K,V> e:hashTable[bucketNo]){
            if(key==e.getKey()){
                noOfEntries--;
                return e.getValue();
            }
        }
        
        return null;
    }
    
    public void resize(){
        LinkedList<entry<K,V>> tempTable[]=hashTable;
        int tempSize=hashTable.length;
        hashTable=new LinkedList[2*tempSize];
        for(int i=0;i<2*tempSize;i++)
        	hashTable[i]=new LinkedList<entry<K,V>>();
        
        for(int i=0;i<tempTable.length;i++){
            for(entry<K,V> e:tempTable[i]){
                this.put(e.getKey(),e.getValue());
            }
        }
    }
    
}