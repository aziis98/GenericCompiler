package com.aziis98.testing;

import com.aziis98.compiler.*;
import com.aziis98.io.*;

public class Test1Lang extends AbstractLanguage {

    public static void main(String[] args) {
        compile( args, new Test1Lang() );
    }

    @Override
    public void compile(DirectoryNode projectStructure, DirectoryNode compiledStructure) {

        System.out.println(projectStructure.toFormattedText());

        compiledStructure.retriveFile( "text.txt" );

        System.out.println(compiledStructure.toFormattedText());

    }
}
