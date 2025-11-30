namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;
    using Data.Enum;

    public class Track
    {
        public int Id { get; init; }

        [MaxLength(512)] public string Title { get; init; } = null!;

        public int? FirstReleaseId { get; init; }

        public bool? IsFavourite { get; init; }

        public string? Length { get; init; }

        public TrackType? Type { get; init; } = TrackType.Original;

        public Release? FirstRelease { get; init; }

        public ICollection<TrackAlias> TrackAliases { get; init; } = new List<TrackAlias>();

        public ICollection<TrackChart> TrackCharts { get; init; } = new List<TrackChart>();

        public ICollection<TrackIsrc> TrackIsrcs { get; init; } = new List<TrackIsrc>();

        public ICollection<TrackIswc> TrackIswcs { get; init; } = new List<TrackIswc>();

        public ICollection<Artist> Artists { get; init; } = new List<Artist>();

        public ICollection<Artist> ArtistsNavigation { get; init; } = new List<Artist>();

        public ICollection<Composer> Composers { get; init; } = new List<Composer>();

        public ICollection<Issue> Issues { get; init; } = new List<Issue>();
    }
}
