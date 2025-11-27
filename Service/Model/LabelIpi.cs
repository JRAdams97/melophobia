namespace Melophobia.Model
{
    public class LabelIpi
    {
        public string Ipi { get; init; } = null!;

        public int? LabelId { get; init; }

        public string? Comment { get; init; }

        public Label? Label { get; init; }
    }
}
