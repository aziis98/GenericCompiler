package com.aziis98.compiler;

public interface ITranslatable<InputLang extends AbstractCompiler, OutputLang extends AbstractCompiler> {

    GElement<OutputLang> translate(GElement<InputLang> element);

}
