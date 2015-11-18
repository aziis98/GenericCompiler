package com.aziis98.io;

import java.util.*;
import java.util.function.*;

public class DirectoryNode extends FileNode {

    private LinkedList<FileNode> children = new LinkedList<>();

    public DirectoryNode(String name) {
        super( name );
    }

    public void add(FileNode fileNode) {
        children.add( fileNode );
    }

    public void remove(FileNode fileNode) {
        children.remove( fileNode );
    }

    public LinkedList<FileNode> getChildren() {
        return children;
    }

    public void walkTree(Consumer<FileNode> action) {
        action.accept( this );
        for (FileNode child : children)
        {
            if ( child instanceof DirectoryNode )
            {
                ((DirectoryNode) child).walkTree( action );
            }
            else
            {
                action.accept( child );
            }
        }
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isFile() {
        return false;
    }
}
