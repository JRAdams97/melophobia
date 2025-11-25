namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/vendors")]
    public class VendorController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<VendorDto> vendors = await dbContext.Vendors
                    .Include(v => v.Location)
                    .Select(v => new VendorDto
                    {
                            Id = v.Id,
                            Name = v.Name,
                            Location = $"{v.Location!.Address!}, {v.Location!.Region.Name}, "
                                       + $"{v.Location!.Region!.Country!.Alpha2Code}",
                            Type = v.Type,
                            Website = v.Website!,
                            Comment = v.Comment!
                    })
                    .ToListAsync();

            return Ok(vendors);
        }
    }
}
