namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;
    using Data.Enum;

    public partial class Producer
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

        public virtual ICollection<ProducerIpi> ProducerIpis { get; init; } = new List<ProducerIpi>();

        public virtual ICollection<Release> Releases { get; init; } = new List<Release>();
    }
}
