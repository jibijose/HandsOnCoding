package hello;

import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveUserRepository extends ReactiveCouchbaseRepository<ReactiveUser, String> {

}
