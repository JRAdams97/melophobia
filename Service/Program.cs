using Melophobia.Data;
using Microsoft.EntityFrameworkCore;

WebApplicationBuilder builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi
builder.Services.AddOpenApi();
builder.Services.AddSwaggerGen();
builder.Services.AddControllers();

// Add PostgreSQL via EFCore
builder.Services.AddDbContext<AppDbContext>(options =>
        options.UseNpgsql(builder.Configuration.GetConnectionString("BaseConnection")));

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
