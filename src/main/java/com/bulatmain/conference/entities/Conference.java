package com.bulatmain.conference.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Conference {
    @Id
    @SequenceGenerator(
            name = "conference_sequence",
            sequenceName = "conference_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conference_sequence"
    )
    private Long id;
    private String name;
}
