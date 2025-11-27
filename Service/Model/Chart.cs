namespace Melophobia.Model
{
    public class Chart
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public short? YearOfOrigin { get; init; }

        public int? CountryOfOriginId { get; init; }

        public Country? CountryOfOrigin { get; init; }

        public ICollection<TrackChart> TrackCharts { get; init; } = new List<TrackChart>();
    }
}
