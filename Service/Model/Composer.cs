namespace Melophobia.Model
{
    public partial class Composer
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public string? SortName { get; init; }

        public string? BirthDate { get; init; }

        public int? BirthLocationId { get; init; }

        public string? DeathDate { get; init; }

        public virtual Location? BirthLocation { get; init; }

        public virtual ICollection<ComposerIpi> ComposerIpis { get; init; } = new List<ComposerIpi>();

        public virtual ICollection<Track> Tracks { get; init; } = new List<Track>();
    }
}
