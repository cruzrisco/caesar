// --------------------------------------------------------------------
// File        : init/MindfulnessPopulator.groovy
// Description : Populator of the Mindfulness family of experiments
// Version     : 2.0
// Author      : Amador Durán
// Date        : 2022-04-16
// --------------------------------------------------------------------

// --------------------------------------------------------------------
// Last update : 2022-04-16 (Amador)
// Adaptation of Margarita's version to avoid problems with Grails.
// Include reference to the 1 entity in 1:n instead of using addToX()
// --------------------------------------------------------------------
// Last update : 2022-04-19 (Amador)
// new attribute report for URL of paper DOI or any other URL
// --------------------------------------------------------------------
// Last update : 2022-05-25 (Amador)
// Changes in some attribute names.
// --------------------------------------------------------------------
// Last update : 2022-06-06 (Amador)
// Code simplification.
// --------------------------------------------------------------------

package caesar

import caesar.EmpiricalStudy
import caesar.OriginalStudy
import caesar.Replication
import caesar.Change
import caesar.ChangeImpact


/**
 * Utility class to populate mindfulness studies
 */
class MindfulnessPopulator {

    /**
     * It creates and saves objects.
     */
    static populate() {

        EmpiricalStudy.withTransaction { status ->
            try {
                OriginalStudy mind1 = new OriginalStudy(
                        acronym: 'MIND#1',
                        report: 'https://doi.org/10.1145/2652524.2652539',
                        site: 'ETSII of University of Seville',
                        date: 'the first semester of the 2013-2014 academic year',
                        goal:
                                '''
To study whether mindfulness practice (cause) improves productivity in 
conceptual modelling (effect) on Software Engineering students (population).
''',
                        description:
                                '''
Taking as a sample population the students of the 2nd year ISEIS course of the 
Software Engineering Degree at the University of Seville (sample), one group 
(experimental group) attended 10-minute mindfulness sessions for 4 weeks, 4 days 
per week (experimental group treatment), while a second group (control group) 
attended a public speaking workshop as a placebo (control group treatment). 
Students completed two conceptual modelling exercises, one before and one after 
the treatment (pre-post experimental design), which were scored against a 
reference solution (measurement procedure). The performance of both groups was 
compared in terms of quality (similarity to the reference solution) and 
productivity (similarity in percentage per unit time)(metrics).
'''
                ).save()

                Replication mind2 = new Replication(
                        base: mind1,
                        report: 'https://doi.org/10.1016/j.jss.2016.06.104',
                        acronym: 'MIND#2',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS, Purpose.OVERCOME_LIMITATIONS],
                        site: 'ETSII of University of Seville',
                        date: 'the first semester of the 2014-2015 academic year',
                        comments: 'First replication after original experiment',
                ).save()

                Replication mind3 = new Replication(
                        base: mind2,
                        report: 'https://doi.org/10.1109/TSE.2020.2991699',
                        acronym: 'MIND#3',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS],
                        site: 'ETSII of University of Seville',
                        date: 'the first semester of the 2015-2016 academic year',
                        comments: 'Second replication based on previous replication'
                ).save()

                Change change1 = new Change(
                        replication: mind2,
                        name: 'Random assignment of subjects to groups',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: "Group assignment was based on subjects' preferences",
                        replicationSituation: 'Assignment to groups was randomized',
                        purpose: 'Mitigating selection and allocation bias and avoiding limitations of statistical analysis'
                ).save()

                new ChangeImpact(
                        change: change1,
                        validity: Validity.CONCLUSION_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'improves the power of applicable statistical tests'
                ).save()

                new ChangeImpact(
                        change: change1,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Eliminates assignment bias'
                ).save()

                Change change2 = new Change(
                        replication: mind2,
                        name: 'Increased duration of the treatment',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'The treatment was applied for 4 weeks, 4 days a week, in 10-minute sessions',
                        replicationSituation: 'The treatment was applied for 6 weeks, 4 days a week, in 12-minute sessions',
                        purpose: 'Increasing the effect of the treatment',
                        comments: 'Available time during class break was 20 minutes.'
                ).save()

                new ChangeImpact(
                        change: change2,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'It reflects better the effect of mindfulness practice'
                ).save()

                new ChangeImpact(
                        change: change2,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'It reinforces the effect of the treatment over other uncontrolled factors'
                ).save()

                Change change3 = new Change(
                        replication: mind2,
                        name: 'Null treatment of the control group',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'The control group received a placebo treatment consisting of a public speaking workshop',
                        replicationSituation: 'The control group received no treatment, as the public speaking workshop was postponed after the second task',
                        purpose: 'Mitigating the potential distorting factor of placebo on experimental results',
                        comments: 'The new public speaking workshop was online mainly.'
                ).save()

                new ChangeImpact(
                        change: change3,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'It eliminates the possibility that the placebo could have any effect on the experimental outcomes'
                ).save()

                Change change4 = new Change(
                        replication: mind3,
                        name: 'Changing the order of conceptual modelling exercises',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'The order in which the exercises were carried out was ERASMUS and then EoDP',
                        replicationSituation: 'The order in which the exercises were carried out was EoDP and then ERASMUS',
                        purpose: 'To study the potential impact of the conceptual modelling exercise on experimental results',
                ).save()

                new ChangeImpact(
                        change: change4,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SLIGHTLY_INCREASES,
                        rationale: 'It allows to study the effect of the difference between tasks on the experimental results'
                ).save()

                new ChangeImpact(
                        change: change4,
                        validity: Validity.CONCLUSION_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'It allows to analyze and adjust the effect of the difference between tasks on the experimental results'
                ).save()
            }
            catch (Exception e) {
                println "ERROR in MindfulnessPopulator.populate() ---> ${e.getMessage()}"
                status.setRollbackOnly()
            }
        }
    }
}