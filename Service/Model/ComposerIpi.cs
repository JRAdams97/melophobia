namespace Melophobia.Model
{
    public partial class ComposerIpi
    {
        public string Ipi { get; init; } = null!;

        public int? ComposerId { get; init; }

        public string? Comment { get; init; }

        public virtual Composer? Composer { get; init; }
    }
}
