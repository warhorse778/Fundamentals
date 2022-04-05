package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {

            Circle a = new Circle (5) ;
            System.out.println (a.getInfo());
            System.out.println(a.getRadius());
          //  a.radius =5;// not anymore with private
            a.setRadius (10);
            System.out.println (a.getInfo());
            System.out.println(a.getRadius());


    }
	// write your code here
    }

