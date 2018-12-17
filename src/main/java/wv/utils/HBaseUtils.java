package wv.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YaboSun
 *
 * HBase操作工具类
 * Java工具类建议采用单例模式
 */
public class HBaseUtils {
    // 基本配置
    HBaseAdmin admin = null;
    Configuration configuration = null;

    /**
     * 私有构造方法
     */
    private HBaseUtils() {
        // 初始化configuration
        configuration = new Configuration();
        // 这里的配置文件主要是将$HBASE_HOME/conf/hbase-site.xml中的配置进行设置
        configuration.set("hbase.zookeeper.quorm", "leader:2181");
        configuration.set("hbase.rootdir", "hdfs://leader:2181/hbase");

        try {
            admin = new HBaseAdmin(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 单例模式设计
    private static HBaseUtils instance = null;
    // 加synchronized防止有线程安全问题
    public static synchronized HBaseUtils getInstance() {
        if (instance == null) {
            instance = new HBaseUtils();
        }
        return instance;
    }

    /**
     * 根据表名获取HTable实例
     * @param tableName 表名
     * @return HTable实例
     */
    public HTable getTable(String tableName) throws IOException {
        HTable table = null;
        table = new HTable(configuration, tableName);
        return table;
    }

    public Map<String, Long> queryByTableName(String tableName, String condition) throws IOException {
        Map<String, Long> map = new HashMap<>();

        HTable table = getTable(tableName);
        String cf = "log_info";
        String qualifier = "click_count";

        Scan scan = new Scan();

        Filter filter = new PrefixFilter(condition.getBytes());
        scan.setFilter(filter);

        ResultScanner rs = table.getScanner(scan);
        for (Result result : rs) {
            String row = Bytes.toString(result.getRow());
            long clickCount = Bytes.toLong(result.getValue(cf.getBytes(), qualifier.getBytes()));
            map.put(row, clickCount);
        }
        return map;
    }

}

