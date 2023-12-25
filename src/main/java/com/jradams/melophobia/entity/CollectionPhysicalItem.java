package com.jradams.melophobia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "collection_physical")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CollectionPhysicalItem {

    @Column(name = "p_collection_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long physicalCollectionId;

    @OneToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    private Quality packagingQuality;
    private Quality mediaQuality;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    private String description;
}
