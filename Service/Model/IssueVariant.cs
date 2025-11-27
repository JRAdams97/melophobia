namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class IssueVariant
    {
        public int Id { get; init; }

        public int? IssueId { get; init; }

        [MaxLength(256)] public string? MatrixRunout { get; init; }

        public string? MasteringSidCode { get; init; }

        public string? MouldSidCode { get; init; }

        public string? SparsCode { get; init; }

        public Guid? Discid { get; init; }

        public Issue? Issue { get; init; }
    }
}
