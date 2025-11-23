namespace Melophobia.Data.Enum
{
    using System.ComponentModel;
    using NpgsqlTypes;

    public enum Gender
    {
        [PgName("F")]
        F,

        [PgName("M")]
        M,

        [PgName("O")]
        O
    }
}
