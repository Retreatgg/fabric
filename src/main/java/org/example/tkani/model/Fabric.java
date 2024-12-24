package org.example.tkani.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fabrics")
public class Fabric {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fabrics_id_gen")
    @SequenceGenerator(name = "fabrics_id_gen", sequenceName = "fabrics_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false, length = 300)
    private String description;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @Size(max = 300)
    @NotNull
    @Column(name = "image", nullable = false, length = 300)
    private String image;

    @ColumnDefault("true")
    @Column(name = "enabled")
    private Boolean enabled;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "badge_id", nullable = false)
    private Badge badge;

}