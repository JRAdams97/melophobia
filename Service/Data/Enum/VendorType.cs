namespace Melophobia.Data.Enum
{
    using NpgsqlTypes;

    public enum VendorType
    {
        [PgName("MusicStore")]
        MusicStore,

        [PgName("Online")]
        Online,

        [PgName("Other")]
        Other,

        [PgName("RetailStore")]
        RetailStore,

        [PgName("ThriftStore")]
        ThriftStore,

        [PgName("Wholesale")]
        Wholesale
    }
}
