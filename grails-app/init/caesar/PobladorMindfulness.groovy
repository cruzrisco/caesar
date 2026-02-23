package caesar
/**
 * Clase de utilidad para poblar los estudios de mindfulness
 */
class PobladorMindfulness {

    /**
     * Crea y salva los objetos.
     */
    static populate() {

        EmpiricalStudy.withTransaction { status ->
            try {
                OriginalStudy mind1 = new OriginalStudy(
                        acronym: 'MIND#1_ES',
                        site: 'ETSII de la US',
                        date: 'Primer semestre del curso 2013-2014',
                        goal:
                                '''
Estudiar si la práctica de mindfulness (causa) mejora la productividad en 
modelado conceptual (efecto) en ingenieros de requisitos (población).
''',
                        description:
                                '''
Tomando como muestra de la población a los estudiantes de la asignatura IISSI 
de 2º año del Grado de Ingeniería de Software de la Universidad de Sevilla 
(muestra), un grupo (grupo experimental) asistió a sesiones de mindfulness de 
10 minutos durante 4 semanas, 4 días por semana (tratamiento grupo experimental), 
mientras que un segundo grupo (grupo de control) asistió a un taller de cómo 
hablar en público a modo de placebo (tratamiento grupo de control). 
Los estudiantes realizaron dos ejercicios de modelado conceptual, uno antes 
y otro después del tratamiento (diseño experimental pre-post), que se puntuaron 
comparándolos con una solución de referencia (procedimiento de medida). Se 
comparó el rendimiento de ambos grupos en términos de calidad (similitud con 
la solución de referencia) y productividad (similitud en porcentaje por unidad 
de tiempo) (métricas).
'''
                ).save()

                Replication mind2 = new Replication(
                        acronym  : 'MIND#2_ES',
                        base     : mind1,
                        type     : ReplicationType.INTERNAL,
                        purposes : [Purpose.CONFIRM_RESULTS, Purpose.OVERCOME_LIMITATIONS],
                        site     : 'ETSII de la US',
                        date     : 'Primer semestre del curso 2014-2015',
                        comments : 'Primera replicación tras el el experimento original'
                ).save()

                mind1.addToReplications(mind2).save()

                Replication mind3 = new Replication(
                        acronym  : 'MIND#3_ES',
                        base     : mind2,
                        type     : ReplicationType.INTERNAL,
                        purposes : [Purpose.CONFIRM_RESULTS],
                        site     : 'ETSII de la US',
                        date     : 'Primer semestre del curso 2015-2016',
                        comments : 'Segunda replicación basada en la replicación previa'
                ).save()

                mind2.addToReplications(mind3).save()

                Change change1 = new Change(
                        name: 'Asignación aleatoria de sujetos a grupos',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'La asignación a grupos se basó en las preferencias de los sujetos.',
                        replicationSituation: 'La asignación a grupos fue aleatoria.',
                        purpose: 'Mitigar el sesgo de selección y asignación y evitar limitaciones del análisis estadístico.'
                )

                ChangeImpact effect1 = new ChangeImpact(
                        validity: Validity.CONCLUSION_VALIDITY,
                        size: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'mejora la potencia de los tests estadísticos aplicables'
                )

                ChangeImpact effect2 = new ChangeImpact(
                        validity: Validity.INTERNAL_VALIDITY,
                        size: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'elimina el sesgo de asignación'
                )

                change1.addToEffects(effect1)
                        .addToEffects(effect2)
                        .save()

                Change change2 = new Change(
                        name: 'Aumento de la duración del tratamiento',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'El tratamiento se aplicó durante 4 semanas, 4 días por semana, en sesiones de 10 minutos.',
                        replicationSituation: 'El tratamiento se aplicó durante 6 semanas, 4 días por semana, en sesiones de 12 minutos.',
                        purpose: 'Obtener una mejora estadísticamente significativa de la efectividad en modelado conceptual'
                )

                ChangeImpact effect3 = new ChangeImpact(
                        validity: Validity.CONSTRUCT_VALIDITY,
                        size: Effect.MODERATELY_INCREASES,
                        rationale: 'refleja mejor el efecto de la práctica de mindfulness'
                )

                ChangeImpact effect4 = new ChangeImpact(
                        validity: Validity.INTERNAL_VALIDITY,
                        size: Effect.MODERATELY_INCREASES,
                        rationale: 'refuerza el efecto del tratamiento sobre el de otros posibles factores'
                )

                change2.addToEffects(effect3)
                        .addToEffects(effect4)
                        .save()

                Change change3 = new Change(
                        name: 'Tratamiento nulo del grupo de control',
                        dimension: ChangeDimension.OPERATIONALIZATION,
                        affectedElement: OperationalizationElement.TREATMENT,
                        baseSituation: 'El grupo de control recibió como tratamiento un placebo consistente en un taller de oratoria.',
                        replicationSituation: 'El grupo de control recibió un tratamiento nulo, ya que el taller de oratoria se pospuso hasta después de la segunda tarea.',
                        purpose: 'Mitigar el potencial factor distorsionador del placebo en los resultados experimentales'
                )

                ChangeImpact effect5 = new ChangeImpact(
                        validity: Validity.INTERNAL_VALIDITY,
                        size: Effect.MODERATELY_INCREASES,
                        rationale: 'elimina la posibilidad de que el placebo pueda tener algún efecto sobre los resultados experimentales'
                )

                change3.addToEffects(effect5)
                        .save()

                mind2.addToChanges(change1)
                        .addToChanges(change2)
                        .addToChanges(change3)
                        .save()

                Change change4 = new Change(
                        name: 'Cambio en el orden de los ejercicios de modelado conceptual',
                        dimension: ChangeDimension.PROTOCOL,
                        affectedElement: ProtocolElement.EXPERIMENTAL_DESIGN,
                        baseSituation: 'El orden de realización de los ejercicios fue ERASMUS y luego EoDP.',
                        replicationSituation: 'El orden de realización de los ejercicios fue EoDP y luego ERASMUS.',
                        purpose: 'Estudiar el potencial impacto del ejercicio de modelado conceptual en los resultados experimentales',
                )

                ChangeImpact effect6 = new ChangeImpact(
                        validity: Validity.INTERNAL_VALIDITY,
                        size: Effect.SLIGHTLY_INCREASES,
                        rationale: 'permite estudiar el efecto de la diferencia entre tareas en los resultados experimentales'
                )

                ChangeImpact effect7 = new ChangeImpact(
                        validity: Validity.CONCLUSION_VALIDITY,
                        size: Effect.SUBSTANTIALLY_INCREASES,
                        rationale: 'permite analizar y ajustar el efecto de la diferencia entre tareas en los resultados experimentales'
                )

                change4.addToEffects(effect6)
                        .addToEffects(effect7)
                        .save()

                mind3.addToChanges(change4).save()
            }
            catch (Exception e) {
                println "ERROR in PobladorMindfulness.populate() ---> ${e.getMessage()}"
                status.setRollbackOnly()
            }
        }
    }
}