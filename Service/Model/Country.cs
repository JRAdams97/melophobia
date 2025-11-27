namespace Melophobia.Model
{
    public class Country
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public string Alpha2Code { get; init; } = null!;

        public ICollection<Chart> Charts { get; init; } = new List<Chart>();

        public ICollection<Region> Regions { get; init; } = new List<Region>();

        public ICollection<Series> Series { get; init; } = new List<Series>();

        public ICollection<Issue> Issues { get; init; } = new List<Issue>();
    }
}
