namespace Melophobia.Data
{
    using Microsoft.EntityFrameworkCore;
    using Model;

    public class MelophobiaContext : DbContext
    {
        public MelophobiaContext()
        {
        }

        public MelophobiaContext(DbContextOptions<MelophobiaContext> options) : base(options)
        {
        }

        public virtual DbSet<Artist> Artists { get; set; }

        public virtual DbSet<ArtistAlias> ArtistAliases { get; set; }

        public virtual DbSet<Chart> Charts { get; set; }

        public virtual DbSet<CollectionItem> CollectionItems { get; set; }

        public virtual DbSet<Composer> Composers { get; set; }

        public virtual DbSet<ComposerIpi> ComposerIpis { get; set; }

        public virtual DbSet<Country> Countries { get; set; }

        public virtual DbSet<EntityLink> EntityLinks { get; set; }

        public virtual DbSet<Genre> Genres { get; set; }

        public virtual DbSet<GenreAlias> GenreAliases { get; set; }

        public virtual DbSet<Issue> Issues { get; set; }

        public virtual DbSet<IssueVariant> IssueVariants { get; set; }

        public virtual DbSet<Label> Labels { get; set; }

        public virtual DbSet<LabelAlias> LabelAliases { get; set; }

        public virtual DbSet<LabelIpi> LabelIpis { get; set; }

        public virtual DbSet<Location> Locations { get; set; }

        public virtual DbSet<LocationAlias> LocationAliases { get; set; }

        public virtual DbSet<LocationPostcode> LocationPostcodes { get; set; }

        public virtual DbSet<Medium> Media { get; set; }

        public virtual DbSet<Producer> Producers { get; set; }

        public virtual DbSet<ProducerIpi> ProducerIpis { get; set; }

        public virtual DbSet<Region> Regions { get; set; }

        public virtual DbSet<Release> Releases { get; set; }

        public virtual DbSet<ReleaseLanguage> ReleaseLanguages { get; set; }

        public virtual DbSet<ReleaseType> ReleaseTypes { get; set; }

        public virtual DbSet<Series> Series { get; set; }

        public virtual DbSet<Track> Tracks { get; set; }

        public virtual DbSet<TrackAlias> TrackAliases { get; set; }

        public virtual DbSet<TrackChart> TrackCharts { get; set; }

        public virtual DbSet<TrackIsrc> TrackIsrcs { get; set; }

        public virtual DbSet<TrackIswc> TrackIswcs { get; set; }

        public virtual DbSet<Vendor> Vendors { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) => optionsBuilder.UseNpgsql(
                "Host=localhost;Port=5432;Database=melophobia;Username=postgres;Password=5VCRpz6415!");

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
                modelBuilder.HasPostgresEnum("e_entity_type", ["Artist", "Composer", "Label", "Producer"]);

            modelBuilder.Entity<Artist>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("artist_pkey");
                entity.ToTable("artist");

                entity.HasIndex(e => e.Isni, "artist_isni_key").IsUnique();
                entity.HasIndex(e => e.FormationLocationId, "idx_artist__formation_location");

                entity.Property(e => e.Id).HasColumnName("id");
                entity.Property(e => e.DisbandYear).HasColumnName("disband_year");
                entity.Property(e => e.FormationLocationId).HasColumnName("formation_location_id");
                entity.Property(e => e.FormationYear).HasColumnName("formation_year");

                entity.Property(e => e.IsFavourite)
                        .HasDefaultValue(false)
                        .HasColumnName("is_favourite");

                entity.Property(e => e.Isni)
                        .HasMaxLength(19)
                        .IsFixedLength()
                        .HasColumnName("isni");

                entity.Property(e => e.Name).HasColumnName("name");
                entity.Property(e => e.SortName).HasColumnName("sort_name");
                entity.Property(e => e.Gender).HasColumnName("gender");
                entity.Property(e => e.Type).HasColumnName("type");
                entity.Property(e => e.Website).HasColumnName("website");

                entity.HasOne(d => d.FormationLocation).WithMany(p => p.Artists)
                        .HasForeignKey(d => d.FormationLocationId)
                        .HasConstraintName("artist_formation_location_id_fkey");

