package vla;

import scala.Int;

import java.util.ArrayList;
import java.util.List;

public class PolyTest<A> {
    void pr(String i) {
        System.out.println(i);;
    }
    void pr(int i) {
        System.out.println(i+1);;
    }
    void pri(A a){
        System.out.println(a.toString());
    }

    public static void main(String[] args) {
        PolyTest <Integer>   polyTest = new PolyTest();
        //ad hoc полиморфизм
        polyTest.pr(2);
        polyTest.pr("2");


        // параметрический полиморфизм
        polyTest.pri(2);

    }
}

    d hoc полиморфизм.
        Параметрический полиморфизм.
        Полиморфизм подтипов.