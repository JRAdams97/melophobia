namespace Melophobia.Model
{
    public partial class LocationPostcode
    {
        public int LocationId { get; init; }

        public string Postcode { get; init; } = null!;

        public virtual Location Location { get; init; } = null!;
    }
}
