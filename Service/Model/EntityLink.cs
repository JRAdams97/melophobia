namespace Melophobia.Model
{
    public partial class EntityLink
    {
        public int Id { get; init; }

        public int LeftId { get; init; }

        public int RightId { get; init; }

        public string Relationship { get; init; } = null!;
    }
}
