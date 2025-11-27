namespace Melophobia.Model
{
    public class ReleaseLanguage
    {
        public int ReleaseId { get; init; }

        public string Language { get; init; } = null!;

        public Release Release { get; init; } = null!;
    }
}
