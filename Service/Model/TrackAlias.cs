namespace Melophobia.Model
{
    public partial class TrackAlias
    {
        public int TrackId { get; init; }

        public string Alias { get; init; } = null!;

        public virtual Track Track { get; init; } = null!;
    }
}
