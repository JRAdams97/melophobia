namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/regions")]
    public class RegionController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<RegionDto> regions = await dbContext.Regions
                    .Include(r => r.Country)
                    .Select(r => new RegionDto
                    {
                            Id = r.Id,
                            Name = r.Name,
                            Abbreviation = r.Abbreviation!,
                            CountryName = r.Country!.Name
                    })
                    .ToListAsync();

            return Ok(regions);
        }
    }
}
