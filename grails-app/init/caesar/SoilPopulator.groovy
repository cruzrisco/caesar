// --------------------------------------------------------------------
// File        : init/SoilPopulator.groovy
// Description : Populator of the Soil family of experiments
// Version     : 2.0
// Author      : Amador Durán
// Date        : 2022-04-18
// --------------------------------------------------------------------

// --------------------------------------------------------------------
// Last update : 2022-04-18 (Margarita)
// Adaptation of Margarita's first version to avoid problems with Grails.
// Include reference to the 1 entity in 1:n instead of using addToX()
// --------------------------------------------------------------------
// Last update : 2022-04-20 (Margarita)
// new attribute report for URL of paper DOI or any other URL
// --------------------------------------------------------------------

package caesar
/**
 * Utility class to populate soil studies
 */
class SoilPopulator {

    /**
     * It creates and saves objects.
     */
    static populate() {

        EmpiricalStudy.withTransaction { status ->
            try {
                OriginalStudy soil2016 = new OriginalStudy(
                        acronym: 'Soil-2016',
                        report: 'http://hdl.handle.net/11441/50282',
                        site: 'the ETSIA of the University of Seville',
                        date: 'October 2015',
                        goal:
                                '''
To study whether the addition of rhamnolipids (cause) improves 
Cu phytoextraction (effect) in Cu-contaminated soils (population).
''',
                        description:
                                '''
Samples are taken from two different soils (Coria and Constantina), which are
then artificially contaminated with Cu, distributed in pots, and left to age for 
45 days. Then, barley and mustard seeds are sown, fertiliser is added, and JBR-425 
rhamnolipid is added after 15 days. After 30 days, the Cu content of plant biomass 
and the availability of Cu in soil are analyzed by two different methods: extraction 
with CaCl2 and EDTA. Cu was applied in three concentrations: 0, 500 and 1000 mg/kg, so 6 
treatment groups were obtained combining the 3 concentrations of Cu with the 
addition or not of JBR-425. The control group was taken as the one that was 
neither contaminated with copper nor added with JBR-425.
'''
                ).save()

                Replication soil2018 = new Replication(
                        base: soil2016,
                        report: 'https://hdl.handle.net/11441/132481',
                        acronym: 'Soil-2018',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS, Purpose.GENERALIZE_RESULTS],
                        site: 'the ETSIA of the University of Seville',
                        date: 'March 2018',
                        comments: "Agrobiology case study - Soil family of experiments"
                ).save()

                Replication soil2019 = new Replication(
                        base: soil2016,
                        report: 'https://hdl.handle.net/11441/132478',
                        acronym: 'Soil-2019',
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS, Purpose.GENERALIZE_RESULTS],
                        site: 'the ETSIA of the University of Seville',
                        date: 'March 2019',
                        comments: "Agrobiology case study - Soil family of experiments"
                ).save()

                Change change1 = new Change(
                        replication: soil2018,
                        name: 'Greenhouse cultivation',
                        dimension: ChangeDimension.CONTEXT,
                        affectedElement: 'Medium',
                        baseSituation: 'The experiment was carried out in a culture chamber',
                        replicationSituation: 'The experiment was carried out in a greenhouse',
                        purpose: 'Simulate natural conditions.'
                ).save()

                ChangeImpact effect1 = new ChangeImpact(
                        change: change1,
                        validity: Validity.EXTERNAL_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'generalises the results for conditions closer to natural conditions'
                ).save()

                Change change2 = new Change(
                        replication: soil2018,
                        name: 'Only mustard is grown',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.MEASUREMENT_INSTRUMENTS,
                        baseSituation: 'Two plants were used: barley and mustard.',
                        replicationSituation: 'Only the mustard plant was used.',
                        purpose: 'Use the plant with the highest Cu phytoextraction capacity.'
                        //comments: 'By using only one type of plant (mustard), it no longer affects the results, i.e. it is not operationalized.'
                ).save()

                ChangeImpact effect2 = new ChangeImpact(
                        change: change2,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Using mustard the effect is best measured by phytoextracted Cu'
                ).save()

                Change change3 = new Change(
                        replication: soil2018,
                        name: 'Only Constantina soil is used',
                        dimension: ChangeDimension.POPULATION,
                        affectedElement: 'Type of soil',
                        baseSituation: 'Two soil types were sampled: Coria (pH=7.8) and Constantina (pH=5.5).',
                        replicationSituation: 'Soil samples were taken only from Constantina.',
                        purpose: 'Use a soil from which the metal can be extracted. In the soil of Coria Cu is strongly absorbed and cannot be phytoextracted.'
                        //comments: 'By using only Constantine soil, it no longer affects the results, i.e. it is not operationalised'
                ).save()

                ChangeImpact effect3 = new ChangeImpact(
                        change: change3,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'It ensures that the metal can be phytoextracted, which is the effect to be measured'
                        //comments: 'the effect is to extract the Cu in the soil.  In the soil of Coria it could not be extracted and therefore cannot be measured.'
                ).save()

                Change change4 = new Change(
                        replication: soil2018,
                        name: 'Cu dose reduction',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'The doses of Cu added to the soils were 0, 500 and 1000 mg/kg',
                        replicationSituation: 'The doses of Cu added to the soils were 0, 125, 250 and 500 mg/kg',
                        purpose: 'Avoiding toxic levels of Cu for the plants',
                        comments: 'Plants died at 1000 mg/kg Cu doses.'
                ).save()

                ChangeImpact effect4 = new ChangeImpact(
                        change: change4,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'At high Cu doses, the plants die and the effect cannot be measured',
                        comments: 'The plant is a measuring instrument.'
                ).save()

                /*
                ChangeImpact effect5 = new ChangeImpact(
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'The Cu level is adjusted to levels allowed by the plants that best reflect the construct.',
                        comments: 'It allows to analyse the effect on valid Cu levels.'
                )
                */

                Change change5 = new Change(
                        replication: soil2018,
                        name: 'Cu applied as Cu sulphate',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'Cu was applied as Cu nitrate.',
                        replicationSituation: 'Cu was applied as Cu Sulphate.',
                        purpose: 'Use the most accessible reagent.',
                        comments: 'It affects the treatment.'
                ).save()

                ChangeImpact effect6 = new ChangeImpact(
                        change: change5,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.DOES_NOT_AFFECT,
                        rationale: 'It is only a change of one reagent for an equivalent one.'
                ).save()

                Change change6 = new Change(
                        replication: soil2018,
                        name: 'Reduction of soil ageing time',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'Soil ageing time (from Cu application to sowing) was 45 days.',
                        replicationSituation: 'The ageing time was 15 days.',
                        purpose: 'Avoid excessive fixation of Cu to the soil which hinders its phytoextraction.',
                        comments: 'It affects the way Cu is applied, which is part of the treatment.'
                ).save()

                ChangeImpact effect7 = new ChangeImpact(
                        change: change6,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'The plant extracts the metal more easily as it is not so retained and reflects the construction better.',
                        comments: 'If it is too retained, it cannot be measured.'
                ).save()

                ChangeImpact effect7b = new ChangeImpact(
                        change: change6,
                        validity: Validity.EXTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_DECREASES,
                        rationale: 'It reduces the generalisation of results to contaminated soils that have been contaminated for a longer period of time.'
                ).save()

                Change change7 = new Change(
                        replication: soil2018,
                        name: 'Increase in the number of pots per treatment type',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'There were (3x2x2x2) 24 experimental units (3 Cu levels, 2 JBR, 2 plant types and 2 soil types). For each experimental unit a pot is prepared and repeated 3 times.',
                        replicationSituation: 'There are (4x2) 8 experimental units (4 Cu and 2 JBR levels). For each experimental unit 4 pots are prepared and placed in a tray. This is repeated 3 times.',
                        purpose: 'Increase the number of pots to obtain sufficient biomass.',
                        comment: 'The experimental unit is now the tray. The experimental unit is the equivalent of the experimental subjects in other fields.'
                ).save()

                /*
                ChangeImpact effect8 = new ChangeImpact(
                    validity: Validity.CONSTRUCT_VALIDITY,
                    effect: Effect.MODERATELY_INCREASES,
                    rationale: 'Further biomass has been obtained for further analysis.',
                    comments: 'Facilitates Cu measures.'
                )
                */

                ChangeImpact effect8b = new ChangeImpact(
                        change: change7,
                        validity: Validity.CONCLUSION_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Increasing the number of subjects (n) improves the power of statistical tests, reducing the probability of obtaining a false negative (type II error)'
                ).save()

                Change change8 = new Change(
                        replication: soil2018,
                        name: 'Biomass obtained at fruiting stage',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.MEASUREMENT_PROCEDURES,
                        baseSituation: 'Biomass was collected when plants had 2 to 3 true leaves.',
                        replicationSituation: 'Biomass was collected when plants reached the fruiting stage.',
                        purpose: 'Obtain more biomass as plants complete their vegetative cycle.',
                        comments: 'Modify the measurement procedure by collecting the leaves later.'
                ).save()

                ChangeImpact effect9 = new ChangeImpact(
                        change: change8,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'Further biomass has been obtained for further analysis.'
                ).save()

                Change change9 = new Change(
                        replication: soil2018,
                        name: 'Increasing the volume of the pots',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_MATERIAL,
                        baseSituation: 'The pots were of the 300 ml tube type.',
                        replicationSituation: 'The pots were of the 500 ml bucket type.',
                        purpose: 'Achieve greater root development and produce more biomass.',
                ).save()

                ChangeImpact effect10 = new ChangeImpact(
                        change: change9,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.SLIGHTLY_INCREASES,
                        rationale: 'Further biomass has been obtained for further analysis.'
                ).save()


                Change change10 = new Change(
                        replication: soil2019,
                        name: 'Growing in greenhouses',
                        dimension: ChangeDimension.CONTEXT,
                        affectedElement: 'Medium',
                        baseSituation: 'The experiment was carried out in a culture chamber',
                        replicationSituation: 'The experiment was carried out in a greenhouse',
                        purpose: 'Simulate natural conditions.'
                ).save()

                ChangeImpact effect11 = new ChangeImpact(
                        change: change10,
                        validity: Validity.EXTERNAL_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'generalises the results for conditions closer to natural conditions'
                ).save()

                Change change11 = new Change(
                        replication: soil2019,
                        name: 'Mustard is only grown',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.MEASUREMENT_INSTRUMENTS,
                        baseSituation: 'Two plants were used: barley and mustard.',
                        replicationSituation: 'Only the mustard plant was used.',
                        purpose: 'Use the plant with the highest Cu phytoextraction capacity.'
                        //comments: 'By using only one type of plant (mustard), it no longer affects the results, i.e. it is not operationalised.'
                ).save()

                ChangeImpact effect12 = new ChangeImpact(
                        change: change11,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Using mustard the effect is best measured by phytoextracted Cu'
                ).save()

                Change change12 = new Change(
                        replication: soil2019,
                        name: 'Naturally contaminated soils',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'Two soil types were sampled: Coria (pH=7.8) and Constantina (pH=5.5).',
                        replicationSituation: 'Three soil types were sampled: Miraflores-1 and Miraflores-2 (with Pb, Zn and Cu) and Lebrija (not contaminated by metals) ',
                        purpose: 'Use naturally contaminated soils. The Miraflores soils are urban gardens with natural contamination and the Lebrija soil is used as a control.'
                ).save()

                ChangeImpact effect13 = new ChangeImpact(
                        change: change12,
                        validity: Validity.CONCLUSION_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'The construct is better reflected'
                ).save()

                ChangeImpact effect14 = new ChangeImpact(
                        change: change12,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Soils of different characteristics are compared.'
                ).save()

                Change change13 = new Change(
                        replication: soil2019,
                        name: 'Cu null dose',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'The doses of Cu added to the soils were 0, 500 and 1000 mg/kg',
                        replicationSituation: 'Soils are not artificially contaminated with Cu',
                        purpose: 'Experiment with the Cu already in the soil',
                        comments: 'These soils are urban gardens with natural contamination (Cu levels 36 and 206 mg/kg).'
                ).save()

                ChangeImpact effect15 = new ChangeImpact(
                        change: change13,
                        validity: Validity.INTERNAL_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'Cu levels are those present in the soil, are non-toxic to the plant and better reflects the construct'
                ).save()

                Change change14 = new Change(
                        replication: soil2019,
                        name: 'Number of pots per treatment type',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'There were (3x2x2x2) 24 experimental units (3 Cu levels, 2 JBR, 2 plant types and 2 soil types). For each experimental unit a pot is prepared and repeated 3 times.',
                        replicationSituation: 'There are (3x2) 6 experimental units (3 soils and 2 JBR levels). For each experimental unit 4 pots are prepared forming a block. This is repeated 3 times.',
                        purpose: 'Analyse the influence of two naturally contaminated soils and a third uncontaminated one.',
                        comment: 'The experimental unit is now the pot. The experimental unit is the equivalent of the experimental subjects in other fields.'
                ).save()

                /*
                ChangeImpact effect16b = new ChangeImpact(
                    validity: Validity.CONSTRUCT_VALIDITY,
                    effect: Effect.MODERATELY_INCREASES,
                    rationale: 'Further biomass has been obtained for further analysis and the change of experimental design better reflects the construct.',
                    comments: 'Facilitates Cu measures.'
                )
                */

                ChangeImpact effect16 = new ChangeImpact(
                        change: change14,
                        validity: Validity.CONCLUSION_VALIDITY,
                        effect: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Increasing the number of subjects (n) improves the power of statistical tests, reducing the probability of obtaining a false negative (type II error)'
                ).save()

                Change change15 = new Change(
                        replication: soil2019,
                        name: 'biomass at fruiting phase',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.MEASUREMENT_PROCEDURES,
                        baseSituation: 'Biomass was collected when plants had 2 to 3 true leaves.',
                        replicationSituation: 'Biomass was collected when plants reached the fruiting stage.',
                        purpose: 'Obtain more biomass as plants complete their vegetative cycle.',
                        comments: 'Modify the measurement procedure by collecting the leaves later.'
                ).save()
                /* Soil-2018 Plants are thinned out when they have 2 to 3 true leaves and 4 plant per pot is left */
                /* Soil-2019 Plants are thinned out when they have 2 to 3 true leaves and only 1 plant per pot is left */


                ChangeImpact effect17 = new ChangeImpact(
                        change: change15,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.MODERATELY_INCREASES,
                        rationale: 'Further biomass has been obtained for further analysis.'
                ).save()

                Change change16 = new Change(
                        replication: soil2019,
                        name: 'Increase the volume of pots',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_MATERIAL,
                        baseSituation: 'The pots were of the 300 ml tube type.',
                        replicationSituation: 'The pots were of the 500 ml bucket type.',
                        purpose: 'Achieve greater root development and produce more biomass.',
                ).save()

                ChangeImpact effect18 = new ChangeImpact(
                        change: change16,
                        validity: Validity.CONSTRUCT_VALIDITY,
                        effect: Effect.SLIGHTLY_INCREASES,
                        rationale: 'Further biomass has been obtained for further analysis.'
                ).save()
            }
            catch (Exception e) {
                println "ERROR in SoilPopulator.populate() ---> ${e.getMessage()}"
                status.setRollbackOnly()
            }
        }
    }
}