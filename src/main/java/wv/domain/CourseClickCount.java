package wv.domain;

/**
 * @author YaboSun
 *
 * 实战课程访问数量实体类
 */
public class CourseClickCount {

    // 根据ECharts中定义的来取名称
    private String name;
    private long value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
