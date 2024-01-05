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

    @Id
    @Column(name = "d_collection_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long digitalCollectionId;

    @OneToOne
    @JoinColumn(name = "release_id")
    private Release release;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @NotNull(message = "Total Track # is mandatory")
    private Integer totalTracks;

    private Integer missingTracks;

    @NotNull(message = "Total Disc # is mandatory")
    private Integer totalDiscs;

    private Integer missingDiscs;
    private Status artQuality;
    private Status tagQuality;
    private Status status;
    private String description;
}
