namespace Melophobia.Data.Enum
{
    using NpgsqlTypes;

    public enum ReleaseType
    {
        [PgName("Audiobook")] Audiobook,
        [PgName("Broadcast")] Broadcast,
        [PgName("Compilation")] Compilation,
        [PgName("Demo")] Demo,
        [PgName("DJ-Mix")] DjMix,
        [PgName("EP")] Ep,
        [PgName("Interview")] Interview,
        [PgName("Live")] Live,
        [PgName("Mixtape")] Mixtape,
        [PgName("Other")] Other,
        [PgName("Remix")] Remix,
        [PgName("Single")] Single,
        [PgName("Soundtrack")] Soundtrack,
        [PgName("Studio")] Studio
    }
}
