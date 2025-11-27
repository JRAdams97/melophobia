namespace Melophobia.Model
{
    using System.ComponentModel.DataAnnotations;

    public class TrackAlias
    {
        public int TrackId { get; init; }

        [MaxLength(512)] public string Alias { get; init; } = null!;

        public Track Track { get; init; } = null!;
    }
}
