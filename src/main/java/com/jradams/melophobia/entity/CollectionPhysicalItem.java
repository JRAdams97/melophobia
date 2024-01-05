package com.jradams.melophobia.entity;

import com.jradams.melophobia.entity.backing.Quality;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Id
    @Column(name = "p_collection_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long physicalCollectionId;

    @OneToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    private Quality packagingQuality;
    private Quality mediaQuality;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    private String description;
}
