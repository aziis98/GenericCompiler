package com.aziis98.io;

import com.aziis98.strings.*;
import com.aziis98.util.*;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class FileNode extends AbstractNode {

    String textBuffer;

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
        try
        {
            return Files.lines( Paths.get( fullPath ) ).collect( Collectors.joining("\n") );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean setText(String text) {
        try
        {
            Files.write( Paths.get( fullPath ), text.getBytes());
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public String getTextBuffer() {
        return textBuffer;
    }

    public void setTextBuffer(String textBuffer) {
        this.textBuffer = textBuffer;
    }

    public boolean writeTextBuffer() {
        return setText( textBuffer );
    }

    public String readToTextBuffer() {
        return textBuffer = getText();
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


    @Override
    protected TreeNode<String> asTreeStructure(TreeNode<String> parent) {
        return new TreeNode<>( parent, name, getText().replace( "\n", "<br>" ) );
    }

}
