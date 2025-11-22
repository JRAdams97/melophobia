namespace Melophobia.Model
{
    public partial class Issue
    {
        public int Id { get; init; }

        public int ReleaseId { get; init; }

        public int LabelId { get; init; }

        public string? ReleaseDate { get; init; }

        public string? CatalogueNumber { get; init; }

        public int? MediaId { get; init; }

        public int? Edition { get; init; }

        public bool? IsReissue { get; init; }

        public bool? IsOfficial { get; init; }

        public bool? IsPromotional { get; init; }

        public bool? HasPregapTrack { get; init; }

        public bool? HasDataTrack { get; init; }

        public string? Barcode { get; init; }

        public string? Comment { get; init; }

        public virtual ICollection<CollectionItem> CollectionItems { get; init; } = new List<CollectionItem>();

        public virtual ICollection<IssueVariant> IssueVariants { get; init; } = new List<IssueVariant>();

        public virtual Label Label { get; init; } = null!;

        public virtual Medium? Media { get; init; }

        public virtual Release Release { get; init; } = null!;

        public virtual ICollection<Country> Countries { get; init; } = new List<Country>();

        public virtual ICollection<Track> Tracks { get; init; } = new List<Track>();
    }
}
