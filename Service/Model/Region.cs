namespace Melophobia.Model
{
    public partial class Region
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public string? Abbreviation { get; init; }

        public int CountryId { get; init; }

        public virtual Country Country { get; init; } = null!;

        public virtual ICollection<Location> Locations { get; init; } = new List<Location>();
    }
}
