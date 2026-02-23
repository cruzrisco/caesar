// --------------------------------------------------------------------
// File        : init/CodePopulator.groovy
// Description : Populator of the Code family of experiments
// Version     : 2.0
// Author      : Amador Durán
// Date        : 2022-04-16
// --------------------------------------------------------------------

// --------------------------------------------------------------------
// Last update : 2022-04-16 (Amador)
// Adaptation of Margarita's version to avoid problems with Grails.
// Include reference to the 1 entity in 1:n instead of using addToX()
// --------------------------------------------------------------------
// Last update : 2022-04-20 (Margarita)
// new attribute report for URL of paper DOI or any other URL
// --------------------------------------------------------------------

package caesar
/**
 * Utility class to populate Code family of experiments
 */
class CodePopulator {

    /**
     * It creates and saves objects.
     */
    static populate() {
        EmpiricalStudy.withTransaction { status ->
            try {
                // Create and save original study
                OriginalStudy VVUPM = new OriginalStudy(
                        acronym: 'VV-UPM',
                        report: 'https://doi.org/10.1109/ESEM.2009.5314236',
                        site: 'Polytechnic University of Madrid',
                        date: '2001',
                        goal:
                                '''
To evaluate the effectiveness of three code verification and validation 
techniques.
''',
                        description:
                                '''
Subjects evaluated the three techniques by applying each one to the C 
programs containing the errors to be detected. Previously, the subjects
received training in the use of each of the error detection techniques.
''',
                        comments: "Original study by Juristo and Vegas."
                ).save()

                // Create and save replications
                Replication VVUPM1 = new Replication(
                        base: VVUPM,
                        report: 'https://doi.org/10.1109/ESEM.2009.5314236',
                        acronym: 'VV-UPM1',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.GENERALIZE_RESULTS],
                        site: 'Polytechnic University of Madrid',
                        date: '2002',
                        comments: 'First replication after original experiment'
                ).save()

                Replication VVUPV = new Replication(
                        base: VVUPM,
                        report: 'https://doi.org/10.1109/ICST.2012.113',
                        acronym: 'VV-UPV',
                        type: ReplicationType.EXTERNAL,
                        purposes: [Purpose.GENERALIZE_RESULTS],
                        site: 'Polytechnic University of Valencia',
                        date: 'around 2005'
                ).save()

                Replication VVUS = new Replication(
                        base: VVUPM,
                        report: 'https://doi.org/10.1016/j.infsof.2012.07.016',
                        acronym: 'VV-US',
                        type: ReplicationType.EXTERNAL,
                        purposes: [Purpose.GENERALIZE_RESULTS],
                        site: 'University of Seville',
                        date: 'around 2005'
                ).save()

                Replication VVORT = new Replication(
                        base: VVUPM,
                        report: 'https://doi.org/10.1016/j.infsof.2012.07.016',
                        acronym: 'VV-ORT',
                        type: ReplicationType.EXTERNAL,
                        purposes: [Purpose.GENERALIZE_RESULTS],
                        site: 'University ORT Uruguay',
                        date: 'around 2005'
                ).save()

                // Create and save changes of VVUPM1
                Change change1 = new Change(
                        replication: VVUPM1,
                        name: 'Visibility of fault',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.METRICS,
                        baseSituation: 'The visibility of fault was not analysed',
                        replicationSituation: 'The visibility of fault or the number of people who detected the fault was analysed',
                        purpose: 'To draw new conclusions'
                ).save()

                ChangeImpact effect1 = new ChangeImpact(
                        change: change1,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'The effect for the evaluation of each technique is better reflected'
                ).save()

                Change change2 = new Change(
                        replication: VVUPM1,
                        name: 'Two versions of each programme',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'The programme is a factor (independent variable) although its influence was not analysed',
                        replicationSituation: 'Two versions of each program are implemented and is a new factor',
                        purpose: 'To draw new conclusions'
                ).save()

                ChangeImpact effect2 = new ChangeImpact(
                        change: change2,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'The possible influence of programme version is analysed'
                ).save()

                Change change3 = new Change(
                        replication: VVUPM1,
                        name: 'Increase the number of faults',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.METRICS,
                        baseSituation: 'There are three types of faults that appeared once. The other three types of faults appeared twice',
                        replicationSituation: 'All types of faults doubled',
                        purpose: 'Increase the number of faults as there were two versions of each programme'
                ).save()

                ChangeImpact effect3 = new ChangeImpact(
                        change: change3,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'By increasing the number of faults to be detected, the comparison of techniques is facilitated'
                ).save()

                Change change4 = new Change(
                        replication: VVUPM1,
                        name: 'Test cases',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_MATERIAL,
                        baseSituation: 'The subjects generated their test cases to detect faults in the code',
                        replicationSituation: 'First, the subjects applied the technique to generate the test cases and then executed the test cases provided to them in order to detect program failures',
                        purpose: 'Check whether the visibility of faults influences their detection'
                ).save()

                ChangeImpact effect4 = new ChangeImpact(
                        change: change4,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SLIGHTLY_INCREASES,
                        rationale: 'The generation and execution of test cases reinforces the effect of the technique'
                ).save()

                Change change5 = new Change(
                        replication: VVUPM1,
                        name: 'Discarding a programme',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'Four programs were used',
                        replicationSituation: 'Three programs were used, one was discarded',
                        purpose: 'Balance the design'
                ).save()

                ChangeImpact effect5 = new ChangeImpact(
                        change: change5,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'One of the programmes on which failures were detected is removed'
                ).save()

                Change change6 = new Change(
                        replication: VVUPM1,
                        name: 'Techniques applied by each subject',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'Each subject applied a technique',
                        replicationSituation: 'Each subject applied the three techniques',
                        purpose: 'To facilitate the comparison of techniques'
                ).save()

                ChangeImpact effect6 = new ChangeImpact(
                        change: change6,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'The applications of each technique are increased and comparability is facilitated'
                ).save()

                // Create and save changes of VVUPV
                Change change7 = new Change(
                        replication: VVUPV,
                        name: 'Discarding technique',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'The three verification and validation techniques were used: code reading, equivalence partitioning and branch testing',
                        replicationSituation: 'The code reading technique was omitted',
                        purpose: 'Adapt to the time available'
                ).save()

                ChangeImpact effect7 = new ChangeImpact(
                        change: change7,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'One of the levels of the techniques factor is removed'
                ).save()

                Change change8 = new Change(
                        replication: VVUPV,
                        name: 'Duration of the sessions',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'The duration of the 3 sessions was 4h. each, i.e. the time was unlimited',
                        replicationSituation: 'The duration of each of the 3 sessions was 2h',
                        purpose: 'Adapt to the time available'
                ).save()

                ChangeImpact effect8 = new ChangeImpact(
                        change: change8,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'Time constraints may influence results'
                ).save()

                Change change9 = new Change(
                        replication: VVUPV,
                        name: 'Training period',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'Subjects received three four-hour training sessions to learn how to apply the techniques',
                        replicationSituation: 'The training consisted of two two-hour tutorials',
                        purpose: 'Take advantage of the fact that the subjects are already familiar with the techniques'
                ).save()

                ChangeImpact effect9 = new ChangeImpact(
                        change: change9,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.DOES_NOT_AFFECT,
                        rationale: 'The subjects are already familiar with the techniques and the training is not operationalised'
                ).save()

                Change change10 = new Change(
                        replication: VVUPV,
                        name: 'Training in each technique',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'Training in the use of the techniques was prior to the execution of the experiment',
                        replicationSituation: 'At the beginning of each session, a tutorial on the use of the technique was  conducted',
                        purpose: 'Take advantage of the fact that the subjects are already familiar with the techniques'
                ).save()

                ChangeImpact effect10 = new ChangeImpact(
                        change: change10,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.DOES_NOT_AFFECT,
                        rationale: 'The subjects are already familiar with the techniques'
                ).save()

                Change change11 = new Change(
                        replication: VVUPV,
                        name: 'Application of techniques',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'Subjects applied a technique to a program in each session',
                        replicationSituation: 'Subjects applied the same technique to different programs in each session',
                        purpose: 'Adapt to the time available'
                ).save()

                ChangeImpact effect11 = new ChangeImpact(
                        change: change11,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_DECREASES,
                        rationale: 'The three techniques are not analysed and compared'
                ).save()

                Change change12 = new Change(
                        replication: VVUPV,
                        name: 'Test cases in separate session',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'Subjects performed the test cases with the application of the technique, i.e. in each session',
                        replicationSituation: 'Subjects performed the test cases of one of the programmes they tested in a separate session',
                        purpose: 'Adapt to the time available'
                ).save()

                ChangeImpact effect12 = new ChangeImpact(
                        change: change12,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'the effect of conducting the test cases in a separate session is analysed'
                ).save()

                // Create and save changes of VVUds
                Change change13 = new Change(
                        replication: VVUS,
                        name: 'Session duraction',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'The duration of the 3 sessions was 4h. each, i.e. the time was unlimited',
                        replicationSituation: 'the duration of each of the 3 sessions was 2h',
                        purpose: 'Adapt to the time available'
                ).save()

                ChangeImpact effect13 = new ChangeImpact(
                        change: change13,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'Time constraints may influence results'
                ).save()

                Change change14 = new Change(
                        replication: VVUS,
                        name: 'Test cases in a separate session',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'Subjects performed the test cases with the application of the technique, i.e. in each session',
                        replicationSituation: 'Subjects performed the test cases of one of the programmes they tested in a later session',
                        purpose: 'Adapt to the time available'
                ).save()

                ChangeImpact effect14 = new ChangeImpact(
                        change: change14,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SLIGHTLY_DECREASES,
                        rationale: 'Time constraints may influence results'
                ).save()

                Change change15 = new Change(
                        replication: VVUS,
                        name: 'Subjects work in pairs',
                        dimension: ChangeDimension.CONTEXT,
                        affectedElement: 'Working methods',
                        baseSituation: 'Subjects worked individually',
                        replicationSituation: 'subjects worked in pairs',
                        purpose: 'Use available computers'
                ).save()

                ChangeImpact effect15 = new ChangeImpact(
                        change: change15,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SLIGHTLY_DECREASES,
                        rationale: 'it could affect the results'
                ).save()

                Change change16 = new Change(
                        replication: VVUS,
                        name: 'Reduction of training period',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'subjects received three four-hour training sessions to learn how to apply the techniques',
                        replicationSituation: 'The training consisted of two two-hour tutorials',
                        purpose: 'Take advantage of the fact that the subjects are already familiar with the techniques'
                ).save()

                ChangeImpact effect16 = new ChangeImpact(
                        change: change16,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.DOES_NOT_AFFECT,
                        rationale: 'The subjects are already familiar with the techniques'
                ).save()

                Change change17 = new Change(
                        replication: VVUS,
                        name: 'Training of each technique',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'Training in the use of the techniques was prior to the execution of the experiment',
                        replicationSituation: 'Each tutorial was conducted in each of the three sessions in which each technique was examined, i.e. the training was interspersed with the performance of the experiment',
                        purpose: 'Take advantage of the fact that the subjects are already familiar with the techniques'
                ).save()

                ChangeImpact effect17 = new ChangeImpact(
                        change: change17,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.DOES_NOT_AFFECT,
                        rationale: 'The subjects are already familiar with the techniques'
                ).save()

                // Create and save changes of VVORT
                Change change18 = new Change(
                        replication: VVORT,
                        name: 'Discarding a technique',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'The three verification and validation techniques were used: code reading, equivalence partitioning and branch testing',
                        replicationSituation: 'The code reading technique was omitted',
                        purpose: 'Adapt to the time available'
                ).save()

                ChangeImpact effect18 = new ChangeImpact(
                        change: change18,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'One of the levels of the techniques factor is removed'
                ).save()

                Change change19 = new Change(
                        replication: VVORT,
                        name: 'Discard a programme',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'Four programs were used',
                        replicationSituation: 'Three programs were used, one was discarded',
                        purpose: 'Balance the design and adapt to the time available'
                ).save()

                ChangeImpact effect19 = new ChangeImpact(
                        change: change19,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'One of the programmes on which failures were detected is removed'
                ).save()

                Change change20 = new Change(
                        replication: VVORT,
                        name: 'Duration of sessions',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'The duration of the 3 sessions was 4h. each, i.e. the time was unlimited',
                        replicationSituation: 'The experiment was carried out in a single session',
                        purpose: 'Adapt to the time available'
                ).save()

                ChangeImpact effect20 = new ChangeImpact(
                        change: change20,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'Time constraints may influence influence results'
                ).save()

                Change change21 = new Change(
                        replication: VVORT,
                        name: 'Technique per programme',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'subjects applied a different technique to evaluate a program in each of the three sessions',
                        replicationSituation: 'the subjects applied two techniques to two programmes in a single session',
                        purpose: 'Adapt to the time available'
                ).save()

                ChangeImpact effect21 = new ChangeImpact(
                        change: change21,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'One of the techniques and one of the programmes is not used'
                ).save()
            }
            catch (Exception e) {
                println "ERROR in CodePopulator.populate() ---> ${e.getMessage()}"
                status.setRollbackOnly()
            }
        }
    }
}
