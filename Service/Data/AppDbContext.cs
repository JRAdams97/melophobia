namespace Melophobia.Data
{
    using Microsoft.EntityFrameworkCore;
    using Model;

    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) {}

        public DbSet<Country> Countries => Set<Country>();
    }
}
