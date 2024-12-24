package org.example.tkani.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.mapstruct.Named;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "badges_id_gen")
    @SequenceGenerator(name = "badges_id_gen", sequenceName = "badges_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @NotNull
    @Size(max = 50)
    @Column(name = "text_color", nullable = false, length = 50)
    private String textColor;

    @NotNull
    @Size(max = 50)
    @Column(name = "background_color", nullable = false, length = 50)
    private String backgroundColor;
}