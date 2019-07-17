package cn.suncsf.framework.core.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 树形工具
 */
public class EntityTreeUtil {

    /**
     * List 转 Tree
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T extends IEntityTree> List<T> create(List<T> source) {
        return create(source, false);
    }


    /**
     * List 转 Tree
     *
     * @param source 数据源
     * @param isSort 是否排序
     * @param <T>
     * @return
     */
    public static <T extends IEntityTree> List<T> create(List<T> source, boolean isSort) {
       return create(source,null,isSort);
    }

    /**
     * List 转 Tree 并判断
     * @param source
     * @param ids
     * @param isSort
     * @param <T>
     * @return
     */
    public static <T extends IEntityTree> List<T> create(List<T> source,List<String> ids, boolean isSort) {
        List<T> trees = new ArrayList<>();
        for (T item : source) {
            if (StringUtils.isBlank(item.getTreeParentId())) {
                trees.add(item);
            }
            List<IEntityTree> list = source.stream()
                    .filter(c -> StringUtils.isNotBlank(c.getTreeParentId())
                            && c.getTreeParentId().equals(item.getTreeId()))
                    .collect(Collectors.toList());

            if (list != null && list.size() > 0) {
                if (isSort) {
                    list.sort(Comparator.comparingInt(IEntityTree::getSort));
                }
                item.setTreeChildrens(list);
            } else {
                if (isSort) {
                    item.getTreeChildrens().sort(Comparator.comparingInt(IEntityTree::getSort));
                }

            }
            item.setStateList(new HashMap<EntityTreeEnum,Boolean>(){{
                put(EntityTreeEnum.checked,isHaveIds(ids,item.getTreeId()));
                put(EntityTreeEnum.HaveChildren,list.size() > 0);
            }});

        }
        return trees;
    }





    /**
     * List 转 List 加 children
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T extends IEntityTree> List<T> toListAppendChildrens(List<T> source) {

        return toListAppendChildrens(source, false);
    }


    /**
     * List 转 List 加 children
     *
     * @param source
     * @param isSort 是否排序
     * @param <T>
     * @return
     */
    public static <T extends IEntityTree> List<T> toListAppendChildrens(List<T> source, boolean isSort) {
        return toListAppendChildrens(source,null,isSort);
    }


    private static boolean isHaveIds(List<String> ids, String id) {
        if (ids == null || ids.size() < 1) {
            return false;
        }
        return ids.contains(id);
    }

    private static <T extends IEntityTree> List<IEntityTree> getChildrens(List<T> source, String id) {
        return source.stream()
                .filter(c -> StringUtils.isNotBlank(c.getTreeParentId())
                        && c.getTreeParentId().equals(id))
                .collect(Collectors.toList());
    }

    /**
     * List 转 List 加 children ,并判断是否包含ids
     * @param source
     * @param ids
     * @param isSort
     * @param <T>
     * @return
     */
    public static <T extends IEntityTree> List<T> toListAppendChildrens(List<T> source, List<String> ids, boolean isSort) {
        List<T> trees = new ArrayList<>();
        for (T item : source) {
            trees.add(item);
            List<IEntityTree> list = getChildrens(source, item.getTreeId());
            if (list != null && list.size() > 0) {
                item.setTreeChildrens(list);
            }
            item.setStateList(new HashMap<EntityTreeEnum, Boolean>() {{
                put(EntityTreeEnum.HaveChildren, list.size() > 0);
                put(EntityTreeEnum.checked, isHaveIds(ids, item.getTreeId()));
            }});
            if (isSort) {
                item.getTreeChildrens().sort(Comparator.comparingInt(IEntityTree::getSort));
            }
        }
        if (isSort) {
            trees.sort(Comparator.comparing(IEntityTree::getSort));
        }
        return trees;
    }
}
