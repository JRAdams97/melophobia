namespace Melophobia.Model
{
    using Data.Enum;

    public class CollectionItem
    {
        public int Id { get; init; }

        public int IssueId { get; init; }

        public Grade PackagingGrade { get; init; }

        public string? PackagingComment { get; init; }

        public Grade MediaGrade { get; init; }

        public string? MediaComment { get; init; }

        public bool IsMissingPackaging { get; init; }

        public bool IsMissingMedia { get; init; }

        public bool HasPromoMaterial { get; init; }

        public short? RemainingTracks { get; init; }

        public short? RemainingDiscs { get; init; }

        public CoverGrade AppCoverQuality { get; init; }

        public RipState RipState { get; init; }

        public string? Comment { get; init; }

        public Issue Issue { get; init; } = null!;
    }
}
