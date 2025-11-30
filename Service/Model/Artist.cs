namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;
    using Data.Enum;

    public class Artist
    {
        public int Id { get; init; }

        [MaxLength(256)] public string Name { get; init; } = null!;

        [MaxLength(256)] public string? SortName { get; init; }

        public Gender? Gender { get; init; }

        public short? FormationYear { get; init; }

        public int? FormationLocationId { get; init; }

        public short? DisbandYear { get; init; }

        public bool? IsFavourite { get; init; }

        public string? Isni { get; init; }

        public ArtistType Type { get; init; }

        [MaxLength(256)] public string? Website { get; init; }

        public ICollection<ArtistAlias> ArtistAliases { get; init; } = new List<ArtistAlias>();

        public Location? FormationLocation { get; init; }

        public ICollection<Genre> Genres { get; init; } = new List<Genre>();

        public ICollection<Label> Labels { get; init; } = new List<Label>();

        public ICollection<Release> Releases { get; init; } = new List<Release>();

        public ICollection<Track> Tracks { get; init; } = new List<Track>();

        public ICollection<Track> TracksNavigation { get; init; } = new List<Track>();
    }
}
