namespace Melophobia.Data.Enum
{
    using NpgsqlTypes;

    public enum LabelType
    {
        [PgName("Bootlegs")]
        Bootlegs,

        [PgName("Originals")]
        Originals,

        [PgName("Reissues")]
        Reissues
    }
}
