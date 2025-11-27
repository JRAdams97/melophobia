namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class LabelAlias
    {
        public int LabelId { get; init; }

        [MaxLength(256)] public string Alias { get; init; } = null!;

        public Label Label { get; init; } = null!;
    }
}