                entity.HasMany(d => d.Genres).WithMany(p => p.Artists)
                        .UsingEntity<Dictionary<string, object>>(
                                "ArtistGenre",
                                r => r.HasOne<Genre>().WithMany()
                                        .HasForeignKey("GenreId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("artist_genres_genre_id_fkey"),
                                l => l.HasOne<Artist>().WithMany()
                                        .HasForeignKey("ArtistId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("artist_genres_artist_id_fkey"),
                                j =>
                                {
                                    j.HasKey("ArtistId", "GenreId").HasName("artist_genres_pkey");
                                    j.ToTable("artist_genres");
                                    j.HasIndex(["ArtistId"], "idx_artist_genres__artist");
                                    j.HasIndex(["GenreId"], "idx_artist_genres__genre");
                                    j.IndexerProperty<int>("ArtistId").HasColumnName("artist_id");
                                    j.IndexerProperty<int>("GenreId").HasColumnName("genre_id");
                                });

                entity.HasMany(d => d.Labels).WithMany(p => p.Artists)
                        .UsingEntity<Dictionary<string, object>>(
                                "ArtistLabel",
                                r => r.HasOne<Label>().WithMany()
                                        .HasForeignKey("LabelId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("artist_labels_label_id_fkey"),
                                l => l.HasOne<Artist>().WithMany()
                                        .HasForeignKey("ArtistId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("artist_labels_artist_id_fkey"),
                                j =>
                                {
                                    j.HasKey("ArtistId", "LabelId").HasName("artist_labels_pkey");
                                    j.ToTable("artist_labels");
                                    j.HasIndex(["ArtistId"], "idx_artist_labels__artist");
                                    j.HasIndex(["LabelId"], "idx_artist_labels__label");
                                    j.IndexerProperty<int>("ArtistId").HasColumnName("artist_id");
                                    j.IndexerProperty<int>("LabelId").HasColumnName("label_id");
                                });
            });

            modelBuilder.Entity<ArtistAlias>(entity =>
            {
                entity.HasKey(e => new { e.ArtistId, e.Alias }).HasName("artist_aliases_pkey");
                entity.ToTable("artist_aliases");

                entity.HasIndex(e => e.ArtistId, "idx_artist_aliases__artist_id");

                entity.Property(e => e.ArtistId).HasColumnName("artist_id");
                entity.Property(e => e.Alias).HasColumnName("alias");

                entity.HasOne(d => d.Artist).WithMany(p => p.ArtistAliases)
                        .HasForeignKey(d => d.ArtistId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("artist_aliases_artist_id_fkey");
            });

            modelBuilder.Entity<Chart>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("chart_pkey");
                entity.ToTable("chart");

                entity.HasIndex(e => e.Name, "chart_name_key").IsUnique();
                entity.HasIndex(e => e.CountryOfOriginId, "idx_chart__country");

                entity.Property(e => e.Id).HasColumnName("id");
                entity.Property(e => e.CountryOfOriginId).HasColumnName("country_of_origin_id");

                entity.Property(e => e.Name)
                        .HasMaxLength(20)
                        .HasColumnName("name");

                entity.Property(e => e.YearOfOrigin).HasColumnName("year_of_origin");

                entity.HasOne(d => d.CountryOfOrigin).WithMany(p => p.Charts)
                        .HasForeignKey(d => d.CountryOfOriginId)
                        .HasConstraintName("chart_country_of_origin_id_fkey");
            });

            modelBuilder.Entity<CollectionItem>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("collection_item_pkey");
                entity.ToTable("collection_item");

                entity.HasIndex(e => e.IssueId, "idx_collection_item__issue");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Comment)
                        .HasMaxLength(100)
                        .HasColumnName("comment");

                entity.Property(e => e.HasPromoMaterial).HasColumnName("has_promo_material");
                entity.Property(e => e.IsMissingMedia).HasColumnName("is_missing_media");
                entity.Property(e => e.IsMissingPackaging).HasColumnName("is_missing_packaging");
                entity.Property(e => e.IssueId).HasColumnName("issue_id");
                entity.Property(e => e.PackagingGrade).HasColumnName("packaging_grade");
                entity.Property(e => e.MediaGrade).HasColumnName("media_grade");
                entity.Property(e => e.AppCoverQuality).HasColumnName("app_cover_quality");
                entity.Property(e => e.RipState).HasColumnName("rip_state");

                entity.Property(e => e.MediaComment)
                        .HasMaxLength(100)
                        .HasColumnName("media_comment");

                entity.Property(e => e.PackagingComment)
                        .HasMaxLength(100)
                        .HasColumnName("packaging_comment");

                entity.Property(e => e.RemainingDiscs)
                        .HasDefaultValue((short)0)
                        .HasColumnName("remaining_discs");

                entity.Property(e => e.RemainingTracks)
                        .HasDefaultValue((short)0)
                        .HasColumnName("remaining_tracks");

                entity.HasOne(d => d.Issue).WithMany(p => p.CollectionItems)
                        .HasForeignKey(d => d.IssueId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("collection_item_issue_id_fkey");
            });

            modelBuilder.Entity<Composer>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("composer_pkey");
                entity.ToTable("composer");

