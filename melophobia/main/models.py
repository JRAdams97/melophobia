from django.db import models

"""
Choices/Enumerates
"""
class ArtistType(models.TextChoices):
    GROUP = 'GROUP', 'Group'
    ORCHESTRA ='ORCHESTRA', 'Orchestra'
    OTHER = 'OTHER', 'Other'
    PERSON = 'PERSON', 'Person'


class BackupState(models.TextChoices):
    CLEANED = 'CLEANED', 'Cleaned'
    DAMAGED = 'DAMAGED', 'Damaged'
    OTHER = 'OTHER', 'Other'
    RIPPED = 'RIPPED', 'Ripped'
    TAGGED = 'TAGGED', 'Tagged'
    TODO = 'TODO', 'TODO'


class CoverGrade(models.TextChoices):
    EXCELLENT = 'EXCELLENT', 'Excellent'
    FAIR = 'FAIR', 'Fair'
    GOOD = 'GOOD', 'Good'
    POOR = 'POOR', 'Poor'
    NONE = 'NONE', 'None'


class Gender(models.TextChoices):
    MALE = 'MALE', 'Male'
    FEMALE = 'FEMALE', 'Female'
    OTHER = 'OTHER', 'Other'


class Grade(models.TextChoices):
  GOOD = 'GOOD', 'Good'
  FAIR = 'FAIR', 'Fair'
  MINT = 'MINT', 'Mint'
  NEARMINT = 'NEARMINT', 'Near Mint'
  POOR = 'POOR', 'Poor'
  UNKNOWN = 'UNKNOWN', 'Unknown'
  VERYGOOD = 'VERYGOOD', 'Very Good'
  VERYGOODPLUS = 'VERYGOODPLUS', 'Very Good +'


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


class ReleaseTypeValue(models.TextChoices):
    AUDIOBOOK = 'AUDIOBOOK', 'Audiobook'
    BROADCAST = 'BROADCAST', 'Broadcast'
    COMPILATION = 'COMPILATION', 'Compilation'
    DEMO = 'DEMO', 'Demo'
    DJMIX = 'DJMIX', 'DJ Mix'
    EP = 'EP', 'Extended Play'
    INTERVIEW = 'INTERVIEW', 'Interview'
    LIVE = 'LIVE', 'Live'
    MIXTAPE = 'MIXTAPE', 'Mixtape'
    OTHER = 'OTHER', 'Other'
    REMIX = 'REMIX', 'Remix'
    SINGLE = 'SINGLE', 'Single'
    SOUNDTRACK = 'SOUNDTRACK', 'Soundtrack'
    STUDIO = 'STUDIO', 'Studio'


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
    abbreviation = models.CharField(max_length=30, blank=True, null=True)
    classification = models.CharField(choices=MediaClass)
    origin_year = models.IntegerField(blank=True, null=True)

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name


class Language(models.Model):
    name = models.CharField(db_index=True)
    script = models.CharField()

    class Meta:
        ordering = ('name',)

    def __str__(self):
        return self.name


class Producer(models.Model):
    name = models.CharField(db_index=True)
    sort_name = models.CharField(blank=True)
    gender = models.CharField(choices=Gender)
    birth_date = models.CharField(default='0000-00-00')
    birth_location = models.ForeignKey(Location, on_delete=models.SET_NULL, null=True)
    death_date = models.CharField(blank=True)
    is_favourite = models.BooleanField(default=False)
    image_ref = models.CharField(max_length=80, blank=True)

    class Meta:
        ordering = ('sort_name',)

    @property
    def image_or_default(self):
        return self.image_ref if self.image_ref else '../fallback.png'

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
    image_ref = models.CharField(max_length=80, blank=True)

    class Meta:
        ordering = ('name',)

    @property
    def image_or_default(self):
        return self.image_ref if self.image_ref else '../fallback.png'

    def __str__(self):
        return self.name + ' (' + self.formation_location.region.country.alpha2_code + ')'


