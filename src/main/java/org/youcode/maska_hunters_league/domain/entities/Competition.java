package org.youcode.maska_hunters_league.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.youcode.maska_hunters_league.domain.enums.SpeciesType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE competition SET deleted = true, deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted=false")
public class Competition {
    @Id
    @GeneratedValue
    private UUID id;

    private String code;

    private String location;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private SpeciesType speciesType;

    private Integer minParticipants;

    private Integer maxParticipants;

    private Boolean openRegistration;

    @OneToMany(mappedBy = "competition")
    private List<Participation> participations;

    @Column(name = "deleted")
    private boolean deleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
