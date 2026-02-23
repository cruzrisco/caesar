// --------------------------------------------------------------------
// File        : domain/Replication.groovy
// Description : Entity for replication studies
// Version     : 2.0
// Author      : Amador Durán
// Date        : 2022-04-16
// --------------------------------------------------------------------

// --------------------------------------------------------------------
// Last update : 2022-04-16 (Amador)
// getValidityDelta(Validity v) added
// base validation commented
// --------------------------------------------------------------------

package caesar

class Replication extends EmpiricalStudy {
    ReplicationType type
    SortedSet<Purpose> purposes
    List<Change> changes

    static belongsTo = [base:EmpiricalStudy]
    static hasMany   = [changes:Change,purposes:Purpose]

    /**
     * It returns the original study at the root of the family of
     * experiments
     * @return EmpiricalStudy
     */
    EmpiricalStudy getOriginal() {
        EmpiricalStudy original = this.base

        while( !original.instanceOf( OriginalStudy ) ) {
            original = original.base
        }

        return original
    }

    static transients = ['original']

    /**
     *  It gets the sum of effects of change influences affecting
     *  the validity passed as an argument
     */
    int getValidityDelta(Validity v) {
        int delta = 0

        changes.each { Change c ->
            delta += c.getValidityDelta(v)
        }

        return delta
    }

    static constraints = {
        type(blank:false)
        purposes()
        changes()
        // this validation crashes, why?
        // https://docs.grails.org/latest/ref/Constraints/validator.html
        /*
        base(validator: { EmpiricalStudy val, Replication obj ->
            println()
            println "Replication.base.validator"
            println "--------------------------"
            println "EmpiricalStudy val = ${val}"
            println "Replication obj = ${obj}"
            println "obj.is(val) = ${obj.is(val)}"
            if ( obj.is(val) ) {
                println "ERROR: self replication!"
                return ['replication.base.selfReplication']
            }
            // How to check multilevel recursivity?
            // Calculate ancestors and descendants and check
            // that they do not contain val
        })
        */
    }
}

enum ReplicationType implements org.springframework.context.MessageSourceResolvable {
    INTERNAL,
    EXTERNAL

    Object[] getArguments() { [] as Object[] }
    String[] getCodes() { [ name() ] }
    String getDefaultMessage() { "?-" + name() }
}

enum Purpose implements org.springframework.context.MessageSourceResolvable {
    CONFIRM_RESULTS,
    GENERALIZE_RESULTS,
    OVERCOME_LIMITATIONS

    Object[] getArguments() { [] as Object[] }
    String[] getCodes() { [ name() ] }
    String getDefaultMessage() { "?-" + name() }
}
