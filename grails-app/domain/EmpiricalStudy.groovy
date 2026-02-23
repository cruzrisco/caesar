// --------------------------------------------------------------------
// File        : domain/EmpiricalStudy.groovy
// Description : Abstract entity for empirical studies
// Version     : 2.0
// Author      : Amador Durán
// Date        : 2022-04-16
// --------------------------------------------------------------------

// --------------------------------------------------------------------
// Last update : 2022-04-16 (Amador)
// getFamily() added with recursivity checking
// replications validation commented
// --------------------------------------------------------------------
// Last update : 2022-04-19 (Amador)
// new attribute report for URL of paper DOI or any other URL
// --------------------------------------------------------------------

package caesar

import caesar.Replication
import org.grails.core.exceptions.GrailsDomainException
import org.springframework.validation.Errors

abstract class EmpiricalStudy {
    String acronym
    String report
    String site
    String date
    String comments

    List<Replication> replications

    static hasMany   = [replications:Replication]
    static mappedBy  = [replications:'base'] // bidirectional association

    /**
     * It returns the list of replications of this empirical study
     * transitively
     * @return List<Replications> : list of transitive replications
     */
    List<Replication> getFamily() {
        // call auxiliary private method to check for recursive loops
        return this._getFamily()
    }

    static transients = ['family']

    /**
     * It returns the list of replications of this empirical study
     * transitively but checking for potential replications loops that
     * should be checked in validators but I haven't been able to do it yet
     * @param prefamily : previosuly collected replications
     * @return List<Replications> : list of transitive replications
     */
    protected List<Replication> _getFamily( Set<EmpiricalStudy> prefamily = [] ) {

        // check it is not a replication of itself
        if ( replications.contains(this) ){
            throw new GrailsDomainException(
                    "ERROR: recursive replication structure in ${this} -> ${this}"
            )
        }

        // check its replications have not been visited yet
        if( !prefamily.disjoint( replications ) ) {
            List<Replication> recursive = prefamily.intersect( replications )
            throw new GrailsDomainException(
                    "ERROR: recursive replication structure in ${this} -> ${recursive}"
            )
        }

        // add itself and all its replications to prefamily
        prefamily.add( this )
        prefamily.addAll( replications )

        // start family with their replications
        List<Replication> _family = replications

        // get the rest of the family
        replications.each {Replication r ->
            _family = _family + r._getFamily( prefamily )
        }

        return _family
    }

    static constraints = {
        acronym(maxSize:15, blank:false, unique:true)
        report(url: true, nullable: true)
        site(maxSize:256, nullable: true, widget:'textarea')
        date(maxSize:256, nullable: true)
        comments(maxSize:2048, nullable: true, widget:'textarea')
        // https://stackoverflow.com/questions/7916333/grails-preventing-a-recursive-one-to-many-relationship
        /*
        replications(validator: { List<Replication> val, EmpiricalStudy obj ->
            // why is val almost always null?
            println()
            println "EmpiricalStudy.replications.validator"
            println "-------------------------------------"
            println "List<Replication> val = ${val}"
            println "EmpiricalStudy obj = ${obj}"
            println "val?.contains(obj) = ${val?.contains(obj)}"
            if(val?.contains(obj)) {
                return ['empiricalStudy.replications.cyclicReplication']
            }
            // How to check multilevel recursivity?
            // Calculate ancestors and descendants and check
            // that they are disjoint with val
            // How can be the old value of replications accessed?
        })
        */
    }

    String toString() {
        acronym
    }
}
