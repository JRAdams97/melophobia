namespace Melophobia.Model
{
    public partial class Genre
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public short YearOfOrigin { get; init; }

        public bool? IsFavourite { get; init; }

        public virtual ICollection<GenreAlias> GenreAliases { get; init; } = new List<GenreAlias>();

        public virtual ICollection<Artist> Artists { get; init; } = new List<Artist>();

        public virtual ICollection<Genre> Genres { get; init; } = new List<Genre>();

        public virtual ICollection<Genre> ParentGenres { get; init; } = new List<Genre>();

        public virtual ICollection<Release> Releases { get; init; } = new List<Release>();
    }
}
