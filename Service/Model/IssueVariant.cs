namespace Melophobia.Model
{
    public partial class IssueVariant
    {
        public int Id { get; init; }

        public int? IssueId { get; init; }

        public string? MatrixRunout { get; init; }

        public string? MasteringSidCode { get; init; }

        public string? MouldSidCode { get; init; }

        public string? SparsCode { get; init; }

        public virtual Issue? Issue { get; init; }
    }
}
