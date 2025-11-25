namespace Melophobia.Dto
{
    using Data.Enum;

    public class VendorDto
    {
        public int Id { get; set; }

        public string Name { get; set; } = "";

        public string Location { get; set; } = "";

        public VendorType Type { get; set; }

        public string Website { get; set; } = "";

        public string Comment { get; set; } = "";
    }
}
