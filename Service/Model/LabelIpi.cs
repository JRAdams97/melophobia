namespace Melophobia.Model
{
    public partial class LabelIpi
    {
        public string Ipi { get; init; } = null!;

        public int? LabelId { get; init; }

        public string? Comment { get; init; }

        public virtual Label? Label { get; init; }
    }
}
