namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/composers")]
    public class ComposerController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<ComposerDto> composers = await dbContext.Composers
                    .Include(c => c.ComposerIpis)
                    .Select(c => new ComposerDto
                    {
                            Id = c.Id,
                            Name = c.Name,
                            SortName = c.SortName!,
                            Gender = c.Gender,
                            BirthDate = c.BirthDate!,
                            BirthLocation = c.BirthLocation!.Address!,
                            DeathDate = c.DeathDate!,
                            Ipis = c.ComposerIpis
                                    .Select(ci => $"{ci.Ipi} ({ci.Comment})")
                                    .ToList()
                    })
                    .ToListAsync();

            return Ok(composers);
        }
    }
}
