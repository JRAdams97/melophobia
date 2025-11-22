namespace Melophobia.Model
{
    public partial class Country
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public string Alpha2Code { get; init; } = null!;

        public virtual ICollection<Chart> Charts { get; init; } = new List<Chart>();

        public virtual ICollection<Region> Regions { get; init; } = new List<Region>();

        public virtual ICollection<Series> Series { get; init; } = new List<Series>();

        public virtual ICollection<Issue> Issues { get; init; } = new List<Issue>();
    }
}
