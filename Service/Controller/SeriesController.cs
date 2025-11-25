namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/series")]
    public class SeriesController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<SeriesDto> series = await dbContext.Series
                    .Include(s => s.Country)
                    .Select(s => new SeriesDto
                    {
                            Id = s.Id,
                            Name = s.Name,
                            YearOfOrigin = s.YearOfOrigin,
                            CountryName = s.Country!.Name
                    })
                    .ToListAsync();

            return Ok(series);
        }
    }
}
