namespace Melophobia.Controller
{
    using Data;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/media")]
    public class MediaController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get() => Ok(await dbContext.Media.ToListAsync());
    }
}
