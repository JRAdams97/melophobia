namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/tracks")]
    public class TrackController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<TrackDto> tracks = await dbContext.Tracks
                    .Include(t => t.Artists)
                    .Include(t => t.Composers)
                    .Include(t => t.TrackAliases)
                    .Include(t => t.TrackCharts)
                    .Include(t => t.TrackIsrcs)
                    .Include(t => t.TrackIswcs)
                    .Select(t => new TrackDto
                    {
                            Id = t.Id,
                            Title = t.Title,
                            RecordedArtists = t.ArtistsNavigation
                                    .Select(tr => tr.Name)
                                    .ToList(),
                            OriginalArtists = t.Artists
                                    .Select(to => to.Name)
                                    .ToList(),
                            FirstRelease = t.FirstRelease!.Title,
                            IsFavourite = t.IsFavourite,
                            Length = t.Length!,
                            Type = t.Type,
                            Isrcs = t.TrackIsrcs
                                    .Select(tir => tir.Isrc)
                                    .ToList(),
                            Iswcs = t.TrackIswcs
                                    .Select(tiw => tiw.Iswc)
                                    .ToList()
                    })
                    .ToListAsync();

            return Ok(tracks);
        }
    }
}
