namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;
    using Data.Enum;

    public class Vendor
    {
        public int Id { get; init; }

        [MaxLength(256)] public string Name { get; init; } = null!;

        public int? LocationId { get; init; }

        public VendorType Type { get; init; }

        [MaxLength(256)] public string? Website { get; init; }

        public string? Comment { get; init; }

        public Location? Location { get; init; }
    }
}
