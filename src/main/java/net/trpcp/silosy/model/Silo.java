package net.trpcp.silosy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Silo extends BaseEntity{

    private String name;
    private Integer capacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "silo", fetch = FetchType.LAZY)
    private Set<SiloEvent> events;


    @Builder
    public Silo(Long id, String name, Integer capacity, Set<SiloEvent> events) {
        super(id);
        this.name = name;
        this.capacity = capacity;
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Silo silo = (Silo) o;
        return Objects.equals(name, silo.name) && Objects.equals(capacity, silo.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity);
    }

    @Override
    public String toString() {
        return "Silo{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
