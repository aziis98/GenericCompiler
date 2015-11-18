package com.aziis98.io;

public class FileNode {

    public static String DEFAULT_SEPARATOR = "/";

    private DirectoryNode parent;
    private String name;

    private String fullPath;

    public FileNode(DirectoryNode parent, String name) {
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

    public FileNode(DirectoryNode parent, String name, String extension) {
        this( parent, name + "." + extension );
    }

    public FileNode(String name) {
        this( (DirectoryNode) null, name );
    }

    public FileNode(String name, String extension) {
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

}










































