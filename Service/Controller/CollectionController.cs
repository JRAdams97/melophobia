namespace Melophobia.Controller
{
    using Data;
    using Dto;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.EntityFrameworkCore;

    [ApiController]
    [Route("api/collection")]
    public class CollectionController(MelophobiaContext dbContext) : ControllerBase
    {
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            List<CollectionItemDto> collection = await dbContext.CollectionItems
                    .Include(c => c.Issue)
                    .Select(c => new CollectionItemDto
                    {
                            Id = c.Id,
                            Issue = new IssueDto
                            {
                                    Id = c.Issue.Id,
                                    ReleaseTitle = c.Issue.Release.Title,
                                    IssueLabel = c.Issue.Label.Name,
                                    ReleaseDate = c.Issue.Release!.ReleaseDate!,
                                    CatalogueNumber = c.Issue.CatalogueNumber!,
                                    IssueCountries = c.Issue.Countries
                                            .Select(ic => ic.Name)
                                            .ToList(),
                                    Media = c.Issue.Media!.Name,
                                    Edition = c.Issue.Edition!,
                                    IsReissue = c.Issue.IsReissue,
                                    IsOfficial = c.Issue.IsOfficial,
                                    IsPromotional = c.Issue.IsPromotional,
                                    HasPregapTrack = c.Issue.HasPregapTrack,
                                    HasDataTrack = c.Issue.HasDataTrack,
                                    IssueVariants = c.Issue.IssueVariants
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
                                    Barcode = c.Issue.Barcode!,
                            },
                            PackagingGrade = c.PackagingGrade,
                            PackagingComment = c.PackagingComment!,
                            MediaGrade = c.MediaGrade,
                            MediaComment = c.MediaComment!,
                            IsMissingPackaging = c.IsMissingPackaging,
                            IsMissingMedia = c.IsMissingMedia,
                            HasPromoMaterial = c.HasPromoMaterial,
                            RemainingTracks = c.RemainingTracks,
                            RemainingDiscs = c.RemainingDiscs,
                            AppCoverQuality = c.AppCoverQuality,
                            RipState = c.RipState
                    })
                    .ToListAsync();

            return Ok(collection);
        }
    }
}
