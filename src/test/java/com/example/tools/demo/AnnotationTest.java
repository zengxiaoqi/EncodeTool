package com.example.tools.demo;

import com.example.tools.component.LoggerApply;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class AnnotationTest {

    @Autowired
    private LoggerApply loggerApply;

    @Test
    public void testAnnotationLogger() {
        try {
            loggerApply.lingLogger("Blog Home");
        } catch (Exception e) {
            System.out.println("a exception be there");
        }
    }


}
