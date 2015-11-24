package com.aziis98;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        List<String> myList = [ "Antonio", "Monica", "Federica", "Clara", "Damiana" ];

        Test.doSomething<String>( "Testing2" );
        Test.doSomething<String>( "Testing2" );
        Test.doSomething<String>( "Testing2" );

        println(myList);

        Multi data = getValues();

        println( data.get(0) );
    }

    public static <T> void doSomething(T obj) {
        println(obj.toString());
    }

    public static Multi getValues() {
        return 5, 2;
    }

}
