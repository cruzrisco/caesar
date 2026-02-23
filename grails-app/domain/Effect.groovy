package caesar

enum Effect implements org.springframework.context.MessageSourceResolvable {
    SUBSTANTIALLY_INCREASES,
    MODERATELY_INCREASES,
    SLIGHTLY_INCREASES,
    DOES_NOT_AFFECT,
    SLIGHTLY_DECREASES,
    MODERATELY_DECREASES,
    SUBSTANTIALLY_DECREASES

    Object[] getArguments() { [] as Object[] }
    String[] getCodes() { [ name() ] }
    String getDefaultMessage() { "?-" + name() }
}