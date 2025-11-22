namespace Melophobia.Model
{
    public partial class Location
    {
        public int Id { get; init; }

        public string? Address { get; init; }

        public int RegionId { get; init; }

        public string? Postcode { get; init; }

        public decimal Latitude { get; init; }

        public decimal Longitude { get; init; }

        public virtual ICollection<Artist> Artists { get; init; } = new List<Artist>();

        public virtual ICollection<Composer> Composers { get; init; } = new List<Composer>();

        public virtual ICollection<Label> Labels { get; init; } = new List<Label>();

        public virtual ICollection<LocationAlias> LocationAliases { get; init; } = new List<LocationAlias>();

        public virtual ICollection<LocationPostcode> LocationPostcodes { get; init; } = new List<LocationPostcode>();

        public virtual ICollection<Producer> Producers { get; init; } = new List<Producer>();

        public virtual Region Region { get; init; } = null!;

        public virtual ICollection<Vendor> Vendors { get; init; } = new List<Vendor>();
    }
}
