namespace Melophobia.Model
{
    public partial class TrackChart
    {
        public int TrackId { get; init; }

        public int ChartId { get; init; }

        public string? StartDate { get; init; }

        public string? EndDate { get; init; }

        public short Peak { get; init; }

        public virtual Chart Chart { get; init; } = null!;

        public virtual Track Track { get; init; } = null!;
    }
}
