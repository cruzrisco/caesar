package caesar

import caesar.Change
import caesar.Effect

class ChangeImpact {
    Validity validity
    Effect effect
    String rationale
    String comments

    static belongsTo = [change:Change]
    
    static constraints = {
        change()
        validity()
        effect()
        rationale(maxSize:2048, nullable: true, widget:'textarea')
        comments(maxSize:2048, nullable: true, widget:'textarea')
    }

    String toString() {
        "${effect}S ${validity} (${rationale})"
    }

    /**
     * Map of integer values associated to effect sizes
     */
    public static ImpactEffectDelta = [
            (Effect.SUBSTANTIALLY_INCREASES): 3,
            (Effect.MODERATELY_INCREASES)   : 2,
            (Effect.SLIGHTLY_INCREASES)     : 1,
            (Effect.DOES_NOT_AFFECT)        : 0,
            (Effect.SLIGHTLY_DECREASES)     : -1,
            (Effect.MODERATELY_DECREASES)   : -2,
            (Effect.SUBSTANTIALLY_DECREASES): -3
    ]
}

enum Validity implements org.springframework.context.MessageSourceResolvable {
    CONSTRUCT_VALIDITY,
    INTERNAL_VALIDITY,
    EXTERNAL_VALIDITY,
    CONCLUSION_VALIDITY

    Object[] getArguments() { [] as Object[] }
    String[] getCodes() { [ name() ] }
    String getDefaultMessage() { "?-" + name() }
}
