package com.aziis98.strings;

public class StringMatch {

    public boolean success;
    public String match;

    public interface IMatcher {
        StringMatch testString(Scanner scanner);
    }

    private StringMatch(boolean success, String match) {
        this.success = success;
        this.match = match;
    }

    public boolean getSuccess() {
        return success;
    }

    public int getLength() {
        if (!getSuccess()) return 0;
        return match.length();
    }


    public static StringMatch success(String match) {
        return new StringMatch(true, match);
    }

    public static StringMatch fail() {
        return new StringMatch(false, null);
    }

}
