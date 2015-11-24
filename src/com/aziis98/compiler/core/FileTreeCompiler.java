package com.aziis98.compiler.core;

import com.aziis98.compiler.*;
import com.aziis98.io.*;

public abstract class FileTreeCompiler extends AbstractCompiler {

    @Override
    public void compile(DirectoryNode projectStructure, DirectoryNode compiledStructure) {

        System.out.println(projectStructure.toFormattedText());
        System.out.println();

        compiledStructure.set( projectStructure );

        compiledStructure.walkTree( node -> {
            if ( node.isFile() )
            {
                System.out.println("Compiling: " + node.getFullPath());
                compile( node.asFile() );
            }
        });

        System.out.println();
        System.out.println(compiledStructure.toFormattedText());

    }

    public abstract void compile(FileNode file);

}
