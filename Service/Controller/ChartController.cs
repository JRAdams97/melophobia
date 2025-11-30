namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/charts")]
    public class ChartController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<ChartDto> charts = await dbContext.Charts
                    .Include(c => c.CountryOfOrigin)
                    .Select(c => new ChartDto
                    {
                            Id = c.Id,
                            Name = c.Name,
                            YearOfOrigin = c.YearOfOrigin,
                            CountryOfOrigin = c.CountryOfOrigin!.Name
                    })
                    .ToListAsync();

            return Ok(charts);
        }
    }
}
