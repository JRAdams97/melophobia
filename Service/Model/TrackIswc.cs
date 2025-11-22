namespace Melophobia.Model
{
    public partial class TrackIswc
    {
        public int TrackId { get; init; }

        public string Iswc { get; init; } = null!;

        public virtual Track Track { get; init; } = null!;
    }
}
