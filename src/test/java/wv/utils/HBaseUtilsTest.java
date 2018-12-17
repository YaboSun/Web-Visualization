package wv.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;


/**
 * @author YaboSun
 */
public class HBaseUtilsTest {

    @Test
    public void queryByTableName() throws IOException {
        String tableName = "course_clickcount";
        String condition = "20181214";
        Map<String, Long> map = HBaseUtils.getInstance().queryByTableName(tableName, condition);

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}