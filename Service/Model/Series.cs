namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class Series
    {
        public int Id { get; init; }

        [MaxLength(256)] public string Name { get; init; } = null!;

        public short? YearOfOrigin { get; init; }

        public int? CountryId { get; init; }

        public Country? Country { get; init; }

        public ICollection<Release> Releases { get; init; } = new List<Release>();
    }
}
