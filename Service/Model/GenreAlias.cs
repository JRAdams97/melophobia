namespace Melophobia.Model
{
    public partial class GenreAlias
    {
        public int GenreId { get; init; }

        public string Alias { get; init; } = null!;

        public virtual Genre Genre { get; init; } = null!;
    }
}
