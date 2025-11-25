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
builder.Services.AddDbContext<MelophobiaContext>(options => options.UseNpgsql(
        "Host=localhost;Port=5432;Database=melophobia;Username=postgres;Password=",
        e => e.MapEnum<Gender>("e_gender")
                .MapEnum<LabelType>("e_label_type")));

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
