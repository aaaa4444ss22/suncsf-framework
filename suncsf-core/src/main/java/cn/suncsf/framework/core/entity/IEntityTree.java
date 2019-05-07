package cn.suncsf.framework.core.entity;

import java.util.List;

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
     * 设置状态
     * @param
     */
    public  void setHaveChildren(Boolean is);

    /**
     * 删除子元素
     * @param k
     */
    public void remove(String k);



}
