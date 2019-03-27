package hello.db;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.RawJsonDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.mapping.CouchbaseMappingContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import rx.Observable;
import rx.RxReactiveStreams;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class CouchbaseConnector {

    @Autowired
    protected Bucket couchBucket;

    protected AsyncBucket bucket;

    public CouchbaseConnector(CouchbaseMappingContext couchbaseContext) {
    }

    @PostConstruct
    protected void init() {
        bucket = couchBucket.async();
    }

    public Mono<RawJsonDocument> get(String key) {
        Observable<RawJsonDocument> promise = bucket.get(key, RawJsonDocument.class);
        Mono<RawJsonDocument> monoDocument = Mono.from(RxReactiveStreams.toPublisher(promise));
        return monoDocument;
    }
}
