package com.aziis98.strings;

import jdk.nashorn.internal.runtime.*;

import java.util.*;
import java.util.regex.*;

public class Tokenizzer {

    private LinkedList<TokenInfo> tokenInfos = new LinkedList<>();


    public Tokenizzer add(String name, String regex, int priority) {
        tokenInfos.add(new TokenInfo(name, Pattern.compile("^("+regex+")"), priority));
        return this;
    }

    public Tokenizzer add(String name, String regex) {
        return add(name, regex, 0);
    }


    public LinkedList<Token> tokenParse(String source) {
        LinkedList<Token> tokens = new LinkedList<>();
        boolean match;

        while (!source.equals(""))
        {
            match = false;

            for (TokenInfo info : tokenInfos)
            {
                Matcher m = info.regex.matcher(source);

                if (m.find())
                {
                    match = true;

                    String tok = m.group().trim();
                    tokens.add(new Token(info.typeName, tok));

                    source = m.replaceFirst("");
                    break;
                }
            }

            if (!match)
            {
                throw new ParserException(
                        "Unexpected character in input: " + source);
            }
        }

        return tokens;
    }


    private static class TokenInfo {
        public String  typeName;
        public Pattern regex;
        public int     priority;

        public TokenInfo(String typeName, Pattern regex, int priority) {
            this.typeName = typeName;
            this.regex = regex;
            this.priority = priority;
        }

        public TokenInfo(String typeName, Pattern regex) {
            this.typeName = typeName;
            this.regex = regex;
            this.priority = 0;
        }
    }

    public static class Token {
        public String typeName;
        public String sequence;

        public Token(String typeName, String sequence) {
            this.typeName = typeName;
            this.sequence = sequence;
        }

        public boolean is(String typeName) {
            return this.typeName.equals(typeName);
        }

        @Override
        public String toString() {
            return typeName + "    seq: '" + sequence + "'";
        }
    }
}
