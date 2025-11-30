namespace Melophobia.Data.Enum
{
    using NpgsqlTypes;

    public enum CoverGrade
    {
        [PgName("Excellent")]
        Excellent,

        [PgName("Good")]
        Good,

        [PgName("[none]")]
        None,

        [PgName("Poor")]
        Poor
    }
}
