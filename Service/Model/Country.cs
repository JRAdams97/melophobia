namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    [Table("country")]
    public class Country
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Column("id")]
        public long Id { get; set; }

        [Required(ErrorMessage = "Country name is required")]
        [Column("name")]
        public string Name { get; set; } = "";

        [Required(ErrorMessage = "alpha-2 code (ISO 3166) is required")]
        [Column("alpha2_code")]
        public string Alpha2Code { get; set; } = "";
    }
}


