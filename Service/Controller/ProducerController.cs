namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/producers")]
    public class ProducerController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<ProducerDto> producers = await dbContext.Producers
                    .Include(p => p.ProducerIpis)
                    .Select(p => new ProducerDto
                    {
                            Id = p.Id,
                            Name = p.Name,
                            SortName = p.SortName!,
                            Gender = p.Gender,
                            BirthDate = p.BirthDate!,
                            BirthLocation = p.BirthLocation!.Address!,
                            DeathDate = p.DeathDate!,
                            Ipis = p.ProducerIpis
                                    .Select(pi => $"{pi.Ipi} ({pi.Comment})")
                                    .ToList()
                    })
                    .ToListAsync();

            return Ok(producers);
        }
    }
}
