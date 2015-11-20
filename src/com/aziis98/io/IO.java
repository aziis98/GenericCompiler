package com.aziis98.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static java.nio.file.Files.*;

public class IO {

    public static DirectoryNode fromGenericPath(String path) {
        int i = path.indexOf( File.separator );
        if ( i == -1 )
        {
            return new DirectoryNode( null, path );
        }
        else
        {
            DirectoryNode directoryNode = new DirectoryNode( null, path.substring( 0, i ) );

            directoryNode.addDeepDirectory( path.substring( i + 1 ) );

            return directoryNode;
        }
    }

    public static AbstractNode generateFromFile(File file) {
        return generateFromPath( file.toPath() );
    }

    public static AbstractNode generateFromPath(String path) {
        return generateFromPath( Paths.get( path ) );
    }

    public static AbstractNode generateFromPath(Path path) { // resources/src
        DirectoryNode root;

        String pathStr = path.toString();
        if ( isDirectory( path ) )
        {
            root = createFromPath( pathStr );
        }
        else
        {
            return new FileNode( pathStr );
        }

        DirectoryNode directoryNode = root.getDeep( IO.getPathTail( pathStr ) ).asDirectory();

        return walkFileTree( pathStr, directoryNode );
    }

    private static DirectoryNode walkFileTree(String path, DirectoryNode root) {
        Set<Path> paths = new HashSet<>();

        try
        {
            walk( Paths.get( path ) ).forEachOrdered( paths::add );

            int startDepth = path.lastIndexOf( File.separator );

            for (Path subPath : paths)
            {
                if ( !subPath.toString().contains( File.separator ) ) continue;

                if ( isRegularFile( subPath ) )
                {
                    root.getRoot().asDirectory().addDeepFile( getPathTail(subPath.toString()) );
                }
                else if ( isDirectory( subPath ) )
                {
                    root.getRoot().asDirectory().addDeepDirectory( getPathTail(subPath.toString()) );
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return root;
    }


    public static DirectoryNode createFromPath(String path) {
        DirectoryNode root = new DirectoryNode( null, getPathHead( path ) );
        root.addDeepDirectory( getPathTail( path ) );
        return root;
    }


    // a/b/c = a
    public static String getPathFist(String path) {
        int i = path.indexOf( File.separator );
        if ( i == -1 )
        {
            return path;
        }
        else
        {
            return path.substring( 0, i );
        }
    }

    // a/b/c = c
    public static String getPathLast(String path) {
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

    // a/b/c = a/b
    public static String getPathHead(String path) {
        int i = path.lastIndexOf( File.separator );
        if ( i == -1 )
        {
            return path;
        }
        else
        {
            return path.substring( 0, i );
        }
    }

    // a/b/c = b/c
    public static String getPathTail(String path) {
        int i = path.indexOf( File.separator );
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
