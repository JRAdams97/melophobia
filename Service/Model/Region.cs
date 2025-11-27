namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class Region
    {
        public int Id { get; init; }

        [MaxLength(256)] public string Name { get; init; } = null!;

        public string? Abbreviation { get; init; }

        public int CountryId { get; init; }

        public Country Country { get; init; } = null!;

        public ICollection<Location> Locations { get; init; } = new List<Location>();
    }
}
