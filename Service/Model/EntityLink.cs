namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class EntityLink
    {
        public int Id { get; init; }

        public int LeftId { get; init; }

        public int RightId { get; init; }

        [MaxLength(100)] public string Relationship { get; init; } = null!;
    }
}
