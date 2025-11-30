namespace Melophobia.Dto
{
    using Data.Enum;

    public class ArtistDto
    {
        public int Id { get; set; }

        public string Name { get; set; } = "";

        public string SortName { get; set; } = "";

        public List<string> Aliases { get; set; } = [];

        public Gender? Gender { get; set; }

        public short? FormationYear { get; set; }

        public string FormationLocation  { get; set; } = "";

        public short? DisbandYear { get; set; }

        public List<string> Genres { get; set; } = [];

        public bool? IsFavourite { get; set; } = false;

        public ArtistType Type { get; set; }

        public List<string> Labels { get; set; } = [];

        public string Isni { get; set; } = "";

        public string Website { get; set; } = "";

    }
}
