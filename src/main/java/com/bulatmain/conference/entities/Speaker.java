package com.bulatmain.conference.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Speaker {
    @Id
    @SequenceGenerator(
            name = "speaker_sequence",
            sequenceName = "speaker_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "speaker_sequence"
    )
    private Long id;
    private String name;

}
