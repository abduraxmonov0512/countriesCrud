package countryCRUD.countrCrud.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity {
    @Column(insertable = true, updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;

    @PrePersist
    void onCreate(){
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }
    @PreUpdate
    void onUpdate(){
        this.setModified(LocalDateTime.now());
    }
}
