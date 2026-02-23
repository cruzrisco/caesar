// --------------------------------------------------------------------
// File        : init/Bootstrap.groovy
// Description : Initialization script
// Version     : 2.0
// Author      : Amador Durán
// Date        : 2022-04-16
// --------------------------------------------------------------------

// --------------------------------------------------------------------
// Last update : 2022-04-16 (Amador)
// Restructured to call populators
// --------------------------------------------------------------------

package caesar

import caesar.CodePopulator
import caesar.MindfulnessPopulator
import caesar.RequirementsPopulator
import caesar.SoilPopulator

class BootStrap {

    def init = { servletContext ->
        MindfulnessPopulator.populate()
        SoilPopulator.populate()
		RequirementsPopulator.populate()
		CodePopulator.populate()
    }

    def destroy = {
    }
}
