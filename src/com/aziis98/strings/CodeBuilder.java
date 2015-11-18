package com.aziis98.strings;

import java.util.*;
import java.util.stream.*;

public class CodeBuilder {

    LinkedList<String> lines = new LinkedList<>();
    String current = "";
    String tabSequence = "    ";
    int currentScope = 0;

    public CodeBuilder() {
    }

    public CodeBuilder(int currentScope) {
        this.currentScope = currentScope;
    }

    public void indented(Runnable indented) {
        indentPush();
        indented.run();
        indentPop();
    }

    public void indentPush() {
        currentScope++;
    }

    public void indentPop() {
        currentScope--;
    }

    public void appendText(String text) {
        current += text;
    }

    public void appendLine() {
        lines.add(genLinePrefix() + current);
        current = "";
    }

    public void appendLine(String text) {
        appendText(text);
        appendLine();
    }

    private String genLinePrefix() {
        String s = "";

        for (int i = 0; i < currentScope; i++)
        {
            s += tabSequence;
        }

        return s;
    }

    public String toFormattedText() {
        return lines.stream().collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return String.format("{lines: %s, currentScope: %s}", lines.toString(), currentScope);
    }

    public void appendFormatedLine(String line, String... strings) {
        for (int i = 0; i < strings.length; i++)
        {
            String string = strings[i];
            line = line.replace("#" + i, string);
        }

        appendLine(line);
    }
}
