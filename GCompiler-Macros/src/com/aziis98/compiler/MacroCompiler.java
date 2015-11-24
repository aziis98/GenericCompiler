package com.aziis98.compiler;

import com.aziis98.compiler.core.*;
import com.aziis98.io.*;
import com.aziis98.strings.*;

import java.util.*;
import java.util.regex.*;

public class MacroCompiler extends FileTreeCompiler {

    private static final Pattern MACRO_PATTERN = Pattern.compile( "///(\\s)*?define(\\s)*(.+?)(\\s)*=(\\s)*(.+?)\\n" );

    public static void main(String[] args) {
        compile( args, new MacroCompiler() );
    }

    private String textBuffer;

    @Override
    public void compile(FileNode file) {

        textBuffer = file.getTextBuffer();

        LinkedList<MatchResult> macroDefs = findMacroDefs(textBuffer);

        macroDefs.forEach( matchResult -> {
            String defName = matchResult.group( 3 );
            String defValue = matchResult.group( 6 );

            System.out.println(defName + " = " + defValue);

            textBuffer = textBuffer
                    .replace( matchResult.group(), "" )
                    .replace( defName, defValue );
        });

        file.setTextBuffer( textBuffer );

    }

    private LinkedList<MatchResult> findMacroDefs(String textBuffer) {
        return StreamString.findAll( textBuffer, MACRO_PATTERN );
    }

}
