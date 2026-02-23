<g:select
        name="${property}"
        from="${caesar.EmpiricalStudy.findAllByIdNotEqual(replication.id)}"
        value="${replication?.base}"/>