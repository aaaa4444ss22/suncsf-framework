package cn.suncsf.framework.core.entity;

import java.util.List;
import java.util.Map;

public interface IEntityTree extends  IEntity{


    /**
     * 获取ID
     * @return
     */
    public String getTreeId();

    /**
     * 获取名称
     * @return
     */
    public String getTreeName();

    /**
     * 获取父级ID
     * @return
     */
    public String getTreeParentId();


    /**
     * 添加子元素
     * @param children
     */
    public void put(IEntityTree children);


    /**
     * 替换全部子集合
     * @param childrens
     */
    public  void setTreeChildrens(List<IEntityTree> childrens);


    /**
     * 获取子集合
     * @return
     */
    public List<IEntityTree> getTreeChildrens();

    /**
     * 设置状态集合
     * @param stateList
     */
    public  void setStateList(Map<EntityTreeEnum,Boolean> stateList);

    /**
     * 删除子元素
     * @param k
     */
    public void remove(String k);

    /**
     * 获取排序
     * @return
     */
    public int getSort();


}
