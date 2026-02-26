from django.db import models


class Country(models.Model):
    name = models.CharField(db_index=True, max_length=50, unique=True)
    alpha2_code = models.CharField(max_length=2, unique=True)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name


class Region(models.Model):
    name = models.CharField(db_index=True, max_length=100)
    abbreviation = models.CharField(max_length=30)
    country = models.ForeignKey(Country, on_delete=models.CASCADE)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name


class Location(models.Model):
    name = models.CharField(db_index=True, max_length=255)
    region = models.ForeignKey(Region, on_delete=models.CASCADE)
    latitude = models.DecimalField(max_digits=7, decimal_places=4)
    longitude = models.DecimalField(max_digits=7, decimal_places=4)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name
