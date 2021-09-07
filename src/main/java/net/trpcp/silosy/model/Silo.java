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
    private Float capacity;
    private Float stored;

    @OneToOne
    private Ware ware;

    private Integer status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "silo", fetch = FetchType.LAZY)
    private Set<SiloEvent> events;


    @Builder
    public Silo(Long id, String name, Float capacity, Set<SiloEvent> events, Float stored, Ware ware, Integer status) {
        super(id);
        this.name = name;
        this.capacity = capacity;
        this.events = events;
        this.stored = stored;
        this.ware = ware;
        this.status = status;
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
        return "Silos " +
                "nazwa: " + name +
                ", pojemność: " + capacity +
                "[t], obecnie: " + stored +
                "[t], towar: " + ware.getName() +
                ", status: " + status;
    }
}
