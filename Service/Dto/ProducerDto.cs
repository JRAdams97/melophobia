namespace Melophobia.Dto
{
    using Data.Enum;

    public class ProducerDto
    {
        public int Id { get; set; }

        public string Name { get; set; } = "";

        public string SortName { get; set; } = "";

        public Gender Gender { get; set; }

        public string BirthDate { get; set; } = "";

        public string BirthLocation { get; set; } = "";

        public string DeathDate { get; set; } = "";

        public List<string> Ipis { get; set; } = [];
    }
}
