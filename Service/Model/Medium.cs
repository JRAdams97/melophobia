namespace Melophobia.Model
{
    public partial class Medium
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public string? Abbreviation { get; init; }

        public string? Classification { get; init; }

        public short? YearOfOrigin { get; init; }

        public virtual ICollection<Issue> Issues { get; init; } = new List<Issue>();
    }
}
