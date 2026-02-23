package caesar
/**
 * Clase de utilidad para poblar los estudios de suelo
 */
class PobladorSuelo {

    /**
     * Crea y salva los objetos.
     */
    static populate() {

        EmpiricalStudy.withTransaction { status ->
            try {
                OriginalStudy suelo2016 = new OriginalStudy(
                        acronym: 'Suelo-2016',
                        site: 'ETSIA de la US',
                        date: 'Octubre 2015',
                        goal:
                                '''
Conocer el efecto de compuestos biodegradables y de origen natural (ramnolípidos)  
en el proceso de fitoextracción de Cu en suelos contaminados.\n
En otras palabras, estudiar si la adición de ramnolípidos (causa) mejora la 
fitoextracción de Cu (efecto) en suelos contaminados (población).
''',
                        description:
                                '''
Se toman muestras de dos suelos distintos (Coria y Constantina) que se contaminan 
artificialmente con Cu (tratamiento) y se distribuyen en macetas. Las muestras 
se dejan envejecer durante 45 días (tratamiento), se siembran semillas de cebada 
y de mostaza (instrumentos de medida), se les añade abono (tratamiento) y se les 
añade JBR-425 a los 15 días (tratamiento). A los 30 días se analiza el contenido 
de Cu de la biomasa de las plantas (métrica), y la disponibilidad del Cu en suelo 
(métrica) por dos métodos distintos: extracción con CaCl2 y con EDTA (métodos de 
médida). El Cu se aplicó en tres concentraciones: 0, 500 y 1000 mg/kg, por lo 
que se obtuvieron 6 grupos de tratamiento combinando las 3 concentraciones de Cu 
con la adición o no de JBR-425. Se toma como grupo de control al que no se 
contaminó con cobre ni se le adicionó JBR-425.
'''
                ).save()

                Replication suelo2018 = new Replication(
                        acronym: 'Suelo-2018',
                        base: suelo2016,
                        type: ReplicationType.INTERNAL,
                        purposes: [Purpose.CONFIRM_RESULTS, Purpose.GENERALIZE_RESULTS],
                        site: 'ETSIA de la US',
                        date: 'Marzo 2018',
                ).save()

                suelo2016.addToReplications(suelo2018).save()

                Change change1 = new Change(
                        name: 'Cultivo en invernadero',
                        dimension: ChangeDimension.CONTEXT,
                        affectedElement: 'Medio',
                        baseSituation: 'El experimento se llevó a cabo en una cámara de cultivo.',
                        replicationSituation: 'El experimento se llevó a cabo en un invernadero.',
                        purpose: 'Simular las condiciones naturales.'
                )

                ChangeImpact effect1 = new ChangeImpact(
                        validity: Validity.EXTERNAL_VALIDITY,
                        size: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'generaliza los resultados para condiciones más cercanas a las naturales.'
                )

                change1.addToEffects(effect1)
                        .save()

                Change change2 = new Change(
                        name: 'Sólo se cultiva mostaza',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.MEASUREMENT_INSTRUMENTS,
                        baseSituation: 'Se utilizaron dos plantas: cebada y mostaza.',
                        replicationSituation: 'Se utilizó sólo la planta de mostaza.',
                        purpose: 'Usar la planta con mayor capacidad de fitoextracción de Cu'
                        //comments: 'Al utilizar un solo tipo de planta (mostaza), ya no afecta a los resultados, es decir no está operacionalizada.'
                )

                ChangeImpact effect2 = new ChangeImpact(
                        validity: Validity.CONSTRUCT_VALIDITY,
                        size: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Usando mostaza se mide mejor el efecto mediante el Cu fitoextraído.'
                )

                change2.addToEffects(effect2)
                        .save()

                Change change3 = new Change(
                        name: 'Sólo se usa el suelo de Constantina',
                        dimension: ChangeDimension.POPULATION,
                        affectedElement: 'Tipo de suelo',
                        baseSituation: 'Se tomaron muestras de dos tipos de suelo: Coria (pH=7.8) y Constantina (pH=5.5).',
                        replicationSituation: 'Se tomaron muestras solo del suelo de Constantina.',
                        purpose: 'Usar un suelo del que se pueda extraer el metal. En el suelo de Coría el Cu está fuertemente absorbido y no se puede fitoextraer.'
                        //comments: 'Al utilizar únicamente el suelo de Constantina, ya no afecta a los resultados, es decir no está operacionalizado'
                )

                ChangeImpact effect3 = new ChangeImpact(
                        validity: Validity.CONSTRUCT_VALIDITY,
                        size: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Se asegura que el metal puede ser fitoextraído, que es el efecto que se quiere medir.'
                        //comments: 'El efecto es extraer el Cu que hay en el suelo.  En el suelo de Coría no se podía extraer y por tanto no se puede medir'
                )

                change3.addToEffects(effect3)
                        .save()

                Change change4 = new Change(
                        name: 'Reducción de la dosis de Cu',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'Las dosis de Cu añadidas al suelo fueron de 0, 500 y 1000 mg/kg.',
                        replicationSituation: 'Las dosis de Cu añadidas al suelo fueron de 0, 125, 250 y 500 mg/kg. ',
                        purpose: 'Ajustar a niveles de Cu no tóxicos para las plantas.'
                )

                ChangeImpact effect4 = new ChangeImpact(
                        validity: Validity.CONSTRUCT_VALIDITY,
                        size: Effect.MODERATELY_INCREASES,
                        rationale: 'Se ajusta el nivel de Cu a niveles permitidos por las plantas. A dosis mayores, las plantas mueren.',
                        comments: 'La planta es instrumento de medida, si muere no se puede medir.'
                )

                ChangeImpact effect5 = new ChangeImpact(
                        validity: Validity.INTERNAL_VALIDITY,
                        size: Effect.MODERATELY_INCREASES,
                        rationale: 'Se ajusta el nivel de Cu a niveles permitidos por las plantas que reflejan mejor el contructo.',
                        comments: 'Permite analizar el efecto en niveles de Cu válidos.'
                )

                change4.addToEffects(effect4)
                        .addToEffects(effect5)
                        .save()

                Change change5 = new Change(
                        name: 'Cu aplicado como sulfato de Cu',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'El Cu se aplicó como Nitrato de Cu.',
                        replicationSituation: 'El Cu se aplicó como Sulfato de Cu.',
                        purpose: 'Utilizar el reactivo más accesible.',
                        comments: 'Afecta al tratamiento.'
                )

                ChangeImpact effect6 = new ChangeImpact(
                        validity: Validity.INTERNAL_VALIDITY,
                        size: Effect.DOES_NOT_AFFECT,
                        rationale: 'Únicamente es un cambio de un reactivo por otro equivalente.'
                )

                change5.addToEffects(effect6)
                        .save()

                Change change6 = new Change(
                        name: 'Reducción del tiempo de envejecimiento del suelo',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'El tiempo de envejecimiento del suelo (desde que se aplica Cu hasta que se siembra) fue de 45 días.',
                        replicationSituation: 'El tiempo de envejecimiento fue de 15 días.',
                        purpose: 'Evitar la fijación excesiva del Cu al suelo que dificulta su fitoextracción.',
                        comments: 'Afecta a la forma de aplicar el Cu, que es parte del tratamiento.'
                )

                ChangeImpact effect7 = new ChangeImpact(
                        validity: Validity.CONSTRUCT_VALIDITY,
                        size: Effect.MODERATELY_INCREASES,
                        rationale: 'La planta extrae el metal con mayor facilidad al no estar tan retenido y refleja mejor el constructo.',
                        comments: 'Si está muy retenido, no se puede medir.'
                )

                ChangeImpact effect7b = new ChangeImpact(
                        validity: Validity.EXTERNAL_VALIDITY,
                        size: Effect.MODERATELY_DECREASES,
                        rationale: 'Reduce la generalización de resultados a suelos contaminados desde hace más tiempo.'
                )

                change6.addToEffects(effect7)
                        .addToEffects(effect7b)
                        .save()

                Change change7 = new Change(
                        name: 'Incremento del número de macetas por tipo de tratamiento',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'Habia (3x2x2x2) 24 unidades experimentales (3 niveles de Cu, 2 de JBR, 2 tipos de plantas y 2 tipos de suelos). Para cada unidad experimental se prepara una maceta y se repite 3 veces.',
                        replicationSituation: 'Hay (4x2) 8 unidades experimentales (4 niveles de Cu y 2 de JBR). Para cada unidad experimental se preparan 4 macetas y se colocan en una bandeja. Se repite 3 veces.',
                        purpose: 'Aumentar el número de macetas para obtener suficiente biomasa.',
                        comment: 'La unidad experimental es ahora la bandeja. La unidad experimental es el equivalente a los sujetos experimentales en otros campos.'
                )

                /*
            ChangeImpact effect8 = new ChangeImpact(
                validity: Validity.CONSTRUCT_VALIDITY,
                 size: Effect.MODERATELY_INCREASES,
                rationale: 'Se ha obtenido más biomasa para análisis posteriores.',
                comments: 'Facilita las medidas de Cu.'
            )
            */

                ChangeImpact effect8b = new ChangeImpact(
                        validity: Validity.CONCLUSION_VALIDITY,
                        size: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'Aumentar el número de sujetos (n) mejora la potencia de los tests estadísticos, reduciendo la probabilidad de obtener un falso negativo (error tipo II)'
                )

                change7.addToEffects(effect8b)
                        .save()

                Change change8 = new Change(
                        name: 'Biomasa obtenida en etapa de fructificación',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.MEASUREMENT_PROCEDURES,
                        baseSituation: 'La biomasa se recogió cuando las plantas tuvieron entre 2 y 3 hojas verdaderas.',
                        replicationSituation: 'La biomasa se recogió cuando las plantas alcanzaron la etapa de fructificación.',
                        purpose: 'Obtener más biomasa al completar las plantas su ciclo vegetativo.',
                        comments: 'Modifica el procedimiento de medida al recoger las hojas más tarde.'
                )

                ChangeImpact effect9 = new ChangeImpact(
                        validity: Validity.CONSTRUCT_VALIDITY,
                        size: Effect.MODERATELY_INCREASES,
                        rationale: 'Se ha obtenido más biomasa para análisis posteriores.'
                )

                change8.addToEffects(effect9)
                        .save()

                Change change9 = new Change(
                        name: 'Aumento del volumen de las macetas',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_MATERIAL,
                        baseSituation: 'Las macetas fueron del tipo tubo de 300 ml.',
                        replicationSituation: 'Las macetas fueron del tipo cubo de 500 ml.',
                        purpose: 'Conseguir mayor desarrollo radicular y producir más biomasa.',
                )

                ChangeImpact effect10 = new ChangeImpact(
                        validity: Validity.CONSTRUCT_VALIDITY,
                        size: Effect.SLIGHTLY_INCREASES,
                        rationale: 'Se ha obtenido más biomasa para análisis posteriores.'
                )

                change9.addToEffects(effect10)
                        .save()

                suelo2018.addToChanges(change1)
                        .addToChanges(change2)
                        .addToChanges(change3)
                        .addToChanges(change4)
                        .addToChanges(change5)
                        .addToChanges(change6)
                        .addToChanges(change7)
                        .addToChanges(change8)
                        .addToChanges(change9)
                        .save()
            }
            catch (Exception e) {
                println "ERROR in PobladorSuelo.populate() ---> ${e.getMessage()}"
                status.setRollbackOnly()
            }
        }
    }
}