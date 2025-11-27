namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;
    using Data.Enum;

    public class Label
    {
        public int Id { get; init; }

        [MaxLength(256)] public string Name { get; init; } = null!;

        [MaxLength(256)] public string? SortName { get; init; }

        public string? FormationDate { get; init; }

        public int? FormationLocationId { get; init; }

        public string? ClosureDate { get; init; }

        public bool? IsFavourite { get; init; }

        public string? LabelCode { get; init; }

        public string? Isni { get; init; }

        public LabelType Type { get; init; }

        public Location? FormationLocation { get; init; }

        public ICollection<Issue> Issues { get; init; } = new List<Issue>();

        public ICollection<LabelAlias> LabelAliases { get; init; } = new List<LabelAlias>();

        public ICollection<LabelIpi> LabelIpis { get; init; } = new List<LabelIpi>();

        public ICollection<Artist> Artists { get; init; } = new List<Artist>();

        public ICollection<Label> Labels { get; init; } = new List<Label>();

        public ICollection<Label> ParentLabels { get; init; } = new List<Label>();
    }
}
