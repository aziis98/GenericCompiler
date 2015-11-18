package com.aziis98.compiler;

import com.aziis98.strings.*;

public interface IFormattable {

    default String toFormattedText() {
        CodeBuilder codeBuilder = new CodeBuilder();
        toFormattedText( codeBuilder );
        return codeBuilder.toFormattedText();
    }

    void toFormattedText(CodeBuilder codeBuilder);

}
