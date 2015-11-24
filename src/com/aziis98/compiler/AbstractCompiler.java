package com.aziis98.compiler;

import com.aziis98.io.*;

import java.io.*;
import java.nio.file.*;

public abstract class AbstractCompiler {

    public abstract void compile(DirectoryNode projectStructure, DirectoryNode compiledStructure);

    public static void compile(String[] args, AbstractCompiler language) {
        DirectoryNode input = IO.generateFromPath( args[0] ).asDirectory();
        input.walkTree( node -> {
            if ( node.isFile() )
            {
                node.asFile().readToTextBuffer();
            }
        });

        DirectoryNode output = IO.fromGenericPath( args[1] );


        DirectoryNode outputOut = output.retriveDirectory( IO.getPathLast( args[1] ) );

        language.compile( input, outputOut );

        IO.deleteRecursivly( outputOut.asPath() );

        try
        {
            Files.createDirectories( outputOut.asPath() );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        output.walkTree( abstractNode -> {
            if ( abstractNode.isFile() )
            {
                abstractNode.asFile().writeTextBuffer();
            }
        });

    }

}
