package ru.ccfit.golubevm.musicdbapp.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role", indexes = {
        @Index(name = "idx_role_name", columnList = "name")
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 32)
    @NotNull
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Override
    public String toString() {
        return name;
    }
}