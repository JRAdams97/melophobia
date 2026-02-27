from django.db import models

"""
Choices/Enumerates
"""
class ArtistType(models.TextChoices):
    GROUP = 'GROUP', 'Group'
    ORCHESTRA ='ORCHESTRA', 'Orchestra'
    OTHER = 'OTHER', 'Other'
    PERSON = 'PERSON', 'Person'


class Gender(models.TextChoices):
    MALE = 'MALE', 'Male'
    FEMALE = 'FEMALE', 'Female'
    OTHER = 'OTHER', 'Other'


class LabelType(models.TextChoices):
    BOOTLEGS = 'BOOTLEGS', 'Bootlegs'
    DISTRIBUTION = 'DISTRIBUTION', 'Distribution'
    ORIGINALS = 'ORIGINALS', 'Originals'
    REISSUES = 'REISSUES', 'Reissues'


class MediaClass(models.TextChoices):
    CARTRIDGE = 'CARTRIDGE', 'Cartridge'
    CASSETTE = 'CASSETTE', 'Cassette'
    DIGITAL = 'DIGITAL', 'Digital'
    FLASH_DRIVE = 'FLASH_DRIVE', 'Flash Drive'
    MAGNETIC_DISK = 'MAGNETIC_DISK', 'Magnetic Disk'
    OPTICAL_DISC = 'OPTICAL_DISC', 'Optical Disk'
    OTHER = 'OTHER', 'Other'
    PHONOGRAPHIC_RECORD = 'PHONOGRAPHIC_RECORD', 'Phonographic Record'
    UNKNOWN = 'UNKNOWN', 'Unknown'


"""
Models
"""
class Country(models.Model):
    name = models.CharField(db_index=True, max_length=50, unique=True)
    alpha2_code = models.CharField(max_length=2, unique=True)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name


class Region(models.Model):
    name = models.CharField(db_index=True, max_length=100, blank=True)
    abbreviation = models.CharField(max_length=30)
    country = models.ForeignKey(Country, on_delete=models.CASCADE)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        if self.name is None:
            return str(self.country.alpha2_code)
        else:
            return self.name + ', ' + str(self.country.alpha2_code)


class Location(models.Model):
    name = models.CharField(db_index=True, max_length=255, blank=True)
    region = models.ForeignKey(Region, on_delete=models.CASCADE)
    latitude = models.DecimalField(max_digits=7, decimal_places=4)
    longitude = models.DecimalField(max_digits=7, decimal_places=4)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name + ', ' + str(self.region.name) + ', ' + str(self.region.country.alpha2_code)


class Media(models.Model):
    name = models.CharField(db_index=True, max_length=100)
    abbreviation = models.CharField(max_length=30)
    classification = models.CharField(choices=MediaClass)
    origin_year = models.IntegerField(blank=True, null=True)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name


class Genre(models.Model):
    name = models.CharField(db_index=True, max_length=50)
    origin_year = models.IntegerField(null=True)
    is_favourite = models.BooleanField(default=False)
    parent_genres = models.ManyToManyField('Genre', blank=True)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name


class Label(models.Model):
    name = models.CharField(db_index=True)
    sort_name = models.CharField(blank=True)
    formation_year = models.IntegerField(blank=True, null=True)
    formation_location = models.ForeignKey(Location, on_delete=models.SET_NULL, null=True)
    closure_year = models.IntegerField(blank=True, null=True)
    is_favourite = models.BooleanField(default=False)
    labelcode = models.IntegerField(blank=True, null=True)
    type = models.CharField(choices=LabelType)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name + ' (' + self.formation_location.region.country.alpha2_code + ')'


class Artist(models.Model):
    name = models.CharField(db_index=True)
    sort_name = models.CharField(blank=True)
    type = models.CharField(choices=ArtistType)
    gender = models.CharField(choices=Gender, blank=True)
    formation_year = models.IntegerField(blank=True, null=True)
    formation_location = models.ForeignKey(Location, on_delete=models.SET_NULL, null=True)
    disband_year = models.IntegerField(blank=True, null=True)
    genres = models.ManyToManyField(Genre)
    is_favourite = models.BooleanField(default=False)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name