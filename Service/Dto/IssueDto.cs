namespace Melophobia.Dto
{
    public class IssueDto
    {
        public int Id { get; set; }

        public string ReleaseTitle { get; set; } = "";

        public string IssueLabel { get; set; } = "";

        public string ReleaseDate { get; set; } = "0000-00-00";

        public string CatalogueNumber { get; set; } = "";

        public List<string> IssueCountries = [];

        public string Media { get; set; } = "";

        public string Edition { get; set; } = "";

        public bool? IsReissue { get; set; } = false;

        public bool? IsOfficial { get; set; } = true;

        public bool? IsPromotional { get; set; } = false;

        public bool? HasPregapTrack { get; set; } = false;

        public bool? HasDataTrack { get; set; } = false;

        public List<IssueVariantDto> IssueVariants { get; set; } = [];

        public string Barcode { get; set; } = "";

        public string Comment { get; set; } = "";
    }
}
