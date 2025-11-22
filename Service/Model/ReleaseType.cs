namespace Melophobia.Model
{
    public partial class ReleaseType
    {
        public int ReleaseId { get; init; }

        public virtual Release Release { get; init; } = null!;
    }
}
