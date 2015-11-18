package com.aziis98.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static java.nio.file.Files.*;

public class IO {

    public static AbstractNode generateFromFile(File file) {
        return generateFromPath( file.toPath() );
    }

    public static AbstractNode generateFromPath(String path) {
        return generateFromPath( Paths.get( path ) );
    }

    public static AbstractNode generateFromPath(Path path) {
        DirectoryNode root;

        if ( isDirectory( path ) )
        {
            root = new DirectoryNode( null, path.toString() );
        }
        else
        {
            return new FileNode( path.toString() );
        }

        return walkFileTree( path, root );
    }

    private static DirectoryNode walkFileTree(Path path, DirectoryNode root) {
        Set<Path> paths = new HashSet<>();

        try
        {
            walk( path ).forEachOrdered( paths::add );

            int startDepth = path.toString().lastIndexOf( '/' );

            for (Path subPath : paths)
            {
                addTo( root, subPath );
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return root;
    }

    public static void addTo(DirectoryNode root, Path path) {
        // src\com\aziis98\strings\StringMatch.java
        // src\com\aziis98

        if ( isDirectory( path ) )
        {
            String[] pathPieces = path.toString().split( "[/\\\\]" );
            DirectoryNode buff = root;
            for (String pathPiece : pathPieces)
            {
                if ( pathPiece.equals( buff.getName() ) )
                    continue;

                AbstractNode folder = buff.get( pathPiece );

                if ( folder != null )
                {
                    folder.asDirectory().add( buff = new DirectoryNode( buff, pathPiece ) );
                }
                else
                {
                    buff.add( buff = new DirectoryNode( buff, pathPiece ) );
                }
            }
        }
        else if ( isRegularFile( path ) )
        {

        }
    }

}
