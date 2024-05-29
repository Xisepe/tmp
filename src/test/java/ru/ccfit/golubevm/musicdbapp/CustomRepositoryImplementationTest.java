package ru.ccfit.golubevm.musicdbapp;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.ccfit.golubevm.musicdbapp.config.IntegrationTestConfig;
import ru.ccfit.golubevm.musicdbapp.core.repository.custom.ProxyProvider;
import ru.ccfit.golubevm.musicdbapp.core.repository.custom.UserRepo;

import static org.hamcrest.MatcherAssert.assertThat;

public class CustomRepositoryImplementationTest extends IntegrationTestConfig {
    @Autowired
    private ProxyProvider proxyProvider;

    @Test
    @Sql("/json_user_test.sql")
    void find_one_by_id_test() {
        var proxy = (UserRepo) proxyProvider.provideProxy(UserRepo.class);
        var res = proxy.find_by_id_eq_one(1);
        assertThat(res.getId(), Matchers.equalTo(1));
    }
    @Test
    @Sql("/json_user_test.sql")
    void find_one_by_string_test() {
        var proxy = (UserRepo) proxyProvider.provideProxy(UserRepo.class);
        var res = proxy.find_by_email_eq_one("test3@test.test");
        assertThat(res.getId(), Matchers.equalTo(3));
    }

    @Test
    @Sql("/json_user_test.sql")
    void find_all_by_id_less_than() {
        var proxy = (UserRepo) proxyProvider.provideProxy(UserRepo.class);
        var res = proxy.find_by_id_lessThan_all(3);
        assertThat(res, Matchers.hasSize(2));
    }
    @Test
    @Sql("/json_user_test.sql")
    void find_all_by_id_less_than_order_by() {
        var proxy = (UserRepo) proxyProvider.provideProxy(UserRepo.class);
        var res = proxy.find_by_id_lessThan_orderBy_id_asc_all(3);
        assertThat(res, Matchers.hasSize(2));
    }

}
