package com.jradams.melophobia.entity;

import com.jradams.melophobia.entity.backing.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "collection_digital")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CollectionDigitalItem {

    @Column(name = "d_collection_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long digitalCollectionId;

    @OneToOne
    @JoinColumn(name = "release_id")
    private Release release;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @NotNull(message = "Total track # is mandatory")
    private Integer totalTracks;

    private Integer missingTracks;

    @NotNull(message = "Total disc # is mandatory")
    private Integer totalDiscs;

    private Integer missingDiscs;
    private Status artQuality;
    private Status tagQuality;
    private Status status;
    private String description;
}
