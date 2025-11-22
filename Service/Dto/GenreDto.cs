namespace Melophobia.Dto
{
    public class GenreDto
    {
        public int Id { get; set; }

        public string Name { get; set; } = null!;

        public List<string> Aliases { get; set; } = null!;

        public int YearOfOrigin { get; set; }

        public List<string> ParentGenres { get; set; } = null!;

        public bool? IsFavourite { get; set; } = false;
    }
}
