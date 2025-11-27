namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class LocationAlias
    {
        public int LocationId { get; init; }

        [MaxLength(256)] public string Alias { get; init; } = null!;

        public Location Location { get; init; } = null!;
    }
}
