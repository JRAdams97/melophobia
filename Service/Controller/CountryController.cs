namespace Melophobia.Controller
{
    using Data;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/[controller]")]
    public class CountryController(AppDbContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get() => Ok(await dbContext.Countries.ToListAsync());
    }
}
