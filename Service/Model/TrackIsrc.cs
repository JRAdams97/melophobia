namespace Melophobia.Model
{
    public class TrackIsrc
    {
        public int TrackId { get; init; }

        public string Isrc { get; init; } = null!;

        public Track Track { get; init; } = null!;
    }
}
