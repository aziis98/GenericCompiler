package com.aziis98.strings;

public class StreamString {

    public String string;

    private StreamString(String string) {
        this.string = string;
    }

    public static StreamString stream(String string) {
        return new StreamString(string);
    }

    public StreamString removeBlanks() {
        return stream(this.string.replaceAll("\\s\\s", ""));
    }

    public StreamString quote() {
        return stream('"' + this.string + '"');
    }

    public StreamString quote(String seq) {
        return stream(seq + this.string + seq);
    }

    public StreamString quote(String prefix, String suffix) {
        return stream(prefix + this.string + suffix);
    }

    @Override
    public String toString() {
        return string;
    }
}
