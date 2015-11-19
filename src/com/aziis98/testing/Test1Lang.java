package com.aziis98.testing;

import com.aziis98.compiler.*;
import com.aziis98.io.*;

public class Test1Lang extends AbstractLanguage {

    public static void main(String[] args) {
        compile( args, new Test1Lang() );
    }

    @Override
    public void compile(DirectoryNode projectStructure, DirectoryNode compiledStructure) {

        System.out.println("Bla");

        for (AbstractNode abstractNode : projectStructure.getChildren())
        {
            abstractNode.forEach( node -> {
                if ( node.getName().equals("src") )
                {
                    node.setName("out");
                }
            });

            compiledStructure.addNode( abstractNode );
        }

    }
}
