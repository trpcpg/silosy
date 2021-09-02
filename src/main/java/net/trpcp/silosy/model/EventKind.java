package net.trpcp.silosy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class EventKind{

    @Id
    private Long id;
    private String name;
    private Integer factor;
    @OneToOne
    private EventKind sibling;

    @Builder
    public EventKind(Long id, String name, Integer factor, EventKind sibling) {
        this.id = id;
        this.name = name;
        this.factor = factor;
        this.sibling = sibling;
    }
}
