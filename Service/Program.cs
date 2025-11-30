using Melophobia.Data;
using Melophobia.Data.Enum;
using Microsoft.EntityFrameworkCore;

WebApplicationBuilder builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi
builder.Services.AddOpenApi();
builder.Services.AddSwaggerGen();
builder.Services.AddControllers();

// Add PostgreSQL via EFCore
builder.Services.AddDbContext<MelophobiaContext>(options => options.UseNpgsql("",
        e => e
                .MapEnum<ArtistType>("e_artist_type")
                .MapEnum<CoverGrade>("e_cover_grade")
                .MapEnum<Gender>("e_gender")
                .MapEnum<Grade>("e_grade")
                .MapEnum<LabelType>("e_label_type")
                .MapEnum<ReleaseType>("e_release_type")
                .MapEnum<RipState>("e_rip_state")
                .MapEnum<TrackType>("e_track_type")
                .MapEnum<VendorType>("e_vendor_type")));

WebApplication app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseStaticFiles();
app.MapControllers();

app.UseCors(policyBuilder => policyBuilder.WithOrigins("http://localhost:4200").AllowAnyMethod().AllowAnyHeader());

app.Run();
