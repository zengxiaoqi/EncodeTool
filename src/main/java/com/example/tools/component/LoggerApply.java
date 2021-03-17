package com.example.tools.component;

import com.example.tools.annotation.MyAnnotation;
import org.springframework.stereotype.Component;

/**
 * @Author: Lingye
 * @Date: 2018/11/11
 * @Describe:
 * @Modified By:
 */
@Component
public class LoggerApply {

    @MyAnnotation(module = "http://www.cnblogs.com/lingyejun/")
    public void lingLogger(String event) throws Exception {
        System.out.println("lingLogger(String event) : MyAnnotation will auth by blog address");
        throw new Exception();
    }
}