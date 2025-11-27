namespace Melophobia.Model
{
    public class Genre
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public short YearOfOrigin { get; init; }

        public bool? IsFavourite { get; init; }

        public ICollection<GenreAlias> GenreAliases { get; init; } = new List<GenreAlias>();

        public ICollection<Artist> Artists { get; init; } = new List<Artist>();

        public ICollection<Genre> Genres { get; init; } = new List<Genre>();

        public ICollection<Genre> ParentGenres { get; init; } = new List<Genre>();

        public ICollection<Release> Releases { get; init; } = new List<Release>();
    }
}
