package com.aziis98.testing;

import com.aziis98.compiler.*;
import com.aziis98.io.*;
import com.aziis98.util.*;

public class Test1Lang extends AbstractLanguage {

    public static void main(String[] args) {

        DirectoryNode resources = IO.generateFromPath( "resources" ).asDirectory();

        System.out.println( resources.toFormattedText() );


        TreeNode<String> root = new TreeNode<>( null, "root" );
        root.append( "a", stringTreeNode -> {
            stringTreeNode.add( "b" );
            stringTreeNode.add( "c" );
        });
        root.add( "b" );

        System.out.println(root.toFormattedText());

        System.out.println(resources.asTreeStructure().toFormattedText());
    }

    @Override
    public void compile(DirectoryNode projectStructure, DirectoryNode compiledStructure) {

    }
}
