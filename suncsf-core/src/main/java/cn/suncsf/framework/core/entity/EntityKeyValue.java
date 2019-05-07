package cn.suncsf.framework.core.entity;

public abstract class EntityKeyValue<K,V> extends EntityBase{

    private K sKey;

    private V sValue;

    public K getsKey() {
        return sKey;
    }

    public void setsKey(K sKey) {
        this.sKey = sKey;
    }

    public V getsValue() {
        return sValue;
    }

    public void setsValue(V sValue) {
        this.sValue = sValue;
    }

    /**
     * 插入
     * @param k
     * @param v
     */
    public  abstract void put(K k,V v);
}
