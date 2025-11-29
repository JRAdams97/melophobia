namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/issues")]
    public class IssueController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<IssueDto> issues = await dbContext.Issues
                    .Include(i => i.Countries)
                    .Include(i => i.IssueVariants)
                    .Include(i => i.Label)
                    .Include(i => i.Media)
                    .Include(i => i.Release)
                    .Select(i => new IssueDto
                    {
                            Id = i.Id,
                            ReleaseTitle = i.Release.Title,
                            IssueLabel = i.Label.Name,
                            ReleaseDate = i.Release!.ReleaseDate!,
                            CatalogueNumber = i.CatalogueNumber!,
                            IssueCountries = i.Countries
                                    .Select(ic => ic.Name)
                                    .ToList(),
                            Media = i.Media!.Name,
                            Edition = i.Edition!,
                            IsReissue = i.IsReissue,
                            IsOfficial = i.IsOfficial,
                            IsPromotional = i.IsPromotional,
                            HasPregapTrack = i.HasPregapTrack,
                            HasDataTrack = i.HasDataTrack,
                            IssueVariants = i.IssueVariants
                                    .Select(iv => new IssueVariantDto
                                            {
                                                    Id = iv.Id,
                                                    IssueId = iv.IssueId,
                                                    MatrixRunout = iv.MatrixRunout!,
                                                    MasteringSidCode = iv.MasteringSidCode!,
                                                    MouldSidCode = iv.MouldSidCode!,
                                                    SparsCode = iv.SparsCode!,
                                                    Discid = iv.Discid,
                                            }
                                    )
                                    .ToList(),
                            Barcode = i.Barcode!,
                            Comment = i.Comment!
                    })
                    .ToListAsync();

            return Ok(issues);
        }
    }
}
