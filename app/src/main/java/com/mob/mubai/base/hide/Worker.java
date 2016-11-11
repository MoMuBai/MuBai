package com.mob.mubai.base.hide;

import com.mob.mubai.base.utils.L;

/**
 * Created by mubai on 2016/11/11.
 */

public class Worker {
    private int age;
    private String name;
    public String id;

    public Worker() {
        super();
        L.d("---> public Worker(){ }");
     }
    public Worker(int age, String name) {
        super();
        this.age = age;
        this.name = name;
        L.d("---> public Worker(int age, String name){ }");
    }
    public int getAge() {
        return age;
        }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Worker [age=" + age + ", name=" + name + "]";
    }
    public void printMessage(String name,int age,int salary){
        L.d("name="+name+",age="+age+",salary="+salary);
    }
}
