namespace Melophobia.Dto
{
    using Model;

    public class LocationDto
    {
        public int Id { get; set; }

        public List<string> Aliases  { get; set; } = [];

        public string Address { get; set; } = "";

        public string RegionAbbreviation { get; set; } = "";

        public List<string> Postcodes { get; set; } = [];

        public string CountryName { get; set; } = "";

        public decimal Latitude { get; set; }

        public decimal Longitude { get; set; }
    }
}
