namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class ArtistAlias
    {
        public int ArtistId { get; init; }

        [MaxLength(256)] public string Alias { get; init; } = null!;

        public Artist Artist { get; init; } = null!;
    }
}