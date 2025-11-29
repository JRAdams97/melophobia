namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/releases")]
    public class ReleaseController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<ReleaseDto> releases = await dbContext.Releases
                    .Include(r => r.Artists)
                    .Include(r => r.Genres)
                    .Include(r => r.Producers)
                    .Include(r => r.ReleaseLanguages)
                    .Include(r => r.Series)
                    .Select(r => new ReleaseDto
                    {
                            Id = r.Id,
                            Title = r.Title,
                            OriginalTitle = r.OriginalTitle!,
                            ReleaseDate = r.ReleaseDate!,
                            Artists = r.Artists
                                    .Select(ra => ra.Name)
                                    .ToList(),
                            Genres = r.Genres
                                    .Select(rg => rg.Name)
                                    .ToList(),
                            IsFavourite = r.IsFavourite,
                            Types = r.Types,
                            Languages = r.ReleaseLanguages
                                    .Select(rl => rl.Language)
                                    .ToList(),
                            Producers = r.Producers
                                    .Select(pr => pr.Name)
                                    .ToList(),
                            Series = r.Series
                                    .Select(sr => sr.Name)
                                    .ToList(),
                            AllmusicCriticRating = r.AllmusicCriticRating,
                            AllmusicUserRating = r.AllmusicUserRating,
                            AotyCriticRank = r.AotyCriticRank,
                            AotyUserRank = r.AotyUserRank,
                            BeaYearlyRank = r.BeaYearlyRank,
                            BeaGlobalRank = r.BeaGlobalRank,
                            ChristgauRating = r.ChristgauRating!,
                            FantanoRating = r.FantanoRating!,
                            LouderThanWarRating = r.LouderThanWarRating,
                            Metacritic = r.Metacritic,
                            NmeRating = r.NmeRating,
                            PitchforkRating = r.PitchforkRating,
                            PopMattersRating = r.PopMattersRating,
                            RollingStoneRating = r.RollingStoneRating,
                            RymRating = r.RymRating,
                            RymYearRank = r.RymYearRank,
                            ScaruffiRating = r.ScaruffiRating,
                            IsOfficial = r.IsOfficial
                    })
                    .ToListAsync();

            return Ok(releases);
        }
    }
}