                entity.HasIndex(e => e.BirthLocationId, "idx_composer__birth_location");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.BirthDate)
                        .HasMaxLength(10)
                        .IsFixedLength()
                        .HasColumnName("birth_date");

                entity.Property(e => e.BirthLocationId).HasColumnName("birth_location_id");

                entity.Property(e => e.DeathDate)
                        .HasMaxLength(10)
                        .IsFixedLength()
                        .HasColumnName("death_date");

                entity.Property(e => e.Name).HasColumnName("name");
                entity.Property(e => e.SortName).HasColumnName("sort_name");
                entity.Property(e => e.Gender).HasColumnName("gender");

                entity.HasOne(d => d.BirthLocation).WithMany(p => p.Composers)
                        .HasForeignKey(d => d.BirthLocationId)
                        .HasConstraintName("composer_birth_location_id_fkey");
            });

            modelBuilder.Entity<ComposerIpi>(entity =>
            {
                entity.HasKey(e => e.Ipi).HasName("composer_ipis_pkey");
                entity.ToTable("composer_ipis");

                entity.HasIndex(e => e.ComposerId, "idx_composer_ipis__composer");

                entity.Property(e => e.Ipi)
                        .HasMaxLength(11)
                        .IsFixedLength()
                        .HasColumnName("ipi");

                entity.Property(e => e.Comment)
                        .HasMaxLength(100)
                        .HasColumnName("comment");

                entity.Property(e => e.ComposerId).HasColumnName("composer_id");

                entity.HasOne(d => d.Composer).WithMany(p => p.ComposerIpis)
                        .HasForeignKey(d => d.ComposerId)
                        .HasConstraintName("composer_ipis_composer_id_fkey");
            });

            modelBuilder.Entity<Country>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("country_pkey");
                entity.ToTable("country");

                entity.HasIndex(e => e.Alpha2Code, "country_alpha2_code_key").IsUnique();
                entity.HasIndex(e => e.Name, "country_name_key").IsUnique();

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Alpha2Code)
                        .HasMaxLength(2)
                        .IsFixedLength()
                        .HasColumnName("alpha2_code");

                entity.Property(e => e.Name)
                        .HasMaxLength(40)
                        .HasColumnName("name");
            });

            modelBuilder.Entity<EntityLink>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("entity_link_pkey");
                entity.ToTable("entity_link");

                entity.Property(e => e.Id).HasColumnName("id");
                entity.Property(e => e.LeftId).HasColumnName("left_id");
                entity.Property(e => e.Relationship).HasColumnName("relationship");
                entity.Property(e => e.RightId).HasColumnName("right_id");
            });

            modelBuilder.Entity<Genre>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("genre_pkey");
                entity.ToTable("genre");

                entity.HasIndex(e => e.Name, "genre_name_key").IsUnique();

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.IsFavourite)
                        .HasDefaultValue(false)
                        .HasColumnName("is_favourite");

                entity.Property(e => e.Name)
                        .HasMaxLength(40)
                        .HasColumnName("name");

                entity.Property(e => e.YearOfOrigin).HasColumnName("year_of_origin");

                entity.HasMany(d => d.Genres).WithMany(p => p.ParentGenres)
                        .UsingEntity<Dictionary<string, object>>(
                                "GenreTree",
                                r => r.HasOne<Genre>().WithMany()
                                        .HasForeignKey("GenreId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("genre_tree_genre_id_fkey"),
                                l => l.HasOne<Genre>().WithMany()
                                        .HasForeignKey("ParentGenreId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("genre_tree_parent_genre_id_fkey"),
                                j =>
                                {
                                    j.HasKey("GenreId", "ParentGenreId").HasName("genre_tree_pkey");
                                    j.ToTable("genre_tree");
                                    j.HasIndex(["GenreId"], "idx_genre_tree__genre");
                                    j.HasIndex(["ParentGenreId"], "idx_genre_tree__parent_genre");
                                    j.IndexerProperty<int>("GenreId").HasColumnName("genre_id");
                                    j.IndexerProperty<int>("ParentGenreId").HasColumnName("parent_genre_id");
                                });

                entity.HasMany(d => d.ParentGenres).WithMany(p => p.Genres)
                        .UsingEntity<Dictionary<string, object>>(
                                "GenreTree",
                                r => r.HasOne<Genre>().WithMany()
                                        .HasForeignKey("ParentGenreId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("genre_tree_parent_genre_id_fkey"),
                                l => l.HasOne<Genre>().WithMany()
                                        .HasForeignKey("GenreId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("genre_tree_genre_id_fkey"),
                                j =>
                                {
                                    j.HasKey("GenreId", "ParentGenreId").HasName("genre_tree_pkey");
                                    j.ToTable("genre_tree");
                                    j.HasIndex(["GenreId"], "idx_genre_tree__genre");
                                    j.HasIndex(["ParentGenreId"], "idx_genre_tree__parent_genre");
                                    j.IndexerProperty<int>("GenreId").HasColumnName("genre_id");
                                    j.IndexerProperty<int>("ParentGenreId").HasColumnName("parent_genre_id");
                                });
            });

            modelBuilder.Entity<GenreAlias>(entity =>
            {
                entity.HasKey(e => new { e.GenreId, e.Alias }).HasName("genre_aliases_pkey");
                entity.ToTable("genre_aliases");

                entity.HasIndex(e => e.GenreId, "idx_genre_aliases__genre");

                entity.Property(e => e.GenreId).HasColumnName("genre_id");
                entity.Property(e => e.Alias).HasColumnName("alias");

                entity.HasOne(d => d.Genre).WithMany(p => p.GenreAliases)
                        .HasForeignKey(d => d.GenreId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("genre_aliases_genre_id_fkey");
            });

            modelBuilder.Entity<Issue>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("issue_pkey");
                entity.ToTable("issue");

                entity.HasIndex(e => e.LabelId, "idx_issue__label");
                entity.HasIndex(e => e.MediaId, "idx_issue__media");
                entity.HasIndex(e => e.ReleaseId, "idx_issue__release");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Barcode)
                        .HasMaxLength(50)
                        .HasColumnName("barcode");

                entity.Property(e => e.CatalogueNumber)
                        .HasMaxLength(20)
                        .HasColumnName("catalogue_number");

                entity.Property(e => e.Comment)
                        .HasMaxLength(100)
                        .HasColumnName("comment");

                entity.Property(e => e.Edition)
                        .HasMaxLength(50)
                        .HasDefaultValue("Standard")
                        .HasColumnName("edition");

                entity.Property(e => e.HasDataTrack)
                        .HasDefaultValue(false)
                        .HasColumnName("has_data_track");

                entity.Property(e => e.HasPregapTrack)
                        .HasDefaultValue(false)
                        .HasColumnName("has_pregap_track");

                entity.Property(e => e.IsOfficial)
                        .HasDefaultValue(true)
                        .HasColumnName("is_official");

                entity.Property(e => e.IsPromotional)
                        .HasDefaultValue(false)
                        .HasColumnName("is_promotional");

                entity.Property(e => e.IsReissue)
                        .HasDefaultValue(false)
                        .HasColumnName("is_reissue");

                entity.Property(e => e.LabelId).HasColumnName("label_id");
                entity.Property(e => e.MediaId).HasColumnName("media_id");

                entity.Property(e => e.ReleaseDate)
                        .HasMaxLength(10)
                        .HasDefaultValueSql("'0000-00-00'::bpchar")
                        .IsFixedLength()
                        .HasColumnName("release_date");

                entity.Property(e => e.ReleaseId).HasColumnName("release_id");

                entity.HasOne(d => d.Label).WithMany(p => p.Issues)
                        .HasForeignKey(d => d.LabelId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("issue_label_id_fkey");

                entity.HasOne(d => d.Media).WithMany(p => p.Issues)
                        .HasForeignKey(d => d.MediaId)
                        .HasConstraintName("issue_media_id_fkey");

                entity.HasOne(d => d.Release).WithMany(p => p.Issues)
                        .HasForeignKey(d => d.ReleaseId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("issue_release_id_fkey");

                entity.HasMany(d => d.Countries).WithMany(p => p.Issues)
                        .UsingEntity<Dictionary<string, object>>(
                                "IssueCountry",
                                r => r.HasOne<Country>().WithMany()
                                        .HasForeignKey("CountryId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("issue_countries_country_id_fkey"),
                                l => l.HasOne<Issue>().WithMany()
                                        .HasForeignKey("IssueId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("issue_countries_issue_id_fkey"),
                                j =>
                                {
                                    j.HasKey("IssueId", "CountryId").HasName("issue_countries_pkey");
                                    j.ToTable("issue_countries");
                                    j.HasIndex(["CountryId"], "idx_issue_countries__country");
                                    j.HasIndex(["IssueId"], "idx_issue_countries__issue");
                                    j.IndexerProperty<int>("IssueId").HasColumnName("issue_id");
                                    j.IndexerProperty<int>("CountryId").HasColumnName("country_id");
                                });

                entity.HasMany(d => d.Tracks).WithMany(p => p.Issues)
                        .UsingEntity<Dictionary<string, object>>(
                                "IssueTrack",
                                r => r.HasOne<Track>().WithMany()
                                        .HasForeignKey("TrackId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("issue_tracks_track_id_fkey"),
                                l => l.HasOne<Issue>().WithMany()
                                        .HasForeignKey("IssueId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("issue_tracks_issue_id_fkey"),
                                j =>
                                {
                                    j.HasKey("IssueId", "TrackId").HasName("issue_tracks_pkey");
                                    j.ToTable("issue_tracks");
                                    j.HasIndex(["IssueId"], "idx_issue_tracks__issue");
                                    j.HasIndex(["TrackId"], "idx_issue_tracks__track");
                                    j.IndexerProperty<int>("IssueId").HasColumnName("issue_id");
                                    j.IndexerProperty<int>("TrackId").HasColumnName("track_id");
                                });
            });

            modelBuilder.Entity<IssueVariant>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("issue_variant_pkey");
                entity.ToTable("issue_variant");

                entity.HasIndex(e => e.IssueId, "idx_issue_variant__issue");

                entity.Property(e => e.Id).HasColumnName("id");
                entity.Property(e => e.Discid).HasColumnName("discid");
                entity.Property(e => e.IssueId).HasColumnName("issue_id");

                entity.Property(e => e.MasteringSidCode)
                        .HasMaxLength(20)
                        .HasColumnName("mastering_sid_code");

                entity.Property(e => e.MatrixRunout)
                        .HasColumnType("character varying")
                        .HasColumnName("matrix_runout");

                entity.Property(e => e.MouldSidCode)
                        .HasMaxLength(20)
                        .HasColumnName("mould_sid_code");

                entity.Property(e => e.SparsCode)
                        .HasMaxLength(7)
                        .HasColumnName("spars_code");

                entity.HasOne(d => d.Issue).WithMany(p => p.IssueVariants)
                        .HasForeignKey(d => d.IssueId)
                        .HasConstraintName("issue_variant_issue_id_fkey");
            });

            modelBuilder.Entity<Label>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("label_pkey");
                entity.ToTable("label");

                entity.HasIndex(e => e.FormationLocationId, "idx_label__formation_location");
                entity.HasIndex(e => e.Isni, "label_isni_key").IsUnique();

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.ClosureDate)
                        .HasMaxLength(10)
                        .IsFixedLength()
                        .HasColumnName("closure_date");

                entity.Property(e => e.FormationDate)
                        .HasMaxLength(10)
                        .HasDefaultValueSql("'0000-00-00'::bpchar")
                        .IsFixedLength()
                        .HasColumnName("formation_date");

                entity.Property(e => e.FormationLocationId).HasColumnName("formation_location_id");

                entity.Property(e => e.IsFavourite)
                        .HasDefaultValue(false)
                        .HasColumnName("is_favourite");

                entity.Property(e => e.Isni)
                        .HasMaxLength(19)
                        .IsFixedLength()
                        .HasColumnName("isni");

                entity.Property(e => e.LabelCode)
                        .HasMaxLength(8)
                        .IsFixedLength()
                        .HasColumnName("label_code");

                entity.Property(e => e.Name).HasColumnName("name");
                entity.Property(e => e.SortName).HasColumnName("sort_name");
                entity.Property(e => e.Type).HasColumnName("type");

                entity.HasOne(d => d.FormationLocation).WithMany(p => p.Labels)
                        .HasForeignKey(d => d.FormationLocationId)
                        .HasConstraintName("label_formation_location_id_fkey");

                entity.HasMany(d => d.Labels).WithMany(p => p.ParentLabels)
                        .UsingEntity<Dictionary<string, object>>(
                                "LabelTree",
                                r => r.HasOne<Label>().WithMany()
                                        .HasForeignKey("LabelId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("label_tree_label_id_fkey"),
                                l => l.HasOne<Label>().WithMany()
                                        .HasForeignKey("ParentLabelId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("label_tree_parent_label_id_fkey"),
                                j =>
                                {
                                    j.HasKey("LabelId", "ParentLabelId").HasName("label_tree_pkey");
                                    j.ToTable("label_tree");
                                    j.HasIndex(["LabelId"], "idx_label_tree__label");
                                    j.HasIndex(["ParentLabelId"], "idx_label_tree__parent_label");
                                    j.IndexerProperty<int>("LabelId").HasColumnName("label_id");
                                    j.IndexerProperty<int>("ParentLabelId").HasColumnName("parent_label_id");
                                });

                entity.HasMany(d => d.ParentLabels).WithMany(p => p.Labels)
                        .UsingEntity<Dictionary<string, object>>(
                                "LabelTree",
                                r => r.HasOne<Label>().WithMany()
                                        .HasForeignKey("ParentLabelId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("label_tree_parent_label_id_fkey"),
                                l => l.HasOne<Label>().WithMany()
                                        .HasForeignKey("LabelId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("label_tree_label_id_fkey"),
                                j =>
                                {
                                    j.HasKey("LabelId", "ParentLabelId").HasName("label_tree_pkey");
                                    j.ToTable("label_tree");
                                    j.HasIndex(["LabelId"], "idx_label_tree__label");
                                    j.HasIndex(["ParentLabelId"], "idx_label_tree__parent_label");
                                    j.IndexerProperty<int>("LabelId").HasColumnName("label_id");
                                    j.IndexerProperty<int>("ParentLabelId").HasColumnName("parent_label_id");
                                });
            });

            modelBuilder.Entity<LabelAlias>(entity =>
            {
                entity.HasKey(e => new { e.LabelId, e.Alias }).HasName("label_aliases_pkey");
                entity.ToTable("label_aliases");

                entity.HasIndex(e => e.LabelId, "idx_label_aliases__label");

                entity.Property(e => e.LabelId).HasColumnName("label_id");
                entity.Property(e => e.Alias).HasColumnName("alias");

                entity.HasOne(d => d.Label).WithMany(p => p.LabelAliases)
                        .HasForeignKey(d => d.LabelId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("label_aliases_label_id_fkey");
            });

            modelBuilder.Entity<LabelIpi>(entity =>
            {
                entity.HasKey(e => e.Ipi).HasName("label_ipis_pkey");
                entity.ToTable("label_ipis");

                entity.HasIndex(e => e.LabelId, "idx_label_ipis__label");

                entity.Property(e => e.Ipi)
                        .HasMaxLength(11)
                        .IsFixedLength()
                        .HasColumnName("ipi");

                entity.Property(e => e.Comment)
                        .HasMaxLength(100)
                        .HasColumnName("comment");

                entity.Property(e => e.LabelId).HasColumnName("label_id");

                entity.HasOne(d => d.Label).WithMany(p => p.LabelIpis)
                        .HasForeignKey(d => d.LabelId)
                        .HasConstraintName("label_ipis_label_id_fkey");
            });

            modelBuilder.Entity<Location>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("location_pkey");
                entity.ToTable("location");

                entity.HasIndex(e => e.RegionId, "idx_location__region");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Address)
                        .HasColumnType("character varying")
                        .HasColumnName("address");

                entity.Property(e => e.Latitude)
                        .HasPrecision(8, 6)
                        .HasColumnName("latitude");

                entity.Property(e => e.Longitude)
                        .HasPrecision(9, 6)
                        .HasColumnName("longitude");

                entity.Property(e => e.Postcode)
                        .HasMaxLength(10)
                        .HasColumnName("postcode");

                entity.Property(e => e.RegionId).HasColumnName("region_id");

                entity.HasOne(d => d.Region).WithMany(p => p.Locations)
                        .HasForeignKey(d => d.RegionId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("location_region_id_fkey");
            });

            modelBuilder.Entity<LocationAlias>(entity =>
            {
                entity.HasKey(e => new { e.LocationId, e.Alias }).HasName("location_aliases_pkey");
                entity.ToTable("location_aliases");

                entity.HasIndex(e => e.LocationId, "idx_location_aliases__location");

                entity.Property(e => e.LocationId).HasColumnName("location_id");
                entity.Property(e => e.Alias).HasColumnName("alias");

                entity.HasOne(d => d.Location).WithMany(p => p.LocationAliases)
                        .HasForeignKey(d => d.LocationId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("location_aliases_location_id_fkey");
            });

            modelBuilder.Entity<LocationPostcode>(entity =>
            {
                entity.HasKey(e => new { e.LocationId, e.Postcode }).HasName("location_postcodes_pkey");
                entity.ToTable("location_postcodes");

                entity.HasIndex(e => e.LocationId, "idx_location_postcodes__location");

                entity.Property(e => e.LocationId).HasColumnName("location_id");

                entity.Property(e => e.Postcode)
                        .HasMaxLength(10)
                        .HasColumnName("postcode");

                entity.HasOne(d => d.Location).WithMany(p => p.LocationPostcodes)
                        .HasForeignKey(d => d.LocationId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("location_postcodes_location_id_fkey");
            });

            modelBuilder.Entity<Medium>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("media_pkey");
                entity.ToTable("media");

                entity.HasIndex(e => e.Name, "media_name_key").IsUnique();

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Abbreviation)
                        .HasMaxLength(20)
                        .HasColumnName("abbreviation");

                entity.Property(e => e.Classification)
                        .HasMaxLength(50)
                        .HasColumnName("classification");

                entity.Property(e => e.Name).HasColumnName("name");
                entity.Property(e => e.YearOfOrigin).HasColumnName("year_of_origin");
            });

            modelBuilder.Entity<Producer>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("producer_pkey");
                entity.ToTable("producer");

                entity.HasIndex(e => e.BirthLocationId, "idx_producer__birth_location");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.BirthDate)
                        .HasMaxLength(10)
                        .HasDefaultValueSql("'0000-00-00'::bpchar")
                        .IsFixedLength()
                        .HasColumnName("birth_date");

                entity.Property(e => e.BirthLocationId).HasColumnName("birth_location_id");

                entity.Property(e => e.DeathDate)
                        .HasMaxLength(10)
                        .IsFixedLength()
                        .HasColumnName("death_date");

                entity.Property(e => e.Name).HasColumnName("name");
                entity.Property(e => e.SortName).HasColumnName("sort_name");
                entity.Property(e => e.Gender).HasColumnName("gender");

                entity.HasOne(d => d.BirthLocation).WithMany(p => p.Producers)
                        .HasForeignKey(d => d.BirthLocationId)
                        .HasConstraintName("producer_birth_location_id_fkey");
            });

            modelBuilder.Entity<ProducerIpi>(entity =>
            {
                entity.HasKey(e => e.Ipi).HasName("producer_ipis_pkey");
                entity.ToTable("producer_ipis");

                entity.Property(e => e.Ipi)
                        .HasMaxLength(11)
                        .IsFixedLength()
                        .HasColumnName("ipi");

                entity.Property(e => e.Comment)
                        .HasMaxLength(100)
                        .HasColumnName("comment");

                entity.Property(e => e.ProducerId).HasColumnName("producer_id");

                entity.HasOne(d => d.Producer).WithMany(p => p.ProducerIpis)
                        .HasForeignKey(d => d.ProducerId)
                        .HasConstraintName("producer_ipis_producer_id_fkey");
            });

            modelBuilder.Entity<Region>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("region_pkey");
                entity.ToTable("region");

                entity.HasIndex(e => e.CountryId, "idx_region__country");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Abbreviation)
                        .HasMaxLength(20)
                        .HasColumnName("abbreviation");

                entity.Property(e => e.CountryId).HasColumnName("country_id");
                entity.Property(e => e.Name).HasColumnName("name");

                entity.HasOne(d => d.Country).WithMany(p => p.Regions)
                        .HasForeignKey(d => d.CountryId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("region_country_id_fkey");
            });

            modelBuilder.Entity<Release>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("release_pkey");
                entity.ToTable("release");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.AllmusicCriticRating)
                        .HasPrecision(3, 1)
                        .HasColumnName("allmusic_critic_rating");

                entity.Property(e => e.AllmusicUserRating)
                        .HasPrecision(3, 1)
                        .HasColumnName("allmusic_user_rating");

                entity.Property(e => e.AotyCriticRank).HasColumnName("aoty_critic_rank");
                entity.Property(e => e.AotyUserRank).HasColumnName("aoty_user_rank");
                entity.Property(e => e.BeaGlobalRank).HasColumnName("bea_global_rank");
                entity.Property(e => e.BeaYearlyRank).HasColumnName("bea_yearly_rank");

                entity.Property(e => e.ChristgauRating)
                        .HasMaxLength(4)
                        .IsFixedLength()
                        .HasColumnName("christgau_rating");

                entity.Property(e => e.FantanoRating)
                        .HasMaxLength(10)
                        .HasColumnName("fantano_rating");

                entity.Property(e => e.IsFavourite)
                        .HasDefaultValue(false)
                        .HasColumnName("is_favourite");

                entity.Property(e => e.IsOfficial).HasColumnName("is_official");
                entity.Property(e => e.LouderThanWarRating).HasColumnName("louder_than_war_rating");
                entity.Property(e => e.Metacritic).HasColumnName("metacritic");

                entity.Property(e => e.NmeRating)
                        .HasPrecision(3, 1)
                        .HasColumnName("nme_rating");

                entity.Property(e => e.OriginalTitle)
                        .HasColumnType("character varying")
                        .HasColumnName("original_title");

                entity.Property(e => e.PitchforkRating)
                        .HasPrecision(3, 1)
                        .HasColumnName("pitchfork_rating");

                entity.Property(e => e.PopMattersRating).HasColumnName("popmatters_rating");

                entity.Property(e => e.ReleaseDate)
                        .HasMaxLength(10)
                        .HasDefaultValueSql("'0000-00-00'::bpchar")
                        .IsFixedLength()
                        .HasColumnName("release_date");

                entity.Property(e => e.RollingStoneRating).HasColumnName("rolling_stone_rating");

                entity.Property(e => e.RymRating)
                        .HasPrecision(3, 2)
                        .HasColumnName("rym_rating");
                entity.Property(e => e.RymYearRank).HasColumnName("rym_year_rank");
                entity.Property(e => e.ScaruffiRating)
                        .HasPrecision(3, 1)
                        .HasColumnName("scaruffi_rating");

                entity.Property(e => e.Title).HasColumnName("title");

                entity.HasMany(d => d.Artists).WithMany(p => p.Releases)
                        .UsingEntity<Dictionary<string, object>>(
                                "ReleaseArtist",
                                r => r.HasOne<Artist>().WithMany()
                                        .HasForeignKey("ArtistId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("release_artists_artist_id_fkey"),
                                l => l.HasOne<Release>().WithMany()
                                        .HasForeignKey("ReleaseId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("release_artists_release_id_fkey"),
                                j =>
                                {
                                    j.HasKey("ReleaseId", "ArtistId").HasName("release_artists_pkey");
                                    j.ToTable("release_artists");
                                    j.HasIndex(["ArtistId"], "idx_release_artists__artist");
                                    j.HasIndex(["ReleaseId"], "idx_release_artists__release");
                                    j.IndexerProperty<int>("ReleaseId").HasColumnName("release_id");
                                    j.IndexerProperty<int>("ArtistId").HasColumnName("artist_id");
                                });

                entity.HasMany(d => d.Genres).WithMany(p => p.Releases)
                        .UsingEntity<Dictionary<string, object>>(
                                "ReleaseGenre",
                                r => r.HasOne<Genre>().WithMany()
                                        .HasForeignKey("GenreId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("release_genres_genre_id_fkey"),
                                l => l.HasOne<Release>().WithMany()
                                        .HasForeignKey("ReleaseId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("release_genres_release_id_fkey"),
                                j =>
                                {
                                    j.HasKey("ReleaseId", "GenreId").HasName("release_genres_pkey");
                                    j.ToTable("release_genres");
                                    j.HasIndex(["GenreId"], "idx_release_genres__genre");
                                    j.HasIndex(["ReleaseId"], "idx_release_genres__release");
                                    j.IndexerProperty<int>("ReleaseId").HasColumnName("release_id");
                                    j.IndexerProperty<int>("GenreId").HasColumnName("genre_id");
                                });

                entity.HasMany(d => d.Producers).WithMany(p => p.Releases)
                        .UsingEntity<Dictionary<string, object>>(
                                "ReleaseProducer",
                                r => r.HasOne<Producer>().WithMany()
                                        .HasForeignKey("ProducerId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("release_producers_producer_id_fkey"),
                                l => l.HasOne<Release>().WithMany()
                                        .HasForeignKey("ReleaseId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("release_producers_release_id_fkey"),
                                j =>
                                {
                                    j.HasKey("ReleaseId", "ProducerId").HasName("release_producers_pkey");
                                    j.ToTable("release_producers");
                                    j.HasIndex(["ProducerId"], "idx_release_producers__producer");
                                    j.HasIndex(["ReleaseId"], "idx_release_producers__release");
                                    j.IndexerProperty<int>("ReleaseId").HasColumnName("release_id");
                                    j.IndexerProperty<int>("ProducerId").HasColumnName("producer_id");
                                });

                entity.HasMany(d => d.Series).WithMany(p => p.Releases)
                        .UsingEntity<Dictionary<string, object>>(
                                "ReleaseSeries",
                                r => r.HasOne<Series>().WithMany()
                                        .HasForeignKey("SeriesId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("release_series_series_id_fkey"),
                                l => l.HasOne<Release>().WithMany()
                                        .HasForeignKey("ReleaseId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("release_series_release_id_fkey"),
                                j =>
                                {
                                    j.HasKey("ReleaseId", "SeriesId").HasName("release_series_pkey");
                                    j.ToTable("release_series");
                                    j.HasIndex(["ReleaseId"], "idx_release_series__release");
                                    j.HasIndex(["SeriesId"], "idx_release_series__series");
                                    j.IndexerProperty<int>("ReleaseId").HasColumnName("release_id");
                                    j.IndexerProperty<int>("SeriesId").HasColumnName("series_id");
                                });
            });

            modelBuilder.Entity<ReleaseLanguage>(entity =>
            {
                entity.HasKey(e => new { e.ReleaseId, e.Language }).HasName("release_languages_pkey");
                entity.ToTable("release_languages");

                entity.HasIndex(e => e.ReleaseId, "idx_release_languages__release");

                entity.Property(e => e.ReleaseId).HasColumnName("release_id");

                entity.Property(e => e.Language)
                        .HasMaxLength(50)
                        .HasColumnName("language");

                entity.HasOne(d => d.Release).WithMany(p => p.ReleaseLanguages)
                        .HasForeignKey(d => d.ReleaseId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("release_languages_release_id_fkey");
            });

            modelBuilder.Entity<ReleaseType>(entity =>
            {
                entity.HasNoKey().ToTable("release_types");
                entity.HasIndex(e => e.ReleaseId, "idx_release_types__release");

                entity.Property(e => e.ReleaseId).HasColumnName("release_id");

                entity.HasOne(d => d.Release).WithMany()
                        .HasForeignKey(d => d.ReleaseId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("release_types_release_id_fkey");
            });

            modelBuilder.Entity<Series>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("series_pkey");
                entity.ToTable("series");

                entity.HasIndex(e => e.CountryId, "idx_series__country");

                entity.Property(e => e.Id).HasColumnName("id");
                entity.Property(e => e.CountryId).HasColumnName("country_id");
                entity.Property(e => e.Name).HasColumnName("name");
                entity.Property(e => e.YearOfOrigin).HasColumnName("year_of_origin");

                entity.HasOne(d => d.Country).WithMany(p => p.Series)
                        .HasForeignKey(d => d.CountryId)
                        .HasConstraintName("series_country_id_fkey");
            });

            modelBuilder.Entity<Track>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("track_pkey");
                entity.ToTable("track");

                entity.HasIndex(e => e.FirstReleaseId, "idx_track__first_release");

                entity.Property(e => e.Id).HasColumnName("id");
                entity.Property(e => e.FirstReleaseId).HasColumnName("first_release_id");
                entity.Property(e => e.Type).HasColumnName("type");

                entity.Property(e => e.IsFavourite)
                        .HasDefaultValue(false)
                        .HasColumnName("is_favourite");

                entity.Property(e => e.Length)
                        .HasMaxLength(8)
                        .HasDefaultValueSql("'00:00:00'::bpchar")
                        .IsFixedLength()
                        .HasColumnName("length");

                entity.Property(e => e.Title).HasColumnName("title");

                entity.HasOne(d => d.FirstRelease).WithMany(p => p.Tracks)
                        .HasForeignKey(d => d.FirstReleaseId)
                        .HasConstraintName("track_first_release_id_fkey");

                entity.HasMany(d => d.Artists).WithMany(p => p.Tracks)
                        .UsingEntity<Dictionary<string, object>>(
                                "TrackOriginalArtist",
                                r => r.HasOne<Artist>().WithMany()
                                        .HasForeignKey("ArtistId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("track_original_artists_artist_id_fkey"),
                                l => l.HasOne<Track>().WithMany()
                                        .HasForeignKey("TrackId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("track_original_artists_track_id_fkey"),
                                j =>
                                {
                                    j.HasKey("TrackId", "ArtistId").HasName("track_original_artists_pkey");
                                    j.ToTable("track_original_artists");
                                    j.HasIndex(["ArtistId"], "idx_track_original_artists__artist");
                                    j.HasIndex(["TrackId"], "idx_track_original_artists__track");
                                    j.IndexerProperty<int>("TrackId").HasColumnName("track_id");
                                    j.IndexerProperty<int>("ArtistId").HasColumnName("artist_id");
                                });

                entity.HasMany(d => d.ArtistsNavigation).WithMany(p => p.TracksNavigation)
                        .UsingEntity<Dictionary<string, object>>(
                                "TrackRecordedArtist",
                                r => r.HasOne<Artist>().WithMany()
                                        .HasForeignKey("ArtistId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("track_recorded_artists_artist_id_fkey"),
                                l => l.HasOne<Track>().WithMany()
                                        .HasForeignKey("TrackId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("track_recorded_artists_track_id_fkey"),
                                j =>
                                {
                                    j.HasKey("TrackId", "ArtistId").HasName("track_recorded_artists_pkey");
                                    j.ToTable("track_recorded_artists");
                                    j.HasIndex(["ArtistId"], "idx_track_recorded_artists__artist");
                                    j.HasIndex(["TrackId"], "idx_track_recorded_artists__track");
                                    j.IndexerProperty<int>("TrackId").HasColumnName("track_id");
                                    j.IndexerProperty<int>("ArtistId").HasColumnName("artist_id");
                                });

                entity.HasMany(d => d.Composers).WithMany(p => p.Tracks)
                        .UsingEntity<Dictionary<string, object>>(
                                "TrackComposer",
                                r => r.HasOne<Composer>().WithMany()
                                        .HasForeignKey("ComposerId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("track_composers_composer_id_fkey"),
                                l => l.HasOne<Track>().WithMany()
                                        .HasForeignKey("TrackId")
                                        .OnDelete(DeleteBehavior.ClientSetNull)
                                        .HasConstraintName("track_composers_track_id_fkey"),
                                j =>
                                {
                                    j.HasKey("TrackId", "ComposerId").HasName("track_composers_pkey");
                                    j.ToTable("track_composers");
                                    j.HasIndex(["ComposerId"], "idx_track_composers__composer");
                                    j.HasIndex(["TrackId"], "idx_track_composers__track");
                                    j.IndexerProperty<int>("TrackId").HasColumnName("track_id");
                                    j.IndexerProperty<int>("ComposerId").HasColumnName("composer_id");
                                });
            });

            modelBuilder.Entity<TrackAlias>(entity =>
            {
                entity.HasKey(e => new { e.TrackId, e.Alias }).HasName("track_aliases_pkey");
                entity.ToTable("track_aliases");

                entity.HasIndex(e => e.TrackId, "idx_track_aliases__track");

                entity.Property(e => e.TrackId).HasColumnName("track_id");
                entity.Property(e => e.Alias).HasColumnName("alias");

                entity.HasOne(d => d.Track).WithMany(p => p.TrackAliases)
                        .HasForeignKey(d => d.TrackId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("track_aliases_track_id_fkey");
            });

            modelBuilder.Entity<TrackChart>(entity =>
            {
                entity.HasKey(e => new { e.TrackId, e.ChartId }).HasName("track_charts_pkey");
                entity.ToTable("track_charts");

                entity.HasIndex(e => e.ChartId, "idx_track_charts__chart");
                entity.HasIndex(e => e.TrackId, "idx_track_charts__track");

                entity.Property(e => e.TrackId).HasColumnName("track_id");
                entity.Property(e => e.ChartId).HasColumnName("chart_id");

                entity.Property(e => e.EndDate)
                        .HasMaxLength(10)
                        .IsFixedLength()
                        .HasColumnName("end_date");

                entity.Property(e => e.Peak).HasColumnName("peak");

                entity.Property(e => e.StartDate)
                        .HasMaxLength(10)
                        .HasDefaultValueSql("'0000-00-00'::bpchar")
                        .IsFixedLength()
                        .HasColumnName("start_date");

                entity.HasOne(d => d.Chart).WithMany(p => p.TrackCharts)
                        .HasForeignKey(d => d.ChartId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("track_charts_chart_id_fkey");

                entity.HasOne(d => d.Track).WithMany(p => p.TrackCharts)
                        .HasForeignKey(d => d.TrackId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("track_charts_track_id_fkey");
            });

            modelBuilder.Entity<TrackIsrc>(entity =>
            {
                entity.HasKey(e => new { e.TrackId, e.Isrc }).HasName("track_isrcs_pkey");
                entity.ToTable("track_isrcs");

                entity.HasIndex(e => e.TrackId, "idx_track_isrcs__track");

                entity.Property(e => e.TrackId).HasColumnName("track_id");
                entity.Property(e => e.Isrc)
                        .HasMaxLength(12)
                        .IsFixedLength()
                        .HasColumnName("isrc");

                entity.HasOne(d => d.Track).WithMany(p => p.TrackIsrcs)
                        .HasForeignKey(d => d.TrackId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("track_isrcs_track_id_fkey");
            });

            modelBuilder.Entity<TrackIswc>(entity =>
            {
                entity.HasKey(e => new { e.TrackId, e.Iswc }).HasName("track_iswcs_pkey");
                entity.ToTable("track_iswcs");

                entity.HasIndex(e => e.TrackId, "idx_track_iswcs__track");

                entity.Property(e => e.TrackId).HasColumnName("track_id");
                entity.Property(e => e.Iswc)
                        .HasMaxLength(15)
                        .IsFixedLength()
                        .HasColumnName("iswc");

                entity.HasOne(d => d.Track).WithMany(p => p.TrackIswcs)
                        .HasForeignKey(d => d.TrackId)
                        .OnDelete(DeleteBehavior.ClientSetNull)
                        .HasConstraintName("track_iswcs_track_id_fkey");
            });

            modelBuilder.Entity<Vendor>(entity =>
            {
                entity.HasKey(e => e.Id).HasName("vendor_pkey");
                entity.ToTable("vendor");

                entity.HasIndex(e => e.LocationId, "idx_vendor__location");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Comment)
                        .HasMaxLength(100)
                        .HasColumnName("comment");

                entity.Property(e => e.LocationId).HasColumnName("location_id");
                entity.Property(e => e.Name).HasColumnName("name");
                entity.Property(e => e.Type).HasColumnName("type");
                entity.Property(e => e.Website).HasColumnName("website");

                entity.HasOne(d => d.Location).WithMany(p => p.Vendors)
                        .HasForeignKey(d => d.LocationId)
                        .HasConstraintName("vendor_location_id_fkey");
            });
        }
    }
}
