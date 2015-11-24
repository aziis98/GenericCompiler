package com.aziis98;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        List<String> myList = Arrays.asList( "Antonio", "Monica", "Federica", "Clara", "Damiana" );

        Test.<String>doSomething( "Testing2" );
        Test.<String>doSomething( "Testing2" );
        Test.<String>doSomething( "Testing2" );

        System.out.println(myList);

        List data = getValues();

        System.out.println( data.get(0) );
    }

    public static <T> void doSomething(T obj) {
        System.out.println(obj.toString());
    }

    public static List getValues() {
        return Arrays.<Object>asList( 5, 2 );
    }

}