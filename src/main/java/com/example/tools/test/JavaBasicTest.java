package com.example.tools.test;

public class JavaBasicTest {
    public static void main(String[] args){
        JavaBasicTest javaBasicTest = new JavaBasicTest();
        javaBasicTest.testEquals();
    }

    /**
     * == 和 equals 的区别是什么？
     * 对于基本类型和引用类型 == 的作用效果是不同的，如下所示：
     * 基本类型：比较的是值是否相同；
     * 引用类型：比较的是引用是否相同；
     *
     *equals 解读
     * equals 本质上就是 ==，只不过 String 和 Integer 等重写了 equals 方法，把它变成了值比较。看下面的代码就明白了。
     */
    public void testEquals(){

        String x = "string";
        String y = "string";
        String z = new String("string");
        System.out.println(x==y); // true
        System.out.println(x==z); // false
        System.out.println(x.equals(y)); // true
        System.out.println(x.equals(z)); // true
        /**
         * 代码解读：因为 x 和 y 指向的是同一个引用，所以 == 也是 true，
         * 而 new String()方法则重写开辟了内存空间，所以 == 结果为 false，
         * 而 equals 比较的一直是值，所以结果都为 true。
         */

        class Cat {
            public Cat(String name) {
                this.name = name;
            }

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        Cat c1 = new Cat("王磊");
        Cat c2 = new Cat("王磊");
        System.out.println(c1.equals(c2)); // false
    }
}
