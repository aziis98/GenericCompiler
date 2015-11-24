package com.aziis98.compiler;

import com.aziis98.compiler.core.*;
import com.aziis98.io.*;
import com.aziis98.strings.*;

import java.util.*;
import java.util.regex.*;

public class MacroCompiler extends FileTreeCompiler {

    private static final Pattern MACRO_PATTERN = Pattern.compile( "///(\\s)+?(.+?)(\\s)+\"(.+?)\"(\\s)*=(\\s)*\"(.+?)\"(\\s)*\\n" );

    public static void main(String[] args) {
        compile( args, new MacroCompiler() );
    }

    private String textBuffer;
    private LinkedList<MatchResult> macroDefs = new LinkedList<>();

    @Override
    public void compile(FileNode file) {

        textBuffer = file.getTextBuffer();

        macroDefs.addAll(findMacroDefs(textBuffer));

        macroDefs.forEach( matchResult -> {
            String defType = matchResult.group( 2 ).toLowerCase();
            String defName = matchResult.group( 4 );
            String defValue = matchResult.group( 7 );

            System.out.println("Appling: " + defType + " " + defName + " = " + defValue);

            if ( defType.toLowerCase().equals( "define" ) )
            {
                textBuffer = textBuffer
                        .replace( matchResult.group(), "" )
                        .replace( defName, defValue );
            }
            else if ( defType.equals( "replace" ) )
            {
                textBuffer = textBuffer
                        .replace( matchResult.group(), "" )
                        .replaceAll( defName, defValue );
            }

        });

        file.setTextBuffer( textBuffer );

    }

    private LinkedList<MatchResult> findMacroDefs(String textBuffer) {
        return StreamString.findAll( textBuffer, MACRO_PATTERN );
    }

}
