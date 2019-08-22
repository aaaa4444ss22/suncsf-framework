package cn.suncsf.framework.core.db.multidb;

/**
 * @author Administrator
 * @version 1.0.0
 * @date 2019/8/8 13:54
 * @create 2019/8/8 13:54
 * @description
 */
public class DataSourceContextHolder {
    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = DataSourceType.MasterDataSource.getValue();

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        System.out.println("切换到{"+dbType+"}数据源");
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }

}
