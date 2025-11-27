namespace Melophobia.Model
{
    public class ReleaseType
    {
        public int ReleaseId { get; init; }

        public Release Release { get; init; } = null!;
    }
}
