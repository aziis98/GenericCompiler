package com.aziis98.compiler;

import com.aziis98.io.*;

import java.io.*;

public abstract class AbstractLanguage {

    public abstract void compile(DirectoryNode projectStructure, DirectoryNode compiledStructure);

    public static void compile(String[] args, AbstractLanguage language) {
        DirectoryNode output = IO.fromGenericPath( args[1] );
        language.compile( IO.generateFromPath( args[0] ).asDirectory(), output );

        System.out.println(output.toFormattedText());
        // do writeTextBuffer over all the output ...
    }


    private static String getLast(String path) {
        int i = path.lastIndexOf( File.separator );
        if ( i == -1 )
        {
            return path;
        }
        else
        {
            return path.substring( i + 1 );
        }
    }

}
