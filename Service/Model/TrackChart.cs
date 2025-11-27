namespace Melophobia.Model
{
    public class TrackChart
    {
        public int TrackId { get; init; }

        public int ChartId { get; init; }

        public string? StartDate { get; init; }

        public string? EndDate { get; init; }

        public short Peak { get; init; }

        public Chart Chart { get; init; } = null!;

        public Track Track { get; init; } = null!;
    }
}
