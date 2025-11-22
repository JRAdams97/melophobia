namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/genres")]
    public class GenreController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<GenreDto> genres = await dbContext.Genres
                    .Include(g => g.GenreAliases)
                    .Include(g => g.ParentGenres)
                    .Select(g => new GenreDto
                    {
                            Id = g.Id,
                            Name = g.Name,
                            Aliases = g.GenreAliases
                                    .Select(ga => ga.Alias)
                                    .ToList(),
                            YearOfOrigin = g.YearOfOrigin,
                            ParentGenres = g.ParentGenres
                                    .Select(pg => pg.Name)
                                    .ToList(),
                            IsFavourite = g.IsFavourite
                    })
                    .ToListAsync();

            return Ok(genres);
        }
    }
}
