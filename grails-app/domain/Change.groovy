package caesar

import caesar.Replication
import caesar.ChangeImpact
import caesar.Validity

class Change {
    String name
    ChangeDimension dimension
    String affectedElement
    String baseSituation
    String replicationSituation
    String purpose
    String comments

    List<ChangeImpact> impacts

    /**
     * It returns the number of the change in its replication.
     * Create change crashes because replication is null.
     * */
    int getNumber() {
        replication ? replication.changes.indexOf(this) + 1 : 1
    }

    String getAcronym() {
        "${replication}-${number}"
    }
    static transients = ['number','acronym']

    static hasMany   = [impacts:ChangeImpact]
    static belongsTo = [replication:Replication]

    /**
     *  It gets the sum of effects of change influences affecting
     *  the validity passed as an argument
     */
    int getValidityDelta(Validity v) {
        int delta = 0

        impacts.each { ChangeImpact i ->
            if ( i.validity == v ) {
                delta += ChangeImpact.ImpactEffectDelta[i.effect]
            }
        }

        return delta
    }

    static constraints = {
        name(blank:false)
        replication(blank:false)
        dimension(blank:false)
        affectedElement()
        baseSituation(maxSize:2048, nullable: true, widget:'textarea')
        replicationSituation(maxSize:2048, nullable: true, widget:'textarea')
        purpose(maxSize:2048, nullable: true, widget:'textarea')
        impacts()
        comments(maxSize:2048, nullable: true, widget:'textarea')
    }

    String toString() {
        "${acronym} - ${name}"
    }
}

enum ChangeDimension implements org.springframework.context.MessageSourceResolvable {
    OPERATIONALIZATION,
    PROTOCOL,
    CONTEXT,
    POPULATION,
    EXPERIMENTER

    Object[] getArguments() { [] as Object[] }
    String[] getCodes() { [ name() ] }
    String getDefaultMessage() { "?-" + name() }
}

enum OperationalizationElement implements org.springframework.context.MessageSourceResolvable {
    TREATMENT,
    METRICS,
    MEASUREMENT_PROCEDURES

    Object[] getArguments() { [] as Object[] }
    String[] getCodes() { [ name() ] }
    String getDefaultMessage() { "?-" + name() }
}

enum ProtocolElement implements org.springframework.context.MessageSourceResolvable {
    EXPERIMENTAL_DESIGN,
    EXPERIMENTAL_MATERIAL,
    EXPERIMENTAL_GUIDES,
    MEASUREMENT_INSTRUMENTS,
    DATA_ANALYSIS_TECHNIQUES

    Object[] getArguments() { [] as Object[] }
    String[] getCodes() { [ name() ] }
    String getDefaultMessage() { "?-" + name() }
}

enum ExperimenterRole implements org.springframework.context.MessageSourceResolvable {
    DESIGNER,
    TRAINER,
    MONITOR,
    MEASURER,
    ANALYST

    Object[] getArguments() { [] as Object[] }
    String[] getCodes() { [ name() ] }
    String getDefaultMessage() { "?-" + name() }
}
