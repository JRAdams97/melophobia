namespace Melophobia.Model
{
    public partial class LocationAlias
    {
        public int LocationId { get; init; }

        public string Alias { get; init; } = null!;

        public virtual Location Location { get; init; } = null!;
    }
}
