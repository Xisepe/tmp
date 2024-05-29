package ru.ccfit.golubevm.musicdbapp.core.repository.custom;

import jakarta.persistence.EntityManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class RepositoryInvocationHandler<T> implements InvocationHandler {
    private final Class<T> typeClass;
    private final EntityManager entityManager;
    private final QueryBuilder queryBuilder;

    public RepositoryInvocationHandler(Class<T> typeClass, EntityManager entityManager, QueryBuilder queryBuilder) {
        this.typeClass = typeClass;
        this.entityManager = entityManager;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var tokens = method.getName().split("_");
        var arguments = getMethodParameters(method, args);
        var sql = queryBuilder.buildQuery(tokens, typeClass);

        var query = entityManager.createQuery(sql);
        for (var e : arguments.entrySet()) {
            query = query.setParameter(e.getKey(), e.getValue());
        }
        if (tokens[tokens.length - 1].equals("all")) {
            return query.getResultList().stream().map(typeClass::cast).toList();
        }
        return typeClass.cast(query.getSingleResult());
    }

    private Map<String, Object> getMethodParameters(Method method, Object[] args) {
        Map<String, Object> arguments = new HashMap<>();
        var params = method.getParameters();
        for (int i = 0; i < params.length; i++) {
            var param = params[i];
            var arg = args[i];
            arguments.put(param.getName(), arg);
        }
        return arguments;
    }


}
