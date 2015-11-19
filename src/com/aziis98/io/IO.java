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

    public static AbstractNode generateFromPath(Path path) {
        DirectoryNode root;

        String pathStr = path.toString();
        if ( isDirectory( path ) )
        {
            root = new DirectoryNode( null, "[search-root]" );
        }
        else
        {
            return new FileNode( pathStr );
        }

        AbstractNode result = walkFileTree( pathStr, root ).getChildren().stream().findAny().get();
        result.setParent( null );
        return result;
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
                    root.addDeepFile( subPath.toString() );
                }
                else if ( isDirectory( subPath ) )
                {
                    root.addDeepDirectory( subPath.toString() );
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return root;
    }

}
