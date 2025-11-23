namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;
    using Data.Enum;

    public partial class Composer
    {
        public int Id { get; init; }

        [MaxLength(256)]
        public string Name { get; init; } = null!;

        [MaxLength(256)]
        public string? SortName { get; init; }

        public Gender Gender { get; init; }

        public string? BirthDate { get; init; }

        public int? BirthLocationId { get; init; }

        public string? DeathDate { get; init; }

        public virtual Location? BirthLocation { get; init; }

        public virtual ICollection<ComposerIpi> ComposerIpis { get; init; } = new List<ComposerIpi>();

        public virtual ICollection<Track> Tracks { get; init; } = new List<Track>();
    }
}
