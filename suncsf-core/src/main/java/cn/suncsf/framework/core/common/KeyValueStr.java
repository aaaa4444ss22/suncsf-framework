package cn.suncsf.framework.core.common;

import cn.suncsf.framework.core.entity.EntityKeyValue;

public class KeyValueStr extends EntityKeyValue<String,String> {
    public KeyValueStr(){}
    public KeyValueStr(String k,String v){
        this.put(k,v);
    }
    @Override
    public void put(String s, String s2) {
        this.setsKey(s);
        this.setsValue(s2);
    }
}
