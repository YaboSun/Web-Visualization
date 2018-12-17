package wv.dao;

import org.junit.Test;
import wv.domain.CourseClickCount;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author YaboSun
 */
public class CourseClickCountDAOTest {

    @Test
    public void queryByDate() throws IOException {
        CourseClickCountDAO dao = new CourseClickCountDAO();
        //String tableName = "course_clickcount";
        String condition = "20181214";

        List<CourseClickCount> list = dao.queryByDate(condition);
        for (CourseClickCount model : list) {
            System.out.println(model.getName() + ":" + model.getValue());
        }
    }
}