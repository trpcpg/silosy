package net.trpcp.silosy.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class SiloEvent extends BaseEntity{

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "silo_id")
    private Silo silo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ware_id")
    private Ware ware;

    private Integer quantity;
    private String document;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    @Builder
    public SiloEvent(Long id, LocalDateTime eventTime, Silo silo, Ware ware, Integer quantity, String document, String description, Person person) {
        super(id);
        this.eventTime = eventTime;
        this.silo = silo;
        this.ware = ware;
        this.quantity = quantity;
        this.document = document;
        this.description = description;
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiloEvent siloEvent = (SiloEvent) o;
        return Objects.equals(eventTime, siloEvent.eventTime) && Objects.equals(silo, siloEvent.silo) && Objects.equals(ware, siloEvent.ware) && Objects.equals(quantity, siloEvent.quantity) && Objects.equals(document, siloEvent.document) && Objects.equals(description, siloEvent.description) && Objects.equals(person, siloEvent.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventTime, silo, ware, quantity, document, description, person);
    }

    @Override
    public String toString() {
        return "SiloEvent{" +
                "time=" + eventTime +
                ", silo=" + silo +
                ", ware=" + ware +
                ", quantity=" + quantity +
                ", document='" + document + '\'' +
                ", description='" + description + '\'' +
                ", person=" + person +
                '}';
    }
}
