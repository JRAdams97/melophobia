namespace Melophobia.Model
{
    public class ComposerIpi
    {
        public string Ipi { get; init; } = null!;

        public int? ComposerId { get; init; }

        public string? Comment { get; init; }

        public Composer? Composer { get; init; }
    }
}
