namespace Melophobia.Model
{
    public class CollectionItem
    {
        public int Id { get; init; }

        public int IssueId { get; init; }

        public string? PackagingComment { get; init; }

        public string? MediaComment { get; init; }

        public bool IsMissingPackaging { get; init; }

        public bool IsMissingMedia { get; init; }

        public bool HasPromoMaterial { get; init; }

        public short? RemainingTracks { get; init; }

        public short? RemainingDiscs { get; init; }

        public string? Comment { get; init; }

        public Issue Issue { get; init; } = null!;
    }
}
