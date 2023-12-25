package com.jradams.melophobia.entity;

import com.jradams.melophobia.entity.backing.ReleaseTypeName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReleaseType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long releaseTypeId;

    private ReleaseTypeName name;
}
