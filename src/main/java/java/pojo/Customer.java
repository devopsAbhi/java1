package java.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by deven.phillips on 2/7/16.
 */
@Data
@Accessors(chain = true, fluent = true)
@Entity
@Table(name="customers")
public class Customer implements Serializable {

    private long id;
    private String givenName;
    private String familyName;
    private String title;

}
