namespace Melophobia.Dto
{
    using Data.Enum;

    public class TrackDto
    {
        public int Id { get; set; }

        public string Title { get; set; } = "";

        public List<string> RecordedArtists { get; set; } = [];

        public List<string> OriginalArtists { get; set; } = [];

        public string FirstRelease { get; set; } = "";

        public bool? IsFavourite { get; set; } = false;

        public string Length { get; set; } = "";

        public TrackType? Type { get; set; } = TrackType.Original;

        public List<string> Isrcs { get; set; } = [];

        public List<string> Iswcs { get; set; } = [];
    }
}
