namespace Melophobia.Data.Enum
{
    using NpgsqlTypes;

    public enum TrackType
    {
        [PgName("Cover")] Cover,
        [PgName("Original")] Original
    }
}
