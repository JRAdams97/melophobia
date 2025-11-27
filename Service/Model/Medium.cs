namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class Medium
    {
        public int Id { get; init; }

        [MaxLength(256)] public string Name { get; init; } = null!;

        public string? Abbreviation { get; init; }

        public string? Classification { get; init; }

        public short? YearOfOrigin { get; init; }

        public ICollection<Issue> Issues { get; init; } = new List<Issue>();
    }
}
