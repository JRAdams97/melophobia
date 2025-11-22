namespace Melophobia.Model
{
    public partial class TrackIsrc
    {
        public int TrackId { get; init; }

        public string Isrc { get; init; } = null!;

        public virtual Track Track { get; init; } = null!;
    }
}
