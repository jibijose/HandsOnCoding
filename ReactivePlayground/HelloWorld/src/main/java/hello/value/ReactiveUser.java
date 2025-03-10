package hello.value;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReactiveUser {

    @Id
    private final String key = null;
    private final String username = null;
    private final int age = -1;

}
