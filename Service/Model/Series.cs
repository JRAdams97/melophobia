namespace Melophobia.Model
{
    public partial class Series
    {
        public int Id { get; init; }

        public string Name { get; init; } = null!;

        public short? YearOfOrigin { get; init; }

        public int? CountryId { get; init; }

        public virtual Country? Country { get; init; }

        public virtual ICollection<Release> Releases { get; init; } = new List<Release>();
    }
}
