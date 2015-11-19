package com.aziis98.util;

import com.aziis98.compiler.*;
import com.aziis98.strings.*;

import java.util.*;
import java.util.function.*;

public class TreeNode<T> implements IFormattable {
    String name;
    T data;
    TreeNode<T> parent;
    HashMap<String, TreeNode<T>> children;

    public TreeNode(TreeNode<T> parent, String name, T data) {
        this.parent = parent;
        this.name = name;
        this.data = data;
        this.children = new HashMap<>();
    }

    public TreeNode(TreeNode<T> parent, String name) {
        this(parent, name, null );
    }


    public void add(TreeNode<T> node) {
        children.putIfAbsent( node.name, node );
    }

    public void add(String name) {
        add( name, null );
    }

    public void add(String name, T data) {
        add( new TreeNode<>( this, name, data ) );
    }

    public TreeNode<T> append(String name, Consumer<TreeNode<T>> initAction) {
        initAction.accept( retrive( name ) );
        return this;
    }

    public TreeNode<T> get(String name) {
        return children.get( name );
    }

    public TreeNode<T> retrive(String name) {
        if ( !contains( name ) )
        {
            add( name, (T) null );
        }
        return children.get( name );
    }

    public TreeNode<T> getDeep(String path, String separator) {
        int endIndex = path.indexOf( separator );

        if ( endIndex == -1 )
        {
            return get( path );
        }
        else
        {
            String childName = path.substring( 0, endIndex );
            String remaining = path.substring( endIndex + 1 );

            return get( childName ).getDeep( remaining , separator );
        }
    }

    public boolean contains(String name) {
        return children.containsKey( name );
    }


    public boolean isRoot() {
        return parent == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public Collection<TreeNode<T>> getChildren() {
        return children.values();
    }


    @Override
    public void toFormattedText(CodeBuilder codeBuilder) {
        if ( data != null )
        {
            codeBuilder.appendLine( name + ": '" + data + "'");
        }
        else
        {
            codeBuilder.appendLine( name + ":");
        }

        if ( !children.isEmpty() )
        {
            codeBuilder.appendLine( "{" );
            codeBuilder.indentPush();
            for (TreeNode<T> treeNode : children.values())
            {
                treeNode.toFormattedText( codeBuilder );
            }
            codeBuilder.indentPop();
            codeBuilder.appendLine("}");
        }

    }
}