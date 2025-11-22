namespace Melophobia.Model
{
    public partial class ProducerIpi
    {
        public string Ipi { get; init; } = null!;

        public int? ProducerId { get; init; }

        public string? Comment { get; init; }

        public virtual Producer? Producer { get; init; }
    }
}
