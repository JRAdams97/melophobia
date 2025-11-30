namespace Melophobia.Data.Enum
{
    using NpgsqlTypes;

    public enum ArtistType
    {
        [PgName("Choir")]
        Choir,

        [PgName("Group")]
        Group,

        [PgName("Orchestra")]
        Orchestra,

        [PgName("Other")]
        Other,

        [PgName("Person")]
        Person
    }
}
