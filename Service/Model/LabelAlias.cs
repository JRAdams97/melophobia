namespace Melophobia.Model
{
    public partial class LabelAlias
    {
        public int LabelId { get; init; }

        public string Alias { get; init; } = null!;

        public virtual Label Label { get; init; } = null!;
    }
}
