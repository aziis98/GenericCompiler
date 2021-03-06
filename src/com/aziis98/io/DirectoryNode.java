package com.aziis98.io;

import com.aziis98.strings.*;
import com.aziis98.util.*;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class DirectoryNode extends AbstractNode implements Iterable<AbstractNode> {

    protected HashMap<String, AbstractNode> children = new HashMap<>();

    public DirectoryNode(DirectoryNode parent, String name) {
        super( parent, name );
    }

    public void set(DirectoryNode otherTree) {
        children = new HashMap<>(otherTree.children);
        children.values().forEach( node -> node.setParent( this ) );
    }

    public void addDeepDirectory(String path) {
        int i1 = path.indexOf( File.separator );
        if ( i1 == -1 )
        {
            retriveDirectory( path );
        }
        else
        {
            String thisName      = path.substring( 0, i1 );
            String remainingPath = path.substring( i1 + 1 );

            retriveDirectory(thisName).addDeepDirectory( remainingPath );
        }
    }

    public void addDeepFile(String path) {
        int i1 = path.indexOf( File.separator );
        if ( i1 == -1 )
        {
            addNode( new FileNode( this, path ) );
        }
        else
        {
            String thisName = path.substring( 0, i1 );
            String remainingPath = path.substring( i1 + 1 );

            retriveDirectory( thisName ).addDeepFile( remainingPath );
        }
    }


    public AbstractNode getDeep(String path) {
        if ( !path.contains( File.separator ) )
        {
            return getNode( path );
        }
        else
        {
            return retriveDirectory( IO.getPathFist( path ) ).getDeep( IO.getPathTail( path ) );
        }
    }



    public void addNode(AbstractNode abstractNode) {
        children.putIfAbsent( abstractNode.getName(), abstractNode );
    }

    public boolean contains(Predicate<AbstractNode> predicate) {
        return children.values().stream().anyMatch( predicate );
    }

    public boolean contains(String name) {
        return children.containsKey( name );
    }

    public void remove(String name) {
        children.remove( name );
    }

    public void remove(AbstractNode abstractNode) {
        children.remove( abstractNode.getName() );
    }

    public Collection<AbstractNode> getChildren() {
        return children.values();
    }

    public AbstractNode getNode(String name) {
        return children.get( name );
    }

    public FileNode retriveFile(String name) {
        if ( !contains( name ) )
        {
            addNode( new FileNode( this, name ) );
        }
        return getNode( name ).asFile();
    }

    public DirectoryNode retriveDirectory(String name) {
        if ( !contains( name ) )
        {
            addNode( new DirectoryNode( this, name ) );
        }
        return getNode( name ).asDirectory();
    }

    public void walkTree(Consumer<AbstractNode> action) {
        action.accept( this );
        LinkedList<AbstractNode> values = new LinkedList<>(children.values());
        values.sort( Comparator.comparing( AbstractNode::isDirectory ) );
        for (AbstractNode child : values)
        {
            if ( child.isDirectory() )
            {
                ((DirectoryNode) child).walkTree( action );
            }
            else if ( child.isFile() )
            {
                action.accept( child );
            }
        }
    }


    @Override
    public void recalculateFullPath() {
        super.recalculateFullPath();

        children.values().forEach( AbstractNode::recalculateFullPath );
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public void toFormattedText(CodeBuilder codeBuilder) {
        codeBuilder.appendLine( getName() + File.separator );
        codeBuilder.indentPush();
        {
            children.values().forEach( abstractNode -> abstractNode.toFormattedText( codeBuilder ) );
        }
        codeBuilder.indentPop();
    }

    @Override
    public String toString() {
        return super.toString() + children.values().stream().map( AbstractNode::getName ).collect( Collectors.joining(", ", "[", "]") );
    }


    @Override
    protected TreeNode<String> asTreeStructure(TreeNode<String> parent) {
        TreeNode<String> thisDirectory = new TreeNode<>( parent, name );

        for (AbstractNode abstractNode : children.values())
        {
            thisDirectory.add( abstractNode.asTreeStructure( thisDirectory ) );
        }

        return thisDirectory;
    }

    @Override
    public Iterator<AbstractNode> iterator() {
        LinkedList<AbstractNode> list = new LinkedList<>();

        list.add( this );
        list.addAll( children.values() );

        return list.iterator();
    }
}





































