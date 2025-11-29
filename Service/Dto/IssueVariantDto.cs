namespace Melophobia.Dto
{
    public class IssueVariantDto
    {
        public int Id { get; set; }

        public int? IssueId { get; set; }

        public string MatrixRunout { get; set; } = "";

        public string MasteringSidCode { get; set; } = "";

        public string MouldSidCode { get; set; } = "";

        public string SparsCode { get; set; } = "";

        public Guid? Discid { get; set; }
    }
}
