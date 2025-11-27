namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class GenreAlias
    {
        public int GenreId { get; init; }

        [MaxLength(256)] public string Alias { get; init; } = null!;

        public Genre Genre { get; init; } = null!;
    }
}
