namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/labels")]
    public class LabelController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<LabelDto> labels = await dbContext.Labels
                    .Include(l => l.LabelAliases)
                    .Include(l => l.LabelIpis)
                    .Select(l => new LabelDto
                    {
                            Id = l.Id,
                            Name = l.Name,
                            SortName = l.SortName!,
                            LabelAliases = l.LabelAliases
                                    .Select(la => la.Alias)
                                    .ToList(),
                            FormationDate = l.FormationDate!,
                            FormationLocation =
                                    $"{l.FormationLocation!.Address!}, {l.FormationLocation!.Region.Name}, {l.FormationLocation!.Region!.Country!.Alpha2Code}",
                            ClosureDate = l.ClosureDate!,
                            IsFavourite = l.IsFavourite,
                            LabelCode = l.LabelCode!,
                            Type = l.Type,
                            Isni = l.Isni!,
                            Ipis = l.LabelIpis
                                    .Select(li => li.Ipi)
                                    .ToList()
                    })
                    .ToListAsync();

            return Ok(labels);
        }
    }
}
