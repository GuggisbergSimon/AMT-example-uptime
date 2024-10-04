package ch.heigvd.amt.entities;

import jakarta.persistence.*;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"url"})
})
public class Probe {
    @Id
    @GeneratedValue
    Long id;

    @NotNull
    @NotBlank
    @URL
    String url;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(@NotBlank @URL String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank @URL String getUrl() {
        return url;
    }

    public Probe() {
    }

    public Probe(Long id, String url) {
        this.id = id;
        this.url = url;
    }
}
