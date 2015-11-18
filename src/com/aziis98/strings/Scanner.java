package com.aziis98.strings;

import java.util.function.*;
import java.util.regex.*;

public class Scanner {

    private String source;
    private int    carret;

    public Scanner(String source) {
        this.source = source;
        this.carret = 0;
    }

    // internal
    public boolean hasNext(String string) {
        return source.startsWith(string);
    }

    // internal
    public boolean hasNext(Predicate<Integer> characterPredicate) {
        return characterPredicate.test((int) source.charAt(0));
    }

    // internal
    public StringMatch hasRegex(String regex) {
        Matcher matcher = Pattern.compile("^(?<test>" + regex + ").*", Pattern.DOTALL).matcher(getRemaining());
        if (matcher.lookingAt())
        {
            return StringMatch.success(matcher.group("test"));
        }
        else
        {
            return StringMatch.fail();
        }
    }

    // internal
    public void moveCarret(int amount) {
        carret += amount;
    }

    // util
    public void jumpSpaces() {
        StringMatch result = hasRegex("\\s+");
        moveCarret(result.getLength());
    }


    public String getSource() {
        return source;
    }

    public int getCarretPosition() {
        return carret;
    }

    public String getRemaining() {
        return source.substring(carret);
    }

    public void pop(String string) {
        if (getRemaining().startsWith(string))
        {
            moveCarret(string.length());
        }
        else
        {
            throw new RuntimeException("Unable to pop: '" + string + "', the buffer doesn't match: '" + getErrorBuffer(string.length() + 10));
        }
    }

    public StringMatch goBefore(String regex) {
        StringMatch stringMatch = null;

        while (!(stringMatch = hasRegex(regex)).getSuccess())
        {
            carret++;
        }

        return stringMatch;
    }

    public void goAfter(String regex) {
        moveCarret(goBefore(regex).getLength());
    }

    public void setCarret(int carret) {
        this.carret = carret;
    }

    private String getErrorBuffer(int size) {
        String buffer = getRemaining();
        size = Math.min(buffer.length(), size);
        return buffer.substring(0, size);
    }

    public String allUntil(String regex) {
        String accumulator = "";
        while (!hasRegex(regex).getSuccess())
        {
            accumulator += source.charAt(carret);
            carret++;
        }
        return accumulator;
    }
}
