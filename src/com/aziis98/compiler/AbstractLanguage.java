package com.aziis98.compiler;

import com.aziis98.io.*;

public abstract class AbstractLanguage {

    public abstract void compile(DirectoryNode projectStructure, DirectoryNode compiledStructure);

    public static void compile(String[] args, AbstractLanguage language) {
        DirectoryNode output = IO.fromGenericPath( args[1] );
        language.compile( IO.generateFromPath( args[0] ).asDirectory(), output.retriveDirectory( IO.getPathLast( args[1] ) ) );

        // do writeTextBuffer over all the output ...
    }

}
