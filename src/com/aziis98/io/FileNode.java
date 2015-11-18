package com.aziis98.io;

import com.aziis98.strings.*;

public class FileNode extends AbstractNode {

    private String text;

    public FileNode(DirectoryNode parent, String name) {
        super( parent, name );
    }

    public FileNode(DirectoryNode parent, String name, String extension) {
        super( parent, name, extension );
    }

    public FileNode(String name) {
        super( name );
    }

    public FileNode(String name, String extension) {
        super( name, extension );
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public void toFormattedText(CodeBuilder codeBuilder) {
        codeBuilder.appendLine( getName() );
    }
}