class Artist(models.Model):
    name = models.CharField(db_index=True)
    sort_name = models.CharField(blank=True)
    type = models.CharField(choices=ArtistType)
    gender = models.CharField(choices=Gender, blank=True)
    formation_year = models.IntegerField(blank=True, null=True)
    formation_location = models.ForeignKey(Location, on_delete=models.SET_NULL, blank=True, null=True)
    disband_year = models.IntegerField(blank=True, null=True)
    genres = models.ManyToManyField(Genre, blank=True)
    is_favourite = models.BooleanField(default=False)
    image_ref = models.CharField(max_length=80, blank=True)

    class Meta:
        ordering = ('name',)

    @property
    def image_or_default(self):
        return self.image_ref if self.image_ref else '../fallback.png'

    def __str__(self):
        return self.name


class ReleaseType(models.Model):
    type = models.CharField(unique=True, choices=ReleaseTypeValue)

    class Meta:
        ordering = ('type',)

    def __str__(self):
        return self.type


class Release(models.Model):
    title = models.CharField(db_index=True)
    alternate_title = models.CharField(blank=True)
    release_date = models.CharField(default='0000-00-00')
    artists = models.ManyToManyField(Artist)
    genres = models.ManyToManyField(Genre)
    is_favourite = models.BooleanField(default=False)
    languages = models.ManyToManyField(Language)
    producers = models.ManyToManyField(Producer, blank=True)
    aoty_rank = models.IntegerField(blank=True, null=True)
    bea_rank = models.IntegerField(blank=True, null=True)
    christgau_rating = models.CharField(blank=True, max_length=5)
    metacritic_rating = models.IntegerField(blank=True, null=True)
    rym_rank = models.IntegerField(blank=True, null=True)
    rym_rating = models.DecimalField(max_digits=3, decimal_places=2, blank=True, null=True)
    is_official = models.BooleanField(default=True)
    types = models.ManyToManyField(ReleaseType)

    class Meta:
        ordering = ('title',)

    def __str__(self):
        return f"{self.title} ({self.release_date[:4] if self.release_date != '' else 'Unknown'})"


class Issue(models.Model):
    parent_release = models.ForeignKey(Release, on_delete=models.CASCADE, related_name='issues')
    label = models.ForeignKey(Label, on_delete=models.SET_NULL, null=True)
    release_year = models.IntegerField(blank=True, null=True)
    release_country = models.ForeignKey(Country, on_delete=models.SET_NULL, null=True)
    catalogue_number = models.CharField(blank=True)
    media = models.ForeignKey(Media, on_delete=models.SET_NULL, null=True)
    edition = models.CharField(blank=True)
    is_reissue = models.BooleanField(default=False)
    is_official = models.BooleanField(default=True)
    is_promo = models.BooleanField(default=False)
    has_pregap = models.BooleanField(default=False)
    has_data_track = models.BooleanField(default=False)
    barcode = models.CharField(blank=True, max_length=14)
    comment = models.TextField(blank=True)

    class Meta:
        ordering = ('catalogue_number',)

    def __str__(self):
        return f"{self.parent_release.title} ({str(self.release_year)}: {self.media.name})"


class IssueVariant(models.Model):
    issue = models.ForeignKey(Issue, on_delete=models.CASCADE, related_name='issue_variants')
    matrix_runout = models.CharField(blank=True)
    master_sid = models.CharField(blank=True)
    mould_sid = models.CharField(blank=True)
    spars_code = models.CharField(blank=True)
    comment = models.TextField(blank=True)

    class Meta:
        ordering = ('matrix_runout',)

    def __str__(self):
        return self.matrix_runout


class CollectionItem(models.Model):
    issue = models.ForeignKey(Issue, on_delete=models.CASCADE)
    packaging_grade = models.CharField(blank=True, choices=Grade)
    packaging_comment = models.TextField(blank=True)
    media_grade = models.CharField(blank=True, choices=Grade)
    media_comment = models.TextField(blank=True)
    is_missing_content = models.BooleanField(default=False)
    missing_content_comment = models.TextField(blank=True)
    has_promo_material = models.BooleanField(default=False)
    remaining_tracks = models.IntegerField(default=0)
    remaining_media_items = models.IntegerField(default=0)
    is_favourite = models.BooleanField(default=False)
    digital_cover_quality = models.CharField(choices=CoverGrade, default=CoverGrade.NONE)
    backup_state = models.CharField(choices=BackupState)
    comment = models.TextField(blank=True)

    class Meta:
        ordering = ('backup_state',)

    def __str__(self):
        return f"{self.issue.release.title} ({self.issue.release_year}: {self.backup_state})"
