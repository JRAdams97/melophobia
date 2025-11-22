namespace Melophobia.Model
{
    public partial class Label
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public string? SortName { get; init; }

        public string? FormationDate { get; init; }

        public int? FormationLocationId { get; init; }

        public string? ClosureDate { get; init; }

        public bool? IsFavourite { get; init; }

        public string? LabelCode { get; init; }

        public string? Isni { get; init; }

        public virtual Location? FormationLocation { get; init; }

        public virtual ICollection<Issue> Issues { get; init; } = new List<Issue>();

        public virtual ICollection<LabelAlias> LabelAliases { get; init; } = new List<LabelAlias>();

        public virtual ICollection<LabelIpi> LabelIpis { get; init; } = new List<LabelIpi>();

        public virtual ICollection<Artist> Artists { get; init; } = new List<Artist>();

        public virtual ICollection<Label> Labels { get; init; } = new List<Label>();

        public virtual ICollection<Label> ParentLabels { get; init; } = new List<Label>();
    }
}
