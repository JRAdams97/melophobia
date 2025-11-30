namespace Melophobia.Data.Enum
{
    using NpgsqlTypes;

    public enum RipState
    {
        [PgName("Cleaned")]
        Cleaned,

        [PgName("Ripped")]
        Ripped,

        [PgName("Tagged")]
        Tagged,

        [PgName("Todo")]
        Todo
    }
}
