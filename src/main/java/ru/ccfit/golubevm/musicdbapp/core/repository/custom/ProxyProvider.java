package ru.ccfit.golubevm.musicdbapp.core.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.jodah.typetools.TypeResolver;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
@RequiredArgsConstructor
public class ProxyProvider {
    @PersistenceContext
    private final EntityManager entityManager;
    private final QueryBuilder queryBuilder;
    @SneakyThrows
    public<T extends Repo> T provideProxy(Class<T> iclass) {
        var type = TypeResolver.resolveRawArgument(Repo.class, iclass);
        return (T) Proxy.newProxyInstance(
                iclass.getClassLoader(),
                new Class[] {iclass},
                new RepositoryInvocationHandler(type, entityManager, queryBuilder)
        );
    }
}
