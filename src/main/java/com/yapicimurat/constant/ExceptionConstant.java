package com.yapicimurat.constant;

public final class ExceptionConstant {
    private ExceptionConstant() {throw new AssertionError();}
    public static final String ENTITY_NOT_FOUND = "Veri bulunamadı.";
    public static final String ENTITY_ALREADY_EXISTS = "Veri zaten kayıtlı.";
    public static final String INVALID_ARGUMENT = "Verilen parametre değeri hatalı.";
}
