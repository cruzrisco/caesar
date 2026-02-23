// --------------------------------------------------------------------
// File        : init/RequirementsPopulator.groovy
// Description : Populator of the Requirements family of experiments
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
 * Utility class to populate requirements studies
 */
class RequirementsPopulator {

    /**
     * It creates and saves objects.
     */
    static populate() {

        EmpiricalStudy.withTransaction { status ->
            try {
                // Create and save original study
                OriginalStudy q2007 = new OriginalStudy(
                        acronym: 'Q-2007',
                        report: 'https://doi.org/10.20868/UPM.thesis.40566',
                        site: 'Polytechnic University of Madrid',
                        date: '2007',
                        goal:
                                '''
To study the influence of analysts’ experience and domain knowledge 
on the effectiveness of requirements elicitation.
''',
                        description:
                                '''
Analysts' effectiveness was analysed using interviews as the requirements 
elicitation technique. In the interviews, the experimenter played the role of 
the customer by answering questions from the experimental subjects (analysts) 
about two possible problems, one from a known domain and another one from an 
unknown domain. To measure the effectiveness of consolidation, after some time, 
the analyst wrote down what he/she remembered from the interview and the number
of problem items mentioned by the analyst was measured.
''',
                        comments: '''
PhD dissertation of Alejandrina Aranda López King, supervised by O. Dieste and N. Juristo. 
'''
                ).save()

                // Create and save replications
                Replication q2009 = new Replication(
                        base: q2007,
                        report: 'https://doi.org/10.20868/UPM.thesis.40566',
                        acronym: 'Q-2009',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS],
                        site: 'Polytechnic University of Madrid',
                        date: '2009',
                        comments: 'First replication after original experiment'
                ).save()

                Replication q2011 = new Replication(
                        base: q2009,
                        report: 'https://doi.org/10.20868/UPM.thesis.40566',
                        acronym: 'Q-2011',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS, Purpose.GENERALIZE_RESULTS],
                        site: 'Polytechnic University of Madrid',
                        date: '2011'
                ).save()

                Replication q2012 = new Replication(
                        base: q2011,
                        report: 'https://doi.org/10.20868/UPM.thesis.40566',
                        acronym: 'Q-2012',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS],
                        site: 'Polytechnic University of Madrid',
                        date: '2012'
                ).save()

