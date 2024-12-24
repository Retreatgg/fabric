package org.example.tkani.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 300)
    @NotNull
    @Column(name = "email", nullable = false, length = 300)
    private String email;

    @Size(max = 260)
    @NotNull
    @Column(name = "password", nullable = false, length = 260)
    private String password;

    @Column(name = "token", nullable = false, length = 260)
    private String token;

    @ColumnDefault("true")
    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles = new LinkedHashSet<>();

}