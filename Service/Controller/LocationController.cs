namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/locations")]
    public class LocationController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<LocationDto> locations = await dbContext.Locations
                    .Include(l  => l.LocationAliases)
                    .Include(l => l.LocationPostcodes)
                    .Include(l => l.Region)
                    .Select(l => new LocationDto
                    {
                            Id = l.Id,
                            Aliases = l.LocationAliases
                                    .Select(a => a.Alias)
                                    .ToList(),
                            Address = l.Address!,
                            RegionAbbreviation = l.Region.Abbreviation!,
                            Postcodes = l.LocationPostcodes
                                    .Select(p => p.Postcode)
                                    .ToList(),
                            CountryName = l.Region.Country.Name,
                            Latitude = l.Latitude,
                            Longitude = l.Longitude
                    })
                    .ToListAsync();

            return Ok(locations);
        }
    }
}
