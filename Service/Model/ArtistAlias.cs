namespace Melophobia.Model
{
    public partial class ArtistAlias
    {
        public int ArtistId { get; init; }

        public string Alias { get; init; } = null!;

        public virtual Artist Artist { get; init; } = null!;
    }
}
