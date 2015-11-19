package com.aziis98.compiler;

import com.aziis98.io.*;

public abstract class AbstractLanguage {

    public abstract void compile(DirectoryNode projectStructure, DirectoryNode compiledStructure);

    public static void compile(String[] args, AbstractLanguage language) {

    }

}
