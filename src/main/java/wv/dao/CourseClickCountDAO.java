package wv.dao;

import org.springframework.stereotype.Component;
import wv.domain.CourseClickCount;
import wv.utils.HBaseUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author YaboSun
 *
 * 实战课程访问数量数据访问层
 */
// 通过注解交给Spring进行管理
@Component
public class CourseClickCountDAO {


    /**
     * 根据天查询
     * @param day
     * @return
     * @throws IOException
     */
    public List<CourseClickCount> queryByDate(String day) throws IOException {

        List<CourseClickCount> list = new ArrayList<>();

        String tableName = "course_clickcount";
        String condition = "20181214";

        // 从HBase表中根据day获取实战课程对应的访问量
        Map<String, Long> map = HBaseUtils.getInstance().queryByTableName(tableName, condition);
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            CourseClickCount model = new CourseClickCount();
            model.setName(entry.getKey());
            model.setValue(entry.getValue());

            list.add(model);
        }

        return list;
    }

}
