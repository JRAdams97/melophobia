namespace Melophobia.Dto
{
    using Data.Enum;

    public class CollectionItemDto
    {
        public int Id { get; set; }

        public IssueDto Issue { get; set; } = new();

        public Grade PackagingGrade { get; set; }

        public string PackagingComment { get; set; } = "";

        public Grade MediaGrade { get; set; }

        public string MediaComment { get; set; } = "";

        public bool IsMissingPackaging { get; set; }

        public bool IsMissingMedia { get; set; }

        public bool HasPromoMaterial { get; set; }

        public short? RemainingTracks { get; set; } = 0;

        public short? RemainingDiscs { get; set; } = 0;

        public CoverGrade AppCoverQuality { get; set; }

        public RipState RipState { get; set; }
    }
}
