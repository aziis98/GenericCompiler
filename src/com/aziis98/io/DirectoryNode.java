package com.aziis98.io;

import com.aziis98.strings.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class DirectoryNode extends AbstractNode {

    private Set<AbstractNode> children = new HashSet<>();

    public DirectoryNode(DirectoryNode parent, String name) {
        super( parent, name );
    }

    public void add(AbstractNode abstractNode) {
        System.out.println(toString() + " += " + abstractNode);
        if ( !children.contains( abstractNode ) ) {
            children.add( abstractNode );
            System.out.println("  done");
        }
    }

    public void remove(AbstractNode abstractNode) {
        children.remove( abstractNode );
    }

    public Set<AbstractNode> getChildren() {
        return children;
    }

    public AbstractNode get(String name) {
        return children.stream()
                .filter( abstractNode -> abstractNode.getName().equals( name ) )
                .findFirst()
                .orElse( null );
    }

    public void walkTree(Consumer<AbstractNode> action) {
        action.accept( this );
        for (AbstractNode child : children)
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
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public void toFormattedText(CodeBuilder codeBuilder) {
        codeBuilder.appendLine( getName() + DEFAULT_SEPARATOR );
        codeBuilder.indentPush();
        {
            children.forEach( abstractNode -> abstractNode.toFormattedText( codeBuilder ) );
        }
        codeBuilder.indentPop();
    }

    @Override
    public String toString() {
        return super.toString() + children.stream().map( AbstractNode::getName ).collect( Collectors.joining(", ", "[", "]") );
    }
}





































