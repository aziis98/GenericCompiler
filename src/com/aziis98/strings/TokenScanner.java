package com.aziis98.strings;

import com.aziis98.strings.Tokenizzer.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class TokenScanner implements Cloneable {

    private LinkedList<Token> tokens;

    public TokenScanner(LinkedList<Token> tokens) {
        this.tokens = tokens;
    }


    public boolean isEmpty() {
        return tokens.isEmpty();
    }


    public Token assertion(Predicate<Token> predicate) {
        Token token = next();
        if (!predicate.test(token))
        {
            throw new RuntimeException("AssertionError at [" + token + "]!");
        }
        return token;
    }


    public Token next(String assertion) {
        return tokens.poll();
    }

    public Token next() {
        return tokens.poll();
    }


    public boolean hasType(String typeName) {
        return !tokens.isEmpty() && tokens.peek().typeName.equals(typeName);

    }

    public boolean hasText(String text) {
        return !tokens.isEmpty() && tokens.peek().sequence.equals(text);

    }

    public boolean has(Predicate<Token> predicate) {
        return predicate.test( tokens.peek() );
    }


    @SafeVarargs
    public final boolean hasSeq(Predicate<Token>... seq) {
        if (tokens.size() < seq.length) return false;

        LinkedList<Token> copy = new LinkedList<>(tokens);

        for (Predicate<Token> tokenPredicate : seq)
        {
            if (!tokenPredicate.test(copy.pop())) return false;
        }

        return true;
    }

    @SafeVarargs
    public final boolean hasOneIn(Predicate<Token>... possibles) {
        if (tokens.isEmpty()) return false;

        for (Predicate<Token> possible : possibles)
        {
            if (possible == null) continue;

            if (possible.test(tokens.peek())) return true;
        }
        return false;
    }

    public static Predicate<Token> type(String typeName) {
        return token -> token.typeName.equals(typeName);
    }

    public static Predicate<Token> text(String text) {
        return token -> token.sequence.equals(text);
    }


    public void filter(Predicate<Token> predicate) {
        this.tokens = tokens.stream()
                .filter(predicate)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void printOut() {
        tokens.forEach(System.out::println);
    }

    public boolean test(Predicate<TokenScanner> predicate) {
        return predicate.test(this.clone());
    }

    @Override
    @SuppressWarnings({ "CloneDoesntDeclareCloneNotSupportedException", "CloneDoesntCallSuperClone" })
    protected TokenScanner clone() {
        return new TokenScanner(new LinkedList<>(tokens));
    }

    public boolean nextOptional(Predicate<Token> predicate) {
        if (predicate.test(tokens.peek()))
        {
            tokens.pop();
            return true;
        }

        return false;
    }
}



















