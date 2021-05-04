package countryCRUD.countrCrud.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BaseEntity {
    @Column(insertable = true, updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;

    @PrePersist
    void onCreate(){
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }
    @PreUpdate
    void onUpdate(){
        this.modified = LocalDateTime.now();
    }
}
