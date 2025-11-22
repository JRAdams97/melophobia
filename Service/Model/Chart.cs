namespace Melophobia.Model
{
    public partial class Chart
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public short? YearOfOrigin { get; init; }

        public int? CountryOfOriginId { get; init; }

        public virtual Country? CountryOfOrigin { get; init; }

        public virtual ICollection<TrackChart> TrackCharts { get; init; } = new List<TrackChart>();
    }
}
