package net.trpcp.silosy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Ware extends BaseEntity{

    @NotNull
    @NotBlank
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ware", fetch = FetchType.LAZY)
    private Set<SiloEvent> events;

    @Builder
    public Ware(Long id, String name, Set<SiloEvent> events) {
        super(id);
        this.name = name;
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ware ware = (Ware) o;
        return Objects.equals(name, ware.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Ware{" +
                "name='" + name + '\'' +
                '}';
    }
}
