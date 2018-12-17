package wv.web;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wv.dao.CourseClickCountDAO;
import wv.domain.CourseClickCount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YaboSun
 */
@RestController
public class StatApp {
    private static Map<String, String> courses = new HashMap<>();

    static {
        courses.put("112", "Spark SQL慕课网日志分析");
        courses.put("128", "10小时入门大数据");
        courses.put("145", "深度学习之神经网络核心原理与算法");
        courses.put("146", "强大的Node.js在Web开发中的应用");
        courses.put("131", "Vue+Django实战");
        courses.put("130", "Web前端性能优化");
    }

    @Autowired
    CourseClickCountDAO courseClickCountDAO;

    @RequestMapping(value = "/course_clickcount_dynamic", method = RequestMethod.GET)
    public ModelAndView courseClickCount() throws Exception {
        String day = "20181214";
        ModelAndView view = new ModelAndView("index");

        // 通过给定的天拿到list
        List<CourseClickCount> list = courseClickCountDAO.queryByDate(day);

        // 将“20181214_112”第九位开始取为model的编号，然后根据编号拿到名称
        for (CourseClickCount model : list) {
            model.setName(courses.get(model.getName().substring(9)));
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        view.addObject("data_json", jsonArray);
        return view;
    }
}
