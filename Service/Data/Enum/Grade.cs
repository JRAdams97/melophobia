namespace Melophobia.Data.Enum
{
    using NpgsqlTypes;

    public enum Grade
    {
        [PgName("F")]
        F,

        [PgName("G")]
        G,

        [PgName("M")]
        M,

        [PgName("NM")]
        NM,

        [PgName("P")]
        P,

        [PgName("VG")]
        VG,

        [PgName("VG+")]
        VGP
    }
}
