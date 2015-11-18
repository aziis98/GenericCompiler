package com.aziis98.compiler;

public interface ITranslatable<InputLang extends AbstractLanguage, OutputLang extends AbstractLanguage> {

    GElement<OutputLang> translate(GElement<InputLang> element);

}
