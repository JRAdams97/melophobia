namespace Melophobia.Dto
{
    using Data.Enum;

    public class ReleaseDto
    {
        public int Id { get; set; }

        public string Title { get; set; } = "";

        public string OriginalTitle { get; set; } = "";

        public string ReleaseDate { get; set; } = "0000-00-00";

        public List<string> Artists { get; set; } = [];

        public List<string> Genres { get; set; } = [];

        public bool? IsFavourite { get; set; } = false;

        public List<ReleaseType> Types { get; set; } = [];

        public List<string> Languages { get; set; } = [];

        public List<string> Producers { get; set; } = [];

        public List<string> Series { get; set; } = [];

        public decimal? AllmusicCriticRating { get; init; }

        public decimal? AllmusicUserRating { get; init; }

        public short? AotyCriticRank { get; init; }

        public short? AotyUserRank { get; init; }

        public short? BeaYearlyRank { get; init; }

        public short? BeaGlobalRank { get; init; }

        public string ChristgauRating { get; init; } = "";

        public string FantanoRating { get; init; } = "";

        public short? LouderThanWarRating { get; init; }

        public short? Metacritic { get; init; }

        public decimal? NmeRating { get; init; }

        public decimal? PitchforkRating { get; init; }

        public short? PopMattersRating { get; init; }

        public short? RollingStoneRating { get; init; }

        public decimal? RymRating { get; init; }

        public decimal? ScaruffiRating { get; init; }

        public bool? IsOfficial { get; init; } = true;

        public short? RymYearRank { get; init; }
    }
}
