namespace Melophobia.Model
{
    public partial class ReleaseLanguage
    {
        public int ReleaseId { get; init; }

        public string Language { get; init; } = null!;

        public virtual Release Release { get; init; } = null!;
    }
}
