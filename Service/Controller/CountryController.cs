namespace Melophobia.Controller
{
    using Data;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/countries")]
    public class CountryController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get() => Ok(await dbContext.Countries.ToListAsync());
    }
}
