namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/artists")]
    public class ArtistController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<ArtistDto> artists = await dbContext.Artists
                    .Include(a => a.ArtistAliases)
                    .Include(a => a.Genres)
                    .Include(a => a.Labels)
                    .Include(a => a.FormationLocation)
                    .Select(a => new ArtistDto
                    {
                            Id = a.Id,
                            Name = a.Name,
                            SortName = a.SortName!,
                            Aliases = a.ArtistAliases
                                    .Select(aa => aa.Alias)
                                    .ToList(),
                            Gender = a.Gender,
                            FormationYear = a.FormationYear,
                            FormationLocation = $"{a.FormationLocation!.Address!}, {a.FormationLocation!.Region.Name}, "
                                                + $"{a.FormationLocation!.Region!.Country!.Alpha2Code}",
                            DisbandYear = a.DisbandYear,
                            Genres = a.Genres
                                    .Select(ag => ag.Name)
                                    .ToList(),
                            IsFavourite = a.IsFavourite,
                            Type = a.Type,
                            Labels = a.Labels
                                    .Select(al => al.Name)
                                    .ToList(),
                            Isni = a.Isni!,
                            Website = a.Website!,
                    })
                    .ToListAsync();

            return Ok(artists);
        }

    }
}
