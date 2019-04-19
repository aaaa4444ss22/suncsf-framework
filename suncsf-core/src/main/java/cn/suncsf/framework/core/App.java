package cn.suncsf.framework.core;

import cn.suncsf.framework.core.entity.EntityResult;
import cn.suncsf.framework.core.entity.ResultObject;
import org.junit.Test;

public class App {

    public static   void main(String args[]){

        EntityResult result = new ResultObject().createResult(2>0,"OK");
        System.out.println(result.toJson());
    }
}
