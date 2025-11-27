namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class Location
    {
        public int Id { get; init; }

        [MaxLength(512)] public string? Address { get; init; }

        public int RegionId { get; init; }

        public string? Postcode { get; init; }

        public decimal Latitude { get; init; }

        public decimal Longitude { get; init; }

        public ICollection<Artist> Artists { get; init; } = new List<Artist>();

        public ICollection<Composer> Composers { get; init; } = new List<Composer>();

        public ICollection<Label> Labels { get; init; } = new List<Label>();

        public ICollection<LocationAlias> LocationAliases { get; init; } = new List<LocationAlias>();

        public ICollection<LocationPostcode> LocationPostcodes { get; init; } = new List<LocationPostcode>();

        public ICollection<Producer> Producers { get; init; } = new List<Producer>();

        public Region Region { get; init; } = null!;

        public ICollection<Vendor> Vendors { get; init; } = new List<Vendor>();
    }
}
