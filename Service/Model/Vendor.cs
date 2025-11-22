namespace Melophobia.Model
{
    public partial class Vendor
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public int? LocationId { get; init; }

        public string? Website { get; init; }

        public string? Comment { get; init; }

        public virtual Location? Location { get; init; }
    }
}
