package com.aziis98.io;

import com.aziis98.compiler.*;
import com.aziis98.strings.*;
import com.aziis98.util.*;

import java.io.*;
import java.nio.file.*;

public abstract class AbstractNode implements IFormattable, Iterable<AbstractNode> {

    DirectoryNode parent;
    String name;

    String fullPath;

    public AbstractNode(DirectoryNode parent, String name) {
        this.parent = parent;
        this.name = name;

        if ( parent != null )
        {
            fullPath = parent.getFullPath() + File.separator + name;
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

    public void setName(String name) {
        this.name = name;
    }

    public DirectoryNode getParent() {
        return parent;
    }

    public void setParent(DirectoryNode parent) {
        this.parent = parent;
        recalculateFullPath();
    }

    protected void recalculateFullPath() {
        if ( parent == null )
        {
            this.fullPath = this.name;
        }
        else
        {
            this.fullPath = parent.getFullPath() + File.separator + this.name;
        }
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

    public Path asPath() {
        return Paths.get( fullPath );
    }

    public TreeNode<String> asTreeStructure() {
        return asTreeStructure( null );
    }

    protected abstract TreeNode<String> asTreeStructure(TreeNode<String> parent);


    @Override
    public String toFormattedText() {
        CodeBuilder codeBuilder = new CodeBuilder();
        codeBuilder.setTabSequence( "  " );
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










































