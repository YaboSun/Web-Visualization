package ybsun.top.spark;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author YaboSun
 */
@RestController
public class HelloSpringBoot {

    // 表示如果请求的是“/hello” 那么就会执行下面的函数
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloSpring() {
        return "hello world spring boot";
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public ModelAndView firstDemo() {
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/static_pie", method = RequestMethod.GET)
    public ModelAndView staticPieDemo() {
        return new ModelAndView("demo");
    }
}
