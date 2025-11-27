namespace Melophobia.Model
{
    public class ProducerIpi
    {
        public string Ipi { get; init; } = null!;

        public int? ProducerId { get; init; }

        public string? Comment { get; init; }

        public Producer? Producer { get; init; }
    }
}
