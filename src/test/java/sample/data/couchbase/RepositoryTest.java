package sample.data.couchbase;


import org.assertj.core.api.Java6Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.Observable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_get_all_users() {

        Collection<User> users = (Collection<User>) userRepository.findAll();

        Java6Assertions.assertThat(users.size()).isEqualTo(1);


    }

    @Test
    public void test_get_all_users_react() throws ExecutionException, InterruptedException {

        CompletableFuture<List<User>> future = userRepository.findAllUsers();

        Java6Assertions.assertThat(future.get().size()).isEqualTo(1);
    }

    @Test
    public void test_get_all_users_rx() throws ExecutionException, InterruptedException {

        Observable<User> allUsersRx = userRepository.findAllUsersRx();

        Java6Assertions.assertThat(allUsersRx.count()).isEqualTo(1);
    }
}