                Replication E2012A = new Replication(
                        base: q2012,
                        report: 'https://doi.org/10.20868/UPM.thesis.40566',
                        acronym: 'E-2012A',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS],
                        site: 'Polytechnic University of Madrid',
                        date: '2012'
                ).save()

                Replication E2012B = new Replication(
                        base: E2012A,
                        report: 'https://doi.org/10.20868/UPM.thesis.40566',
                        acronym: 'E-2012B',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS],
                        site: 'Polytechnic University of Madrid',
                        date: '2012'
                ).save()

                Replication E2013 = new Replication(
                        base: E2012B,
                        report: 'https://doi.org/10.20868/UPM.thesis.40566',
                        acronym: 'E-2013',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS],
                        site: 'Polytechnic University of Madrid',
                        date: '2013'
                ).save()

                Replication E2014 = new Replication(
                        base: E2013,
                        report: 'https://doi.org/10.20868/UPM.thesis.40566',
                        acronym: 'E-2014',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS],
                        site: 'Polytechnic University of Madrid',
                        date: '2014'
                ).save()

                Replication E2015 = new Replication(
                        base: E2014,
                        report: 'https://doi.org/10.20868/UPM.thesis.40566',
                        acronym: 'E-2015',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS],
                        site: 'Polytechnic University of Madrid',
                        date: '2015'
                ).save()

                // Create and save changes of q2009
                Change change1 = new Change(
                        replication: q2009,
                        name: 'Effectiveness analysis',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.METRICS,
                        baseSituation: "Analysts’ effectiveness in interview sessions was analyzed",
                        replicationSituation: 'Analysts’ effectiveness in interview was not analyzed',
                        purpose: 'Reduce the high cost of transcribing and analysing all interviews.'
                ).save()

                ChangeImpact effect1 = new ChangeImpact(
                        change: change1,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_DECREASES,
                        rationale: 'the dependent variable is not analysed'
                ).save()

                Change change2 = new Change(
                        replication: q2009,
                        name: 'Retention capacity analysis',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.METRICS,
                        baseSituation: 'Retention capacity was analyzed',
                        replicationSituation: 'Retention capacity was not analysed',
                        purpose: 'Reduce the high cost of transcribing and analysing all interviews.'
                ).save()

                ChangeImpact effect2 = new ChangeImpact(
                        change: change2,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_DECREASES,
                        rationale: 'the retention capacity is not analysed'
                ).save()

                Change change3 = new Change(
                        replication: q2009,
                        name: "Analysts' experience in development",
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'Development experience was not considered',
                        replicationSituation: 'Experience in development was considered to calculate the independent variable experience',
                        purpose: 'To analyse its effect on the results'
                ).save()

                ChangeImpact effect3 = new ChangeImpact(
                        change: change3,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'The possible influence of one more variable is analysed'
                ).save()

                Change change4 = new Change(
                        replication: q2009,
                        name: 'Interview language',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_MATERIAL,
                        baseSituation: 'Interviews were conducted in Spanish',
                        replicationSituation: 'Interviews were conducted in English',
                        purpose: "Use the language of the students' master's degree"
                ).save()

                ChangeImpact effect4 = new ChangeImpact(
                        change: change4,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'There may be comprehension problems that affect the results'
                ).save()

                Change change5 = new Change(
                        replication: q2009,
                        name: 'Change of experimenter',
                        dimension: ChangeDimension.EXPERIMENTER,
                        affectedElement: ExperimenterRole.MONITOR,
                        baseSituation: 'An experimenter played the role of client in the interviews by answering questions from the experimental subjects (analysts)',
                        replicationSituation: 'The experimenter (client) was replaced',
                        purpose: 'Replacing the experimenter who was not available'
                ).save()

                ChangeImpact effect5 = new ChangeImpact(
                        change: change5,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'Experimenter bias is eliminated'
                ).save()

                // Create and save changes of q2011
                Change change6 = new Change(
                        replication: q2011,
                        name: 'Group interviews',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'interviews between subjects (analysts) and experimenter were individual',
                        replicationSituation: 'interviews were conducted in groups',
                        purpose: 'Reduce the cost and effort of conducting individual interviews'
                ).save()

                ChangeImpact effect6 = new ChangeImpact(
                        change: change6,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'There may be comprehension problems that affect the results'
                ).save()

                Change change7 = new Change(
                        replication: q2011,
                        name: 'Experience determination',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'Experience in requirements analysis was determined by their years of experience',
                        replicationSituation: "Experience was further determined by the subject's abilities",
                        purpose: "Consider the subject's abilities"
                ).save()

                ChangeImpact effect7 = new ChangeImpact(
                        change: change7,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'There is a new variable to better capture the construct'
                ).save()

                ChangeImpact effect8 = new ChangeImpact(
                        change: change7,
                        validity: Validity.CONCLUSION_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'The procedure becomes tedious'
                ).save()

                Change change8 = new Change(
                        replication: q2011,
                        name: 'Duration of interviews',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'The duration of the interviews was 30 minutes',
                        replicationSituation: 'The duration of the interviews was 60 minutes',
                        purpose: 'The interviews are group interviews'
                ).save()

                ChangeImpact effect9 = new ChangeImpact(
                        change: change8,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'Increasing the duration of interviews allows for a better understanding of the requirements'
                ).save()

                Change change9 = new Change(
                        replication: q2011,
                        name: 'Requirements report',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'After the interview, the subjects (analysts) had 7 days to report the requirements',
                        replicationSituation: 'Subjects (analysts) reported the requirements immediately after the interview',
                        purpose: 'Avoid loss of information'
                ).save()

                ChangeImpact effect10 = new ChangeImpact(
                        change: change9,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'The information is collected after the interview so that it is not forgotten'
                ).save()

                Change change10 = new Change(
                        replication: q2011,
                        name: 'Submission time',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'The time required by the analyst to report the requirements was not measured',
                        replicationSituation: 'The consolidation time was 120 minutes',
                        purpose: 'Submit the requirements report immediately after the interview'
                ).save()

                ChangeImpact effect11 = new ChangeImpact(
                        change: change10,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'It is analysed whether time influences the information collected'
                ).save()

                Change change11 = new Change(
                        replication: q2011,
                        name: 'Changing experimenter',
                        dimension: ChangeDimension.EXPERIMENTER,
                        affectedElement: ExperimenterRole.MONITOR,
                        baseSituation: 'An experimenter plays the role of client in the interviews by answering questions from the experimental subjects (analysts)',
                        replicationSituation: 'The experimenter (client) was replaced',
                        purpose: 'Replacing the experimenter who was not available'
                ).save()

                ChangeImpact effect12 = new ChangeImpact(
                        change: change11,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'Experimenter bias is eliminated'
                ).save()

                // Create and save changes of q2012
                Change change12 = new Change(
                        replication: q2012,
                        name: 'Professional subjects',
                        dimension: ChangeDimension.POPULATION,
                        affectedElement: 'Experience',
                        baseSituation: "The subjects were Master's students",
                        replicationSituation: 'The subjects were professionals',
                        purpose: 'Use participants in the International Working Conference on Requirements Engineering as subjects'
                ).save()

                ChangeImpact effect13 = new ChangeImpact(
                        change: change12,
                        validity: Validity.EXTERNAL_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'The results extend to subjects who are professionals'
                ).save()

                Change change13 = new Change(
                        replication: q2012,
                        name: 'Development experience',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'Subjects had little or no development experience',
                        replicationSituation: 'The subjects were professionals with experience in development',
                        purpose: 'Analyse the influence of experience on developmentAdapting to the'
                ).save()

                ChangeImpact effect14 = new ChangeImpact(
                        change: change13,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: "The new independent variable 'experience on development' is defined"
                ).save()

                Change change14 = new Change(
                        replication: q2012,
                        name: 'Consolidation time',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'The subjects (analysts) had 120 minutes to report the requirements (consolidation time)',
                        replicationSituation: 'The consolidation time was 30 minutes',
                        purpose: 'Adapt to the time available'
                ).save()

                ChangeImpact effect15 = new ChangeImpact(
                        change: change14,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'The time available may affect the results'
                ).save()

                Change change15 = new Change(
                        replication: q2012,
                        name: 'Elimination of the training period',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'The experiment was carried out at the end of the course, i.e. after the training period',
                        replicationSituation: 'There was no training period',
                        purpose: 'Adapting to the International Working Conference on Requirements Engineering'
                ).save()

                ChangeImpact effect16 = new ChangeImpact(
                        change: change15,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.DOES_NOT_AFFECT,
                        rationale: 'The subjects are experienced professionals and do not need a period of training'
                ).save()

                // Create and save changes of E2012A
                Change change16 = new Change(
                        replication: E2012A,
                        name: 'Problem domain',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: "Knowledge of the problem domain was defined, by subjective assessment, as 'familiarity'",
                        replicationSituation: 'Knowledge was defined as an independent variable with two levels: known and unknown problem domain',
                        purpose: 'Analyze whether knowledge of the problem domain affected the results'
                ).save()

                ChangeImpact effect17 = new ChangeImpact(
                        change: change16,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: "The new independent variable 'problem domain' is defined"
                ).save()

                Change change17 = new Change(
                        replication: E2012A,
                        name: 'Repeated measures design',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'The interviews were conducted on two different days',
                        replicationSituation: 'The design was changed to a design of repeated measurements (within-subjects)',
                        purpose: 'Adapt the experiment as there were fewer experimental subjects'
                ).save()

                ChangeImpact effect18 = new ChangeImpact(
                        change: change17,
                        validity: Validity.CONCLUSION_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'Improves the power of the applicable statistical tests'
                ).save()

                ChangeImpact effect19 = new ChangeImpact(
                        change: change17,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'The change of experimental design better reflects the construct'
                ).save()

                Change change18 = new Change(
                        replication: E2012A,
                        name: 'Design of repeated measures',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'Interviews between subjects (analysts) and experimenter (clients) were in group',
                        replicationSituation: 'interviews were individual',
                        purpose: 'Conduct interviews with 2 experimenters (clients) and in two languages.'
                ).save()

                ChangeImpact effect20 = new ChangeImpact(
                        change: change18,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'Experimenter bias is eliminated'
                ).save()

                ChangeImpact effect21 = new ChangeImpact(
                        change: change18,
                        validity: Validity.CONCLUSION_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'Although there were two responding monitors, the process was tedious for these monitors due to the larger number of individual interviews'
                ).save()

                Change change19 = new Change(
                        replication: E2012A,
                        name: 'Blocking by language',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'There were no blocking variables',
                        replicationSituation: 'There were a blocking variable per language',
                        purpose: 'Prevent language from influencing results'
                ).save()

                ChangeImpact effect22 = new ChangeImpact(
                        change: change19,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Language is prevented from influencing the results'
                ).save()

                Change change20 = new Change(
                        replication: E2012A,
                        name: 'Blocking by experimenter',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'There were no blocking variables',
                        replicationSituation: 'There were a blocking variable per experimenter',
                        purpose: 'Prevent experimenter from influencing results'
                ).save()

                ChangeImpact effect23 = new ChangeImpact(
                        change: change20,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Experimenter is prevented from influencing the results'
                ).save()

                Change change21 = new Change(
                        replication: E2012A,
                        name: 'Number of experimenter',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'There was an experimenter (clients)',
                        replicationSituation: 'There were two experimenters (clients)',
                        purpose: 'Alleviate the effects of fatigue and learning of the experimenter'
                ).save()

                ChangeImpact effect24 = new ChangeImpact(
                        change: change21,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'Experimenter fatigue and learning is prevented from influencing the results'
                ).save()

                Change change22 = new Change(
                        replication: E2012A,
                        name: 'Number of problems',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'There was only one problem',
                        replicationSituation: 'There were two problems',
                        purpose: 'Use two problems due to the new design and the blocking variables'
                ).save()

                ChangeImpact effect25 = new ChangeImpact(
                        change: change22,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'In the new design 2 blocking variables have been defined'
                ).save()

                Change change23 = new Change(
                        replication: E2012A,
                        name: 'Interview duration',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'The duration of the interviews was 60 minutes',
                        replicationSituation: 'The duration of the interviews was 30 minutes',
                        purpose: 'The interviews were individual'
                ).save()

                ChangeImpact effect26 = new ChangeImpact(
                        change: change23,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'The interviews were individual and this could affect results'
                ).save()

                Change change24 = new Change(
                        replication: E2012A,
                        name: 'Time of consolidation',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'The subjects (analysts) had 30 minutes to report the requirements (consolidation time)',
                        replicationSituation: 'The consolidation time was 90 minutes',
                        purpose: 'More time available for reporting requirements'
                ).save()

                ChangeImpact effect27 = new ChangeImpact(
                        change: change24,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SLIGHTLY_INCREASES,
                        rationale: 'More time is available and the information collected can be more accurate'
                ).save()

                Change change25 = new Change(
                        replication: E2012A,
                        name: 'Problem difficulty',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'The difficulty of the problem was not measured',
                        replicationSituation: "The new variable 'problem difficulty' is defined",
                        purpose: 'Analyse the influence of the difficulty of the problem on the results'
                ).save()

                ChangeImpact effect28 = new ChangeImpact(
                        change: change25,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'There is a new variable to better capture the construct'
                ).save()

                // Create and save changes of E2012B
                Change change26 = new Change(
                        replication: E2012B,
                        name: 'New problems',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_MATERIAL,
                        baseSituation: 'Two problem domains were used, one known domain and one unknown domain',
                        replicationSituation: 'The problems have been changed although they are still known domain and unknown domain',
                        purpose: 'Using different problems for requirements analysis'
                ).save()

                ChangeImpact effect29 = new ChangeImpact(
                        change: change26,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.DOES_NOT_AFFECT,
                        rationale: 'The difficulty of the problems is similar to the previous ones'
                ).save()

                Change change27 = new Change(
                        replication: E2012B,
                        name: 'Order of problems',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'First the known domain problem was performed and then the unknown domain problem',
                        replicationSituation: 'The order of the problems was swapped',
                        purpose: 'Analyse whether it affects the results'
                ).save()

                ChangeImpact effect30 = new ChangeImpact(
                        change: change27,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SLIGHTLY_INCREASES,
                        rationale: 'Allows to analyse whether the order affects the results'
                ).save()

                ChangeImpact effect31 = new ChangeImpact(
                        change: change27,
                        validity: Validity.CONCLUSION_VALIDITY,
                        effect: Effect.SLIGHTLY_INCREASES,
                        rationale: 'It allows to analyse and adjust the effect of the difference between tasks on the experimental results'
                ).save()

                Change change28 = new Change(
                        replication: E2012B,
                        name: 'Timing of the experiment',
                        dimension: ChangeDimension.CONTEXT,
                        affectedElement: 'Time of performance',
                        baseSituation: 'The experiment was carried out at the beginning of the course',
                        replicationSituation: 'The experiment was carried out after the subjects have received training in requirements engineering',
                        purpose: 'Analyse whether it affects the results'
                ).save()

                ChangeImpact effect32 = new ChangeImpact(
                        change: change28,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'The replication was carried out at the end of the course and may influence the results'
                ).save()

                // Create and save changes of E2013
                Change change29 = new Change(
                        replication: E2013,
                        name: 'Inter-subject design',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'The design was of repeated measurements',
                        replicationSituation: 'The design was between-subjects',
                        purpose: 'Avoiding the learning effect'
                ).save()

                ChangeImpact effect33 = new ChangeImpact(
                        change: change29,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'In the between-subjects design, each subject receives a single treatment to avoid the learning effect',
                        comments: 'Avoids bias due to the order in which treatments are administered'
                ).save()

                Change change30 = new Change(
                        replication: E2013,
                        name: 'Previous training',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'There was no short training (warm-up) before the course',
                        replicationSituation: 'Short training (warm-up) was 1 week',
                        purpose: 'To analyse the effect of training'
                ).save()

                ChangeImpact effect34 = new ChangeImpact(
                        change: change30,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'the effect of training is analysed against other factors'
                ).save()

                ChangeImpact effect35 = new ChangeImpact(
                        change: change30,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'The effect of training is better reflected'
                ).save()

                // Create and save changes of E2014
                Change change31 = new Change(
                        replication: E2014,
                        name: 'Only one experimenter',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_GUIDES,
                        baseSituation: 'Two experimenters played the role of clients in the interviews by answering questions from the experimental subjects (analysts)',
                        replicationSituation: 'There was only one experimenter',
                        purpose: 'Having only one experimenter as the other was not available'
                ).save()

                ChangeImpact effect36 = new ChangeImpact(
                        change: change31,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'All subjects (analysts) receive the same information as there is only one experimenter (client)'
                ).save()

                ChangeImpact effect37 = new ChangeImpact(
                        change: change31,
                        validity: Validity.CONCLUSION_VALIDITY,
                        effect: Effect.SLIGHTLY_DECREASES,
                        rationale: 'By having only one experimenter (client), the process can become tedious for the experimenter'
                ).save()

                Change change32 = new Change(
                        replication: E2014,
                        name: 'Training increased',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'Short training (warm-up) was 1 week',
                        replicationSituation: 'Short training (warm-up) was 6 week',
                        purpose: 'Analyse the effect of increasing training'
                ).save()

                ChangeImpact effect38 = new ChangeImpact(
                        change: change32,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'The effect of training is analysed against other factors'
                ).save()

                ChangeImpact effect39 = new ChangeImpact(
                        change: change32,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'the effect of training is better reflected'
                ).save()

                // Create and save changes of E2015
                Change change33 = new Change(
                        replication: E2015,
                        name: 'Increasing the duration of training',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'Short training (warm-up) was 6 week',
                        replicationSituation: 'Short training (warm-up) was 2 week',
                        purpose: 'Analyse the effect of increasing training'
                ).save()

                ChangeImpact effect40 = new ChangeImpact(
                        change: change33,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'the effect of training is analysed against other factors'
                ).save()

                ChangeImpact effect41 = new ChangeImpact(
                        change: change33,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'the effect of training is better reflected'
                ).save()
            }
            catch (Exception e) {
                println "ERROR in RequirementsPopulator.populate() ---> ${e.getMessage()}"
                status.setRollbackOnly()
            }
        }
    }
}