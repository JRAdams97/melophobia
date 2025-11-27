namespace Melophobia.Model
{
    public class LocationPostcode
    {
        public int LocationId { get; init; }

        public string Postcode { get; init; } = null!;

        public Location Location { get; init; } = null!;
    }
}
