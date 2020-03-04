package cn.suncsf.framework.core.utils.extr;

import cn.suncsf.framework.core.entity.EntityKeyValue;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public class KeyValueGen extends EntityKeyValue<PropertyDescriptor[], Field[]> {
    @Override
    public void put(PropertyDescriptor[] propertyDescriptors, Field[] fields) {
        setsKey(propertyDescriptors);
        setsValue(fields);
    }
}
