namespace Melophobia.Dto
{
    using Data.Enum;

    public class LabelDto
    {
        public int Id { get; set; }

        public string Name { get; set; } = "";

        public string SortName { get; set; } = "";

        public List<string> LabelAliases { get; set; } = [];

        public string FormationDate { get; set; } = "";

        public string FormationLocation { get; set; } = "";

        public string ClosureDate { get; set; } = "";

        public bool? IsFavourite { get; set; } = false;

        public string LabelCode { get; set; } = "";

        public LabelType Type { get; set; }

        public string Isni { get; set; } = "";

        public List<string> Ipis { get; set; } = [];
    }
}
