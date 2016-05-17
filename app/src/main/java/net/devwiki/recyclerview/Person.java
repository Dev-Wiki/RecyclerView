package net.devwiki.recyclerview;

import java.io.Serializable;

/**
 * 数据对象
 * Created by zyz on 2016/5/17.
 */
public class Person implements Serializable {

    /**
     * 性别:男
     */
    public static final int MALE = 0;

    /**
     * 性别:女
     */
    public static final int FEMALE = 1;

    private String name;

    private int age;

    private int sex;

    public Person(String name, int age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
