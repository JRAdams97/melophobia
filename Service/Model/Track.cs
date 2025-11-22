namespace Melophobia.Model
{
    public partial class Track
    {
        public int Id { get; init; }

        public string Title { get; init; } = null!;

        public int? FirstReleaseId { get; init; }

        public bool? IsFavourite { get; init; }

        public string? Length { get; init; }

        public virtual Release? FirstRelease { get; init; }

        public virtual ICollection<TrackAlias> TrackAliases { get; init; } = new List<TrackAlias>();

        public virtual ICollection<TrackChart> TrackCharts { get; init; } = new List<TrackChart>();

        public virtual ICollection<TrackIsrc> TrackIsrcs { get; init; } = new List<TrackIsrc>();

        public virtual ICollection<TrackIswc> TrackIswcs { get; init; } = new List<TrackIswc>();

        public virtual ICollection<Artist> Artists { get; init; } = new List<Artist>();

        public virtual ICollection<Artist> ArtistsNavigation { get; init; } = new List<Artist>();

        public virtual ICollection<Composer> Composers { get; init; } = new List<Composer>();

        public virtual ICollection<Issue> Issues { get; init; } = new List<Issue>();
    }
}
