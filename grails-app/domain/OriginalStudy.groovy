// --------------------------------------------------------------------
// File        : domain/OriginalStudy.groovy
// Description : Entity for original studies
// Version     : 2.0
// Author      : Amador Durán
// Date        : 2022-04-16
// --------------------------------------------------------------------

// --------------------------------------------------------------------
// Last update : 2022-04-16 (Amador)
// getValidityEvolution(Validity v) added
// --------------------------------------------------------------------

package caesar

import caesar.Validity
import caesar.Replication
import caesar.EmpiricalStudy

class OriginalStudy extends EmpiricalStudy {
    String goal
    String description

    /**
     *  it returns a list of the accumulated effects of all the
     *  experiments in its family on a given validity passed as an
     *  argument
     * @param Validity v : validity type to get accumulated effects for
     * @return List<Integer> : list of accumulated effects for each
     * replication in the family
     */
    List<Integer> getValidityEvolution(Validity v) {
        List<Integer> result = [0]

        family.eachWithIndex { Replication r, Integer index ->
            result[index+1] = result[index] + r.getValidityDelta( v )
        }

        return result
    }

    static constraints = {
        goal(maxSize:2048, nullable: true, widget:'textarea')
        description(maxSize:4096, nullable: true, widget:'textarea')
    }
}
