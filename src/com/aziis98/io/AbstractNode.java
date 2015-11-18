package com.aziis98.io;

import com.aziis98.compiler.*;
import com.aziis98.strings.*;

public abstract class AbstractNode implements IFormattable {

    public static String DEFAULT_SEPARATOR = "/";

    private DirectoryNode parent;
    private String name;

    private String fullPath;

    public AbstractNode(DirectoryNode parent, String name) {
        this.parent = parent;
        this.name = name;

        if ( parent != null )
        {
            fullPath = parent.getFullPath() + DEFAULT_SEPARATOR + name;
        }
        else
        {
            fullPath = name;
        }
    }

    public AbstractNode(DirectoryNode parent, String name, String extension) {
        this( parent, name + "." + extension );
    }

    public AbstractNode(String name) {
        this( (DirectoryNode) null, name );
    }

    public AbstractNode(String name, String extension) {
        this( null, name, extension );
    }

    public String getName() {
        return name;
    }

    public DirectoryNode getParent() {
        return parent;
    }

    public String getFullPath() {
        return fullPath;
    }


    public boolean isDirectory() {
        return false;
    }

    public boolean isFile() {
        return true;
    }


    public DirectoryNode asDirectory() {
        return (DirectoryNode) this;
    }

    public FileNode asFile() {
        return (FileNode) this;
    }


    @Override
    public String toFormattedText() {
        CodeBuilder codeBuilder = new CodeBuilder();
        toFormattedText( codeBuilder );
        return codeBuilder.toFormattedText();
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        AbstractNode that = (AbstractNode) o;

        return name.equals( that.name );

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name + "(" + fullPath + ")";
    }
}










































