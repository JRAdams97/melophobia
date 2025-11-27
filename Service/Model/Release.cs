namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class Release
    {
        public int Id { get; init; }

        [MaxLength(512)] public string Title { get; init; } = null!;

        [MaxLength(512)] public string? OriginalTitle { get; init; }

        public string? ReleaseDate { get; init; }

        public bool? IsFavourite { get; init; }

        public decimal? AllmusicCriticRating { get; init; }

        public decimal? AllmusicUserRating { get; init; }

        public short? AotyCriticRank { get; init; }

        public short? AotyUserRank { get; init; }

        public short? BeaYearlyRank { get; init; }

        public short? BeaGlobalRank { get; init; }

        public string? ChristgauRating { get; init; }

        public string? FantanoRating { get; init; }

        public short? LouderThanWarRating { get; init; }

        public short? Metacritic { get; init; }

        public decimal? NmeRating { get; init; }

        public decimal? PitchforkRating { get; init; }

        public short? PopMattersRating { get; init; }

        public short? RollingStoneRating { get; init; }

        public decimal? RymRating { get; init; }

        public decimal? ScaruffiRating { get; init; }

        public bool IsOfficial { get; init; }

        public short? RymYearRank { get; init; }

        public ICollection<Issue> Issues { get; init; } = new List<Issue>();

        public ICollection<ReleaseLanguage> ReleaseLanguages { get; init; } = new List<ReleaseLanguage>();

        public ICollection<Track> Tracks { get; init; } = new List<Track>();

        public ICollection<Artist> Artists { get; init; } = new List<Artist>();

        public ICollection<Genre> Genres { get; init; } = new List<Genre>();

        public ICollection<Producer> Producers { get; init; } = new List<Producer>();

        public ICollection<Series> Series { get; init; } = new List<Series>();
    }
}
