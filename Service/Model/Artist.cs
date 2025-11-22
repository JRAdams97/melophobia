namespace Melophobia.Model
{
    public partial class Artist
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public string? SortName { get; init; }

        public short? FormationYear { get; init; }

        public int? FormationLocationId { get; init; }

        public short? DisbandYear { get; init; }

        public bool? IsFavourite { get; init; }

        public string? Isni { get; init; }

        public string? Website { get; init; }

        public virtual ICollection<ArtistAlias> ArtistAliases { get; init; } = new List<ArtistAlias>();

        public virtual Location? FormationLocation { get; init; }

        public virtual ICollection<Genre> Genres { get; init; } = new List<Genre>();

        public virtual ICollection<Label> Labels { get; init; } = new List<Label>();

        public virtual ICollection<Release> Releases { get; init; } = new List<Release>();

        public virtual ICollection<Track> Tracks { get; init; } = new List<Track>();

        public virtual ICollection<Track> TracksNavigation { get; init; } = new List<Track>();
    }
}
